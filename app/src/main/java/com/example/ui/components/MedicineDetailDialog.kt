package com.example.ui.components

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.data.MedicineRepository
import com.example.model.Medicine
import com.example.ui.theme.JanAushadhiBlueDark
import com.example.ui.theme.JanAushadhiBlueLight
import com.example.ui.theme.JanAushadhiBluePrimary
import com.example.ui.theme.JanAushadhiGreen
import com.example.ui.theme.JanAushadhiOrange
import com.example.ui.theme.WhatsAppGreen
import com.example.util.IntentHelper

@Composable
fun MedicineDetailDialog(
    medicine: Medicine,
    isSaved: Boolean,
    onToggleSave: () -> Unit,
    onBookNow: () -> Unit,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val store = MedicineRepository.storeDetails

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("medicine_detail_dialog"),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                // Header with dismiss
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = JanAushadhiBlueLight
                        ) {
                            Text(
                                text = medicine.category.titleHindi,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = JanAushadhiBlueDark,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                            )
                        }

                        if (medicine.drugCode.isNotEmpty()) {
                            Spacer(modifier = Modifier.width(6.dp))
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = Color(0xFFFFF3E0)
                            ) {
                                Text(
                                    text = "PMBJP कोड: ${medicine.drugCode}",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = JanAushadhiOrange,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                )
                            }
                        }
                    }

                    IconButton(onClick = onDismiss, modifier = Modifier.size(32.dp)) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Close", tint = Color.Gray)
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = medicine.hindiName,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = medicine.englishName,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = JanAushadhiBluePrimary,
                    modifier = Modifier.padding(top = 2.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Salt and packaging
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF8FAFC), RoundedCornerShape(10.dp))
                        .padding(12.dp)
                ) {
                    Text(
                        text = "🧪 जेनेरिक साल्ट (Generic Salt):",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF475569)
                    )
                    Text(
                        text = medicine.genericSalt,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF0F172A),
                        modifier = Modifier.padding(top = 2.dp)
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "📦 पैकेजिंग (Packaging): ${medicine.packSize}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF334155)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Price comparison card
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Color(0xFFE8F5E9),
                    border = BorderStroke(1.dp, Color(0xFFA5D6A7)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "जनऔषधि केंद्र मूल्य",
                                fontSize = 11.sp,
                                color = Color(0xFF2E7D32),
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                text = "₹${String.format("%.2f", medicine.kendraPrice)}",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = JanAushadhiBluePrimary
                            )
                        }

                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                text = "बाजार MRP: ₹${String.format("%.2f", medicine.marketMrp)}",
                                fontSize = 12.sp,
                                textDecoration = TextDecoration.LineThrough,
                                color = Color.Gray
                            )
                            Text(
                                text = "${medicine.savingsPercent}% बचत! (₹${String.format("%.2f", medicine.savingsAmount)} कम)",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = JanAushadhiGreen
                            )
                        }
                    }
                }

                if (medicine.descriptionHindi.isNotBlank()) {
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "उपयोग एवं विवरण:",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF475569)
                    )
                    Text(
                        text = medicine.descriptionHindi,
                        fontSize = 13.sp,
                        color = Color(0xFF334155),
                        lineHeight = 18.sp,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Prescription notice
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            if (medicine.isRxRequired) Color(0xFFFFEBEE) else Color(0xFFF1F8E9),
                            RoundedCornerShape(8.dp)
                        )
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = if (medicine.isRxRequired) Icons.Default.Warning else Icons.Default.Check,
                        contentDescription = null,
                        tint = if (medicine.isRxRequired) Color(0xFFC62828) else Color(0xFF2E7D32),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (medicine.isRxRequired)
                            "यह Rx औषधि है, केवल डॉक्टर के पर्चे पर दी जाएगी।"
                        else
                            "यह OTC उत्पाद है, बिना पर्चे के भी उपलब्ध है।",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (medicine.isRxRequired) Color(0xFFC62828) else Color(0xFF2E7D32)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Primary CTA: Book Now
                Button(
                    onClick = {
                        onBookNow()
                        onDismiss()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = JanAushadhiOrange,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth().testTag("dialog_book_now_btn")
                ) {
                    Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("यह दवा अभी बुक करें (Book Now)", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Secondary Row: WhatsApp & Save to calculator
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = {
                            val msg = "नमस्ते प्रो. धीरज वर्मा जी, मैं जनऔषधि केंद्र (${store.centerCode}) से '${medicine.englishName}' (साल्ट: ${medicine.genericSalt}, दर: ₹${medicine.kendraPrice}) की उपलब्धता जानना चाहता हूँ।"
                            IntentHelper.openWhatsApp(context, store.whatsappNumber, msg)
                            onDismiss()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = WhatsAppGreen,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(imageVector = Icons.Default.Chat, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("WhatsApp", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }

                    OutlinedButton(
                        onClick = onToggleSave,
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            imageVector = if (isSaved) Icons.Default.Check else Icons.Default.Add,
                            contentDescription = null,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = if (isSaved) "सूची में है" else "बचत सूची",
                            fontSize = 11.sp
                        )
                    }
                }
            }
        }
    }
}
