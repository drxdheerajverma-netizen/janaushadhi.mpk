package com.example.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.Biotech
import androidx.compose.material.icons.filled.Bloodtype
import androidx.compose.material.icons.filled.ChildCare
import androidx.compose.material.icons.filled.CleanHands
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.Healing
import androidx.compose.material.icons.filled.HealthAndSafety
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
import com.example.model.ProductCategory
import com.example.ui.theme.JanAushadhiBlueDark
import com.example.ui.theme.JanAushadhiBlueLight
import com.example.ui.theme.JanAushadhiBluePrimary

@Composable
fun CategoryChipsRow(
    selectedCategory: ProductCategory,
    onCategorySelected: (ProductCategory) -> Unit,
    mainFilter: MainCategoryFilter = MainCategoryFilter.ALL,
    modifier: Modifier = Modifier
) {
    val visibleCategories = ProductCategory.values().filter { cat ->
        if (mainFilter == MainCategoryFilter.ALL) true
        else cat == ProductCategory.ALL || mainFilter.matches(cat)
    }

    if (visibleCategories.size <= 2 && mainFilter != MainCategoryFilter.ALL) {
        return
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        visibleCategories.forEach { category ->
            val isSelected = category == selectedCategory

            val icon: ImageVector = when (category) {
                ProductCategory.ALL -> Icons.Default.Medication
                ProductCategory.DIABETES -> Icons.Default.Bloodtype
                ProductCategory.BP_HEART -> Icons.Default.Favorite
                ProductCategory.ANTIBIOTICS -> Icons.Default.Biotech
                ProductCategory.GASTRO_ACIDITY -> Icons.Default.LocalPharmacy
                ProductCategory.RESPIRATORY -> Icons.Default.Air
                ProductCategory.THYROID -> Icons.Default.HealthAndSafety
                ProductCategory.VITAMINS -> Icons.Default.Spa
                ProductCategory.SKIN_DERMA -> Icons.Default.Healing
                ProductCategory.WOMEN_CARE -> Icons.Default.Female
                ProductCategory.CHILD_CARE -> Icons.Default.ChildCare
                ProductCategory.SANITARY_NAPKINS -> Icons.Default.CleanHands
                ProductCategory.SURGICAL_DEVICES -> Icons.Default.MedicalServices
                ProductCategory.GENERAL_MEDS -> Icons.Default.Healing
            }

            Surface(
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .clickable { onCategorySelected(category) }
                    .testTag("category_chip_${category.id}"),
                shape = RoundedCornerShape(50.dp),
                color = if (isSelected) JanAushadhiBluePrimary else Color(0xFFF1F5F9),
                border = if (isSelected) null else androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFCBD5E1))
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 7.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = if (isSelected) Color.White else JanAushadhiBlueDark,
                        modifier = Modifier.size(15.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = category.titleHindi,
                        fontSize = 12.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        color = if (isSelected) Color.White else Color(0xFF1E293B)
                    )
                }
            }
        }
    }
}
