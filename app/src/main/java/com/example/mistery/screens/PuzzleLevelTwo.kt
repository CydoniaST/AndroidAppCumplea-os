package com.example.mistery.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mistery.R
import com.example.mistery.navigation.Screen
import com.example.mistery.ui.theme.StarWhite
import com.google.mlkit.nl.translate.Translator

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PuzzleLevelTwo(navController: NavController) {
    val baseText = """
        
    """.trimIndent()

    val solutionText = """
        Текст скрытого сообщения
    """.trimIndent()

    var currentText by remember { mutableStateOf(baseText) }
    var colorFilter by remember { mutableStateOf<ColorFilter?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Image(
            painter = painterResource(id = R.drawable.puzzle_background),
            contentDescription = "Puzzle Image",
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            colorFilter = colorFilter
        )

        // Texto
        Text(
            text = currentText,
            fontSize = 18.sp,
            color = StarWhite,
            modifier = Modifier.padding(16.dp)
        )

        // Botones
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = { colorFilter = greenFilter() }) {
                    Text("Verde")
                }
                Button(onClick = { colorFilter = whiteFilter() }) {
                    Text("Blanco")
                }
                Button(onClick = { colorFilter = redFilter() }) {
                    Text("Rojo")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {

            }
        }
    }
}
fun redFilter(): ColorFilter {

    val matrix = floatArrayOf(
        1f, 0f, 0f, 0f, 0f,
        0f, 0f, 0f, 0f, 0f,
        0f, 0f, 0f, 0f, 0f,
        0f, 0f, 0f, 1f, 0f
    )
    return ColorFilter.colorMatrix(androidx.compose.ui.graphics.ColorMatrix(matrix))
}

fun greenFilter(): ColorFilter {

    val matrix = floatArrayOf(
        0f, 0f, 0f, 0f, 0f,
        0f, 1f, 0f, 0f, 0f,
        0f, 0f, 0f, 0f, 0f,
        0f, 0f, 0f, 1f, 0f
    )
    return ColorFilter.colorMatrix(androidx.compose.ui.graphics.ColorMatrix(matrix))
}

fun whiteFilter(): ColorFilter {

    val matrix = floatArrayOf(
        1f, 0f, 0f, 0f, 0f,  // rojo
        0f, 1f, 0f, 0f, 0f,  // verde
        0f, 0f, 1f, 0f, 0f,  // azul
        0f, 0f, 0f, 1f, 0f   // alfa
    )
    return ColorFilter.colorMatrix(androidx.compose.ui.graphics.ColorMatrix(matrix))
}
