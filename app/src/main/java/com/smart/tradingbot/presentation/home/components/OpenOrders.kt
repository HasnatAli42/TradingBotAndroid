package com.smart.tradingbot.presentation.home.components

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smart.tradingbot.domain.model.OpenOrdersResponseModel.OpenOrderResponseModelItem
import com.smart.tradingbot.ui.theme.*

@Composable
fun OpenOrderCardView(
    Data : OpenOrderResponseModelItem,
){
    val context = LocalContext.current as ComponentActivity

    Card(
        modifier = Modifier
            .fillMaxWidth(1f)
            .background(color = LightGreenCard, shape = RoundedCornerShape(15.dp))
        ,
        shape = RoundedCornerShape(15.dp)
    )
    {
        Column(modifier = Modifier.fillMaxWidth(1f)
            .background(color = LightGreenCard)) {
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
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 10.dp)
                )
                Text(
                    "${Data.dateTime}",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
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
                        color = SeaGreenText,
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
                        color = SeaGreenText,
                        fontSize = 14.sp
                    )
                    Text(
                        if(Data.price.toInt() == 0){Data.stopPrice}else{Data.price},
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
                        "Status :",
                        color = SeaGreenText,
                        fontSize = 14.sp
                    )
                    Text(
                        Data.status,
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
                        "Type :",
                        color = SeaGreenText,
                        fontSize = 14.sp
                    )
                    Text(
                        Data.type,
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
                        "Quantity :",
                        color = SeaGreenText,
                        fontSize = 14.sp
                    )
                    Text(
                        Data.origQty,
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
                        "Reduce Only :",
                        color = SeaGreenText,
                        fontSize = 14.sp
                    )
                    Text(
                        Data.reduceOnly.toString(),
                        color = determineTypeColor(Data.reduceOnly),
                        fontSize = 14.sp
                    )
                }
            }
            Spacer(modifier = Modifier.padding(bottom = 10.dp))
        }
    }

}