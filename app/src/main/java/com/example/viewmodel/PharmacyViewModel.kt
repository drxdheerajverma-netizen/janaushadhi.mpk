package com.example.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.BookingRepository
import com.example.data.MedicineRepository
import com.example.data.StoreInfo
import com.example.data.local.AppDatabase
import com.example.data.local.BookingEntity
import com.example.model.BookingType
import com.example.model.CartItem
import com.example.model.MainCategoryFilter
import com.example.model.Medicine
import com.example.model.ProductCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

enum class AppNavDestination(val titleHindi: String, val titleEnglish: String) {
    HOME("होम", "Home"),
    MEDICINES("दवाइयाँ", "Medicines"),
    BOOKING("बुकिंग", "Booking"),
    SAVINGS("बचत", "Savings"),
    ABOUT("परिचय", "About"),
    CONTACT("संपर्क", "Contact")
}

data class PharmacyUiState(
    val currentDestination: AppNavDestination = AppNavDestination.HOME,
    val selectedMainFilter: MainCategoryFilter = MainCategoryFilter.ALL,
    val selectedCategory: ProductCategory = ProductCategory.ALL,
    val searchQuery: String = "",
    val filteredMedicines: List<Medicine> = MedicineRepository.medicines,
    val selectedMedicineForDetail: Medicine? = null,
    val savedMedicinesList: List<Medicine> = emptyList(),
    val cartItems: List<CartItem> = emptyList(),
    val bookingType: BookingType = BookingType.STORE_PICKUP,
    val patientNameInput: String = "",
    val patientPhoneInput: String = "",
    val deliveryAddressInput: String = "",
    val prescriptionNoteInput: String = "",
    val prescriptionImageUriInput: String? = null,
    val bookingSuccessReceipt: BookingEntity? = null,
    val isEnglishSubtitlesEnabled: Boolean = true
) {
    val totalKendraCost: Double
        get() = savedMedicinesList.sumOf { it.kendraPrice }

    val totalMarketCost: Double
        get() = savedMedicinesList.sumOf { it.marketMrp }

    val totalSavings: Double
        get() = (totalMarketCost - totalKendraCost).coerceAtLeast(0.0)

    val overallSavingsPercent: Int
        get() = if (totalMarketCost > 0) {
            (((totalMarketCost - totalKendraCost) / totalMarketCost) * 100).toInt().coerceIn(0, 99)
        } else {
            0
        }

    // Cart calculations for Booking
    val cartKendraTotal: Double
        get() = cartItems.sumOf { it.totalKendraPrice }

    val cartMarketTotal: Double
        get() = cartItems.sumOf { it.totalMarketMrp }

    val cartTotalSavings: Double
        get() = (cartMarketTotal - cartKendraTotal).coerceAtLeast(0.0)

    val cartSavingsPercent: Int
        get() = if (cartMarketTotal > 0) {
            (((cartMarketTotal - cartKendraTotal) / cartMarketTotal) * 100).toInt().coerceIn(0, 99)
        } else {
            0
        }

    val cartItemCount: Int
        get() = cartItems.sumOf { it.quantity }
}

class PharmacyViewModel(application: Application) : AndroidViewModel(application) {

    private val bookingRepository = BookingRepository(
        AppDatabase.getDatabase(application).bookingDao()
    )

    val pastBookings: StateFlow<List<BookingEntity>> = bookingRepository.allBookings
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private val _uiState = MutableStateFlow(PharmacyUiState())
    val uiState: StateFlow<PharmacyUiState> = _uiState.asStateFlow()

    val storeInfo: StoreInfo = MedicineRepository.storeDetails
    val faqs = MedicineRepository.faqs

    fun navigateTo(destination: AppNavDestination) {
        _uiState.update { it.copy(currentDestination = destination) }
    }

    fun selectMainFilter(mainFilter: MainCategoryFilter) {
        _uiState.update { current ->
            val updatedList = MedicineRepository.searchMedicines(
                query = current.searchQuery,
                category = ProductCategory.ALL,
                mainFilter = mainFilter
            )
            current.copy(
                selectedMainFilter = mainFilter,
                selectedCategory = ProductCategory.ALL,
                filteredMedicines = updatedList
            )
        }
    }

    fun selectCategory(category: ProductCategory) {
        _uiState.update { current ->
            val updatedList = MedicineRepository.searchMedicines(
                query = current.searchQuery,
                category = category,
                mainFilter = current.selectedMainFilter
            )
            current.copy(
                selectedCategory = category,
                filteredMedicines = updatedList
            )
        }
    }

    fun onSearchQueryChange(query: String) {
        _uiState.update { current ->
            val updatedList = MedicineRepository.searchMedicines(
                query = query,
                category = current.selectedCategory,
                mainFilter = current.selectedMainFilter
            )
            current.copy(
                searchQuery = query,
                filteredMedicines = updatedList
            )
        }
    }

