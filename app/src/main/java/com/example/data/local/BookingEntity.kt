package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookings")
data class BookingEntity(
    @PrimaryKey
    val bookingId: String,
    val patientName: String,
    val patientPhone: String,
    val deliveryType: String, // "STORE_PICKUP" or "HOME_DELIVERY"
    val deliveryAddress: String,
    val medicinesSummary: String,
    val totalAmount: Double,
    val totalSavings: Double,
    val prescriptionNote: String,
    val prescriptionImageUri: String? = null,
    val status: String = "पुष्टीकृत (Confirmed)",
    val timestamp: Long = System.currentTimeMillis()
)
