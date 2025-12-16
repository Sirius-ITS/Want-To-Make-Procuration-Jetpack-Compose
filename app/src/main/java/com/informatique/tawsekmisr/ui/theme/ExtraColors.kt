//package com.informatique.tawsekmisr.ui.theme
//
//import androidx.compose.runtime.staticCompositionLocalOf
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Color
//
//data class ExtraColors(
//
//    val accent : Color,
//    val background : Color,
//    val cardBackground : Color,
//    val cardDarkBackground : Color,
//    val textBlue : Color,
//    val textGray : Color,
//    val green : Color,
//    val lightGreen : Color,
//    val gold : Color,
//    val iconDarkBackground : Color,
//    val iconLightBackground : Color,
//    val iconLightBlue : Color,
//    val iconDarkBlue : Color,
//    val buttonDarkBlue : Color,
//    val backgroundGradient : Brush,
//    val homeVerticalCardBackground : Color,
//    val iconSettings : Color,
//    val textDarkGray : Color,
//    val white : Color,
//    val black :Color,
//
//
//    // Settings Screen Specific Colors
//    val backButtonIcon : Color,
//    val backButtonBorder : Color,
//    val doneButtonBackground : Color,
//    val doneButtonBorder : Color,
//    val doneButtonText : Color,
//    val languageCardSelectedBackground : Color,
//    val languageCardUnselectedBackground : Color,
//    val themeCardSelectedBackground : Color,
//    val themeCardUnselectedBackground : Color,
//    val themeCardBorder : Color,
//    val themeIconBackground : Color,
//    val versionCardBackground : Color
//)
//val LightExtraColors = ExtraColors(
//    accent = AccentLight,
//    background = BackGroundLight,
//    cardBackground = CardBackGroundLight,
//    cardDarkBackground = CardDarkBackGroundLight,
//    textBlue = TextBlueLight,
//    textGray = TextGrayLight,
//    green = GreenLight,
//    lightGreen = LightGreenLight,
//    gold = GoldLight,
//    iconDarkBackground = IconDarkBackGroundLight,
//    iconLightBackground = IconLightBackGroundLight,
//    iconLightBlue = IconLightBlueLight,
//    iconDarkBlue = IconDarkBlueLight,
//    buttonDarkBlue = ButtonDrakBlueLight,
//    backgroundGradient = Brush.linearGradient(
//        colors = listOf(
//            AccentLight.copy(alpha = 0.5f),
//            AccentLight.copy(alpha = 0.15f),
//            AccentLight.copy(alpha = 0.05f),
//            Color.White
//        )
//    ),
//    homeVerticalCardBackground = HomeVerticalCardsBackgroundLight,
//    iconSettings = SettingiconLight ,
//    textDarkGray = TextDarkGrayLight,
//    white = White,
//    black = WhiteB,
//
//
//            // Settings Screen Light Theme
//
//    backButtonIcon = Color(0xFF1C346B),
//    backButtonBorder = Color(0xFFE8EAED),
//    doneButtonBackground = Color(0xFFF4F6FC),
//    doneButtonBorder = Color(0xFFE8EBFA),
//    doneButtonText = Color(0xFF5B7FD8),
//    languageCardSelectedBackground = Color(0xFFF4F6FC),
//    languageCardUnselectedBackground = Color(0xFFE3E8FC),
//    themeCardSelectedBackground = Color(0xFFE2E6F0),
//    themeCardUnselectedBackground = Color(0xFFFFFFFF),
//    themeCardBorder = Color(0xFFE5E7EB),
//    themeIconBackground = Color(0xFFDEE5F7),
//    versionCardBackground = Color(0xF7F7F7F7)
//
//)
//
//val DarkExtraColors = ExtraColors(
//    accent = AccentDark,
//    background = BackGroundDark,
//    cardBackground = CardBacKGroundDark,
//    cardDarkBackground = CardDarkBackGroundDark,
//    textBlue = TextBlueDark,
//    textGray = TextGrayDark,
//    green = GreenDark,
//    lightGreen = LightGreenDark,
//    gold = GoldDark,
//    iconDarkBackground = IconDarkBackGroundDark,
//    iconLightBackground = IconLightBackGroundDark,
//    iconLightBlue = IconLightBlueDark,
//    iconDarkBlue = IconDarkBlueLDark,
//    buttonDarkBlue = ButtonDrakBlueDark,
//    backgroundGradient = Brush.linearGradient(
//        colors = listOf(
//            AccentDark.copy(alpha = 0.5f),
//            AccentDark.copy(alpha = 0.15f),
//            AccentDark.copy(alpha = 0.05f),
//            BackGroundDark
//        )
//    ),
//    homeVerticalCardBackground = HomeVerticalCardsBackgroundDark,
//    iconSettings = SettingiconDark,
//    textDarkGray = TextDarkGrayDark,
//    white = Black,
//    black =  BlackW,
//
//
//    // Settings Screen Dark Theme
//    backButtonIcon = Color(0xFFFFFFFF),
//    backButtonBorder = Color(0xFF3A3E44),
//    doneButtonBackground = Color(0xFF2B2F35),
//    doneButtonBorder = Color(0xFF3A3E44),
//    doneButtonText = Color(0xFF7B96D8),
//    languageCardSelectedBackground = Color(0xFF2B2F35),
//    languageCardUnselectedBackground = Color(0xFF2B2F35),
//    themeCardSelectedBackground = Color(0xFF232733),
//    themeCardUnselectedBackground = Color(0xFF1F2225),
//    themeCardBorder = Color(0xFF3A3E44),
//    themeIconBackground = Color(0xFF3E4652),
//    versionCardBackground = Color(0xFF242426)
//)
//
//val LocalExtraColors = staticCompositionLocalOf { LightExtraColors }

