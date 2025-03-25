package com.example.calculator

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val buttonList= listOf(
    "C","(",")","/",
    "1","2","3","*",
    "4","5","6","+",
    "7","8","9","-",
    "AC","0",".","="
)
@Composable
fun Calculator(viewModel: CalculatorViewModel) {
    val equationText by viewModel.equationText.observeAsState()
    val resultText by viewModel.resultText.observeAsState()

    Box {
        Column(
            modifier= Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.End
        ) {
            Spacer(modifier = Modifier.height(2.dp))
            Text(text =equationText.toString(),
                style = TextStyle(fontSize =50.sp,
                    textAlign = TextAlign.End),
                maxLines = 5,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(text = resultText.toString(),
                style = TextStyle(fontSize =50.sp,
                    textAlign = TextAlign.End),
                maxLines = 5,
                overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.height(10.dp))
            LazyVerticalGrid(columns = GridCells.Fixed(4)){
                items(buttonList){
                    CalculatorButton(btn = it, clicked = {
                        viewModel.buttonOnClick(it)
                    })
                }
            }
        }
    }

}

@Composable
fun CalculatorButton(btn:String, clicked:()->Unit){
    Box(modifier = Modifier.padding(8.dp)) {
        FloatingActionButton(
            onClick =clicked,
            modifier = Modifier.size(80.dp),
            contentColor = Color.White,
            containerColor = buttonColor(btn = btn)
        ) {
            Text(text = btn,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun buttonColor(btn:String):Color{
    return when (btn) {
        "C", "AC" -> Color(0xFFAC1721)
        "/", "*", "+", "-", "=" -> Color(0xFFFB8C00)
        else -> Color(0xFF03F4EA)
    }
}