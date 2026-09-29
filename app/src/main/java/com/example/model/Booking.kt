package com.example.model

enum class BookingType(val titleHindi: String, val titleEnglish: String, val descriptionHindi: String) {
    STORE_PICKUP(
        titleHindi = "केंद्र से पिकअप (Store Pickup)",
        titleEnglish = "Pickup from Kendra",
        descriptionHindi = "मोहम्मदपुर खाला केंद्र (निकट मनोज ज्वैलर्स) पर आपकी दवाइयाँ 30 मिनट में पैक होकर तैयार मिलेंगी।"
    ),
    HOME_DELIVERY(
        titleHindi = "होम डिलीवरी (Home Delivery)",
        titleEnglish = "Doorstep Delivery",
        descriptionHindi = "मोहम्मदपुर खाला, सूरतगंज व निकटवर्ती ग्रामीण क्षेत्रों में घर बैठे सुरक्षित डिलीवरी।"
    )
}

data class CartItem(
    val medicine: Medicine,
    val quantity: Int = 1
) {
    val totalKendraPrice: Double
        get() = medicine.kendraPrice * quantity

    val totalMarketMrp: Double
        get() = medicine.marketMrp * quantity

    val totalSavings: Double
        get() = (totalMarketMrp - totalKendraPrice).coerceAtLeast(0.0)
}