    fun clearSearch() {
        onSearchQueryChange("")
    }

    fun openMedicineDetail(medicine: Medicine?) {
        _uiState.update { it.copy(selectedMedicineForDetail = medicine) }
    }

    // --- SAVINGS CALCULATOR LIST ---
    fun toggleMedicineInSavedList(medicine: Medicine) {
        _uiState.update { current ->
            val existing = current.savedMedicinesList
            val updated = if (existing.any { it.id == medicine.id }) {
                existing.filter { it.id != medicine.id }
            } else {
                existing + medicine
            }
            current.copy(savedMedicinesList = updated)
        }
    }

    fun isMedicineSaved(medicineId: String): Boolean {
        return _uiState.value.savedMedicinesList.any { it.id == medicineId }
    }

    fun clearSavedList() {
        _uiState.update { it.copy(savedMedicinesList = emptyList()) }
    }

    // --- CART & BOOKING MANAGEMENT ---
    fun addToCart(medicine: Medicine, quantity: Int = 1) {
        _uiState.update { current ->
            val existing = current.cartItems.find { it.medicine.id == medicine.id }
            val updated = if (existing != null) {
                current.cartItems.map {
                    if (it.medicine.id == medicine.id) it.copy(quantity = it.quantity + quantity) else it
                }
            } else {
                current.cartItems + CartItem(medicine = medicine, quantity = quantity)
            }
            current.copy(cartItems = updated)
        }
    }

    fun updateCartQuantity(medicineId: String, delta: Int) {
        _uiState.update { current ->
            val updated = current.cartItems.mapNotNull { item ->
                if (item.medicine.id == medicineId) {
                    val newQty = item.quantity + delta
                    if (newQty > 0) item.copy(quantity = newQty) else null
                } else {
                    item
                }
            }
            current.copy(cartItems = updated)
        }
    }

    fun removeFromCart(medicineId: String) {
        _uiState.update { current ->
            current.copy(cartItems = current.cartItems.filter { it.medicine.id != medicineId })
        }
    }

    fun clearCart() {
        _uiState.update { it.copy(cartItems = emptyList()) }
    }

    fun populateCartFromSavedList() {
        _uiState.update { current ->
            val itemsFromSaved = current.savedMedicinesList.map { CartItem(medicine = it, quantity = 1) }
            current.copy(cartItems = itemsFromSaved)
        }
    }

    fun setBookingType(type: BookingType) {
        _uiState.update { it.copy(bookingType = type) }
    }

    fun onPatientNameChange(name: String) {
        _uiState.update { it.copy(patientNameInput = name) }
    }

    fun onPatientPhoneChange(phone: String) {
        _uiState.update { it.copy(patientPhoneInput = phone) }
    }

    fun onDeliveryAddressChange(address: String) {
        _uiState.update { it.copy(deliveryAddressInput = address) }
    }

    fun onPrescriptionNoteChange(note: String) {
        _uiState.update { it.copy(prescriptionNoteInput = note) }
    }

    fun onPrescriptionImagePicked(uriString: String?) {
        _uiState.update { it.copy(prescriptionImageUriInput = uriString) }
    }

    fun dismissBookingSuccess() {
        _uiState.update { it.copy(bookingSuccessReceipt = null) }
    }

    fun submitBooking(onSuccess: (BookingEntity) -> Unit) {
        val state = _uiState.value
        val randomSuffix = (1000..9999).random()
        val bookingId = "PMBJK-BK-$randomSuffix"

        val summarySb = StringBuilder()
        if (state.cartItems.isNotEmpty()) {
            state.cartItems.forEachIndexed { idx, item ->
                summarySb.append("${idx + 1}. ${item.medicine.englishName} x ${item.quantity} (${item.medicine.packSize}) = ₹${String.format("%.2f", item.totalKendraPrice)}\n")
            }
        } else if (state.prescriptionNoteInput.isNotBlank()) {
            summarySb.append("पर्चे के अनुसार दवाइयाँ: ${state.prescriptionNoteInput.take(120)}")
        } else {
            summarySb.append("दवा परामर्श व पर्चा बुकिंग")
        }

        val bookingEntity = BookingEntity(
            bookingId = bookingId,
            patientName = if (state.patientNameInput.isNotBlank()) state.patientNameInput.trim() else "मरीज / ग्राहक",
            patientPhone = if (state.patientPhoneInput.isNotBlank()) state.patientPhoneInput.trim() else "उपलब्ध नहीं",
            deliveryType = if (state.bookingType == BookingType.HOME_DELIVERY) "होम डिलीवरी (Home Delivery)" else "केंद्र से पिकअप (Store Pickup)",
            deliveryAddress = if (state.deliveryAddressInput.isNotBlank()) state.deliveryAddressInput.trim() else "मोहम्मदपुर खाला केंद्र पर पिकअप",
            medicinesSummary = summarySb.toString().trim(),
            totalAmount = state.cartKendraTotal,
            totalSavings = state.cartTotalSavings,
            prescriptionNote = state.prescriptionNoteInput.trim(),
            prescriptionImageUri = state.prescriptionImageUriInput,
            status = "पुष्टीकृत (Confirmed)",
            timestamp = System.currentTimeMillis()
        )

        viewModelScope.launch {
            bookingRepository.saveBooking(bookingEntity)
            _uiState.update {
                it.copy(
                    bookingSuccessReceipt = bookingEntity,
                    cartItems = emptyList(),
                    prescriptionNoteInput = "",
                    prescriptionImageUriInput = null
                )
            }
            onSuccess(bookingEntity)
        }
    }

