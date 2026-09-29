package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
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
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.TrendingDown
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.MedicineRepository
import com.example.model.Medicine
import com.example.ui.theme.JanAushadhiBlueDark
import com.example.ui.theme.JanAushadhiBlueLight
import com.example.ui.theme.JanAushadhiBluePrimary
import com.example.ui.theme.JanAushadhiGreen
import com.example.ui.theme.JanAushadhiOrange
import com.example.ui.theme.WhatsAppGreen
import com.example.util.IntentHelper

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MedicineCard(
    medicine: Medicine,
    isSaved: Boolean,
    onToggleSave: () -> Unit,
    onClick: () -> Unit,
    onBookNow: (() -> Unit)? = null
) {
    val context = LocalContext.current
    val store = MedicineRepository.storeDetails

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .testTag("medicine_card_${medicine.id}"),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f))
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            // Header: Category Pill & Rx Badge
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
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
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
                                text = "कोड: ${medicine.drugCode}",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = JanAushadhiOrange,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
                            )
                        }
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (medicine.isRxRequired) {
                        Surface(
                            shape = RoundedCornerShape(4.dp),
                            color = Color(0xFFFFEBEE)
                        ) {
                            Text(
                                text = "Rx पर्चा आवश्यक",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFC62828),
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    } else {
                        Surface(
                            shape = RoundedCornerShape(4.dp),
                            color = Color(0xFFE8F5E9)
                        ) {
                            Text(
                                text = "OTC (बिना पर्चे)",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF2E7D32),
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(6.dp))

                    // Bookmark / Calculator add button
                    IconButton(
                        onClick = onToggleSave,
                        modifier = Modifier
                            .size(32.dp)
                            .testTag("save_btn_${medicine.id}")
                    ) {
                        Icon(
                            imageVector = if (isSaved) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                            contentDescription = if (isSaved) "Remove from calculator" else "Add to calculator",
                            tint = if (isSaved) JanAushadhiGreen else Color.Gray,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Medicine Hindi & English Names
            Text(
                text = medicine.hindiName,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = medicine.englishName,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.primary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            // Generic salt composition
            Text(
                text = "साल्ट: ${medicine.genericSalt}",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 2.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = "पैकिंग: ${medicine.packSize}",
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF64748B),
                modifier = Modifier.padding(top = 2.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Pricing Row: Kendra Price vs Market MRP & Savings percentage
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = Color(0xFFF8FAFC),
                border = BorderStroke(1.dp, Color(0xFFE2E8F0)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "केंद्र मूल्य (Kendra Price)",
                            fontSize = 10.sp,
                            color = Color(0xFF64748B),
                            fontWeight = FontWeight.SemiBold
                        )
                        Row(verticalAlignment = Alignment.Bottom) {
                            Text(
                                text = "₹${String.format("%.2f", medicine.kendraPrice)}",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = JanAushadhiBluePrimary
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "MRP ₹${String.format("%.2f", medicine.marketMrp)}",
                                fontSize = 12.sp,
                                textDecoration = TextDecoration.LineThrough,
                                color = Color(0xFF94A3B8),
                                modifier = Modifier.padding(bottom = 2.dp)
                            )
                        }
                    }

                    // Savings Pill
                    Surface(
                        shape = RoundedCornerShape(50.dp),
                        color = Color(0xFFE8F5E9)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.TrendingDown,
                                contentDescription = null,
                                tint = JanAushadhiGreen,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(3.dp))
                            Text(
                                text = "${medicine.savingsPercent}% बचत",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = JanAushadhiGreen
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Actions: Book Now, WhatsApp Inquiry, and Calculator Toggle
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Book Now Button
                Button(
                    onClick = {
                        if (onBookNow != null) {
                            onBookNow()
                        } else {
                            val msg = "नमस्ते प्रो. धीरज वर्मा जी, मैं जनऔषधि केंद्र (${store.centerCode}) से '${medicine.englishName}' बुक करना चाहता हूँ।"
                            IntentHelper.openWhatsApp(context, store.whatsappNumber, msg)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = JanAushadhiOrange,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .weight(1f)
                        .testTag("book_btn_${medicine.id}")
                ) {
                    Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = null, modifier = Modifier.size(13.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "बुक करें",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Button(
                    onClick = {
                        val message = "नमस्ते प्रो. धीरज वर्मा जी, प्रधानमंत्री जनऔषधि केंद्र (${store.centerCode}) से मुझे '${medicine.englishName}' (साल्ट: ${medicine.genericSalt}, दर: ₹${medicine.kendraPrice}) की जानकारी चाहिए।"
                        IntentHelper.openWhatsApp(context, store.whatsappNumber, message)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = WhatsAppGreen,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .weight(1f)
                        .testTag("whatsapp_inquiry_${medicine.id}")
                ) {
                    Text(
                        text = "WhatsApp",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                OutlinedButton(
                    onClick = onToggleSave,
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(
                        1.dp,
                        if (isSaved) JanAushadhiGreen else JanAushadhiBluePrimary
                    ),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = if (isSaved) JanAushadhiGreen else JanAushadhiBluePrimary
                    ),
                    modifier = Modifier
                        .weight(0.9f)
                        .testTag("calculator_toggle_${medicine.id}")
                ) {
                    Icon(
                        imageVector = if (isSaved) Icons.Default.Check else Icons.Default.Add,
                        contentDescription = null,
                        modifier = Modifier.size(13.dp)
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = if (isSaved) "शामिल" else "बचत",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}
