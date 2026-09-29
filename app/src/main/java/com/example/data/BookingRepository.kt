package com.example.data

import com.example.data.local.BookingDao
import com.example.data.local.BookingEntity
import kotlinx.coroutines.flow.Flow

class BookingRepository(private val bookingDao: BookingDao) {

    val allBookings: Flow<List<BookingEntity>> = bookingDao.getAllBookings()

    suspend fun saveBooking(booking: BookingEntity) {
        bookingDao.insertBooking(booking)
    }

    suspend fun deleteBooking(id: String) {
        bookingDao.deleteBooking(id)
    }
}