    fun deletePastBooking(bookingId: String) {
        viewModelScope.launch {
            bookingRepository.deleteBooking(bookingId)
        }
    }

    // --- WHATSAPP MESSAGE GENERATORS ---
    fun generateWhatsAppBookingReceipt(booking: BookingEntity): String {
        val dateFormat = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale("hi", "IN"))
        val dateStr = dateFormat.format(Date(booking.timestamp))

        val sb = StringBuilder()
        sb.append("🏥 *प्रधानमंत्री भारतीय जनऔषधि केंद्र*\n")
        sb.append("📍 मोहम्मदपुर खाला, बाराबंकी (कोड: ${storeInfo.centerCode})\n")
        sb.append("👨‍⚕️ प्रो. धीरज वर्मा जी को दवाई बुकिंग सूचना\n")
        sb.append("━━━━━━━━━━━━━━━━━━━\n")
        sb.append("🔖 *बुकिंग आईडी:* ${booking.bookingId}\n")
        sb.append("📅 *तारीख:* $dateStr\n")
        sb.append("👤 *मरीज का नाम:* ${booking.patientName}\n")
        sb.append("📞 *मोबाइल:* ${booking.patientPhone}\n")
        sb.append("🚚 *प्रकार:* ${booking.deliveryType}\n")
        if (booking.deliveryAddress.isNotBlank()) {
            sb.append("🏠 *पता:* ${booking.deliveryAddress}\n")
        }
        sb.append("━━━━━━━━━━━━━━━━━━━\n")
        sb.append("📋 *दवाइयों का विवरण:*\n")
        sb.append("${booking.medicinesSummary}\n")

        if (booking.totalAmount > 0) {
            sb.append("━━━━━━━━━━━━━━━━━━━\n")
            sb.append("💰 *कुल केंद्र मूल्य (देय):* ₹${String.format("%.2f", booking.totalAmount)}\n")
            if (booking.totalSavings > 0) {
                sb.append("🎉 *कुल बचत:* ₹${String.format("%.2f", booking.totalSavings)}\n")
            }
        }

        if (booking.prescriptionNote.isNotBlank()) {
            sb.append("📝 *पर्चे संबंधी नोट:* ${booking.prescriptionNote}\n")
        }

        sb.append("━━━━━━━━━━━━━━━━━━━\n")
        sb.append("कृपया इस आर्डर को तैयार/पैक करें। धन्यवाद!")
        return sb.toString()
    }

    fun generateWhatsAppPrescriptionInquiryText(): String {
        val state = _uiState.value
        val sb = StringBuilder()
        sb.append("नमस्ते प्रो. धीरज वर्मा जी,\n")
        sb.append("मैं प्रधानमंत्री जनऔषधि केंद्र (${storeInfo.centerCode}), मोहम्मदपुर खाला, बाराबंकी से दवाओं की जानकारी व उपलब्धता के लिए संपर्क कर रहा हूँ।\n\n")

        if (state.savedMedicinesList.isNotEmpty()) {
            sb.append("📋 मेरी चुनी हुई दवाइयाँ:\n")
            state.savedMedicinesList.forEachIndexed { index, med ->
                sb.append("${index + 1}. ${med.englishName} (${med.packSize}) - केंद्र दर: ₹${med.kendraPrice}\n")
            }
            sb.append("\n💰 कुल केंद्र मूल्य: ₹${String.format("%.2f", state.totalKendraCost)}\n")
            sb.append("🏷️ बाजार मूल्य: ₹${String.format("%.2f", state.totalMarketCost)}\n")
            sb.append("🎉 कुल बचत: ₹${String.format("%.2f", state.totalSavings)} (${state.overallSavingsPercent}% बचत)\n\n")
        }

        sb.append("कृपया इन दवाओं की उपलब्धता की पुष्टि करें। धन्यवाद!")
        return sb.toString()
    }
}
