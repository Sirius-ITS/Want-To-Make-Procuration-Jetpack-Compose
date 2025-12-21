package com.informatique.tawsekmisr.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.AccountBalance
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.Category
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Description
import androidx.compose.material.icons.rounded.Error
import androidx.compose.material.icons.rounded.EventBusy
import androidx.compose.material.icons.rounded.Schedule
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.informatique.tawsekmisr.R
import com.informatique.tawsekmisr.data.model.InquireReservation
import com.informatique.tawsekmisr.ui.components.CommonButton
import com.informatique.tawsekmisr.ui.components.InquiryTextField
import com.informatique.tawsekmisr.ui.components.localizedApp
import com.informatique.tawsekmisr.ui.components.localizedPluralsApp
import com.informatique.tawsekmisr.ui.theme.LocalExtraColors
import com.informatique.tawsekmisr.ui.viewmodels.BookingInquiryViewModel
import com.informatique.tawsekmisr.ui.viewmodels.NationalIdValidationState
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingInquiryScreen(
    navController: NavController,
    viewModel: BookingInquiryViewModel = hiltViewModel()
) {
    val extraColors = LocalExtraColors.current

    // Collect ViewModel states
    val nationalId by viewModel.nationalId.collectAsState()
    val nationalIdError by viewModel.nationalIdError.collectAsState()
    val nationalIdValidation by viewModel.nationalIdValidation.collectAsState()
    val filteredReservations by viewModel.filteredReservations.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()
    val hasPerformedInquiry by viewModel.hasPerformedInquiry.collectAsState()

    Scaffold { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(extraColors.backgroundGradient)
                .padding(paddingValues)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))

                Surface(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .clickable { navController.navigateUp() },
                    color = extraColors.iconLightBackground
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = "Back",
                        tint = extraColors.white,
                        modifier = Modifier.padding(12.dp)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = localizedApp(R.string.service_booking_inquiry),
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = extraColors.textBlue,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))

                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    border = BorderStroke(1.dp, extraColors.textGray.copy(alpha = 0.5f)),
                    color = extraColors.cardBackground,
                    shadowElevation = 0.dp
                ) {
                    Row(
                        modifier = Modifier.padding(20.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Surface(
                            modifier = Modifier.size(50.dp),
                            shape = CircleShape,
                            color = extraColors.iconDarkBlue
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Search,
                                contentDescription = null,
                                tint = extraColors.cardBackground,
                                modifier = Modifier.padding(12.dp)
                            )
                        }

                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = localizedApp(R.string.quick_inquiry_title),
                                fontSize = 18.sp,
                                color = extraColors.textBlue
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = localizedApp(R.string.quick_inquiry_desc),
                                fontSize = 13.sp,
                                color = extraColors.textDarkGray,
                                lineHeight = 18.sp
                            )
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))

                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    border = BorderStroke(1.dp, extraColors.textGray.copy(alpha = 0.5f)),
                    color = extraColors.cardBackground,
                    shadowElevation = 0.dp
                ) {

                    Column(
                        modifier = Modifier.padding(20.dp)
                    ){
                        Text(
                            text = localizedApp(R.string.reservation_national_id),
                            fontSize = 14.sp,
                            color = extraColors.textBlue,
                            fontWeight = FontWeight.Medium
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // National ID Field
                        InquiryTextField(
                            value = nationalId,
                            onValueChange = { viewModel.updateNationalId(it) },
                            label = localizedApp(R.string.reservation_national_id),
                            isNumeric = true,
                            placeholder = localizedApp(R.string.enter_national_id),
                            error = nationalIdError,
                            mandatory = true
                        )

                        // Show validation status
                        if (nationalIdValidation is NationalIdValidationState.Loading) {
                            Row(
                                modifier = Modifier.padding(top = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(16.dp),
                                    strokeWidth = 2.dp,
                                    color = extraColors.accent
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = localizedApp(R.string.national_id_checker),
                                    fontSize = 12.sp,
                                    color = extraColors.textGray
                                )
                            }
                        } else if (nationalIdValidation is NationalIdValidationState.Valid) {
                            Row(
                                modifier = Modifier.padding(top = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.CheckCircle,
                                    contentDescription = "Valid",
                                    tint = extraColors.green,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = localizedApp(R.string.national_id_valid),
                                    fontSize = 12.sp,
                                    color = extraColors.green
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(18.dp))

                        // Inquiry Button
                        CommonButton(
                            text = localizedApp(R.string.booking_inquiry_btn),
                            backgroundColor = if (isLoading || nationalIdValidation !is NationalIdValidationState.Valid) extraColors.textDarkGray else extraColors.iconDarkBlue,
                            enabled = !isLoading || nationalIdValidation is NationalIdValidationState.Valid,
                            onClick = { if (!isLoading || nationalIdValidation is NationalIdValidationState.Valid) viewModel.fetchReservations() }
                        )
                    }

                }

            }

            // Loading indicator
            if (isLoading) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = extraColors.iconDarkBlue,
                        )
                    }
                }
            }

            // Error message
            if (error != null) {
                item {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        color = Color(244, 67, 54).copy(alpha = 0.1f)
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Error,
                                contentDescription = null,
                                tint = Color(244, 67, 54),
                                modifier = Modifier.size(24.dp)
                            )
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "خطأ",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(244, 67, 54)
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = error ?: "حدث خطأ غير متوقع",
                                    fontSize = 12.sp,
                                    color = Color(126, 126, 133)
                                )
                            }
                        }
                    }
                }
            }

            // Reservations List
            if (filteredReservations.isNotEmpty()) {
                item {
                    Text(
                        text = localizedPluralsApp(R.plurals.reservations_count,
                            filteredReservations.size, filteredReservations.size),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = extraColors.textBlue,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                items(filteredReservations) { reservation ->
                    ReservationCard(reservation = reservation)
                }
            } else if (!isLoading && error == null && hasPerformedInquiry) {
                // Show empty state only after inquiry button is clicked
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.EventBusy,
                                contentDescription = null,
                                tint = extraColors.gold,
                                modifier = Modifier.size(64.dp)
                            )
                            Text(
                                text = localizedApp(R.string.no_reservations_found),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = extraColors.textDarkGray
                            )
                            Text(
                                text = localizedApp(R.string.no_reservations_for_id),
                                fontSize = 12.sp,
                                color = extraColors.textGray
                            )
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
private fun ReservationCard(reservation: InquireReservation) {
    // Parse and format date
    val formattedDate = remember(reservation.reservationDate) {
        try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S", Locale.ENGLISH)
            val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val date = inputFormat.parse(reservation.reservationDate)
            date?.let { outputFormat.format(it) } ?: reservation.reservationDate
        } catch (e: Exception) {
            reservation.reservationDate
        }
    }

    // Format time based on vipFlag
    val formattedTime = remember(reservation.reserveTime, reservation.orgVipFlag) {
        when (reservation.orgVipFlag) {
            "1", "4" -> {
                // Format: "2023-09-18 12:00:00.0" -> "12:00"
                try {
                    val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S", Locale.ENGLISH)
                    val outputFormat = SimpleDateFormat("hh:mm a", Locale.ENGLISH)
                    val time = inputFormat.parse(reservation.reserveTime)
                    time?.let { outputFormat.format(it) } ?: reservation.reserveTime
                } catch (e: Exception) {
                    reservation.reserveTime
                }
            }
            "2", "3" -> {
                // Already formatted as period: "13:30-16:00"
                reservation.reserveTime
            }
            else -> reservation.reserveTime
        }
    }

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = LocalExtraColors.current.cardBackground,
        shadowElevation = 0.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Header with office name
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = reservation.orgName,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = LocalExtraColors.current.textBlue,
                    modifier = Modifier.weight(1f, fill = false)
                )

            }

            Spacer(modifier = Modifier.height(12.dp))

            // Transaction Category
            InfoRow(
                icon = Icons.Rounded.Category,
                iconColor =LocalExtraColors.current.textBlue,
                // label = "التصنيف",
                value = reservation.transactionDesc
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Transaction Type
            InfoRow(
                icon = Icons.Rounded.Description,
                iconColor =LocalExtraColors.current.textBlue,
                // label = "نوع المعاملة",
                value = reservation.transactionTypeDesc
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Date and Time
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Date
                Box(modifier = Modifier.weight(1f)) {
                    InfoRow(
                        icon = Icons.Rounded.CalendarMonth,
                        iconColor =LocalExtraColors.current.textBlue,
                        // label = "التاريخ",
                        value = formattedDate
                    )
                }

                // Time
                Box(modifier = Modifier.weight(1f)) {
                    InfoRow(
                        icon = Icons.Rounded.Schedule,
                        iconColor =LocalExtraColors.current.textBlue,
                        // label = "الوقت",
                        value = formattedTime
                    )
                }
            }
        }
    }
}

@Composable
private fun InfoRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconColor: Color,
    value: String
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier.size(20.dp)/*.padding(6.dp)*/
        )

        Text(
            modifier = Modifier.weight(1f),
            text = value,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = LocalExtraColors.current.textDarkGray
        )
    }
}