package com.informatique.tawsekmisr.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

data class ExtraColors(
    val accent: Color,
    val background: Color,
    val cardBackground: Color,
    val cardDarkBackground: Color,
    val textBlue: Color,
    val textGray: Color,
    val green: Color,
    val lightGreen: Color,
    val gold: Color,
    val iconDarkBackground: Color,
    val iconLightBackground: Color,
    val iconLightBlue: Color,
    val iconDarkBlue: Color,
    val buttonDarkBlue: Color,
    val backgroundGradient: Brush,
    val homeVerticalCardBackground: Color,
    val iconSettings: Color,
    val textDarkGray: Color,
    val white: Color,
    val black: Color,
    val surface1PersonIcon: Color,
    val textBlueB :Color,
    // office icon
    val officeIcon: Color,


    // Settings Screen Specific
    val backButtonIcon: Color,
    val backButtonBorder: Color,
    val doneButtonBackground: Color,
    val doneButtonBorder: Color,
    val doneButtonText: Color,
    val languageCardSelectedBackground: Color,
    val languageCardUnselectedBackground: Color,
    val themeCardSelectedBackground: Color,
    val themeCardUnselectedBackground: Color,
    val themeCardBorder: Color,
    val themeIconBackground: Color,
    val versionCardBackground: Color,
    val settingCardBorder :Color
)

