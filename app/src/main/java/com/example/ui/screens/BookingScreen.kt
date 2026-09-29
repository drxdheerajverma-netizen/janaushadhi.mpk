package com.example.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddPhotoAlternate
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DeliveryDining
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.LocalPharmacy
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.MedicineRepository
import com.example.data.local.BookingEntity
import com.example.model.BookingType
import com.example.ui.components.MedicalDisclaimerCard
import com.example.ui.components.StoreFooterSection
import com.example.ui.theme.JanAushadhiBlueDark
import com.example.ui.theme.JanAushadhiBlueLight
import com.example.ui.theme.JanAushadhiBluePrimary
import com.example.ui.theme.JanAushadhiGreen
import com.example.ui.theme.JanAushadhiOrange
import com.example.ui.theme.WhatsAppGreen
import com.example.util.IntentHelper
import com.example.viewmodel.PharmacyUiState
import com.example.viewmodel.PharmacyViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun BookingScreen(
    viewModel: PharmacyViewModel,
    uiState: PharmacyUiState,
    onNavigateToMedicines: () -> Unit
) {
    val context = LocalContext.current
    val store = MedicineRepository.storeDetails
    val pastBookings by viewModel.pastBookings.collectAsStateWithLifecycle()

    var selectedTabIndex by remember { mutableIntStateOf(0) }

    // Google Play Policy Compliant Photo Picker (Zero runtime storage permissions!)
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        viewModel.onPrescriptionImagePicked(uri?.toString())
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("booking_screen"),
        contentPadding = PaddingValues(bottom = 80.dp)
    ) {
        // Top Header Banner
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(18.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            Brush.verticalGradient(
                                listOf(JanAushadhiBlueDark, JanAushadhiBluePrimary)
                            )
                        )
                        .padding(18.dp)
                ) {
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "दवाई बुकिंग सेवा",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White
                            )
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = JanAushadhiOrange
                            ) {
                                Text(
                                    text = "कोड: ${store.centerCode}",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }

                        Text(
                            text = "घर बैठे या केंद्र से पिकअप हेतु जेनेरिक दवाइयाँ बुक करें — 50% से 90% की भारी बचत!",
                            fontSize = 13.sp,
                            color = Color(0xFFE0F7FA),
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }
        }

        // Sub Tabs: New Booking vs Past Bookings
        item {
            TabRow(
                selectedTabIndex = selectedTabIndex,
                containerColor = Color.Transparent,
                contentColor = JanAushadhiBluePrimary,
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                        color = JanAushadhiBluePrimary,
                        height = 3.dp
                    )
                },
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Tab(
                    selected = selectedTabIndex == 0,
                    onClick = { selectedTabIndex = 0 },
                    text = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("नई बुकिंग (${uiState.cartItemCount})", fontWeight = FontWeight.Bold)
                        }
                    }
                )

                Tab(
                    selected = selectedTabIndex == 1,
                    onClick = { selectedTabIndex = 1 },
                    text = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.History, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("मेरी बुकिंग्स (${pastBookings.size})", fontWeight = FontWeight.Bold)
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.height(14.dp))
        }

        if (selectedTabIndex == 0) {
            // === TAB 1: NEW BOOKING FLOW ===

            // Cart Items Section
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = BorderStroke(1.dp, Color(0xFFE2E8F0))
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Medication,
                                    contentDescription = null,
                                    tint = JanAushadhiBluePrimary,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "चुनी हुई दवाइयाँ (${uiState.cartItems.size})",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }

                            if (uiState.cartItems.isNotEmpty()) {
                                Text(
                                    text = "साफ करें",
                                    fontSize = 12.sp,
                                    color = Color.Red,
                                    fontWeight = FontWeight.SemiBold,
                                    modifier = Modifier.clickable { viewModel.clearCart() }
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        if (uiState.cartItems.isEmpty()) {
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = Color(0xFFF8FAFC),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(
                                    modifier = Modifier.padding(14.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "कोई दवा नहीं चुनी गई है",
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = Color(0xFF64748B)
                                    )
                                    Text(
                                        text = "आप नीचे कैटलॉग से दवाइयाँ जोड़ सकते हैं अथवा पर्चा अपलोड कर सकते हैं।",
                                        fontSize = 11.sp,
                                        color = Color(0xFF94A3B8),
                                        modifier = Modifier.padding(top = 2.dp, bottom = 8.dp)
                                    )
                                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                        OutlinedButton(
                                            onClick = onNavigateToMedicines,
                                            shape = RoundedCornerShape(8.dp)
                                        ) {
                                            Icon(imageVector = Icons.Default.AddShoppingCart, contentDescription = null, modifier = Modifier.size(14.dp))
                                            Spacer(modifier = Modifier.width(4.dp))
                                            Text("दवाइयाँ जोड़ें", fontSize = 12.sp)
                                        }

                                        if (uiState.savedMedicinesList.isNotEmpty()) {
                                            Button(
                                                onClick = { viewModel.populateCartFromSavedList() },
                                                colors = ButtonDefaults.buttonColors(containerColor = JanAushadhiGreen),
                                                shape = RoundedCornerShape(8.dp)
                                            ) {
                                                Text("बचत सूची से लें (${uiState.savedMedicinesList.size})", fontSize = 12.sp)
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            // Cart item rows
                            uiState.cartItems.forEach { item ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 6.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = item.medicine.englishName,
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                        Text(
                                            text = "${item.medicine.hindiName} | दर: ₹${item.medicine.kendraPrice}",
                                            fontSize = 11.sp,
                                            color = Color(0xFF64748B)
                                        )
                                    }

                                    // Quantity controls
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                            .background(Color(0xFFF1F5F9), RoundedCornerShape(8.dp))
                                            .padding(horizontal = 4.dp, vertical = 2.dp)
                                    ) {
                                        IconButton(
                                            onClick = { viewModel.updateCartQuantity(item.medicine.id, -1) },
                                            modifier = Modifier.size(24.dp)
                                        ) {
                                            Icon(imageVector = Icons.Default.Remove, contentDescription = "Decrease", modifier = Modifier.size(14.dp))
                                        }

                                        Text(
                                            text = "${item.quantity}",
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.padding(horizontal = 8.dp)
                                        )

                                        IconButton(
                                            onClick = { viewModel.updateCartQuantity(item.medicine.id, 1) },
                                            modifier = Modifier.size(24.dp)
                                        ) {
                                            Icon(imageVector = Icons.Default.Add, contentDescription = "Increase", modifier = Modifier.size(14.dp))
                                        }
                                    }

                                    Spacer(modifier = Modifier.width(8.dp))

                                    Text(
                                        text = "₹${String.format("%.2f", item.totalKendraPrice)}",
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = JanAushadhiBluePrimary
                                    )
                                }
                                HorizontalDivider(color = Color(0xFFF1F5F9))
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            OutlinedButton(
                                onClick = onNavigateToMedicines,
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Icon(imageVector = Icons.Default.Add, contentDescription = null, modifier = Modifier.size(14.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("कैटलॉग से और दवाइयाँ जोड़ें", fontSize = 12.sp)
                            }
                        }
                    }
                }
            }

            // Prescription Upload Section (Photo Picker)
            item {
                Spacer(modifier = Modifier.height(14.dp))
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = BorderStroke(1.dp, Color(0xFFE2E8F0))
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Receipt,
                                contentDescription = null,
                                tint = JanAushadhiOrange,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(
                                    text = "डॉक्टर का पर्चा (Prescription)",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "पर्चे की फोटो जोड़ें या दवाइयों का नाम लिखें",
                                    fontSize = 11.sp,
                                    color = Color(0xFF64748B)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Photo Picker Trigger
                        if (uiState.prescriptionImageUriInput == null) {
                            OutlinedButton(
                                onClick = {
                                    photoPickerLauncher.launch(
                                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                    )
                                },
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("upload_prescription_btn")
                            ) {
                                Icon(imageVector = Icons.Default.AddPhotoAlternate, contentDescription = null, modifier = Modifier.size(18.dp))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("पर्चे की फोटो चुनें (Attach Photo)", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            }
                        } else {
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = Color(0xFFE8F5E9),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier.padding(10.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null, tint = JanAushadhiGreen, modifier = Modifier.size(18.dp))
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text("पर्चे की फोटो संलग्न है", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = JanAushadhiGreen)
                                    }
                                    IconButton(
                                        onClick = { viewModel.onPrescriptionImagePicked(null) },
                                        modifier = Modifier.size(24.dp)
                                    ) {
                                        Icon(imageVector = Icons.Default.Close, contentDescription = "Remove", tint = Color.Gray, modifier = Modifier.size(16.dp))
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = uiState.prescriptionNoteInput,
                            onValueChange = { viewModel.onPrescriptionNoteChange(it) },
                            placeholder = { Text("अथवा दवाइयों के नाम या विशेष निर्देश यहाँ लिखें...", fontSize = 12.sp) },
                            minLines = 2,
                            maxLines = 4,
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("prescription_note_input")
                        )
                    }
                }
            }

            // Delivery Mode Selection
            item {
                Spacer(modifier = Modifier.height(14.dp))
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = BorderStroke(1.dp, Color(0xFFE2E8F0))
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = "दवाई प्राप्त करने का माध्यम (Delivery Mode):",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // Option 1: Pickup
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = if (uiState.bookingType == BookingType.STORE_PICKUP) JanAushadhiBlueLight else Color(0xFFF8FAFC),
                            border = BorderStroke(
                                1.dp,
                                if (uiState.bookingType == BookingType.STORE_PICKUP) JanAushadhiBluePrimary else Color(0xFFE2E8F0)
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { viewModel.setBookingType(BookingType.STORE_PICKUP) }
                        ) {
                            Row(
                                modifier = Modifier.padding(10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = uiState.bookingType == BookingType.STORE_PICKUP,
                                    onClick = { viewModel.setBookingType(BookingType.STORE_PICKUP) },
                                    colors = RadioButtonDefaults.colors(selectedColor = JanAushadhiBluePrimary)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Column {
                                    Text(
                                        text = BookingType.STORE_PICKUP.titleHindi,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = if (uiState.bookingType == BookingType.STORE_PICKUP) JanAushadhiBlueDark else Color(0xFF1E293B)
                                    )
                                    Text(
                                        text = BookingType.STORE_PICKUP.descriptionHindi,
                                        fontSize = 11.sp,
                                        color = Color(0xFF64748B)
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        // Option 2: Home Delivery
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = if (uiState.bookingType == BookingType.HOME_DELIVERY) JanAushadhiBlueLight else Color(0xFFF8FAFC),
                            border = BorderStroke(
                                1.dp,
                                if (uiState.bookingType == BookingType.HOME_DELIVERY) JanAushadhiBluePrimary else Color(0xFFE2E8F0)
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { viewModel.setBookingType(BookingType.HOME_DELIVERY) }
                        ) {
                            Row(
                                modifier = Modifier.padding(10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = uiState.bookingType == BookingType.HOME_DELIVERY,
                                    onClick = { viewModel.setBookingType(BookingType.HOME_DELIVERY) },
                                    colors = RadioButtonDefaults.colors(selectedColor = JanAushadhiBluePrimary)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Column {
                                    Text(
                                        text = BookingType.HOME_DELIVERY.titleHindi,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = if (uiState.bookingType == BookingType.HOME_DELIVERY) JanAushadhiBlueDark else Color(0xFF1E293B)
                                    )
                                    Text(
                                        text = BookingType.HOME_DELIVERY.descriptionHindi,
                                        fontSize = 11.sp,
                                        color = Color(0xFF64748B)
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Customer Information Form
            item {
                Spacer(modifier = Modifier.height(14.dp))
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = BorderStroke(1.dp, Color(0xFFE2E8F0))
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = "मरीज / ग्राहक का विवरण (Patient Info):",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        OutlinedTextField(
                            value = uiState.patientNameInput,
                            onValueChange = { viewModel.onPatientNameChange(it) },
                            label = { Text("मरीज का नाम (Patient Name)") },
                            placeholder = { Text("उदा. रमेश वर्मा") },
                            singleLine = true,
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("booking_name_input")
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = uiState.patientPhoneInput,
                            onValueChange = { viewModel.onPatientPhoneChange(it) },
                            label = { Text("मोबाइल नंबर (Mobile Number)") },
                            placeholder = { Text("उदा. 9450000000") },
                            singleLine = true,
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("booking_phone_input")
                        )

                        if (uiState.bookingType == BookingType.HOME_DELIVERY) {
                            Spacer(modifier = Modifier.height(8.dp))
                            OutlinedTextField(
                                value = uiState.deliveryAddressInput,
                                onValueChange = { viewModel.onDeliveryAddressChange(it) },
                                label = { Text("डिलीवरी का पूरा पता (गाँव / मोहल्ला / निकटतम पहचान)") },
                                placeholder = { Text("उदा. ग्राम मोहम्मदपुर खाला, निकट प्राथमिक विद्यालय...") },
                                minLines = 2,
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("booking_address_input")
                            )
                        }
                    }
                }
            }

            // Summary & Confirm Button
            item {
                Spacer(modifier = Modifier.height(14.dp))
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF8FAFC)),
                    border = BorderStroke(1.dp, Color(0xFFE2E8F0))
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        if (uiState.cartKendraTotal > 0) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text("केंद्र मूल्य (देय):", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                                Text(
                                    "₹${String.format("%.2f", uiState.cartKendraTotal)}",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = JanAushadhiBluePrimary
                                )
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("बाजार MRP: ₹${String.format("%.2f", uiState.cartMarketTotal)}", fontSize = 12.sp, color = Color.Gray, textDecoration = TextDecoration.LineThrough)
                                Surface(
                                    shape = RoundedCornerShape(4.dp),
                                    color = Color(0xFFE8F5E9)
                                ) {
                                    Text(
                                        text = "${uiState.cartSavingsPercent}% बचत (₹${String.format("%.2f", uiState.cartTotalSavings)} कम)",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = JanAushadhiGreen,
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                    )
                                }
                            }
                            HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp), color = Color(0xFFE2E8F0))
                        }

                        Text(
                            text = "भुगतान माध्यम: केंद्र पर पिकअप के समय या डिलीवरी पर नकद (COD) / UPI द्वारा।",
                            fontSize = 11.sp,
                            color = Color(0xFF64748B)
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Button(
                            onClick = {
                                viewModel.submitBooking { createdBooking ->
                                    val msg = viewModel.generateWhatsAppBookingReceipt(createdBooking)
                                    IntentHelper.openWhatsApp(context, store.whatsappNumber, msg)
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = WhatsAppGreen,
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("submit_booking_btn")
                        ) {
                            Icon(imageVector = Icons.Default.Send, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "बुकिंग दर्ज करें व WhatsApp पर भेजें",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        } else {
            // === TAB 2: MY BOOKINGS (ROOM DATABASE) ===
            if (pastBookings.isEmpty()) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFF1F5F9)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(imageVector = Icons.Default.Receipt, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(32.dp))
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "कोई पूर्व बुकिंग नहीं है",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "जब आप कोई दवाई या पर्चा बुक करेंगे, उसका विवरण यहाँ सुरक्षित रहेगा।",
                            fontSize = 12.sp,
                            color = Color(0xFF64748B),
                            modifier = Modifier.padding(top = 4.dp, bottom = 12.dp)
                        )
                        Button(
                            onClick = { selectedTabIndex = 0 },
                            colors = ButtonDefaults.buttonColors(containerColor = JanAushadhiBluePrimary)
                        ) {
                            Text("अभी दवाई बुक करें")
                        }
                    }
                }
            } else {
                items(pastBookings, key = { it.bookingId }) { booking ->
                    val dateFormat = SimpleDateFormat("dd MMM, hh:mm a", Locale("hi", "IN"))
                    val dateFormatted = dateFormat.format(Date(booking.timestamp))

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 6.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        border = BorderStroke(1.dp, Color(0xFFE2E8F0))
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Surface(
                                    shape = RoundedCornerShape(4.dp),
                                    color = Color(0xFFFFF3E0)
                                ) {
                                    Text(
                                        text = booking.bookingId,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = JanAushadhiOrange,
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                    )
                                }

                                Surface(
                                    shape = RoundedCornerShape(50.dp),
                                    color = Color(0xFFE8F5E9)
                                ) {
                                    Text(
                                        text = booking.status,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = JanAushadhiGreen,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = "मरीज: ${booking.patientName} (${booking.patientPhone})",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )

                            Text(
                                text = "प्रकार: ${booking.deliveryType} | तारीख: $dateFormatted",
                                fontSize = 11.sp,
                                color = Color(0xFF64748B)
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = Color(0xFFF8FAFC),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = booking.medicinesSummary,
                                    fontSize = 11.sp,
                                    color = Color(0xFF334155),
                                    modifier = Modifier.padding(8.dp)
                                )
                            }

                            if (booking.totalAmount > 0) {
                                Spacer(modifier = Modifier.height(6.dp))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "कुल राशि: ₹${String.format("%.2f", booking.totalAmount)}",
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = JanAushadhiBluePrimary
                                    )
                                    if (booking.totalSavings > 0) {
                                        Text(
                                            text = "बचत: ₹${String.format("%.2f", booking.totalSavings)}",
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = JanAushadhiGreen
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Button(
                                    onClick = {
                                        val inquiryMsg = "नमस्ते प्रो. धीरज वर्मा जी, कृपया मेरी बुकिंग (${booking.bookingId}) की स्थिति बताएं। मरीज: ${booking.patientName}।"
                                        IntentHelper.openWhatsApp(context, store.whatsappNumber, inquiryMsg)
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = WhatsAppGreen),
                                    shape = RoundedCornerShape(6.dp),
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Icon(imageVector = Icons.Default.Share, contentDescription = null, modifier = Modifier.size(14.dp))
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text("स्थिति पूछें", fontSize = 11.sp)
                                }

                                OutlinedButton(
                                    onClick = { viewModel.deletePastBooking(booking.bookingId) },
                                    shape = RoundedCornerShape(6.dp)
                                ) {
                                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete", modifier = Modifier.size(14.dp), tint = Color.Gray)
                                }
                            }
                        }
                    }
                }
            }
        }

        // Disclaimer
        item {
            Spacer(modifier = Modifier.height(16.dp))
            MedicalDisclaimerCard(modifier = Modifier.padding(horizontal = 16.dp))
        }

        // Footer
        item {
            Spacer(modifier = Modifier.height(24.dp))
            StoreFooterSection()
        }
    }
}
