package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Percent
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.MedicineRepository
import com.example.ui.theme.CallBlue
import com.example.ui.theme.JanAushadhiBlueDark
import com.example.ui.theme.JanAushadhiBlueLight
import com.example.ui.theme.JanAushadhiBluePrimary
import com.example.ui.theme.JanAushadhiGreen
import com.example.ui.theme.JanAushadhiOrange
import com.example.ui.theme.WhatsAppGreen
import com.example.util.IntentHelper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PharmacyTopAppBar(
    onSearchClick: () -> Unit,
    savedItemsCount: Int = 0,
    onSavingsClick: () -> Unit = {}
) {
    val context = LocalContext.current
    val store = MedicineRepository.storeDetails

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = JanAushadhiBlueDark,
            titleContentColor = Color.White,
            actionIconContentColor = Color.White
        ),
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.MedicalServices,
                        contentDescription = "Janaushadhi Emblem",
                        tint = JanAushadhiBluePrimary,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "जनऔषधि केंद्र",
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Surface(
                            shape = RoundedCornerShape(4.dp),
                            color = JanAushadhiOrange
                        ) {
                            Text(
                                text = store.centerCode,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                            )
                        }
                    }
                    Text(
                        text = "मोहम्मदपुर खाला, बाराबंकी",
                        fontSize = 11.sp,
                        color = Color(0xFFE2E8F0),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        },
        actions = {
            // Quick Call
            IconButton(
                onClick = { IntentHelper.openDialer(context, store.phone) },
                modifier = Modifier.testTag("appbar_call_btn")
            ) {
                Icon(
                    imageVector = Icons.Default.Call,
                    contentDescription = "Call store",
                    tint = Color.White
                )
            }

            // Savings badge button
            IconButton(
                onClick = onSavingsClick,
                modifier = Modifier.testTag("appbar_savings_btn")
            ) {
                if (savedItemsCount > 0) {
                    BadgedBox(
                        badge = {
                            Badge(
                                containerColor = JanAushadhiGreen,
                                contentColor = Color.White
                            ) {
                                Text("$savedItemsCount")
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Percent,
                            contentDescription = "Savings Calculator",
                            tint = Color.White
                        )
                    }
                } else {
                    Icon(
                        imageVector = Icons.Default.Percent,
                        contentDescription = "Savings Calculator",
                        tint = Color.White
                    )
                }
            }

            // Quick Search
            IconButton(
                onClick = onSearchClick,
                modifier = Modifier.testTag("appbar_search_btn")
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search medicines",
                    tint = Color.White
                )
            }
        }
    )
}

@Composable
fun HeroBanner(
    onExploreMedicines: () -> Unit,
    onOpenSavings: () -> Unit
) {
    val context = LocalContext.current
    val store = MedicineRepository.storeDetails

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF003B6D),
                            Color(0xFF026AA7),
                            Color(0xFF00838F)
                        )
                    )
                )
                .padding(20.dp)
        ) {
            Column {
                // Top Tag Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        shape = RoundedCornerShape(50.dp),
                        color = Color(0x33FFFFFF)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Verified,
                                contentDescription = null,
                                tint = Color(0xFFFFD54F),
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "भारत सरकार की योजना (PMBJP)",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White
                            )
                        }
                    }

                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = Color(0xFFFFF3E0)
                    ) {
                        Text(
                            text = "कोड: ${store.centerCode}",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = JanAushadhiOrange,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "प्रधानमंत्री भारतीय जनऔषधि केंद्र",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White,
                    lineHeight = 28.sp
                )

                Text(
                    text = "अच्छी व उच्च गुणवत्ता युक्त दवाइयाँ — 50% से 90% तक कम कीमत पर!",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFFE0F7FA),
                    modifier = Modifier.padding(top = 6.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Location & Landmark pill
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = Color(0x22FFFFFF),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = null,
                                tint = Color(0xFFFFD54F),
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "मोहम्मदपुर खाला, तहसील फ़तेहपुर, बाराबंकी",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                        Text(
                            text = "निकट मनोज ज्वैलर्स | ${store.proprietorHindi}",
                            fontSize = 11.sp,
                            color = Color(0xFFF1F5F9),
                            modifier = Modifier.padding(start = 22.dp, top = 2.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Action Buttons Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // WhatsApp Button
                    Button(
                        onClick = {
                            val msg = "नमस्ते प्रो. धीरज वर्मा जी, मैं जनऔषधि केंद्र (${store.centerCode}) से दवाओं की जानकारी चाहता हूँ।"
                            IntentHelper.openWhatsApp(context, store.whatsappNumber, msg)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = WhatsAppGreen,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .weight(1f)
                            .testTag("hero_whatsapp_btn")
                    ) {
                        Text(
                            text = "व्हाट्सएप परामर्श",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    // Call Button
                    Button(
                        onClick = {
                            IntentHelper.openDialer(context, store.phone)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = JanAushadhiBluePrimary
                        ),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .weight(1f)
                            .testTag("hero_call_btn")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Phone,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = JanAushadhiBluePrimary
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "कॉल करें",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Secondary row: Explore Medicines & Savings Calculator
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OutlinedButton(
                        onClick = onExploreMedicines,
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
                        border = ButtonDefaults.outlinedButtonBorder.copy(brush = Brush.horizontalGradient(listOf(Color.White, Color.White))),
                        modifier = Modifier
                            .weight(1f)
                            .testTag("hero_medicines_btn")
                    ) {
                        Text(
                            text = "दवाइयाँ देखें",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    FilledTonalButton(
                        onClick = onOpenSavings,
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.filledTonalButtonColors(
                            containerColor = Color(0x33FFFFFF),
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .testTag("hero_calculator_btn")
                    ) {
                        Text(
                            text = "बचत कैलकुलेटर",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}
