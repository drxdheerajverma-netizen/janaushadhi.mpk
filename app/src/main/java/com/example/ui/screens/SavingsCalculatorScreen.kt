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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.TrendingDown
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.MedicineRepository
import com.example.model.Medicine
import com.example.model.ProductCategory
import com.example.ui.components.MedicalDisclaimerCard
import com.example.ui.components.StoreFooterSection
import com.example.ui.theme.JanAushadhiBlueDark
import com.example.ui.theme.JanAushadhiBlueLight
import com.example.ui.theme.JanAushadhiBluePrimary
import com.example.ui.theme.JanAushadhiGreen
import com.example.ui.theme.JanAushadhiOrange
import com.example.ui.theme.WhatsAppGreen
import com.example.util.IntentHelper
import com.example.viewmodel.PharmacyUiState
import com.example.viewmodel.PharmacyViewModel

@Composable
fun SavingsCalculatorScreen(
    viewModel: PharmacyViewModel,
    uiState: PharmacyUiState,
    onNavigateToMedicines: () -> Unit,
    onNavigateToBooking: () -> Unit
) {
    val context = LocalContext.current
    val store = MedicineRepository.storeDetails

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("savings_calculator_screen"),
        contentPadding = PaddingValues(bottom = 80.dp)
    ) {
        // Savings Header Card
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
                                colors = listOf(Color(0xFF1B5E20), Color(0xFF2E7D32), Color(0xFF388E3C))
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
                            Surface(
                                shape = RoundedCornerShape(50.dp),
                                color = Color(0x33FFFFFF)
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Savings,
                                        contentDescription = null,
                                        tint = Color(0xFFFFD54F),
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "मासिक बचत कैलकुलेटर",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                }
                            }

                            if (uiState.savedMedicinesList.isNotEmpty()) {
                                IconButton(
                                    onClick = { viewModel.clearSavedList() },
                                    modifier = Modifier.size(32.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.DeleteSweep,
                                        contentDescription = "Clear all",
                                        tint = Color.White
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Text(
                            text = "आपकी मासिक दवाई बचत",
                            fontSize = 14.sp,
                            color = Color(0xFFE8F5E9)
                        )

                        Text(
                            text = "₹${String.format("%.2f", uiState.totalSavings)}",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White
                        )

                        Text(
                            text = if (uiState.overallSavingsPercent > 0) "${uiState.overallSavingsPercent}% तक की बचत!" else "दवाइयाँ जोड़कर अपनी बचत देखें",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFFFD54F)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Comparison Stats Box
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = Color(0x22FFFFFF),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                                horizontalArrangement = Arrangement.SpaceAround
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text("केंद्र मूल्य (Kendra)", fontSize = 11.sp, color = Color(0xFFE8F5E9))
                                    Text(
                                        "₹${String.format("%.2f", uiState.totalKendraCost)}",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                }
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text("बाजार MRP (Market)", fontSize = 11.sp, color = Color(0xFFE8F5E9))
                                    Text(
                                        "₹${String.format("%.2f", uiState.totalMarketCost)}",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFFFFCDD2),
                                        textDecoration = TextDecoration.LineThrough
                                    )
                                }
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text("कुल दवाइयाँ", fontSize = 11.sp, color = Color(0xFFE8F5E9))
                                    Text(
                                        "${uiState.savedMedicinesList.size}",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                }
                            }
                        }

                        if (uiState.savedMedicinesList.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(14.dp))

                            // Action 1: Book this list
                            Button(
                                onClick = {
                                    viewModel.populateCartFromSavedList()
                                    onNavigateToBooking()
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = JanAushadhiOrange,
                                    contentColor = Color.White
                                ),
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("book_saved_list_btn")
                            ) {
                                Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = null, modifier = Modifier.size(18.dp))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "इस सूची की दवाइयाँ बुक करें (Book Order)",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            // Action 2: WhatsApp Share Button
                            Button(
                                onClick = {
                                    val msg = viewModel.generateWhatsAppPrescriptionInquiryText()
                                    IntentHelper.openWhatsApp(context, store.whatsappNumber, msg)
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = WhatsAppGreen,
                                    contentColor = Color.White
                                ),
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("whatsapp_share_list_btn")
                            ) {
                                Icon(imageVector = Icons.Default.Chat, contentDescription = null, modifier = Modifier.size(18.dp))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "व्हाट्सएप पर शेयर करें",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }

        // Quick add pre-made bundles if empty
        if (uiState.savedMedicinesList.isEmpty()) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = BorderStroke(1.dp, Color(0xFFE2E8F0))
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.Calculate,
                            contentDescription = null,
                            tint = JanAushadhiBluePrimary,
                            modifier = Modifier.size(40.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "आपकी बचत सूची अभी खाली है",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "दवाइयों के कार्ड पर 'बचत जोड़ें' बटन दबाकर अपनी मासिक दवाइयाँ यहाँ जोड़ें और देखें कि आप हर महीने कितने पैसे बचाते हैं!",
                            fontSize = 12.sp,
                            color = Color(0xFF64748B),
                            modifier = Modifier.padding(top = 4.dp, bottom = 12.dp)
                        )

                        Button(
                            onClick = onNavigateToMedicines,
                            colors = ButtonDefaults.buttonColors(containerColor = JanAushadhiBluePrimary)
                        ) {
                            Text("दवाइयों की सूची देखें")
                        }

                        Spacer(modifier = Modifier.height(14.dp))
                        HorizontalDivider(color = Color(0xFFF1F5F9))
                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "⚡ उदाहरण के लिए लोकप्रिय किट जोड़ें:",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = JanAushadhiOrange
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedButton(
                            onClick = {
                                // Add sample diabetes + BP combo
                                MedicineRepository.medicines
                                    .filter { it.category == ProductCategory.DIABETES || it.category == ProductCategory.BP_HEART }
                                    .take(4)
                                    .forEach { med ->
                                        if (!viewModel.isMedicineSaved(med.id)) {
                                            viewModel.toggleMedicineInSavedList(med)
                                        }
                                    }
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("डायबिटीज + बीपी कॉम्बो किट जोड़ें (उदा.)")
                        }
                    }
                }
            }
        } else {
            // Header for saved list
            item {
                Text(
                    text = "चुनी हुई दवाइयों की सूची (${uiState.savedMedicinesList.size}):",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
                )
            }

            // Saved Items List
            items(uiState.savedMedicinesList, key = { it.id }) { medicine ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = BorderStroke(1.dp, Color(0xFFE2E8F0))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = medicine.englishName,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "${medicine.hindiName} (${medicine.packSize})",
                                fontSize = 11.sp,
                                color = Color(0xFF64748B)
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(top = 2.dp)
                            ) {
                                Text(
                                    text = "केंद्र: ₹${medicine.kendraPrice}",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = JanAushadhiBluePrimary
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "बाजार: ₹${medicine.marketMrp}",
                                    fontSize = 11.sp,
                                    textDecoration = TextDecoration.LineThrough,
                                    color = Color.Gray
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Surface(
                                    shape = RoundedCornerShape(4.dp),
                                    color = Color(0xFFE8F5E9)
                                ) {
                                    Text(
                                        text = "${medicine.savingsPercent}% बचत",
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = JanAushadhiGreen,
                                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                                    )
                                }
                            }
                        }

                        IconButton(
                            onClick = { viewModel.toggleMedicineInSavedList(medicine) },
                            modifier = Modifier.size(32.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Remove",
                                tint = Color.Gray,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(10.dp))
                Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                    OutlinedButton(
                        onClick = onNavigateToMedicines,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(imageVector = Icons.Default.AddCircle, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("और दवाइयाँ जोड़ें")
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
