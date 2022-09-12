package com.example.tradingbot.presentation.home.components

import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tradingbot.R
import com.example.tradingbot.domain.model.AccountInfoModel.AccountInfoResponseModelItem
import com.example.tradingbot.ui.theme.YellowishRed
import org.chromium.base.Log

@Composable
fun AccountInFoCardView(Data : AccountInfoResponseModelItem,
refresh : MutableState<Boolean>){
    val context = LocalContext.current as ComponentActivity

    Card(
        modifier = Modifier
            .fillMaxWidth(1f)
            .shadow(5.dp)
            .background(color = Color.White)
            ,
        shape = RectangleShape
    )
    {
        Column(modifier = Modifier.fillMaxWidth(1f)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(start = 3.dp, end = 3.dp)
            ) {
                Text(
                    "Margin Available : ${Data.marginAvailable}",
                    color = Color.Green,
                    modifier = Modifier.padding(top = 10.dp)
                )
                IconButton(modifier = Modifier.size(40.dp), onClick = {
                    refresh.value = true
                }) {
                    Row(verticalAlignment = Alignment.Top) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_refresh_24),
                            contentDescription = "Refresh",
                            tint = YellowishRed
                        )
                    }
                }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .offset(x = 0.dp, y = (-15).dp)
            ) {
                val drawableId = remember(Data.asset.lowercase()) {
                    context.resources.getIdentifier(
                        Data.asset.lowercase(),
                        "drawable",
                        context.packageName
                    )
                }
                    Image(
                        painter = painterResource(id = drawableId),
                        contentDescription = "Symbol",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(color = Color.White)
                    )

                Text(Data.asset, fontSize = 24.sp)
                Text(
                    "Balance : ${Data.availableBalance}",
                    color = Color.LightGray,
                    fontSize = 14.sp
                )
            }
        }
    }

}