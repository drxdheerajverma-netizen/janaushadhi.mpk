package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Store
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.MedicineRepository
import com.example.ui.components.GoogleMapsLocationSection
import com.example.ui.components.MedicalDisclaimerCard
import com.example.ui.components.ProprietorSection
import com.example.ui.components.StoreFooterSection
import com.example.ui.components.StoreTimingSection
import com.example.ui.theme.JanAushadhiBlueDark
import com.example.ui.theme.JanAushadhiBlueLight
import com.example.ui.theme.JanAushadhiBluePrimary
import com.example.ui.theme.JanAushadhiGreen
import com.example.ui.theme.JanAushadhiOrange
import com.example.ui.theme.WhatsAppGreen
import com.example.util.IntentHelper

@Composable
fun ContactScreen() {
    val context = LocalContext.current
    val store = MedicineRepository.storeDetails

    var inquiryText by remember { mutableStateOf("") }
    var patientName by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("contact_screen"),
        contentPadding = PaddingValues(bottom = 80.dp)
    ) {
        // Contact Header Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(18.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color(0xFF003B6D), Color(0xFF026AA7))
                            )
                        )
                        .padding(20.dp)
                ) {
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "संपर्क व सहायता केंद्र",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )

                            Surface(
                                shape = RoundedCornerShape(4.dp),
                                color = JanAushadhiOrange
                            ) {
                                Text(
                                    text = store.centerCode,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }

                        Text(
                            text = "प्रधानमंत्री भारतीय जनऔषधि केंद्र — मोहम्मदपुर खाला, बाराबंकी",
                            fontSize = 13.sp,
                            color = Color(0xFFE0F7FA),
                            modifier = Modifier.padding(top = 4.dp)
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        // Quick buttons
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Button(
                                onClick = { IntentHelper.openDialer(context, store.phone) },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.White,
                                    contentColor = JanAushadhiBluePrimary
                                ),
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .weight(1f)
                                    .testTag("contact_call_btn")
                            ) {
                                Icon(imageVector = Icons.Default.Call, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("कॉल करें", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                            }

                            Button(
                                onClick = {
                                    val msg = "नमस्ते प्रो. धीरज वर्मा जी, मैं जनऔषधि केंद्र (${store.centerCode}) से संपर्क करना चाहता हूँ।"
                                    IntentHelper.openWhatsApp(context, store.whatsappNumber, msg)
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = WhatsAppGreen,
                                    contentColor = Color.White
                                ),
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .weight(1f)
                                    .testTag("contact_whatsapp_btn")
                            ) {
                                Icon(imageVector = Icons.Default.Chat, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("व्हाट्सएप", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                            }
                        }
                    }
                }
            }
        }

        // WhatsApp Prescription Inquiry Box
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, Color(0xFFE2E8F0))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFE8F5E9)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Chat,
                                contentDescription = null,
                                tint = WhatsAppGreen,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = "व्हाट्सएप पर पर्चा / दवाई की पूछताछ भेजें",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "Direct Prescription Availability Query",
                                fontSize = 11.sp,
                                color = Color(0xFF64748B)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = patientName,
                        onValueChange = { patientName = it },
                        label = { Text("आपका नाम (Patient Name)") },
                        placeholder = { Text("उदा. रमेश कुमार") },
                        singleLine = true,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("inquiry_name_input")
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = inquiryText,
                        onValueChange = { inquiryText = it },
                        label = { Text("दवा का नाम या पर्चे की जानकारी (Medicines Query)") },
                        placeholder = { Text("उदा. मुझे बीपी की टेल्मीसार्टन 40mg और सुगर की मेटफॉर्मिन 500mg चाहिए...") },
                        minLines = 3,
                        maxLines = 5,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("inquiry_text_input")
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Button(
                        onClick = {
                            val textToSend = buildString {
                                append("नमस्ते प्रो. धीरज वर्मा जी,\n")
                                append("जनऔषधि केंद्र (${store.centerCode}), मोहम्मदपुर खाला, बाराबंकी।\n\n")
                                if (patientName.isNotBlank()) {
                                    append("मरीज का नाम: ${patientName.trim()}\n")
                                }
                                if (inquiryText.isNotBlank()) {
                                    append("दवाई संबंधी पूछताछ:\n${inquiryText.trim()}\n\n")
                                } else {
                                    append("दवाइयों की उपलब्धता जानने के लिए संपर्क किया गया।\n\n")
                                }
                                append("कृपया उपलब्धता एवं कुल बचत की जानकारी दें। धन्यवाद!")
                            }
                            IntentHelper.openWhatsApp(context, store.whatsappNumber, textToSend)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = WhatsAppGreen,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("send_whatsapp_inquiry_btn")
                    ) {
                        Icon(imageVector = Icons.Default.Send, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "व्हाट्सएप पर भेजें",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        // Store Timings Card (Requirement 15)
        item {
            Spacer(modifier = Modifier.height(14.dp))
            StoreTimingSection(modifier = Modifier.padding(horizontal = 16.dp))
        }

        // Google Maps Location Section (Requirement 12)
        item {
            Spacer(modifier = Modifier.height(14.dp))
            GoogleMapsLocationSection(modifier = Modifier.padding(horizontal = 16.dp))
        }

        // Proprietor Card
        item {
            Spacer(modifier = Modifier.height(14.dp))
            ProprietorSection(modifier = Modifier.padding(horizontal = 16.dp))
        }

        // Email & Help Box
        item {
            Spacer(modifier = Modifier.height(14.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, Color(0xFFE2E8F0))
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFE3F2FD)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = null,
                            tint = JanAushadhiBluePrimary,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "आधिकारिक ईमेल (Email Support)",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = store.email,
                            fontSize = 13.sp,
                            color = JanAushadhiBluePrimary,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Button(
                        onClick = {
                            IntentHelper.openEmail(
                                context,
                                store.email,
                                "जनऔषधि केंद्र PMBJK22063 पूछताछ",
                                "नमस्ते प्रो. धीरज वर्मा जी,"
                            )
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = JanAushadhiBlueLight, contentColor = JanAushadhiBlueDark),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text("ईमेल", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        // Statutory Medical Disclaimer
        item {
            Spacer(modifier = Modifier.height(16.dp))
            MedicalDisclaimerCard(modifier = Modifier.padding(horizontal = 16.dp))
        }

        // Footer
        item {
            Spacer(modifier = Modifier.height(24.dp))
            StoreFooterSection()
        }
    }
}
