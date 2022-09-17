package com.example.tradingbot.presentation.main.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TermsOfUseAndPrivacyPolicy() {
    val context = LocalContext.current as ComponentActivity
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .fillMaxHeight(1f)
            .fillMaxWidth(1f)
    ) {

        Text(
            "Terms of Use & Privacy Policy",
            color = Color.White,
            fontSize = 18.sp,
            style = MaterialTheme.typography.body2,
            modifier = Modifier
                .clickable(onClick = {
                    val url = "https://mytradingbot.netlify.app/terms"
                    val i = Intent(Intent.ACTION_VIEW)
                    i.data = Uri.parse(url)
                    context.startActivity(i)
                })
        )

        Row(modifier = Modifier.padding(top=10.dp)) {
            Text("Have questions?",
                color = Color.White,
                fontSize = 18.sp,
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .clickable { })

            Text("Contact Us",
                color = Color.White,
                fontSize = 18.sp,
                style = MaterialTheme.typography.body2,
                modifier = Modifier
                    .padding(start = 20.dp)
                    .clickable { composeEmail(arrayOf("tradingbot.developers@gmail.com"), "", context) })
        }

    }
}


fun composeEmail(addresses: Array<String?>?, subject: String?,context: Context) {
    val intent = Intent(Intent.ACTION_SENDTO)
    intent.data = Uri.parse("mailto:") // only email apps should handle this
    intent.putExtra(Intent.EXTRA_EMAIL, addresses)
    intent.putExtra(Intent.EXTRA_SUBJECT, subject)
//    if (intent.resolveActivity(getPackageManager()) != null) {
    context.startActivity(intent)
//    }
}