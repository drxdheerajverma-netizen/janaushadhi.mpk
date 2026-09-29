package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.LocalPharmacy
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.MainCategoryFilter
import com.example.model.Medicine
import com.example.model.ProductCategory
import com.example.ui.components.CategoryChipsRow
import com.example.ui.components.MainCategoryFilterTabs
import com.example.ui.components.MedicalDisclaimerCard
import com.example.ui.components.MedicineCard
import com.example.ui.components.StoreFooterSection
import com.example.ui.theme.JanAushadhiBlueDark
import com.example.ui.theme.JanAushadhiBlueLight
import com.example.ui.theme.JanAushadhiBluePrimary
import com.example.ui.theme.JanAushadhiOrange
import com.example.viewmodel.PharmacyUiState
import com.example.viewmodel.PharmacyViewModel

@Composable
fun MedicinesScreen(
    viewModel: PharmacyViewModel,
    uiState: PharmacyUiState,
    onBookMedicine: (Medicine) -> Unit,
    onSelectMedicine: (Medicine) -> Unit
) {
    val quickSearchTags = listOf(
        "Paracetamol",
        "Metformin",
        "Telmisartan",
        "Pantoprazole",
        "Azithromycin",
        "सुविधा पैड",
        "विटामिन D3",
        "ग्लूकोमीटर",
        "Amlodipine"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .testTag("medicines_screen")
    ) {
        // ==========================================
        // 1. PINNED TOP SEARCH BAR & HEADER
        // ==========================================
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = Color.White,
            shadowElevation = 4.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 8.dp)
            ) {
                // Search Input Field
                Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                    OutlinedTextField(
                        value = uiState.searchQuery,
                        onValueChange = { viewModel.onSearchQueryChange(it) },
                        placeholder = {
                            Text(
                                text = "दवा, साल्ट या कोड लिखें (उदा: Paracetamol, 34, BP)...",
                                fontSize = 13.sp,
                                color = Color(0xFF64748B)
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search icon",
                                tint = JanAushadhiBluePrimary,
                                modifier = Modifier.size(22.dp)
                            )
                        },
                        trailingIcon = {
                            if (uiState.searchQuery.isNotEmpty()) {
                                IconButton(
                                    onClick = { viewModel.clearSearch() },
                                    modifier = Modifier.testTag("clear_search_btn")
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Clear,
                                        contentDescription = "Clear search query",
                                        tint = Color(0xFF64748B),
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            }
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(14.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFFF8FAFC),
                            unfocusedContainerColor = Color(0xFFF8FAFC),
                            focusedBorderColor = JanAushadhiBluePrimary,
                            unfocusedBorderColor = Color(0xFFE2E8F0)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("medicines_search_input")
                    )
                }

                // Quick Popular Search Suggestions Chips
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(horizontal = 16.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "त्वरित खोज:",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF64748B)
                    )

                    quickSearchTags.forEach { tag ->
                        val isCurrentQuery = uiState.searchQuery.equals(tag, ignoreCase = true)
                        Surface(
                            shape = RoundedCornerShape(50.dp),
                            color = if (isCurrentQuery) JanAushadhiBlueLight else Color(0xFFF1F5F9),
                            modifier = Modifier
                                .clip(RoundedCornerShape(50.dp))
                                .clickable {
                                    if (isCurrentQuery) {
                                        viewModel.clearSearch()
                                    } else {
                                        viewModel.onSearchQueryChange(tag)
                                    }
                                }
                        ) {
                            Text(
                                text = tag,
                                fontSize = 11.sp,
                                fontWeight = if (isCurrentQuery) FontWeight.Bold else FontWeight.Medium,
                                color = if (isCurrentQuery) JanAushadhiBluePrimary else Color(0xFF334155),
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                            )
                        }
                    }
                }

                // High-Level Category Filter (Generic, Surgical, Supplements, etc.)
                MainCategoryFilterTabs(
                    selectedFilter = uiState.selectedMainFilter,
                    onFilterSelected = { viewModel.selectMainFilter(it) },
                    modifier = Modifier.padding(top = 2.dp)
                )

                // Sub-Category Chips Selector
                CategoryChipsRow(
                    selectedCategory = uiState.selectedCategory,
                    onCategorySelected = { viewModel.selectCategory(it) },
                    mainFilter = uiState.selectedMainFilter,
                    modifier = Modifier.padding(top = 2.dp)
                )

                // Results Counter & Active Filter Badge
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = if (uiState.selectedCategory != ProductCategory.ALL) {
                                uiState.selectedCategory.titleHindi
                            } else {
                                uiState.selectedMainFilter.titleHindi
                            },
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        if (uiState.searchQuery.isNotEmpty()) {
                            Text(
                                text = " • खोज: \"${uiState.searchQuery}\"",
                                fontSize = 12.sp,
                                color = JanAushadhiOrange,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                    Surface(
                        shape = RoundedCornerShape(50.dp),
                        color = JanAushadhiBlueLight
                    ) {
                        Text(
                            text = "${uiState.filteredMedicines.size} उत्पाद उपलब्ध",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = JanAushadhiBlueDark,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }
                }
            }
        }

        // ==========================================
        // 2. SCROLLABLE MEDICINE LIST
        // ==========================================
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(top = 6.dp, bottom = 80.dp)
        ) {
            // Empty State
            if (uiState.filteredMedicines.isEmpty()) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFF1F5F9)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.SearchOff,
                                contentDescription = null,
                                tint = Color.Gray,
                                modifier = Modifier.size(36.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Text(
                            text = "कोई दवा नहीं मिली",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Text(
                            text = if (uiState.searchQuery.isNotEmpty()) {
                                "\"${uiState.searchQuery}\" के नाम या साल्ट से कोई दवा नहीं मिली। आप वर्तनी (spelling) जांचें अथवा सीधे पर्चा अपलोड करके या प्रो. धीरज वर्मा (+91 93050 72480) से व्हाट्सएप पर उपलब्धता जान सकते हैं।"
                            } else {
                                "इस श्रेणी में अभी कोई उत्पाद चयनित नहीं है।"
                            },
                            fontSize = 12.sp,
                            color = Color(0xFF64748B),
                            modifier = Modifier.padding(top = 6.dp, bottom = 16.dp)
                        )

                        Button(
                            onClick = {
                                viewModel.clearSearch()
                                viewModel.selectMainFilter(MainCategoryFilter.ALL)
                                viewModel.selectCategory(ProductCategory.ALL)
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = JanAushadhiBluePrimary)
                        ) {
                            Icon(
                                imageVector = Icons.Default.LocalPharmacy,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("सभी दवाइयाँ देखें (Reset Filter)")
                        }
                    }
                }
            } else {
                // Medicine Cards
                items(uiState.filteredMedicines, key = { it.id }) { medicine ->
                    Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 5.dp)) {
                        MedicineCard(
                            medicine = medicine,
                            isSaved = viewModel.isMedicineSaved(medicine.id),
                            onToggleSave = { viewModel.toggleMedicineInSavedList(medicine) },
                            onBookNow = { onBookMedicine(medicine) },
                            onClick = { onSelectMedicine(medicine) }
                        )
                    }
                }
            }

            // Medical Disclaimer
            item {
                Spacer(modifier = Modifier.height(14.dp))
                MedicalDisclaimerCard(modifier = Modifier.padding(horizontal = 16.dp))
            }

            // Store Footer
            item {
                Spacer(modifier = Modifier.height(20.dp))
                StoreFooterSection()
            }
        }
    }
}
