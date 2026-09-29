package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.ui.components.AppBottomNavigation
import com.example.ui.components.BookingSuccessDialog
import com.example.ui.components.MedicineDetailDialog
import com.example.ui.components.PharmacyTopAppBar
import com.example.ui.screens.AboutScreen
import com.example.ui.screens.BookingScreen
import com.example.ui.screens.ContactScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.MedicinesScreen
import com.example.ui.screens.SavingsCalculatorScreen
import com.example.ui.theme.MyApplicationTheme
import com.example.viewmodel.AppNavDestination
import com.example.viewmodel.PharmacyViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: PharmacyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MyApplicationTheme {
                MainAppContent(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun MainAppContent(viewModel: PharmacyViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    // Handle back button when not on Home screen
    if (uiState.currentDestination != AppNavDestination.HOME) {
        BackHandler {
            viewModel.navigateTo(AppNavDestination.HOME)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = {
            PharmacyTopAppBar(
                onSearchClick = {
                    viewModel.navigateTo(AppNavDestination.MEDICINES)
                },
                savedItemsCount = uiState.savedMedicinesList.size,
                onSavingsClick = {
                    viewModel.navigateTo(AppNavDestination.SAVINGS)
                }
            )
        },
        bottomBar = {
            AppBottomNavigation(
                currentDestination = uiState.currentDestination,
                onNavigate = { destination ->
                    viewModel.navigateTo(destination)
                },
                cartItemCount = uiState.cartItemCount,
                savedMedicinesCount = uiState.savedMedicinesList.size
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF8FAFC))
                .padding(innerPadding),
            contentAlignment = Alignment.TopCenter
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .widthIn(max = 680.dp) // Responsive tablet & large screen optimization
            ) {
                AnimatedContent(
                    targetState = uiState.currentDestination,
                    transitionSpec = { fadeIn() togetherWith fadeOut() },
                    label = "ScreenTransition"
                ) { targetDestination ->
                    when (targetDestination) {
                        AppNavDestination.HOME -> {
                            HomeScreen(
                                viewModel = viewModel,
                                uiState = uiState,
                                onNavigateToMedicines = { viewModel.navigateTo(AppNavDestination.MEDICINES) },
                                onNavigateToBooking = { viewModel.navigateTo(AppNavDestination.BOOKING) },
                                onNavigateToSavings = { viewModel.navigateTo(AppNavDestination.SAVINGS) },
                                onSelectMedicine = { med -> viewModel.openMedicineDetail(med) }
                            )
                        }

                        AppNavDestination.MEDICINES -> {
                            MedicinesScreen(
                                viewModel = viewModel,
                                uiState = uiState,
                                onBookMedicine = { med ->
                                    viewModel.addToCart(med, 1)
                                    viewModel.navigateTo(AppNavDestination.BOOKING)
                                },
                                onSelectMedicine = { med -> viewModel.openMedicineDetail(med) }
                            )
                        }

                        AppNavDestination.BOOKING -> {
                            BookingScreen(
                                viewModel = viewModel,
                                uiState = uiState,
                                onNavigateToMedicines = { viewModel.navigateTo(AppNavDestination.MEDICINES) }
                            )
                        }

                        AppNavDestination.SAVINGS -> {
                            SavingsCalculatorScreen(
                                viewModel = viewModel,
                                uiState = uiState,
                                onNavigateToMedicines = { viewModel.navigateTo(AppNavDestination.MEDICINES) },
                                onNavigateToBooking = { viewModel.navigateTo(AppNavDestination.BOOKING) }
                            )
                        }

                        AppNavDestination.ABOUT -> {
                            AboutScreen()
                        }

                        AppNavDestination.CONTACT -> {
                            ContactScreen()
                        }
                    }
                }
            }
        }

        // Show Medicine Detail Dialog if a medicine is selected
        uiState.selectedMedicineForDetail?.let { medicine ->
            MedicineDetailDialog(
                medicine = medicine,
                isSaved = viewModel.isMedicineSaved(medicine.id),
                onToggleSave = { viewModel.toggleMedicineInSavedList(medicine) },
                onBookNow = {
                    viewModel.addToCart(medicine, 1)
                    viewModel.navigateTo(AppNavDestination.BOOKING)
                },
                onDismiss = { viewModel.openMedicineDetail(null) }
            )
        }

        // Show Booking Confirmation Dialog if booking just succeeded
        uiState.bookingSuccessReceipt?.let { receipt ->
            val msg = viewModel.generateWhatsAppBookingReceipt(receipt)
            BookingSuccessDialog(
                booking = receipt,
                whatsappMessage = msg,
                onDismiss = { viewModel.dismissBookingSuccess() }
            )
        }
    }
}
