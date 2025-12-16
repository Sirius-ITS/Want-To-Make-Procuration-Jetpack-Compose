//package com.informatique.tawsekmisr.ui.screens
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.rounded.Check
//import androidx.compose.material3.CircularProgressIndicator
//import androidx.compose.material3.Icon
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.navigation.NavController
//import com.informatique.tawsekmisr.ui.components.localizedApp
//import com.informatique.tawsekmisr.ui.theme.LocalExtraColors
//import com.informatique.tawsekmisr.ui.theme.ThemeOption
//import com.informatique.tawsekmisr.ui.viewmodels.LanguageViewModel
//import com.informatique.tawsekmisr.ui.viewmodels.SharedUserViewModel
//import com.informatique.tawsekmisr.viewmodel.ThemeViewModel
//import com.informatique.tawsekmisr.R
//
//@Composable
//fun SettingsScreen(
//    viewModel: ThemeViewModel,
//    navController: NavController,
//    sharedUserViewModel: SharedUserViewModel,
//    languageViewModel: LanguageViewModel = hiltViewModel()
//) {
//    val extraColors = LocalExtraColors.current
//    val theme by viewModel.theme.collectAsState()
//    val currentLanguage by languageViewModel.languageFlow.collectAsState(initial = "ar")
//    val isLoading = languageViewModel.isLoading.value
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(extraColors.background)
//            .padding(top = 40.dp)
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .verticalScroll(rememberScrollState())
//                .padding(horizontal = 20.dp)
//        ) {
//            // Top Bar with Title centered and Done Button
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(bottom = 40.dp)
//            ) {
//                // Title centered
//                Text(
//                    text = localizedApp(R.string.settings_title),
//                    fontSize = 22.sp,
//                    color = extraColors.textBlue,
//                    modifier = Modifier.align(Alignment.Center)
//                )
//
//                // Done Button (will be on right in LTR, left in RTL)
//                Box(
//                    modifier = Modifier
//                        .size(56.dp)
//                        .background(extraColors.cardBackground, CircleShape)
//                        .border(1.5.dp, extraColors.iconLightBackground, CircleShape)
//                        .clickable { navController.popBackStack() },
//                    contentAlignment = Alignment.Center
//                ) {
//                    Text(
//                        text = localizedApp(R.string.done),
//                        fontSize = 16.sp,
//                        fontWeight = FontWeight.SemiBold,
//                        color = extraColors.textBlue
//                    )
//                }
//            }
//
//            // Language Section Title
//            Text(
//                text = localizedApp(R.string.settings_language_section),
//                fontSize = 20.sp,
//                color = extraColors.textBlue,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(bottom = 20.dp)
//            )
//
////            // English Language Card
////            LanguageCardEnhanced(
////                languageName = "English",
////                flagEmoji = "🇺🇸",
////                isSelected = currentLanguage == "en",
////                onClick = { languageViewModel.saveLanguage("en") }
////            )
////
////            Spacer(modifier = Modifier.height(16.dp))
//
//            // Arabic Language Card
//            LanguageCardEnhanced(
//                languageName = "العربية",
//                flagEmoji = "🇴🇲",
//                isSelected = currentLanguage == "ar",
//                onClick = { languageViewModel.saveLanguage("ar") }
//            )
//
//            Spacer(modifier = Modifier.height(60.dp))
//
//            // Theme Section Title
//            Text(
//                text = localizedApp(R.string.settings_theme_section),
//                fontSize = 20.sp,
//                color = extraColors.textBlue,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(bottom = 20.dp)
//            )
//
//            // Theme Cards Row
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.spacedBy(16.dp)
//            ) {
//                // Light Theme Card
//                ThemeCardEnhanced(
//                    themeIcon = "☀️",
//                    themeName = localizedApp(R.string.theme_light),
//                    isSelected = theme == ThemeOption.LIGHT,
//                    onClick = { viewModel.setTheme(ThemeOption.LIGHT) },
//                    modifier = Modifier.weight(1f)
//                )
//
//                // Dark Theme Card
//                ThemeCardEnhanced(
//                    themeIcon = "🌙",
//                    themeName = localizedApp(R.string.theme_dark),
//                    isSelected = theme == ThemeOption.DARK,
//                    onClick = { viewModel.setTheme(ThemeOption.DARK) },
//                    modifier = Modifier.weight(1f)
//                )
//            }
//
//            Spacer(modifier = Modifier.height(60.dp))
//
//            // About App Section Title
//            Text(
//                text = localizedApp(R.string.about_app),
//                fontSize = 20.sp,
//                color = extraColors.textBlue,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(bottom = 20.dp)
//            )
//
//            // Version Card
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(64.dp)
//                    .clip(RoundedCornerShape(16.dp))
//                    .background(extraColors.cardDarkBackground)
//                    .padding(horizontal = 24.dp),
//                contentAlignment = Alignment.Center
//            ) {
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceBetween,
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Text(
//                        text = localizedApp(R.string.version),
//                        fontSize = 16.sp,
//                        fontWeight = FontWeight.SemiBold,
//                        color = extraColors.textBlue
//                    )
//                    Text(
//                        text = "1.0",
//                        fontSize = 16.sp,
//                        fontWeight = FontWeight.Normal,
//                        color = extraColors.textGray
//                    )
//                }
//            }
//
//            Spacer(modifier = Modifier.height(40.dp))
//        }
//
//        if (isLoading) {
//            CircularProgressIndicator(
//                modifier = Modifier.align(Alignment.Center),
//                color = extraColors.accent
//            )
//        }
//    }
//}
//
//@Composable
//fun LanguageCardEnhanced(
//    languageName: String,
//    flagEmoji: String,
//    isSelected: Boolean,
//    onClick: () -> Unit
//) {
//    val extraColors = LocalExtraColors.current
//
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(68.dp)
//            .clip(RoundedCornerShape(16.dp))
//            .background(
//                if (isSelected)
//                    Color.Transparent
//                else
//                    extraColors.cardDarkBackground
//            )
//            .then(
//                if (isSelected)
//                    Modifier.border(2.dp, extraColors.accent, RoundedCornerShape(16.dp))
//                else
//                    Modifier
//            )
//            .clickable(onClick = onClick)
//            .padding(horizontal = 24.dp),
//        contentAlignment = Alignment.Center
//    ) {
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            // Language name and flag (will be on left in LTR, right in RTL)
//            Row(
//                horizontalArrangement = Arrangement.spacedBy(12.dp),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Text(
//                    text = languageName,
//                    fontSize = 17.sp,
//                    fontWeight = FontWeight.SemiBold,
//                    color = extraColors.textBlue
//                )
//                Text(
//                    text = flagEmoji,
//                    fontSize = 26.sp
//                )
//            }
//
//            // Checkmark (will be on right in LTR, left in RTL - only when selected)
//            if (isSelected) {
//                Icon(
//                    imageVector = Icons.Rounded.Check,
//                    contentDescription = "Selected",
//                    tint = extraColors.accent,
//                    modifier = Modifier.size(22.dp)
//                )
//            } else {
//                Spacer(modifier = Modifier.width(22.dp))
//            }
//        }
//    }
//}
//
//@Composable
//fun ThemeCardEnhanced(
//    themeIcon: String,
//    themeName: String,
//    isSelected: Boolean,
//    onClick: () -> Unit,
//    modifier: Modifier = Modifier
//) {
//    val extraColors = LocalExtraColors.current
//
//    Box(
//        modifier = modifier
//            .height(150.dp)
//            .clip(RoundedCornerShape(16.dp))
//            .background(Color.Transparent)
//            .then(
//                if (isSelected)
//                    Modifier.border(2.dp, extraColors.accent, RoundedCornerShape(16.dp)).background(extraColors.cardDarkBackground)
//                else
//                    Modifier.border(1.dp, extraColors.iconLightBackground, RoundedCornerShape(16.dp))
//            )
//            .clickable(onClick = onClick)
//            .padding(vertical = 20.dp),
//        contentAlignment = Alignment.Center
//    ) {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center,
//            modifier = Modifier.fillMaxSize()
//        ) {
//            // Theme Icon in Circle
//            Box(
//                modifier = Modifier
//                    .size(64.dp)
//                    .clip(CircleShape)
//                    .background(extraColors.iconLightBackground),
//                contentAlignment = Alignment.Center
//            ) {
//                Text(
//                    text = themeIcon,
//                    fontSize = 32.sp
//                )
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            Text(
//                text = themeName,
//                fontSize = 15.sp,
//                fontWeight = FontWeight.SemiBold,
//                color = extraColors.textBlue
//            )
//        }
//    }
//}


//package com.informatique.tawsekmisr.ui.screens
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.automirrored.rounded.ArrowBack
//import androidx.compose.material.icons.outlined.ArrowBackIosNew
//import androidx.compose.material.icons.rounded.Check
//import androidx.compose.material3.CircularProgressIndicator
//import androidx.compose.material3.Icon
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.navigation.NavController
//import com.informatique.tawsekmisr.ui.components.localizedApp
//import com.informatique.tawsekmisr.ui.theme.LocalExtraColors
//import com.informatique.tawsekmisr.ui.theme.ThemeOption
//import com.informatique.tawsekmisr.ui.viewmodels.LanguageViewModel
//import com.informatique.tawsekmisr.ui.viewmodels.SharedUserViewModel
//import com.informatique.tawsekmisr.viewmodel.ThemeViewModel
//import com.informatique.tawsekmisr.R
//
//@Composable
//fun SettingsScreen(
//    viewModel: ThemeViewModel,
//    navController: NavController,
//    sharedUserViewModel: SharedUserViewModel,
//    languageViewModel: LanguageViewModel = hiltViewModel()
//) {
//    val extraColors = LocalExtraColors.current
//    val theme by viewModel.theme.collectAsState()
//    val currentLanguage by languageViewModel.languageFlow.collectAsState(initial = "ar")
//    val isLoading = languageViewModel.isLoading.value
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(extraColors.background)
//            .padding(top = 40.dp)
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .verticalScroll(rememberScrollState())
//                .padding(horizontal = 20.dp)
//        ) {
//            // Top Bar with Title centered and Done Button
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(top=12.dp , bottom = 32.dp)
//            ) {
//                // Done Button (will be on right in LTR, left in RTL)
//                Box(
//                    modifier = Modifier
//                        .size(56.dp)
//                        .background(extraColors.doneButtonBackground, CircleShape)
//                        .border(1.dp, extraColors.doneButtonBorder, CircleShape)
//                        .clickable { navController.popBackStack() }
//                        .align(Alignment.CenterStart),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Text(
//                        text = localizedApp(R.string.done),
//                        fontSize = 15.sp,
//                        fontWeight = FontWeight.Medium,
//                        color = extraColors.doneButtonText
//                    )
//                }
//
//                // Title centered
//                Text(
//                    text = localizedApp(R.string.settings_title),
//                    fontSize = 20.sp,
//                    fontWeight = FontWeight.Medium,
//                    color = extraColors.textBlue,
//                    modifier = Modifier.align(Alignment.Center)
//                )
//
//                Box(
//                    modifier = Modifier
//                        .size(48.dp)
//                        .background(extraColors.cardBackground, CircleShape)
//                        .border(1.dp, extraColors.backButtonBorder, CircleShape)
//                        .clickable { navController.popBackStack() }
//                        .align(Alignment.CenterEnd),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Icon(
//                        imageVector = Icons.Outlined.ArrowBackIosNew,
//                        contentDescription = "Back",
//                        tint = extraColors.backButtonIcon,
//                        modifier = Modifier.size(20.dp)
//                    )
//                }
//            }
//            // Language Section Title
//            Text(
//                text = localizedApp(R.string.settings_language_section),
//                fontSize = 18.sp,
//                fontWeight = FontWeight.Medium,
//                color = extraColors.textBlue,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(bottom = 16.dp)
//            )
//
//            // Arabic Language Card
//            LanguageCardEnhanced(
//                languageName = "العربية",
//                flagEmoji = "🇪🇬",
//                isSelected = currentLanguage == "ar",
//                onClick = { languageViewModel.saveLanguage("ar") }
//            )
//
//            Spacer(modifier = Modifier.height(48.dp))
//
//            // Theme Section Title
//            Text(
//                text = localizedApp(R.string.settings_theme_section),
//                fontSize = 18.sp,
//                fontWeight = FontWeight.Medium,
//                color = extraColors.textBlue,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(bottom = 16.dp)
//            )
//
//            // Theme Cards Row
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.spacedBy(12.dp)
//            ) {
//                // Light Theme Card (Right in LTR, Left in RTL)
//                ThemeCardEnhanced(
//                    themeIcon = "☀️",
//                    themeName = localizedApp(R.string.theme_light),
//                    isSelected = theme == ThemeOption.LIGHT,
//                    onClick = { viewModel.setTheme(ThemeOption.LIGHT) },
//                    modifier = Modifier.weight(1f)
//                )
//                // Dark Theme Card (Left in LTR, Right in RTL)
//                ThemeCardEnhanced(
//                    themeIcon = "🌙",
//                    themeName = localizedApp(R.string.theme_dark),
//                    isSelected = theme == ThemeOption.DARK,
//                    onClick = { viewModel.setTheme(ThemeOption.DARK) },
//                    modifier = Modifier.weight(1f)
//                )
//            }
//
//            Spacer(modifier = Modifier.height(48.dp))
//
//            // About App Section Title
//            Text(
//                text = localizedApp(R.string.about_app),
//                fontSize = 18.sp,
//                fontWeight = FontWeight.Medium,
//                color = extraColors.textBlue,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(bottom = 16.dp)
//            )
//
//            // Version Card
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(64.dp)
//                    .clip(RoundedCornerShape(16.dp))
//                    .background(extraColors.cardBackground)
//                    .padding(horizontal = 20.dp),
//                contentAlignment = Alignment.Center
//            ) {
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceBetween,
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Text(
//                        text = localizedApp(R.string.version),
//                        fontSize = 16.sp,
//                        fontWeight = FontWeight.Medium,
//                        color = extraColors.textBlue
//                    )
//                    Text(
//                        text = "1.07",
//                        fontSize = 16.sp,
//                        fontWeight = FontWeight.Medium,
//                        color = extraColors.textGray
//                    )
//                }
//            }
//
//            Spacer(modifier = Modifier.height(40.dp))
//        }
//
//        if (isLoading) {
//            CircularProgressIndicator(
//                modifier = Modifier.align(Alignment.Center),
//                color = extraColors.accent
//            )
//        }
//    }
//}
//
//@Composable
//fun LanguageCardEnhanced(
//    languageName: String,
//    flagEmoji: String,
//    isSelected: Boolean,
//    onClick: () -> Unit
//) {
//    val extraColors = LocalExtraColors.current
//
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(64.dp)
//            .clip(RoundedCornerShape(16.dp))
//            .background(
//                if (isSelected)
//                    extraColors.languageCardSelectedBackground
//                else
//                    extraColors.languageCardUnselectedBackground
//            )
//            .border(
//                width = if (isSelected) 2.dp else 0.dp,
//                color = if (isSelected) extraColors.accent else Color.Transparent,
//                shape = RoundedCornerShape(16.dp)
//            )
//            .clickable(onClick = onClick)
//            .padding(horizontal = 20.dp),
//        contentAlignment = Alignment.Center
//    ) {
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//
//            // Language name and flag (will be on right in LTR, left in RTL)
//            Row(
//                horizontalArrangement = Arrangement.spacedBy(10.dp),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Text(
//                    text = flagEmoji,
//                    fontSize = 24.sp
//                )
//                Text(
//                    text = languageName,
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.Medium,
//                    color = extraColors.textBlue
//                )
//            }
//
//            // Checkmark (will be on left in LTR, right in RTL)
//            if (isSelected) {
//                Icon(
//                    imageVector = Icons.Rounded.Check,
//                    contentDescription = "Selected",
//                    tint = extraColors.accent,
//                    modifier = Modifier.size(24.dp)
//                )
//            } else {
//                Spacer(modifier = Modifier.width(24.dp))
//            }
//        }
//    }
//}
//
//@Composable
//fun ThemeCardEnhanced(
//    themeIcon: String,
//    themeName: String,
//    isSelected: Boolean,
//    onClick: () -> Unit,
//    modifier: Modifier = Modifier
//) {
//    val extraColors = LocalExtraColors.current
//
//    Box(
//        modifier = modifier
//            .height(140.dp)
//            .clip(RoundedCornerShape(16.dp))
//            .background(
//                if (isSelected)
//                    extraColors.themeCardSelectedBackground
//                else
//                    extraColors.themeCardUnselectedBackground
//            )
//            .border(
//                width = if (isSelected) 2.dp else 1.dp,
//                color = if (isSelected) extraColors.accent else extraColors.themeCardBorder,
//                shape = RoundedCornerShape(16.dp)
//            )
//            .clickable(onClick = onClick)
//            .padding(vertical = 20.dp),
//        contentAlignment = Alignment.Center
//    ) {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center,
//            modifier = Modifier.fillMaxSize()
//        ) {
//            // Theme Icon in Circle
//            Box(
//                modifier = Modifier
//                    .size(60.dp)
//                    .clip(CircleShape)
//                    .background(extraColors.themeIconBackground),
//                contentAlignment = Alignment.Center
//            ) {
//                Text(
//                    text = themeIcon,
//                    fontSize = 30.sp
//                )
//            }
//
//            Spacer(modifier = Modifier.height(14.dp))
//
//            Text(
//                text = themeName,
//                fontSize = 15.sp,
//                fontWeight = FontWeight.Medium,
//                color = extraColors.textBlue
//            )
//        }
//    }
//}

package com.informatique.tawsekmisr.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.informatique.tawsekmisr.ui.components.localizedApp
import com.informatique.tawsekmisr.ui.theme.LocalExtraColors
import com.informatique.tawsekmisr.ui.theme.ThemeOption
import com.informatique.tawsekmisr.ui.viewmodels.LanguageViewModel
import com.informatique.tawsekmisr.ui.viewmodels.SharedUserViewModel
import com.informatique.tawsekmisr.viewmodel.ThemeViewModel
import com.informatique.tawsekmisr.R

@Composable
fun SettingsScreen(
    viewModel: ThemeViewModel,
    navController: NavController,
    sharedUserViewModel: SharedUserViewModel,
    languageViewModel: LanguageViewModel = hiltViewModel()
) {
    val extraColors = LocalExtraColors.current
    val theme by viewModel.theme.collectAsState()
    val currentLanguage by languageViewModel.languageFlow.collectAsState(initial = "ar")
    val isLoading = languageViewModel.isLoading.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(extraColors.background)
            .padding(top = 40.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {

            // Top Bar: Back (left), Title (center), Done (right)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 32.dp)
            ) {
                // Back button on the left
                Box(
                    modifier = Modifier
                        .size(45.dp)
                        .clip(CircleShape)
                        .background(extraColors.cardBackground)
                        .border(2.dp, extraColors.backButtonBorder, CircleShape)
                        .clickable { navController.popBackStack() }
                        .align(Alignment.CenterEnd),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBackIosNew,
                        contentDescription = "Back",
                        tint = extraColors.backButtonIcon,
                        modifier = Modifier.size(25.dp)
                    )
                }

                // Title centered
                Text(
                    text = localizedApp(R.string.settings_title),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = extraColors.textBlue,
                    modifier = Modifier.align(Alignment.Center)
                )

                // Done button on the right
                Box(
                    modifier = Modifier
                        .size(45.dp)
                        .clip(CircleShape)
                        .background(extraColors.doneButtonBackground)
                        .border(1.dp, extraColors.doneButtonBorder, CircleShape)
                        .clickable { navController.popBackStack() }
                        .align(Alignment.CenterStart),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = localizedApp(R.string.done),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = extraColors.doneButtonText
                    )
                }
            }

            // Language Section Title (RTL right aligned effect via full width + natural RTL)
            Text(
                text = localizedApp(R.string.settings_language_section),
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = extraColors.textBlue,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            // Arabic Language Card
            LanguageCardEnhanced(
                languageName = "العربية",
                flagEmoji = "🇪🇬",
                isSelected = currentLanguage == "ar",
                onClick = { languageViewModel.saveLanguage("ar") }
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Theme Section Title
            Text(
                text = localizedApp(R.string.settings_theme_section),
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = extraColors.textBlue,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            // Theme Cards Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ThemeCardEnhanced(
                    themeIcon = "☀️",
                    themeName = localizedApp(R.string.theme_light),
                    isSelected = theme == ThemeOption.LIGHT,
                    onClick = { viewModel.setTheme(ThemeOption.LIGHT) },
                    modifier = Modifier.weight(1f)
                )
                ThemeCardEnhanced(
                    themeIcon = "🌙",
                    themeName = localizedApp(R.string.theme_dark),
                    isSelected = theme == ThemeOption.DARK,
                    onClick = { viewModel.setTheme(ThemeOption.DARK) },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(48.dp))

            // About App Section Title
            Text(
                text = localizedApp(R.string.about_app),
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = extraColors.textBlue,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            // Version Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(extraColors.cardBackground)
                    .border(1.dp, extraColors.themeCardBorder, RoundedCornerShape(16.dp))
                    .padding(horizontal = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = localizedApp(R.string.version),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = extraColors.textBlue
                    )
                    Text(
                        text = "1.07",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = extraColors.textGray
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))
        }

        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = extraColors.accent
            )
        }
    }
}

