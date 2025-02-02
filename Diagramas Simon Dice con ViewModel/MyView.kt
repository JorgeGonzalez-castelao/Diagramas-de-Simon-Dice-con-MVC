package com.example.simondicerec.ui

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simondicerec.Data
import com.example.simondicerec.MyColors
import com.example.simondicerec.MyViewModel
import com.example.simondicerec.R
import com.example.simondicerec.State

var ctxt: Context? = null

@Composable
fun UserInterface(miViewModel: MyViewModel) {
    ctxt = LocalContext.current
    miViewModel
    Column {
        round(miViewModel)
        botonesSimon(miViewModel)
        startIncreaseRound(miViewModel)
    }
}

@Composable
fun round(myViewModel: MyViewModel){
    Column {
        // Row with the text "Record" and "Round"
        Row {
            Text(
                modifier = Modifier.padding(20.dp,0.dp,0.dp,0.dp),
                text = "record"
            )
            Text(
                modifier = Modifier.padding(240.dp,0.dp,0.dp,0.dp),
                text = "round"
            )
        }
        // Row with the record and the round
        Row {
            Text(
                modifier = Modifier.padding(20.dp,0.dp,0.dp,0.dp),
                text = "${myViewModel.getRecord()}",
                fontSize = 25.sp
            )
            Text(
                text = "${myViewModel.getRound()}",
                modifier = Modifier.padding(if(myViewModel.getRound()<10) 295.dp else 290.dp,0.dp,0.dp,0.dp),
                fontSize = 25.sp
            )
        }

    }
}


@Composable
fun botonesSimon(myViewModel: MyViewModel){
    Row (modifier = Modifier.padding(0.dp,100.dp,0.dp,0.dp)){
        columnButtonSimon(color = MyColors.BLUE.color,myViewModel)
        columnButtonSimon(color = MyColors.GREEN.color, myViewModel)
    }
    Row (){
        columnButtonSimon(color = MyColors.RED.color, myViewModel)
        columnButtonSimon(color = MyColors.YELLOW.color, myViewModel)
    }
}

@Composable
fun columnButtonSimon(color: MutableState<Color>, myViewModel: MyViewModel){
    Column {
        Button(
            onClick = {

                if (Data.state != State.SEQUENCE && Data.state != State.INPUT && !myViewModel.getPlayStatus().equals("Start")) {
                    myViewModel.increaseUserSecuence(Data.colors.indexOf(color))
                    myViewModel.showButtonPressed(color)
                }
            },
            shape = RectangleShape,
            modifier = Modifier
                .height(200.dp)
                .width(200.dp)
                .padding(50.dp, 50.dp)
            ,
            colors = ButtonDefaults.buttonColors(color.value)
        ){

        }
    }
}

@Composable
fun startIncreaseRound(miViewModel: MyViewModel){
    Row {
        Column {
            Button(
                onClick = {
                    // change the play status
                    miViewModel.changePlayStatus()
                },
                modifier = Modifier
                    .height(200.dp)
                    .width(200.dp)
                    .padding(50.dp, 50.dp)
            ){
                Text(
                    text = miViewModel.getPlayStatus(), textAlign = TextAlign.Center
                )
            }
        }
        Column {
            Button(
                onClick = {
                    if (miViewModel.getPlayStatus().equals("Start")){
                        //nothing
                    } else {
                        // si no pongo esto y le doy al reset, boton y mandar secuencia a la vez, se fastidian los colores de los botones
                        if(Data.state != State.SEQUENCE && Data.state != State.INPUT) {
                            miViewModel.checkSecuence()
                        }
                    }
                },
                modifier = Modifier
                    .height(200.dp)
                    .width(200.dp)
                    .padding(50.dp, 50.dp)
            ){
                Image(
                    painter = painterResource(id = R.drawable.play_arrow),
                    contentDescription = "arrow"
                )
            }
        }
    }
}