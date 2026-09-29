package com.example

import com.example.data.MedicineRepository
import com.example.data.local.BookingEntity
import com.example.model.BookingType
import com.example.model.CartItem
import com.example.model.ProductCategory
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ExampleUnitTest {

    @Test
    fun storeDetails_areConfiguredCorrectly() {
        val store = MedicineRepository.storeDetails
        assertEquals("PMBJK22063", store.centerCode)
        assertTrue(store.storeNameHindi.contains("जनऔषधि"))
        assertTrue(store.proprietorHindi.contains("धीरज वर्मा"))
        assertTrue(store.addressHindi.contains("मोहम्मदपुर खाला"))
        assertTrue(store.landmarkHindi.contains("मनोज ज्वैलर्स"))
        assertEquals("+919305072480", store.phone)
        assertEquals("+919305072480", store.whatsappNumber)
    }

    @Test
    fun medicineRepository_containsAllRequiredCategories() {
        val categories = MedicineRepository.medicines.map { it.category }.toSet()
        assertTrue(categories.contains(ProductCategory.DIABETES))
        assertTrue(categories.contains(ProductCategory.BP_HEART))
        assertTrue(categories.contains(ProductCategory.THYROID))
        assertTrue(categories.contains(ProductCategory.VITAMINS))
        assertTrue(categories.contains(ProductCategory.WOMEN_CARE))
        assertTrue(categories.contains(ProductCategory.CHILD_CARE))
        assertTrue(categories.contains(ProductCategory.SANITARY_NAPKINS))
        assertTrue(categories.contains(ProductCategory.GENERAL_MEDS))
    }

    @Test
    fun searchMedicines_filtersAccurately() {
        val metforminResults = MedicineRepository.searchMedicines("Metformin", ProductCategory.ALL)
        assertTrue(metforminResults.isNotEmpty())
        assertTrue(metforminResults.any { it.englishName.contains("Metformin") })

        val thyroidResults = MedicineRepository.searchMedicines("", ProductCategory.THYROID)
        assertTrue(thyroidResults.size >= 4)

        val suvidhaResults = MedicineRepository.searchMedicines("सुविधा", ProductCategory.SANITARY_NAPKINS)
        assertTrue(suvidhaResults.isNotEmpty())

        // Test MainCategoryFilter (Generic, Surgical, Supplements)
        val genericResults = MedicineRepository.searchMedicines("", ProductCategory.ALL, com.example.model.MainCategoryFilter.GENERIC)
        assertTrue(genericResults.isNotEmpty())

        val surgicalResults = MedicineRepository.searchMedicines("", ProductCategory.ALL, com.example.model.MainCategoryFilter.SURGICAL)
        assertTrue(surgicalResults.isNotEmpty())
        assertTrue(surgicalResults.all { it.category == ProductCategory.SURGICAL_DEVICES })

        val supplementsResults = MedicineRepository.searchMedicines("", ProductCategory.ALL, com.example.model.MainCategoryFilter.SUPPLEMENTS)
        assertTrue(supplementsResults.isNotEmpty())
        assertTrue(supplementsResults.all { it.category == ProductCategory.VITAMINS })
    }

    @Test
    fun savingsPercentage_computesAccurately() {
        val suvidha = MedicineRepository.medicines.first { it.drugCode == "8140" }
        assertEquals(4.0, suvidha.kendraPrice, 0.01)
        assertEquals(36.0, suvidha.marketMrp, 0.01)
        assertEquals(32.0, suvidha.savingsAmount, 0.01)
        assertTrue(suvidha.savingsPercent >= 85)
    }

    @Test
    fun cartItem_computesTotalsAndSavingsCorrectly() {
        val telmisartan = MedicineRepository.medicines.first { it.drugCode == "300" } // 11.25 vs 68.0
        val cartItem = CartItem(medicine = telmisartan, quantity = 2)
        assertEquals(22.50, cartItem.totalKendraPrice, 0.01)
        assertEquals(136.0, cartItem.totalMarketMrp, 0.01)
        assertEquals(113.50, cartItem.totalSavings, 0.01)
    }

    @Test
    fun bookingEntity_storesAllDetails() {
        val booking = BookingEntity(
            bookingId = "PMBJK-BK-1234",
            patientName = "राम कुमार",
            patientPhone = "9450000000",
            deliveryType = BookingType.STORE_PICKUP.titleHindi,
            deliveryAddress = "मोहम्मदपुर खाला",
            medicinesSummary = "1. Telmisartan 40mg x 2",
            totalAmount = 16.0,
            totalSavings = 108.0,
            prescriptionNote = "डॉक्टर का पर्चा उपलब्ध है"
        )
        assertEquals("PMBJK-BK-1234", booking.bookingId)
        assertEquals("राम कुमार", booking.patientName)
        assertTrue(booking.deliveryType.contains("पिकअप"))
    }
}
