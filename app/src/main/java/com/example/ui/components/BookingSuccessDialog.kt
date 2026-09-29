package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.data.MedicineRepository
import com.example.data.local.BookingEntity
import com.example.ui.theme.JanAushadhiBlueDark
import com.example.ui.theme.JanAushadhiBluePrimary
import com.example.ui.theme.JanAushadhiGreen
import com.example.ui.theme.JanAushadhiOrange
import com.example.ui.theme.WhatsAppGreen
import com.example.util.IntentHelper

@Composable
fun BookingSuccessDialog(
    booking: BookingEntity,
    whatsappMessage: String,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val store = MedicineRepository.storeDetails

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("booking_success_dialog"),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(onClick = onDismiss, modifier = Modifier.size(28.dp)) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Close", tint = Color.Gray)
                    }
                }

                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFE8F5E9)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "Success",
                        tint = JanAushadhiGreen,
                        modifier = Modifier.size(36.dp)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "दवाई बुकिंग सफल!",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = JanAushadhiGreen
                )

                Text(
                    text = "आपकी बुकिंग जनऔषधि केंद्र पर दर्ज हो गई है",
                    fontSize = 12.sp,
                    color = Color(0xFF64748B)
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Booking ID Pill
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Color(0xFFFFF3E0)
                ) {
                    Text(
                        text = "बुकिंग आईडी: ${booking.bookingId}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = JanAushadhiOrange,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Details Box
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Color(0xFFF8FAFC),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("मरीज:", fontSize = 12.sp, color = Color(0xFF64748B))
                            Text(booking.patientName, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("प्रकार:", fontSize = 12.sp, color = Color(0xFF64748B))
                            Text(booking.deliveryType, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = JanAushadhiBluePrimary)
                        }

                        if (booking.totalAmount > 0) {
                            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), color = Color(0xFFE2E8F0))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text("कुल देय राशि:", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                                Text("₹${String.format("%.2f", booking.totalAmount)}", fontSize = 15.sp, fontWeight = FontWeight.ExtraBold, color = JanAushadhiBluePrimary)
                            }
                            if (booking.totalSavings > 0) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text("आपकी कुल बचत:", fontSize = 12.sp, color = JanAushadhiGreen)
                                    Text("₹${String.format("%.2f", booking.totalSavings)}", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = JanAushadhiGreen)
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Action: Send to WhatsApp
                Button(
                    onClick = {
                        IntentHelper.openWhatsApp(context, store.whatsappNumber, whatsappMessage)
                        onDismiss()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = WhatsAppGreen,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("dialog_send_whatsapp_btn")
                ) {
                    Icon(imageVector = Icons.Default.Share, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "प्रो. धीरज वर्मा जी को WhatsApp भेजें",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedButton(
                    onClick = onDismiss,
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("ठीक है (Done)", fontSize = 13.sp)
                }
            }
        }
    }
}
