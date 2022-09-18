package com.example.tradingbot.presentation.home.components

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tradingbot.domain.model.OpenOrdersResponseModel.OpenOrderResponseModelItem
import com.example.tradingbot.domain.model.OpenTradesResponseModel.OpenTradesResponseModelItem
import com.example.tradingbot.ui.theme.*
import kotlin.math.absoluteValue
import kotlin.math.round
import kotlin.math.roundToInt

@Composable
fun OpenTradesCardView(
    Data : OpenTradesResponseModelItem,
){
    val context = LocalContext.current as ComponentActivity

    Card(
        modifier = Modifier
            .fillMaxWidth(1f)
            .background(color = determineROIColorCard(Data= Data), shape = RoundedCornerShape(15.dp))
        ,
        shape = RoundedCornerShape(15.dp)
    )
    {
        Column(modifier = Modifier.fillMaxWidth(1f)
            .background(determineROIColorCard(Data= Data))) {
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
                    "ROI: ${plusROI(Data = Data)}${calculateROI(Data = Data)}%",
                    color = determineROIColor(Data = Data),
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
                        color = determineROIColorText(Data= Data),
                        fontSize = 14.sp
                    )
                    Text(
                        determinePositionType(s= Data.positionAmt),
                        color = determineSideColor(determinePositionType(s= Data.positionAmt)),
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
                        color = determineROIColorText(Data = Data),
                        fontSize = 14.sp
                    )
                    Text(
                        Data.marginType,
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
                        "Entry Price :",
                        color = determineROIColorText(Data = Data),
                        fontSize = 14.sp
                    )
                    Text(
                        Data.entryPrice,
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
                        "Mark Price :",
                        color = determineROIColorText(Data = Data),
                        fontSize = 14.sp
                    )
                    Text(
                        Data.markPrice.dropLast(4),
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
                        "Quantity :",
                        color = determineROIColorText(Data = Data),
                        fontSize = 14.sp
                    )
                    Text(
                        Data.positionAmt.toFloat().absoluteValue.toString(),
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
                        "Leverage :",
                        color = determineROIColorText(Data = Data),
                        fontSize = 14.sp
                    )
                    Text(
                        Data.leverage,
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
                        "Amount Used :",
                        color = determineROIColorText(Data = Data),
                        fontSize = 14.sp
                    )
                    Text(
                        ((Data.positionAmt.toFloat().absoluteValue * Data.entryPrice.toFloat() / Data.leverage.toFloat() * 100).roundToInt().toFloat()/100).toString(),
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
                        "Traded :",
                        color = determineROIColorText(Data = Data),
                        fontSize = 14.sp
                    )
                    Text(
                        ((Data.positionAmt.toFloat().absoluteValue * Data.entryPrice.toFloat()*100).roundToInt().toFloat()/100).toString(),
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
                        "Liq. Price :",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                    Text(
                        Data.liquidationPrice.dropLast(4),
                        color = Red,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                }
                Spacer(modifier = Modifier.fillMaxWidth(0.1f))
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth(1f))

                {
                    Text(
                        "Unrealized PNL :",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                    Text(
                        ((Data.unRealizedProfit.toFloat() * 100).roundToInt().toFloat()/100).toString(),
                        color = determineROIColor(Data = Data),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.padding(bottom = 10.dp))
        }
    }

}

fun determinePositionType(s : String): String{
    return if (s.toFloat()< 0){
        "SELL"
    }else {
        "BUY"
    }
}

fun calculateROI(Data: OpenTradesResponseModelItem): Float{
    return (((Data.unRealizedProfit.toFloat()/(Data.positionAmt.toFloat().absoluteValue * Data.entryPrice.toFloat() / Data.leverage.toFloat())*10000).roundToInt()).toFloat()/100)
}

fun determineROIColor(Data: OpenTradesResponseModelItem): Color{
    return if (calculateROI(Data = Data) > 0) {
        GreenText
    }else if (calculateROI(Data = Data) < 0){
        Red
    } else  {
        YellowText
    }
}

fun determineROIColorCard(Data: OpenTradesResponseModelItem): Color{
    return if (calculateROI(Data = Data) > 0) {
        LightGreenCard
    }else if (calculateROI(Data = Data) < 0){
        RedCard
    } else  {
        YellowCard
    }
}

fun determineROIColorText(Data: OpenTradesResponseModelItem): Color{
    return if (calculateROI(Data = Data) > 0) {
        SeaGreenText
    }else if (calculateROI(Data = Data) < 0){
        RedText
    } else  {
        YellowText
    }
}

fun plusROI(Data: OpenTradesResponseModelItem):String{
    return if (calculateROI(Data = Data) >= 0) {
        "+"
    } else {
        ""
    }

}