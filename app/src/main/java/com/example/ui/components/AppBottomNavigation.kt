package com.example.ui.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.JanAushadhiBlueDark
import com.example.ui.theme.JanAushadhiBlueLight
import com.example.ui.theme.JanAushadhiBluePrimary
import com.example.ui.theme.JanAushadhiGreen
import com.example.ui.theme.JanAushadhiOrange
import com.example.viewmodel.AppNavDestination

@Composable
fun AppBottomNavigation(
    currentDestination: AppNavDestination,
    onNavigate: (AppNavDestination) -> Unit,
    cartItemCount: Int = 0,
    savedMedicinesCount: Int = 0
) {
    NavigationBar(
        containerColor = Color.White,
        contentColor = JanAushadhiBlueDark,
        tonalElevation = 8.dp,
        windowInsets = WindowInsets.navigationBars
    ) {
        // 1. Home
        NavigationBarItem(
            selected = currentDestination == AppNavDestination.HOME,
            onClick = { onNavigate(AppNavDestination.HOME) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home",
                    modifier = Modifier.size(20.dp)
                )
            },
            label = {
                Text(
                    text = "होम",
                    fontSize = 10.sp,
                    fontWeight = if (currentDestination == AppNavDestination.HOME) FontWeight.Bold else FontWeight.Normal
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = JanAushadhiBluePrimary,
                selectedTextColor = JanAushadhiBluePrimary,
                indicatorColor = JanAushadhiBlueLight,
                unselectedIconColor = Color(0xFF64748B),
                unselectedTextColor = Color(0xFF64748B)
            ),
            modifier = Modifier.testTag("nav_home")
        )

        // 2. Medicines
        NavigationBarItem(
            selected = currentDestination == AppNavDestination.MEDICINES,
            onClick = { onNavigate(AppNavDestination.MEDICINES) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Medication,
                    contentDescription = "Medicines",
                    modifier = Modifier.size(20.dp)
                )
            },
            label = {
                Text(
                    text = "दवाइयाँ",
                    fontSize = 10.sp,
                    fontWeight = if (currentDestination == AppNavDestination.MEDICINES) FontWeight.Bold else FontWeight.Normal
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = JanAushadhiBluePrimary,
                selectedTextColor = JanAushadhiBluePrimary,
                indicatorColor = JanAushadhiBlueLight,
                unselectedIconColor = Color(0xFF64748B),
                unselectedTextColor = Color(0xFF64748B)
            ),
            modifier = Modifier.testTag("nav_medicines")
        )

        // 3. Booking (NEW FEATURE)
        NavigationBarItem(
            selected = currentDestination == AppNavDestination.BOOKING,
            onClick = { onNavigate(AppNavDestination.BOOKING) },
            icon = {
                if (cartItemCount > 0) {
                    BadgedBox(
                        badge = {
                            Badge(
                                containerColor = JanAushadhiOrange,
                                contentColor = Color.White
                            ) {
                                Text("$cartItemCount")
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "Booking",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                } else {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Booking",
                        modifier = Modifier.size(20.dp)
                    )
                }
            },
            label = {
                Text(
                    text = "बुकिंग",
                    fontSize = 10.sp,
                    fontWeight = if (currentDestination == AppNavDestination.BOOKING) FontWeight.Bold else FontWeight.Normal
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = JanAushadhiOrange,
                selectedTextColor = JanAushadhiOrange,
                indicatorColor = Color(0xFFFFF3E0),
                unselectedIconColor = Color(0xFF64748B),
                unselectedTextColor = Color(0xFF64748B)
            ),
            modifier = Modifier.testTag("nav_booking")
        )

        // 4. Savings Calculator
        NavigationBarItem(
            selected = currentDestination == AppNavDestination.SAVINGS,
            onClick = { onNavigate(AppNavDestination.SAVINGS) },
            icon = {
                if (savedMedicinesCount > 0) {
                    BadgedBox(
                        badge = {
                            Badge(
                                containerColor = JanAushadhiGreen,
                                contentColor = Color.White
                            ) {
                                Text("$savedMedicinesCount")
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Savings,
                            contentDescription = "Savings",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                } else {
                    Icon(
                        imageVector = Icons.Default.Savings,
                        contentDescription = "Savings",
                        modifier = Modifier.size(20.dp)
                    )
                }
            },
            label = {
                Text(
                    text = "बचत",
                    fontSize = 10.sp,
                    fontWeight = if (currentDestination == AppNavDestination.SAVINGS) FontWeight.Bold else FontWeight.Normal
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = JanAushadhiGreen,
                selectedTextColor = JanAushadhiGreen,
                indicatorColor = Color(0xFFE8F5E9),
                unselectedIconColor = Color(0xFF64748B),
                unselectedTextColor = Color(0xFF64748B)
            ),
            modifier = Modifier.testTag("nav_savings")
        )

        // 5. Contact
        NavigationBarItem(
            selected = currentDestination == AppNavDestination.CONTACT,
            onClick = { onNavigate(AppNavDestination.CONTACT) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = "Contact",
                    modifier = Modifier.size(20.dp)
                )
            },
            label = {
                Text(
                    text = "संपर्क",
                    fontSize = 10.sp,
                    fontWeight = if (currentDestination == AppNavDestination.CONTACT) FontWeight.Bold else FontWeight.Normal
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = JanAushadhiBluePrimary,
                selectedTextColor = JanAushadhiBluePrimary,
                indicatorColor = JanAushadhiBlueLight,
                unselectedIconColor = Color(0xFF64748B),
                unselectedTextColor = Color(0xFF64748B)
            ),
            modifier = Modifier.testTag("nav_contact")
        )
    }
}