val LightExtraColors = ExtraColors(
    accent = AccentLight,                      // 0xFF5B84FF
    background = BackGroundLight,              // 0xFFF2F3F5
    cardBackground = CardBackGroundLight,      // white
    cardDarkBackground = CardDarkBackGroundLight, // 0xFFEAF0FF
    textBlue = TextBlueLight,                  // 0xFF1C346B
    textGray = TextGrayLight,                  // 0xFF7E7E85
    green = GreenLight,
    lightGreen = LightGreenLight,
    gold = GoldLight,
    iconDarkBackground = IconDarkBackGroundLight,   // 0xFFE0E6F7
    iconLightBackground = IconLightBackGroundLight, // 0xFFF5F6FA
    iconLightBlue = IconLightBlueLight,         // 0xFF5B84FF
    iconDarkBlue = IconDarkBlueLight,           // 0xFF294889
    buttonDarkBlue = ButtonDrakBlueLight,
    backgroundGradient = Brush.linearGradient(
        colors = listOf(
            AccentLight.copy(alpha = 0.45f),
            AccentLight.copy(alpha = 0.18f),
            AccentLight.copy(alpha = 0.06f),
            Color.White
        )
    ),
    homeVerticalCardBackground = HomeVerticalCardsBackgroundLight,
    iconSettings = SettingiconLight,
    textDarkGray = TextDarkGrayLight,
    white = White,
    black = WhiteB,
    surface1PersonIcon = Surface1PersonIconLight,
    textBlueB = TextBluebLight,
    officeIcon = OfficeIconLight,



    // Settings Screen (Light)
    backButtonIcon = Color(0xFF1C346B),
    backButtonBorder = Color(0xFFE5E7EB),            // light gray border
    doneButtonBackground = Color(0xFFF5F6FA),        // white-ish circle
    doneButtonBorder = Color(0xFFE5ECF9),            // subtle bluish border
    doneButtonText = Color(0xFF5B84FF),              // accent blue
    languageCardSelectedBackground = Color(0xFFEAF0FF), // very light blue
    languageCardUnselectedBackground = Color(0xFFFFFFFF), // white for unselected
    themeCardSelectedBackground = Color(0xFFEAF0FF),     // same selected blue tint
    themeCardUnselectedBackground = Color(0xFFFFFFFF),   // white
    themeCardBorder = Color(0xFFE5E7EB),                // neutral gray border
    themeIconBackground = Color(0xFFE0E6F7),            // bluish icon bubble
    versionCardBackground = Color(0xFFFFFFFF),        // white card like screenshot
    settingCardBorder = SettingsCardBorderLight
)

val DarkExtraColors = ExtraColors(
    accent = AccentDark,                         // 0xFF6A8CFF
    background = BackGroundDark,                 // 0xFF1C1C1E
    cardBackground = CardBacKGroundDark,         // 0xFF2A2B2F
    cardDarkBackground = CardDarkBackGroundDark, // 0xFF272A31
    textBlue = TextBlueDark,                     // white title
    textGray = TextGrayDark,                     // #D2D3D5
    green = GreenDark,
    lightGreen = LightGreenDark,
    gold = GoldDark,
    iconDarkBackground = IconDarkBackGroundDark,     // 0xFF3A4050 (accent bubble)
    iconLightBackground = IconLightBackGroundDark,   // 0xFF2B2F35
    iconLightBlue = IconLightBlueDark,               // 0xFF7EA0FF
    iconDarkBlue = IconDarkBlueLDark,                // 0xFF5A7AA1
    buttonDarkBlue = ButtonDrakBlueDark,
    backgroundGradient = Brush.linearGradient(
        colors = listOf(
            AccentDark.copy(alpha = 0.45f),
            AccentDark.copy(alpha = 0.18f),
            AccentDark.copy(alpha = 0.06f),
            BackGroundDark
        )
    ),
    homeVerticalCardBackground = HomeVerticalCardsBackgroundDark,
    iconSettings = SettingiconDark,
    textDarkGray = TextDarkGrayDark,
    white = Black,
    black = BlackW,
    surface1PersonIcon = Surface1PersonIconDark,
    textBlueB = TextBluebDark,
    officeIcon = OfficeIconDark,

    // Settings Screen (Dark)
    backButtonIcon = Color(0xFFFFFFFF),
    backButtonBorder = Color(0xFF3A3E44),           // cool gray border
    doneButtonBackground = Color(0xFF2B2F35),       // dark circular bg
    doneButtonBorder = Color(0xFF3A3E44),           // same cool gray
    doneButtonText = Color(0xFF7EA0FF),             // lighter accent for legibility
    languageCardSelectedBackground = Color(0xFF232733), // selected has bluish dark tint
    languageCardUnselectedBackground = Color(0xFF1F2225), // unselected darker
    themeCardSelectedBackground = Color(0xFF232733),     // selected theme card bg
    themeCardUnselectedBackground = Color(0xFF1F2225),   // unselected theme card bg
    themeCardBorder = Color(0xFF3A3E44),                // border
    themeIconBackground = Color(0xFF3E4652),            // icon bubble dark
    versionCardBackground = Color(0xFF242426),           // version card dark bg
    settingCardBorder = SettingsCardBorderDark
)

val LocalExtraColors = staticCompositionLocalOf { LightExtraColors }