@Composable
fun LanguageCardEnhanced(
    languageName: String,
    flagEmoji: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val extraColors = LocalExtraColors.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp) // أعلى شوية زي الصورة
            .clip(RoundedCornerShape(16.dp))
            .background(
                if (isSelected)
                    extraColors.languageCardSelectedBackground // أزرق فاتح في اللايت، أزرق داكن في الدارك
                else
                    extraColors.languageCardUnselectedBackground
            )
            .border(
                width = if (isSelected) 2.dp else 1.dp,
                color = if (isSelected) extraColors.settingCardBorder else extraColors.themeCardBorder,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // اسم اللغة والعلم على اليمين
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = flagEmoji,
                    fontSize = 20.sp
                )
                Text(
                    text = languageName,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = extraColors.textBlue
                )
            }
            // علامة الصح داخل دائرة على اليسار (حسب الصور)
            if (isSelected) {
                Box(
                    modifier = Modifier
                        .size(22.dp)
                        .clip(CircleShape)
                        .background(extraColors.settingCardBorder),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Check,
                        contentDescription = "Selected",
                        tint = extraColors.black,
                        modifier = Modifier.size(18.dp)
                    )
                }
            } else {
                Spacer(modifier = Modifier.width(28.dp))
            }
        }
    }
}

@Composable
fun ThemeCardEnhanced(
    themeIcon: String,
    themeName: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val extraColors = LocalExtraColors.current

    Box(
        modifier = modifier
            .height(140.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(
                if (isSelected)
                    extraColors.themeCardSelectedBackground
                else
                    extraColors.themeCardUnselectedBackground
            )
            .border(
                width = if (isSelected) 2.dp else 1.dp,
                color = if (isSelected) extraColors.settingCardBorder else extraColors.themeCardBorder,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable(onClick = onClick)
            .padding(vertical = 20.dp, horizontal = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            // أيقونة داخل دائرة
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(extraColors.themeIconBackground),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = themeIcon,
                    fontSize = 30.sp
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = themeName,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = extraColors.textBlue
            )
        }
    }
}