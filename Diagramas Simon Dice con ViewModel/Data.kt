package com.example.simondicerec

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color

object Data {
    var round = mutableStateOf(0)
    var playStatus = mutableStateOf("Start")
    var UserSecuence = mutableListOf<Int>()
    var botSecuence = mutableListOf<Int>()
    var record = mutableStateOf(0)
    var state = State.START
    var colors = listOf(
        MyColors.BLUE.color,
        MyColors.GREEN.color,
        MyColors.RED.color,
        MyColors.YELLOW.color
    )
    var colorsMyColors = MyColors.values()
    var colorFlag: Color = Color.White
    var colorUserFlag: Color = Color.White


}
enum class MyColors(val color: MutableState<Color>){
    // go from 0 to 3
    BLUE(mutableStateOf(Color.Blue)),
    GREEN(mutableStateOf(Color.Green)),
    RED(mutableStateOf(Color.Red)),
    YELLOW(mutableStateOf(Color.Yellow))
}
enum class State {
    START,
    SEQUENCE,
    WAITING,
    INPUT,
    CHECKING,
    FINISH
}