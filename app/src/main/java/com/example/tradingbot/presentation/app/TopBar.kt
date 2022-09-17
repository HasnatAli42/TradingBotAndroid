package com.example.tradingbot.presentation.app

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tradingbot.R
import com.example.tradingbot.presentation.home.components.ProfilePicture
import com.example.tradingbot.ui.theme.*

@Composable
fun MainTopBar(iconExpanded: MutableState<Boolean>) {
    Box(
        modifier = Modifier
            .fillMaxWidth(1f)
            .clip(RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp))
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth(1f)
                .background(gradientBlackGray)
                .padding(top = 5.dp, start = 5.dp, end = 5.dp, bottom = 5.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.stock_market),
                contentDescription = "",
                Modifier.size(40.dp, 40.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.bot_name_logo),
                contentDescription = "",
                Modifier.size(150.dp, 40.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_power_settings_new_24),
                contentDescription = "",
                Modifier
                    .size(40.dp, 40.dp)
                    .clickable {
                        iconExpanded.value = true
                    }
            )
        }
}
}

@Composable
fun HomeTopBar(profileName : MutableState<String>, profileImage : MutableState<String>){
    Box(
        modifier = Modifier
            .fillMaxWidth(1f)
            .clip(RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp))
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween,modifier = Modifier
            .fillMaxWidth(1f)
            .background(gradientGrayblack)
            .padding(all = 10.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {

                Image(
                    painter = painterResource(id = R.drawable.stock_market),
                    contentDescription = "",
                    Modifier.size(30.dp, 30.dp)
                )
                Spacer(Modifier.padding(start = 5.dp))
                Image(
                    painter = painterResource(id = R.drawable.bot_name_logo),
                    contentDescription = "",
                    Modifier.size(150.dp, 40.dp)
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = profileName.value, fontFamily = RobotoCondensed, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Spacer(Modifier.padding(start = 5.dp))
                ProfilePicture(avatar = profileImage)
            }
        }

    }

}


@Composable
fun HomeBottomBar(homeButton : MutableState<Boolean>,
                  orderButton: MutableState<Boolean>,
                  positionButton: MutableState<Boolean>,
                  tradeHistoryButton: MutableState<Boolean>,
                  orderHistoryButton: MutableState<Boolean>){
    Box(
        modifier = Modifier
            .fillMaxWidth(1f)
            .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
    ) {
        Row(horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .fillMaxWidth(1f)
                .background(gradientBlackGray)
                .padding(all = 10.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxHeight(1f)
            ) {
                IconButton(modifier = Modifier.size(30.dp), onClick = {
                    if (homeButton.value) {
                    } else {
                        homeButton.value = true
                        orderButton.value = false
                        positionButton.value = false
                        tradeHistoryButton.value = false
                        orderHistoryButton.value = false
                    }
                })
                {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_outline_home_24)
                        , contentDescription = "Home Button", tint = if (homeButton.value) { colorOn
                        }else{
                            colorOff
                        }
                    )
                }
                Text(
                    "Home",
                    fontFamily = RobotoCondensed,
                    fontSize = 10.sp,
                    color = if (homeButton.value) {
                        colorOn
                    } else {
                        colorOff
                    }
                )
            }

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxHeight(1f)
            ) {
                IconButton(modifier = Modifier.size(30.dp), onClick = {
                    if (orderButton.value) {
                    } else {
                        homeButton.value = false
                        orderButton.value = true
                        positionButton.value = false
                        tradeHistoryButton.value = false
                        orderHistoryButton.value = false
                    }
                })
                {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_outline_book_24)
                        , contentDescription = "Order Button", tint = if (orderButton.value) { colorOn
                        }else{
                            colorOff
                        }
                    )
                }
                Text(
                    "Open Orders",
                    fontFamily = RobotoCondensed,
                    fontSize = 10.sp,
                    color = if (orderButton.value) {
                        colorOn
                    } else {
                        colorOff
                    }
                )
            }

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxHeight(1f)
            ) {
                IconButton(modifier = Modifier.size(30.dp), onClick = {
                    if (positionButton.value) {
                    } else {
                        homeButton.value = false
                        orderButton.value = false
                        positionButton.value = true
                        tradeHistoryButton.value = false
                        orderHistoryButton.value = false
                    }
                })
                {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_outline_position_24)
                        , contentDescription = "Positions Button", tint = if (positionButton.value) { colorOn
                        }else{
                            colorOff
                        }
                    )
                }
                Text(
                    "Positions",
                    fontFamily = RobotoCondensed,
                    fontSize = 10.sp,
                    color = if (positionButton.value) {
                        colorOn
                    } else {
                        colorOff
                    }
                )
            }

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxHeight(1f)
            ) {
                IconButton(modifier = Modifier.size(30.dp), onClick = {
                    if (tradeHistoryButton.value) {
                    } else {
                        homeButton.value = false
                        orderButton.value = false
                        positionButton.value = false
                        tradeHistoryButton.value = true
                        orderHistoryButton.value = false
                    }
                })
                {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_outline_history_24)
                        , contentDescription = "Trade History Button", tint = if (tradeHistoryButton.value) { colorOn
                        }else{
                            colorOff
                        }
                    )
                }
                Text(
                    "Trade History",
                    fontFamily = RobotoCondensed,
                    fontSize = 10.sp,
                    color = if (tradeHistoryButton.value) {
                        colorOn
                    } else {
                        colorOff
                    }
                )
            }

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxHeight(1f)
            ) {
                IconButton(modifier = Modifier.size(30.dp), onClick = {
                    if (orderHistoryButton.value) {
                    } else {
                        homeButton.value = false
                        orderButton.value = false
                        positionButton.value = false
                        tradeHistoryButton.value = false
                        orderHistoryButton.value = true
                    }
                })
                {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_outline_order_history_24),
                        contentDescription = "Order History Button",
                        tint = if (orderHistoryButton.value) {
                            colorOn
                        } else {
                            colorOff
                        }
                    )
                }
                Text(
                    "Order History",
                    fontFamily = RobotoCondensed,
                    fontSize = 10.sp,
                    color = if (orderHistoryButton.value) {
                        colorOn
                    } else {
                        colorOff
                    }
                )
            }
        }

    }

}
