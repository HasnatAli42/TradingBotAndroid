package com.example.tradingbot.ui.theme

import androidx.compose.material.DismissValue
import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.example.tradingbot.R

// Set of Material typography styles to start with

 val Roboto = FontFamily(
    Font(R.font.roboto_regular),
    Font(R.font.roboto_bold, FontWeight.Bold),
    Font(R.font.roboto_light, FontWeight.Light),
    Font(R.font.roboto_medium, FontWeight.Medium),
    Font(R.font.roboto_thin, FontWeight.Thin)
)

val RobotoCondensed = FontFamily(
    Font(R.font.robotocondensed_regular),
    Font(R.font.robotocondensed_bold, FontWeight.Bold),
    Font(R.font.robotocondensed_light, FontWeight.Light)
)

val Typography = Typography(

    h1 = TextStyle(
        fontFamily = RobotoCondensed,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    body1 = TextStyle(
        fontFamily = RobotoCondensed,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
            body2 = TextStyle(
            fontFamily = RobotoCondensed,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
                textDecoration = TextDecoration.Underline
)


//
//    Other default text styles to override
//    button = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.W500,
//        fontSize = 14.sp
//    ),
//    caption = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Normal,
//        fontSize = 12.sp
//    )

)

