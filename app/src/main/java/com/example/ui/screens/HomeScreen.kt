package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DeliveryDining
import androidx.compose.material.icons.filled.LocalPharmacy
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.MedicineRepository
import com.example.model.Medicine
import com.example.model.ProductCategory
import com.example.ui.components.CategoryChipsRow
import com.example.ui.components.FaqSection
import com.example.ui.components.GoogleMapsLocationSection
import com.example.ui.components.HeroBanner
import com.example.ui.components.MedicalDisclaimerCard
import com.example.ui.components.MedicineCard
import com.example.ui.components.ProprietorSection
import com.example.ui.components.StoreFooterSection
import com.example.ui.components.StoreTimingSection
import com.example.ui.components.SuvidhaNapkinHighlightCard
import com.example.ui.theme.JanAushadhiBlueDark
import com.example.ui.theme.JanAushadhiBlueLight
import com.example.ui.theme.JanAushadhiBluePrimary
import com.example.ui.theme.JanAushadhiGreen
import com.example.ui.theme.JanAushadhiOrange
import com.example.viewmodel.PharmacyUiState
import com.example.viewmodel.PharmacyViewModel

@Composable
fun HomeScreen(
    viewModel: PharmacyViewModel,
    uiState: PharmacyUiState,
    onNavigateToMedicines: () -> Unit,
    onNavigateToBooking: () -> Unit,
    onNavigateToSavings: () -> Unit,
    onSelectMedicine: (Medicine) -> Unit
) {
    val featuredMedicines = MedicineRepository.medicines.take(6)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("home_screen"),
        contentPadding = PaddingValues(bottom = 80.dp)
    ) {
        // 1. Hero Banner with Quick Contacts
        item {
            HeroBanner(
                onExploreMedicines = onNavigateToMedicines,
                onOpenSavings = onNavigateToSavings
            )
        }

        // 2. Online Medicine Booking Highlight Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp)
                    .testTag("booking_banner_card"),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF8E1)),
                border = BorderStroke(1.dp, Color(0xFFFFE082)),
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
                            color = JanAushadhiOrange
                        ) {
                            Text(
                                text = "नई ऑनलाइन सुविधा",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                            )
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null, tint = JanAushadhiGreen, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("पिकअप व होम डिलीवरी", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = JanAushadhiGreen)
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "दवाई व डॉक्टर का पर्चा ऑनलाइन बुक करें",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF78350F)
                    )

                    Text(
                        text = "कैटलॉग से चुनकर या पर्चे की फोटो अपलोड करके आर्डर करें। केंद्र पर बिना लाइन लगाए 30 मिनट में पैक होकर तैयार मिलेगा!",
                        fontSize = 12.sp,
                        color = Color(0xFF92400E),
                        modifier = Modifier.padding(top = 2.dp)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = onNavigateToBooking,
                            colors = ButtonDefaults.buttonColors(containerColor = JanAushadhiOrange, contentColor = Color.White),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.weight(1f).testTag("home_book_medicine_btn")
                        ) {
                            Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = null, modifier = Modifier.size(15.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("दवाई बुक करें", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }

                        OutlinedButton(
                            onClick = onNavigateToBooking,
                            shape = RoundedCornerShape(8.dp),
                            border = BorderStroke(1.dp, JanAushadhiOrange),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = JanAushadhiOrange),
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(imageVector = Icons.Default.Receipt, contentDescription = null, modifier = Modifier.size(15.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("पर्चा भेजें", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }

        // 3. Category Quick Filters
        item {
            Column(modifier = Modifier.padding(top = 8.dp)) {
                Text(
                    text = "दवाइयों की श्रेणियाँ (Categories)",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                )
                CategoryChipsRow(
                    selectedCategory = uiState.selectedCategory,
                    onCategorySelected = { category ->
                        viewModel.selectCategory(category)
                        onNavigateToMedicines()
                    }
                )
            }
        }

        // 4. Suvidha Sanitary Napkin Banner (Requirement 10)
        item {
            SuvidhaNapkinHighlightCard(
                onExploreCategory = {
                    viewModel.selectCategory(ProductCategory.SANITARY_NAPKINS)
                    onNavigateToMedicines()
                },
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }

        // 5. Featured Medicines Section Header
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "लोकप्रिय व जरूरी दवाइयाँ",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "डायबिटीज, बीपी, थायराइड व विटामिन्स",
                        fontSize = 12.sp,
                        color = Color(0xFF64748B)
                    )
                }

                TextButton(
                    onClick = onNavigateToMedicines,
                    colors = ButtonDefaults.textButtonColors(contentColor = JanAushadhiBluePrimary)
                ) {
                    Text("सभी देखें", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.size(4.dp))
                    Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null, modifier = Modifier.size(16.dp))
                }
            }
        }

        // Featured Medicine Cards
        items(featuredMedicines) { medicine ->
            Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)) {
                MedicineCard(
                    medicine = medicine,
                    isSaved = viewModel.isMedicineSaved(medicine.id),
                    onToggleSave = { viewModel.toggleMedicineInSavedList(medicine) },
                    onBookNow = {
                        viewModel.addToCart(medicine, 1)
                        onNavigateToBooking()
                    },
                    onClick = { onSelectMedicine(medicine) }
                )
            }
        }

        // 6. Store Timings Card (Requirement 15)
        item {
            Spacer(modifier = Modifier.height(10.dp))
            StoreTimingSection(modifier = Modifier.padding(horizontal = 16.dp))
        }

        // 7. Google Maps Location Section (Requirement 12)
        item {
            Spacer(modifier = Modifier.height(14.dp))
            GoogleMapsLocationSection(modifier = Modifier.padding(horizontal = 16.dp))
        }

        // 8. Proprietor Section (प्रो. धीरज वर्मा)
        item {
            Spacer(modifier = Modifier.height(14.dp))
            ProprietorSection(modifier = Modifier.padding(horizontal = 16.dp))
        }

        // 9. FAQs Section (Requirement 22)
        item {
            Spacer(modifier = Modifier.height(16.dp))
            FaqSection(
                faqs = viewModel.faqs,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        // 10. Statutory Medical Disclaimer Card (Prescription Note)
        item {
            Spacer(modifier = Modifier.height(16.dp))
            MedicalDisclaimerCard(modifier = Modifier.padding(horizontal = 16.dp))
        }

        // 11. Complete Footer with Store Details
        item {
            Spacer(modifier = Modifier.height(24.dp))
            StoreFooterSection()
        }
    }
}
