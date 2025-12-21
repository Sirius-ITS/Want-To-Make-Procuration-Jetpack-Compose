//package com.informatique.tawsekmisr.ui.theme
//
//import androidx.compose.ui.graphics.Color
//
//val Purple80 = Color(0xFFD0BCFF)
//val PurpleGrey80 = Color(0xFFCCC2DC)
//val Pink80 = Color(0xFFEFB8C8)
//
//val Purple40 = Color(0xFF6650a4)
//val PurpleGrey40 = Color(0xFF625b71)
//val Pink40 = Color(0xFF7D5260)
//val WarmYellow = Color(0xFFFFCA40)
//val LightGrayBackground = Color(0xFFEBEBEB)
//val blue = Color(0xFF0000FF)
//val lightBlue = Color(0xFF128EFF)
//
//
//// Custom (optional)
//
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//// Light Colors
//val AccentLight = Color(0xFF628AEC)
//
//val BackGroundLight = Color(0xFFF0F0F0)  // Light mode: RGB(240, 240, 240)
//val CardBackGroundLight = Color(0xFFFFFFFF)  // Light mode: أبيض بالكامل
//val CardDarkBackGroundLight = Color(0xFFE3E8FC)
//val TextBlueLight = Color(0xFF1C346B)
//val TextGrayLight = Color(0xFF7E7E85)
//val TextDarkGrayLight = Color(0xFF484848)
//val GreenLight = Color(0xFF39D451)
//val LightGreenLight = Color(0x1A6EB3A6)
//val GoldLight = Color(0xFFBF9834)
//val IconDarkBackGroundLight = Color(0xFFBEC9E5)
//val IconLightBackGroundLight = Color(0xFFEEEFF3)
//val IconLightBlueLight = Color(0xFF576C98)
//val IconDarkBlueLight = Color(0xFF1A3564)
//val ButtonDrakBlueLight = Color(0xFF1D336C)
//val HomeVerticalCardsBackgroundLight = Color(0xFFFCFCFC)
//
//val SettingiconLight = Color(0xFF8A898F)
//val White = Color(0xFF000000)
//val WhiteB = Color(0xFFFFFFFF)
//
//
//
//// Dark colors
//val AccentDark = Color(0xFF628AEC)
//
//val BackGroundDark = Color(0xFF1C1C1E)   // Dark mode: RGB(50, 50, 50)
//val CardBacKGroundDark = Color(0xFF313235)   // Dark mode: أبيض بشفافية 10%
//val CardDarkBackGroundDark = Color(0xFFE3E8FC)
//val TextBlueDark = Color(0xFFFFFFFF)
//val TextGrayDark = Color(0xFFD2D3D5)
//val TextDarkGrayDark = Color(0xFFADADAD)
//val GreenDark = Color(0xFF6EB3A6)
//val LightGreenDark = Color(0x1A6EB3A6)
//val GoldDark = Color(0xFFC39847)
//val IconDarkBackGroundDark = Color(0xFFBEC9E5)
//val IconLightBackGroundDark = Color(0xFF2E3237)
//val IconLightBlueDark = Color(0xFF576C98)
//val IconDarkBlueLDark = Color(0xFF597A9D)
//val ButtonDrakBlueDark = Color(0xFF1D336C)
//val HomeVerticalCardsBackgroundDark = Color(0xFF2A2A2C)
//
//val SettingiconDark = Color(0xFFFAFAFC)
//val Black = Color(0xFFFFFFFF)
//val BlackW = Color(0xFF000000)
//
//
//
//
//

package com.informatique.tawsekmisr.ui.theme

import androidx.compose.ui.graphics.Color

// Existing keep
val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)
val WarmYellow = Color(0xFFFFCA40)
val LightGrayBackground = Color(0xFFEBEBEB)
val blue = Color(0xFF0000FF)
val lightBlue = Color(0xFF128EFF)

// Tuned palette to match screenshots 1:1

// Accent and primary blues (borders, selection, icons)
val AccentLight = Color(0xFF5B84FF)        // vivid cornflower blue for borders/selection (light)
val AccentDark  = Color(0xFF6A8CFF)        // slightly softer in dark for contrast

// Backgrounds
val BackGroundLight = Color(0xFFECF0FA)    // light app background (neutral very light gray)
val BackGroundDark  = Color(0xFF242833)    // system-like dark background

// Cards
val CardBackGroundLight     = Color(0xFFFFFFFF)  // white card
val CardDarkBackGroundLight = Color(0xFFEAF0FF)  // very light blue for selected language
val CardBacKGroundDark      = Color(0xFF2C2C2E)  // dark card bg for general
val CardDarkBackGroundDark  = Color(0xFF272A31)  // darker bluish card for selected state

// Text
val TextBlueLight    = Color(0xFF1D356B)   // section titles (matches your base)
val TextBluebLight   = Color(0xFF1D356B)   // section titles (matches your base)
val TextGrayLight    = Color(0xFF1A3564)   // secondary text
val TextDarkGrayLight= Color(0xFF696969)   // body darker gray

val TextBlueDark     = Color(0xFFFFFFFF)   // titles in dark
val TextBluebDark   = Color(0xFF1D356B)   // section titles (matches your base)

val TextGrayDark     = Color(0xFFD2D3D5)   // secondary in dark
val TextDarkGrayDark = Color(0xFFADADAD)   // body gray in dark

// Icon bubbles
val IconLightBackGroundLight = Color(0xFFEDEFF3) // circular bg for icons in light (back/done/check)
val IconDarkBackGroundLight  = Color(0xFFE0E6F7) // bluish circle for selected check in language

// surface 1 person icon
val Surface1PersonIconLight = Color(0xFFBDC9E5)
val Surface1PersonIconDark  = Color(0xFF212F48)

val IconLightBackGroundDark  = Color(0xFF2F3236) // circular bg for icons in dark
val IconDarkBackGroundDark   = Color(0xFF3A4050) // bluish circle accent for selected check in dark

// office icon
val OfficeIconLight = Color(0xFF5A6B92)
val OfficeIconDark  = Color(0xFF4E667F)
// Blues inside icons
val IconLightBlueLight = Color(0xFF5B84FF)
val IconDarkBlueLight  = Color(0xFF1C346B)

val IconLightBlueDark  = Color(0xFF7EA0FF)
val IconDarkBlueLDark  = Color(0xFF5A7AA1)

// Buttons
val ButtonDrakBlueLight = Color(0xFF1D336C) // keep
val ButtonDrakBlueDark  = Color(0xFF1D336C) // keep

// Other backgrounds
val HomeVerticalCardsBackgroundLight = Color(0xFFFCFCFC)
val HomeVerticalCardsBackgroundDark  = Color(0xFF242833)

// Settings icon color
val SettingiconLight = Color(0xFF1C346B)
val SettingiconDark  = Color(0xFFFAFAFC)

// settings card border
val SettingsCardBorderLight = Color(0xFF628AEC)
val SettingsCardBorderDark  = Color(0xFF628AEC)


// White/Black helpers
val White  = Color(0xFF000000)
val WhiteB = Color(0xFFFFFFFF)
val Black  = Color(0xFFFFFFFF)
val BlackW = Color(0xFF000000)
val GoldDark = Color(0xFFC39847)
val LightGreenDark = Color(0x1A6EB3A6)
val GreenDark = Color(0xFF6EB3A6)
val GreenLight = Color(0xFF39D451)
val LightGreenLight = Color(0x1A6EB3A6)
val GoldLight = Color(0xFFBF9834)

