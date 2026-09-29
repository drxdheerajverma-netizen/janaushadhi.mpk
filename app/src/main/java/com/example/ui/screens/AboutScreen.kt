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
import androidx.compose.material.icons.filled.Biotech
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.PriceCheck
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.MedicineRepository
import com.example.ui.components.MedicalDisclaimerCard
import com.example.ui.components.ProprietorSection
import com.example.ui.components.StoreFooterSection
import com.example.ui.theme.JanAushadhiBlueDark
import com.example.ui.theme.JanAushadhiBlueLight
import com.example.ui.theme.JanAushadhiBluePrimary
import com.example.ui.theme.JanAushadhiGreen
import com.example.ui.theme.JanAushadhiOrange

@Composable
fun AboutScreen() {
    val store = MedicineRepository.storeDetails

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("about_screen"),
        contentPadding = PaddingValues(bottom = 80.dp)
    ) {
        // Hero About Header
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
                        Surface(
                            shape = RoundedCornerShape(50.dp),
                            color = Color(0x33FFFFFF)
                        ) {
                            Text(
                                text = "योजना का परिचय एवं उद्देश्य",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "प्रधानमंत्री भारतीय जनऔषधि केंद्र",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White
                        )

                        Text(
                            text = "केंद्र कोड: ${store.centerCode} | मोहम्मदपुर खाला, बाराबंकी",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFFFFD54F),
                            modifier = Modifier.padding(top = 4.dp)
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "भारत सरकार के रसायन एवं उर्वरक मंत्रालय (फार्मास्यूटिकल्स विभाग) द्वारा संचालित इस महाअभियान का लक्ष्य है — 'अच्छी दवाई, कम दाम, हर नागरिक के नाम'।",
                            fontSize = 13.sp,
                            color = Color(0xFFE0F7FA),
                            lineHeight = 18.sp
                        )
                    }
                }
            }
        }

        // Quality & Efficacy Pillars
        item {
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    text = "गुणवत्ता के 4 मजबूत स्तंभ (Quality Assurance)",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(bottom = 10.dp)
                )

                // Pillar 1: WHO-GMP
                QualityPillarCard(
                    icon = Icons.Default.VerifiedUser,
                    title = "WHO-GMP प्रमाणित निर्माण",
                    description = "जनऔषधि की सभी दवाइयाँ केवल विश्व स्वास्थ्य संगठन (WHO) के Good Manufacturing Practices प्रमाणित उच्च स्तरीय फार्मा कारखानों में बनती हैं।"
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Pillar 2: NABL Labs Testing
                QualityPillarCard(
                    icon = Icons.Default.Biotech,
                    title = "NABL लैब में प्रत्येक बैच की जांच",
                    description = "दवाइयों के हर एक बैच को बाजार में उतारने से पहले राष्ट्रीय स्तर की NABL मान्यता प्राप्त प्रयोगशालाओं में कठोर रासायनिक परीक्षण से गुजारा जाता है।"
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Pillar 3: Therapeutic Efficacy
                QualityPillarCard(
                    icon = Icons.Default.HealthAndSafety,
                    title = "ब्रांडेड दवाओं जैसी ही 100% प्रभावशीलता",
                    description = "समान सक्रिय रासायनिक तत्व (Active Pharmaceutical Ingredient - API), समान शक्ति (Bioequivalence) और समान असर। कोई समझौता नहीं।"
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Pillar 4: Direct to Patient Pricing
                QualityPillarCard(
                    icon = Icons.Default.PriceCheck,
                    title = "50% से 90% कम कीमत का रहस्य",
                    description = "बिना किसी बड़े विज्ञापन खर्च, सेलिब्रिटी एंडोर्समेंट या बिचौलियों के, सरकार सीधे थोक में खरीदकर न्यूनतम लागत पर जनता तक पहुंचाती है।"
                )
            }
        }

        // Proprietor Information Card
        item {
            Spacer(modifier = Modifier.height(16.dp))
            ProprietorSection(modifier = Modifier.padding(horizontal = 16.dp))
        }

        // Mission for Barabanki
        item {
            Spacer(modifier = Modifier.height(16.dp))
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
                        Icon(
                            imageVector = Icons.Default.MedicalServices,
                            contentDescription = null,
                            tint = JanAushadhiBluePrimary,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "मोहम्मदपुर खाला व फतेहपुर क्षेत्र हेतु हमारा संकल्प",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "हमारे केंद्र (PMBJK22063) का उद्देश्य मोहम्मदपुर खाला, सूरतगंज, फतेहपुर और आसपास के सभी ग्रामीण व कस्बाई परिवारों को मासिक दवाइयों के भारी खर्च से मुक्ति दिलाना है। विशेष रूप से ब्लड प्रेशर, शुगर, हृदय रोग और थायराइड के मरीजों के लिए यह केंद्र एक वरदान है।",
                        fontSize = 13.sp,
                        color = Color(0xFF334155),
                        lineHeight = 19.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = Color(0xFFF8FAFC)
                    ) {
                        Text(
                            text = "💡 मरीज सलाह: अपने डॉक्टर से हमेशा दवा के पर्चे में जेनेरिक साल्ट का नाम लिखने का अनुरोध करें ताकि आप किसी भी जनऔषधि केंद्र से सस्ती दवा ले सकें।",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = JanAushadhiBlueDark,
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                }
            }
        }

        // Disclaimer
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

@Composable
fun QualityPillarCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    description: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, Color(0xFFE2E8F0))
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(JanAushadhiBlueLight),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = JanAushadhiBluePrimary,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = description,
                    fontSize = 12.sp,
                    color = Color(0xFF475569),
                    lineHeight = 17.sp,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }
        }
    }
}
