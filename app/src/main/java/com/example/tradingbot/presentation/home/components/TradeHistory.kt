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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tradingbot.R
import com.example.tradingbot.domain.model.AccountInfoModel.AccountInfoResponseModelItem
import com.example.tradingbot.domain.model.TradeHistoryModel.TradeHistoryResponseModelItem
import com.example.tradingbot.ui.theme.Green
import com.example.tradingbot.ui.theme.Red
import com.example.tradingbot.ui.theme.YellowishRed

@Composable
fun TradeHistoryCardView(
    Data : TradeHistoryResponseModelItem,
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
                    "SYMBOL : ${Data.symbol}",
                    color = Color.Black,
                    modifier = Modifier.padding(top = 10.dp)
                )
                Text(
                    "${Data.dateTime}",
                    color = Color.Black,
                    modifier = Modifier.padding(top = 10.dp)
                )

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
                        "Side :",
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                    Text(
                        Data.side,
                        color = determineSideColor(Data.side),
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
                        "Price :",
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                    Text(
                        Data.price,
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
                        "Asset :",
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                    Text(
                        Data.marginAsset,
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
                        "Quantity :",
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                    Text(
                        Data.qty,
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
                        "Fee :",
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                    Text(
                        Data.commission.dropLast(4),
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
                        "Order Type :",
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                    Text(
                        determineType(Data.maker),
                        color = determineTypeColor(Data.maker),
                        fontSize = 14.sp
                    )
                }
            }
            Spacer(modifier = Modifier.padding(bottom = 10.dp))
        }
    }

}

fun determineSideColor (s : String): Color{
    return if (s == "SELL"){
        Red
    }else{
        Green
    }

}

fun determineType (s : Boolean): String{
    return if (s){
        "Maker"
    }else{
        "Taker"
    }

}

fun determineTypeColor (s : Boolean): Color{
    return if (s){
        Green
    }else{
        Color.Gray
    }

}