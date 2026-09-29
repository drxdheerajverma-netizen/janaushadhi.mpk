package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Store
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.MedicineRepository
import com.example.model.FaqItem
import com.example.ui.theme.JanAushadhiBlueDark
import com.example.ui.theme.JanAushadhiBlueLight
import com.example.ui.theme.JanAushadhiBluePrimary
import com.example.ui.theme.JanAushadhiGreen
import com.example.ui.theme.JanAushadhiOrange
import com.example.ui.theme.WhatsAppGreen
import com.example.util.IntentHelper

@Composable
fun StoreTimingSection(modifier: Modifier = Modifier) {
    val store = MedicineRepository.storeDetails

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("store_timings_card"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, Color(0xFFE2E8F0)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(JanAushadhiBlueLight),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccessTime,
                            contentDescription = null,
                            tint = JanAushadhiBluePrimary,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "दुकान खुलने का समय",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "Store Working Hours",
                            fontSize = 11.sp,
                            color = Color(0xFF64748B)
                        )
                    }
                }

                Surface(
                    shape = RoundedCornerShape(50.dp),
                    color = Color(0xFFE8F5E9)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(JanAushadhiGreen)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "नियमित सेवा",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = JanAushadhiGreen
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Timings breakdown
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = Color(0xFFF8FAFC),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "सोमवार से शनिवार (Mon - Sat)",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1E293B)
                            )
                            Text(
                                text = "सुबह 10:00 AM से रात्रि 8:00 PM तक",
                                fontSize = 13.sp,
                                color = JanAushadhiBlueDark,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp), color = Color(0xFFE2E8F0))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "रविवार (Sunday)",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1E293B)
                            )
                            Text(
                                text = "सुबह 10:00 AM से रात्रि 8:00 PM तक",
                                fontSize = 13.sp,
                                color = JanAushadhiGreen,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GoogleMapsLocationSection(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val store = MedicineRepository.storeDetails

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("google_maps_section_card"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, Color(0xFFE2E8F0)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFFFEBEE)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = Color(0xFFD32F2F),
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = "केंद्र का पता व मानचित्र (Location)",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Google Maps Location & Directions",
                        fontSize = 11.sp,
                        color = Color(0xFF64748B)
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Simulated Stylized Map Visual Card
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = Color(0xFFE3F2FD),
                border = BorderStroke(1.dp, Color(0xFF90CAF9)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .clickable {
                        IntentHelper.openGoogleMaps(
                            context,
                            store.latitude,
                            store.longitude,
                            "PMBJK - Mohammad Pur Khala"
                        )
                    }
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.linearGradient(
                                colors = listOf(Color(0xFFE3F2FD), Color(0xFFBBDEFB), Color(0xFF90CAF9))
                            )
                        )
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Surface(
                            shape = CircleShape,
                            color = Color.White,
                            shadowElevation = 4.dp
                        ) {
                            Icon(
                                imageVector = Icons.Default.Navigation,
                                contentDescription = null,
                                tint = JanAushadhiBluePrimary,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .size(28.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "मोहम्मदपुर खाला (निकट मनोज ज्वैलर्स)",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = JanAushadhiBlueDark
                        )
                        Text(
                            text = "Google Maps में नेविगेट करने के लिए यहाँ टैप करें ↗",
                            fontSize = 11.sp,
                            color = Color(0xFF0D47A1),
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Address Details Text
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF8FAFC), RoundedCornerShape(10.dp))
                    .padding(12.dp)
            ) {
                Text(
                    text = "📍 पूरा पता:",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF334155)
                )
                Text(
                    text = store.addressHindi,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF0F172A),
                    modifier = Modifier.padding(top = 2.dp)
                )
                Text(
                    text = "पहचान चिन्ह (Landmark): ${store.landmarkHindi}",
                    fontSize = 12.sp,
                    color = JanAushadhiOrange,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 4.dp)
                )
                Text(
                    text = "तहसील: फ़तेहपुर | जनपद: बाराबंकी | पिनकोड: 225303",
                    fontSize = 11.sp,
                    color = Color(0xFF64748B),
                    modifier = Modifier.padding(top = 2.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Action Button to open Maps
            Button(
                onClick = {
                    IntentHelper.openGoogleMaps(
                        context,
                        store.latitude,
                        store.longitude,
                        "प्रधानमंत्री भारतीय जनऔषधि केंद्र Mohammad Pur Khala"
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("open_maps_btn"),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = JanAushadhiBluePrimary,
                    contentColor = Color.White
                )
            ) {
                Icon(imageVector = Icons.Default.Map, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "गूगल मैप्स में दिशा-निर्देश देखें (Directions)",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun SuvidhaNapkinHighlightCard(onExploreCategory: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("suvidha_banner_card"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFCE4EC)),
        border = BorderStroke(1.dp, Color(0xFFF8BBD0)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(50.dp),
                    color = Color(0xFFD81B60)
                ) {
                    Text(
                        text = "भारत सरकार की विशेष पहल",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }

                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = Color.White
                ) {
                    Text(
                        text = "मात्र ₹1 प्रति पैड!",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFFC2185B),
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "जनऔषधि 'सुविधा' सैनिटरी नैपकिन",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF880E4F)
            )

            Text(
                text = "100% ऑक्सो-बायोडिग्रेडेबल (पर्यावरण हितैषी), विशेष विंग्स और उच्च अवशोषण क्षमता के साथ 4 पैड का पैक मात्र ₹4 में उपलब्ध।",
                fontSize = 13.sp,
                color = Color(0xFF4A148C),
                modifier = Modifier.padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = onExploreCategory,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFD81B60),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.testTag("suvidha_view_btn")
            ) {
                Text(text = "सैनिटरी उत्पाद देखें", fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun ProprietorSection(modifier: Modifier = Modifier) {
    val store = MedicineRepository.storeDetails
    val context = LocalContext.current

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("proprietor_card"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, Color(0xFFE2E8F0)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(JanAushadhiBlueLight),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = JanAushadhiBluePrimary,
                        modifier = Modifier.size(26.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = store.proprietorHindi,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "प्रोपराइटर / फार्मासिस्ट (D.Pharm)",
                        fontSize = 12.sp,
                        color = JanAushadhiGreen,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "प्रधानमंत्री भारतीय जनऔषधि केंद्र (PMBJK22063) के माध्यम से क्षेत्रवासियों को गुणवत्तापूर्ण जेनेरिक औषधियां न्यूनतम मूल्य पर उपलब्ध कराने के लिए सदैव समर्पित।",
                fontSize = 13.sp,
                color = Color(0xFF475569)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    onClick = { IntentHelper.openDialer(context, store.phone) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = JanAushadhiBluePrimary,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(imageVector = Icons.Default.Call, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("सीधे बात करें", fontSize = 12.sp)
                }

                Button(
                    onClick = {
                        val message = "नमस्ते प्रो. धीरज वर्मा जी, जनऔषधि केंद्र से संपर्क कर रहा हूँ।"
                        IntentHelper.openWhatsApp(context, store.whatsappNumber, message)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = WhatsAppGreen,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(imageVector = Icons.Default.Chat, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("व्हाट्सएप", fontSize = 12.sp)
                }
            }
        }
    }
}

@Composable
fun MedicalDisclaimerCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("medical_disclaimer_card"),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFBEB)),
        border = BorderStroke(1.dp, Color(0xFFFDE68A))
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.Top
        ) {
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = "Medical Disclaimer",
                tint = Color(0xFFD97706),
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = "वैधानिक एवं चिकित्सकीय परामर्श सूचना (Medical Disclaimer)",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF92400E)
                )
                Text(
                    text = "यह ऐप/वेबसाइट केवल जन-जागरूकता एवं मूल्य सूची की जानकारी हेतु है। कोई भी चिकित्सकीय दावा नहीं किया गया है। सभी प्रिस्क्रिप्शन (Rx) युक्त दवाइयों का उपयोग केवल किसी योग्य व पंजीकृत चिकित्सक (Doctor/RMP) के परामर्श एवं पर्चे के आधार पर ही करें।",
                    fontSize = 11.sp,
                    color = Color(0xFF78350F),
                    lineHeight = 16.sp,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }
        }
    }
}

