package com.example.model

data class Medicine(
    val id: String,
    val drugCode: String = "",
    val hindiName: String,
    val englishName: String,
    val genericSalt: String,
    val category: ProductCategory,
    val kendraPrice: Double,
    val marketMrp: Double,
    val packSize: String,
    val isRxRequired: Boolean = true,
    val descriptionHindi: String = "",
    val inStock: Boolean = true
) {
    val savingsAmount: Double
        get() = (marketMrp - kendraPrice).coerceAtLeast(0.0)

    val savingsPercent: Int
        get() = if (marketMrp > 0) {
            (((marketMrp - kendraPrice) / marketMrp) * 100).toInt().coerceIn(0, 99)
        } else {
            0
        }
}

data class FaqItem(
    val id: String,
    val questionHindi: String,
    val questionEnglish: String,
    val answerHindi: String,
    val answerEnglish: String
)
