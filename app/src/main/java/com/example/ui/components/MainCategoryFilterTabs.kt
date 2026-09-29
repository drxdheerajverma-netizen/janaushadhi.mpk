package com.example.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.LocalPharmacy
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.MainCategoryFilter
import com.example.ui.theme.JanAushadhiBlueDark
import com.example.ui.theme.JanAushadhiBluePrimary
import com.example.ui.theme.JanAushadhiGreen

@Composable
fun MainCategoryFilterTabs(
    selectedFilter: MainCategoryFilter,
    onFilterSelected: (MainCategoryFilter) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            MainCategoryFilter.values().forEach { filter ->
                val isSelected = filter == selectedFilter

                val icon: ImageVector = when (filter) {
                    MainCategoryFilter.ALL -> Icons.Default.Medication
                    MainCategoryFilter.GENERIC -> Icons.Default.LocalPharmacy
                    MainCategoryFilter.SURGICAL -> Icons.Default.MedicalServices
                    MainCategoryFilter.SUPPLEMENTS -> Icons.Default.Spa
                    MainCategoryFilter.WOMEN_CHILD -> Icons.Default.Female
                }

                val activeColor = when (filter) {
                    MainCategoryFilter.SURGICAL -> Color(0xFF0284C7)
                    MainCategoryFilter.SUPPLEMENTS -> JanAushadhiGreen
                    MainCategoryFilter.WOMEN_CHILD -> Color(0xFFD946EF)
                    MainCategoryFilter.GENERIC -> JanAushadhiBluePrimary
                    MainCategoryFilter.ALL -> JanAushadhiBlueDark
                }

                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = if (isSelected) activeColor else Color(0xFFF8FAFC),
                    border = if (isSelected) null else androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFCBD5E1)),
                    shadowElevation = if (isSelected) 3.dp else 0.dp,
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .clickable { onFilterSelected(filter) }
                        .testTag("main_category_${filter.id}")
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = if (isSelected) Color.White else activeColor,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = filter.titleHindi,
                                fontSize = 13.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.SemiBold,
                                color = if (isSelected) Color.White else Color(0xFF1E293B)
                            )
                            Text(
                                text = filter.titleEnglish,
                                fontSize = 10.sp,
                                color = if (isSelected) Color.White.copy(alpha = 0.85f) else Color(0xFF64748B)
                            )
                        }
                    }
                }
            }
        }
    }
}