@Composable
fun FaqSection(faqs: List<FaqItem>, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(JanAushadhiBlueLight),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.HelpOutline,
                    contentDescription = null,
                    tint = JanAushadhiBluePrimary,
                    modifier = Modifier.size(18.dp)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = "अक्सर पूछे जाने वाले सवाल (FAQs)",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Frequently Asked Questions about Jan Aushadhi",
                    fontSize = 11.sp,
                    color = Color(0xFF64748B)
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        faqs.forEach { faq ->
            FaqItemCard(faq = faq)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun FaqItemCard(faq: FaqItem) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded }
            .testTag("faq_card_${faq.id}"),
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
                Text(
                    text = faq.questionHindi,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = if (expanded) "Collapse" else "Expand",
                    tint = JanAushadhiBluePrimary
                )
            }

            Text(
                text = faq.questionEnglish,
                fontSize = 11.sp,
                color = Color(0xFF64748B),
                modifier = Modifier.padding(top = 2.dp)
            )

            AnimatedVisibility(visible = expanded) {
                Column(modifier = Modifier.padding(top = 10.dp)) {
                    HorizontalDivider(color = Color(0xFFF1F5F9))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = faq.answerHindi,
                        fontSize = 13.sp,
                        color = Color(0xFF334155),
                        lineHeight = 18.sp
                    )
                    Text(
                        text = faq.answerEnglish,
                        fontSize = 11.sp,
                        color = Color(0xFF64748B),
                        lineHeight = 16.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun StoreFooterSection(modifier: Modifier = Modifier) {
    val store = MedicineRepository.storeDetails
    val context = LocalContext.current

    Surface(
        modifier = modifier.fillMaxWidth(),
        color = JanAushadhiBlueDark
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.MedicalServices,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "प्रधानमंत्री भारतीय जनऔषधि केंद्र",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Surface(
                shape = RoundedCornerShape(4.dp),
                color = JanAushadhiOrange
            ) {
                Text(
                    text = "Center Code: ${store.centerCode}",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "संचालक: ${store.proprietorHindi} (${store.proprietorEnglish})",
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFFE2E8F0)
            )

            Text(
                text = "📍 ${store.addressHindi}",
                fontSize = 12.sp,
                color = Color(0xFFCBD5E1),
                modifier = Modifier.padding(top = 4.dp)
            )

            Text(
                text = "निकट मनोज ज्वैलर्स | पिनकोड: 225303",
                fontSize = 12.sp,
                color = Color(0xFFFFD54F),
                modifier = Modifier.padding(top = 2.dp)
            )

            Text(
                text = "📞 संपर्क: ${store.displayPhone} | ✉️ ${store.email}",
                fontSize = 12.sp,
                color = Color(0xFFE2E8F0),
                modifier = Modifier.padding(top = 4.dp)
            )

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 14.dp),
                color = Color(0x33FFFFFF)
            )

            Text(
                text = "फार्मास्यूटिकल्स विभाग, रसायन एवं उर्वरक मंत्रालय, भारत सरकार की जनहितकारी पहल। जन-जन को सस्ती, सुरक्षित व गुणवत्तापूर्ण स्वास्थ्य सेवाएं।",
                fontSize = 11.sp,
                color = Color(0xFF94A3B8),
                lineHeight = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "© 2026 PMBJK Mohammad Pur Khala. सर्वाधिकार सुरक्षित।",
                fontSize = 10.sp,
                color = Color(0xFF64748B),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
