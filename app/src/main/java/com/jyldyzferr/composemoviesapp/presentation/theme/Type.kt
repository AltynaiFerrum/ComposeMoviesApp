package com.jyldyzferr.composemoviesapp.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.jyldyzferr.composemoviesapp.R

val GoogleSans = FontFamily(
    Font(R.font.googlesans_regular, FontWeight.Normal),
    Font(R.font.googlesans_medium, FontWeight.Medium),
    Font(R.font.googlesans_bold, FontWeight.Bold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = GoogleSans,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold
    ),
    headlineMedium = TextStyle(
        fontFamily = GoogleSans,
        fontSize = 25.sp,
        fontWeight = FontWeight.Normal
    ),
    titleLarge = TextStyle(
        fontFamily = GoogleSans,
        fontSize = 20.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 22.sp
    ),
    titleMedium = TextStyle(
        fontFamily = GoogleSans,
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium
    ),
    bodyLarge = TextStyle(
        fontFamily = GoogleSans,
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium
    ),
    bodyMedium = TextStyle(
        fontFamily = GoogleSans,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal
    ),
    bodySmall = TextStyle(
        fontFamily = GoogleSans,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal
    )
)
// Set of Material typography styles to start with
//val Typography = Typography(
//    bodyLarge = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Normal,
//        fontSize = 16.sp,
//        lineHeight = 24.sp,
//        letterSpacing = 0.5.sp
//    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
//)