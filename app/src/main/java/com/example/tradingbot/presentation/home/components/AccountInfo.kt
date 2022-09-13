package com.example.tradingbot.presentation.home.components

import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import com.example.tradingbot.ui.theme.Green
import com.example.tradingbot.ui.theme.Red
import com.example.tradingbot.ui.theme.Yellow
import com.example.tradingbot.ui.theme.YellowishRed
import org.chromium.base.Log
import kotlin.math.roundToLong

@Composable
fun AccountInFoCardView(
    Data : AccountInfoResponseModelItem,
refresh : MutableState<Boolean>,
    profileName: MutableState<String>,
){
    val context = LocalContext.current as ComponentActivity

    Card(
        modifier = Modifier
            .fillMaxWidth(1f)
            .background(color = Color.White, shape = RoundedCornerShape(15.dp))
            ,
        shape = RoundedCornerShape(15.dp)
    )
    {
        Column(modifier = Modifier.fillMaxWidth(1f)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(start = 5.dp, end = 5.dp)
            ) {
                Text(
                    "${profileName.value}'s Futures Wallet ${Data.asset}",
                    color = Color.Black,
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
            Spacer(modifier = Modifier.padding(top = 5.dp))
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
            }
            Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(start = 5.dp, end = 5.dp))
            {
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth(0.45f))

                {
                    Text(
                        "Wallet Balance :",
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                    Text(
                        Data.walletBalance.dropLast(4),
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                }
                Spacer(modifier = Modifier.fillMaxWidth(0.1f))
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth(1f))

                {
                    Text(
                        "Margin Balance :",
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                    Text(
                        Data.marginBalance.dropLast(4),
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                }
            }
            Divider(color = Color.Black, thickness = 1.dp, modifier = Modifier.padding(start = 5.dp, end = 5.dp))
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(start = 5.dp, end = 5.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth(0.45f))

                {
                    Text(
                        "UnTraded Balance :",
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                    Text(
                        Data.availableBalance.dropLast(4),
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                }
                Spacer(modifier = Modifier.fillMaxWidth(0.1f))
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth(1f))

                {
                    Text(
                        "Traded Balance :",
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                    Text(
                        Data.positionInitialMargin.dropLast(4),
                        color = YellowishRed,
                        fontSize = 14.sp
                    )
                }
            }

            Divider(color = Color.Black, thickness = 1.dp, modifier = Modifier.padding(start = 5.dp, end = 5.dp))
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(start = 5.dp, end = 5.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth(0.45f))

                {
                    Text(
                        "Initial Margin :",
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                    Text(
                        Data.initialMargin.dropLast(4),
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                }
                Spacer(modifier = Modifier.fillMaxWidth(0.1f))
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth(1f))

                {
                    Text(
                        "UnRealized PNL :",
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                    Text(
                        Data.unrealizedProfit.dropLast(4),
                        color = determineColor(Data.unrealizedProfit),
                        fontSize = 14.sp
                    )
                }
            }
            Spacer(modifier = Modifier.padding(bottom = 10.dp))
        }
    }

}


fun determineColor(value : String) : Color{
    var floatValue = value.toFloat()
    return if (floatValue > 0){
        Green
    }else if (floatValue < 0){
        Red
    }else {
        YellowishRed
    }
}