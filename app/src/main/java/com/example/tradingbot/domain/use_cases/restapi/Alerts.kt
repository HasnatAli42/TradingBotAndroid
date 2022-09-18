package com.example.tradingbot.domain.use_cases.restapi

import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tradingbot.R
import com.example.tradingbot.domain.functions.callbacks.ProgressBar
import com.example.tradingbot.ui.theme.RobotoCondensed
import com.example.tradingbot.ui.theme.YellowishRed
import java.time.temporal.TemporalQueries.offset


@Composable
fun showWarningAlerts(context: Context, message:String, warningsCallBack: WarningsCallBack){

    val alertDialog = AlertDialog.Builder(context)

    // Setting Dialog Title
    alertDialog.setTitle("Alert")

    // Setting Dialog Message
    alertDialog.setMessage(message)

    // On pressing Settings button
    alertDialog.setPositiveButton("Retry") { dialog, which ->
        warningsCallBack.onRetry()
    }

    // on pressing cancel button
    alertDialog.setNegativeButton("Close") { dialog, which ->
        dialog.cancel()
        warningsCallBack.onCancel()
    }

    // Showing Alert Message
    alertDialog.show()

}

interface WarningsCallBack{
    fun onRetry();
    fun onCancel()
}


@Composable
fun CustomWarningAlertWithTwoButtons(firstButtonText:String,secondButtonText:String,message:String,color:Color, warningsCallBack: WarningsCallBack){

    ProvideTextStyle(TextStyle(color = Color.Black, fontFamily = RobotoCondensed, fontSize = 20.sp)) {
        Box(
            modifier = Modifier
                .fillMaxSize(1f)
                .background(color = Color.Transparent)
                .clickable { },
            Alignment.Center
        ) {
            Column(
                Modifier
                    .fillMaxWidth(0.5f)
                    .background(color = color, shape = RoundedCornerShape(10.dp))
            ) {
                Row(
                    Modifier
                        .fillMaxWidth(1f)
                        .drawBehind {
                            drawLine(
                                color = Color.Black,
                                start = Offset(0f, size.height - 1.dp.toPx()),
                                end = Offset(size.width, size.height - 1.dp.toPx()),
                                strokeWidth = 1.dp.toPx()
                            )
                        }) {
                    Text(text = "Alert",Modifier.padding(top=5.dp,start=5.dp,bottom=5.dp))
                    Text(text = " !",Modifier.padding(top=5.dp,bottom=5.dp),color = YellowishRed)
                }
                Row(Modifier
                    .fillMaxWidth(1f)) {
                    Text(
                        text = message,
                        Modifier.padding(top = 10.dp, start = 5.dp, bottom = 20.dp), fontSize = 14.sp, color = YellowishRed
                    )
                }

                Row(modifier = Modifier
                    .fillMaxWidth(1f)
                    .drawBehind {
                        drawLine(
                            color = Color.Black,
                            start = Offset(0f, 0f),
                            end = Offset(size.width, 0f),
                            strokeWidth = 1.dp.toPx()
                        )
                        drawLine(
                            color = Color.Black,
                            start = Offset(size.width / 2, 0f),
                            end = Offset(size.width / 2, size.height),
                            strokeWidth = 1.dp.toPx()
                        )
                    }, verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.SpaceAround) {

                    Text(
                        text = firstButtonText,color = YellowishRed,
                        modifier = Modifier
                            .clickable { warningsCallBack.onCancel() }
                            .padding(5.dp))
                    Text(
                        text = secondButtonText,color = Color.Blue,
                        modifier = Modifier
                            .clickable { warningsCallBack.onRetry() }
                            .padding(5.dp))

                }
            }
        }
    }
}


@Composable
fun CustomWarningAlertWithRetry(message:String,color: Color, warningsCallBack: WarningsCallBack){
    ProvideTextStyle(TextStyle(color = Color.Black, fontFamily = RobotoCondensed, fontSize = 20.sp)) {
        Box(
            modifier = Modifier
                .fillMaxSize(1f)
                .background(color = Color.Transparent)
                .clickable { },
            Alignment.Center
        ) {
            Column(
                Modifier
                    .fillMaxWidth(0.5f)
                    .background(color = color, shape = RoundedCornerShape(10.dp))
            ) {
                Row(
                    Modifier
                        .fillMaxWidth(1f)
                        .drawBehind {
                            drawLine(
                                color = Color.Black,
                                start = Offset(0f, size.height - 1.dp.toPx()),
                                end = Offset(size.width, size.height - 1.dp.toPx()),
                                strokeWidth = 1.dp.toPx()
                            )
                        }) {
                    Text(text = "Alert",Modifier.padding(top=5.dp,start=5.dp,bottom=5.dp))
                    Text(text = " !",Modifier.padding(top=5.dp,bottom=5.dp),color = YellowishRed)
                }
                Row(Modifier
                    .fillMaxWidth(1f)) {
                    Text(
                        text = message,
                        Modifier.padding(top = 10.dp, start = 5.dp, bottom = 20.dp), fontSize = 14.sp, color = YellowishRed
                        )
                }

                Row(modifier = Modifier
                    .fillMaxWidth(1f)
                    .drawBehind {
                        drawLine(
                            color = Color.Black,
                            start = Offset(0f, 0f),
                            end = Offset(size.width, 0f),
                            strokeWidth = 1.dp.toPx()
                        )
                    }, verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.End) {

                    Icon(painter = painterResource(id = R.drawable.ic_baseline_redo_24), contentDescription = "Retry", tint = YellowishRed )
                    Text(
                        text = "Retry",
                        modifier = Modifier
                            .clickable { warningsCallBack.onRetry() }
                            .padding(5.dp))

                }
            }
        }
    }
}


@Composable
fun CustomProgressBar(){
    ProvideTextStyle(TextStyle(color = Color.Black, fontFamily = RobotoCondensed, fontSize = 20.sp)) {
        Box(
            modifier = Modifier
                .fillMaxSize(1f)
                .background(color = Color.Transparent)
                .clickable { },
            Alignment.Center
        ) {
            Column(Modifier.fillMaxSize(1f), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                CircularProgressIndicator(color = YellowishRed)
            }
        }
    }
}


@Composable
fun ProfilePopUp(isPopUpState : MutableState<Boolean>, isLogoutCalled : MutableState<Boolean>){
    Box(
        modifier = Modifier
            .fillMaxSize(1f)
            .background(color = Color.Transparent),
    ) {
        Column(modifier = Modifier.fillMaxSize(1f)) {
            DropdownMenu(
                expanded = isPopUpState.value,
                onDismissRequest = { isPopUpState.value = false },
            ) {
                DropdownMenuItem(onClick = { isLogoutCalled.value = true }) {
                    Text("Logout")
                }
            }
        }
    }

}

