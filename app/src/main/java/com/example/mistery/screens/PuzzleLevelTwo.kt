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
    val baseText = ""

    var colorFilter by remember { mutableStateOf<ColorFilter?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = androidx.compose.ui.graphics.Brush.verticalGradient(
                    listOf(Color(0xFF0D0D0D), Color(0xFF1A1A2E), Color(0xFF16213E))
                )
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(8.dp)
                .background(Color.Black.copy(alpha = 0.3f), RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.puzzle_background),
                contentDescription = "Puzzle Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                colorFilter = colorFilter
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Elige un filtro:",
                color = Color.White,
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                FilterButton(
                    text = "Verde",
                    color = Color(0xFF16DB65),
                    onClick = {
                        colorFilter = greenFilter()
                    },
                    modifier = Modifier.weight(1f)
                )
                FilterButton(
                    text = "Blanco",
                    color = Color(0xFFF5F5F5),
                    onClick = {
                        colorFilter = whiteFilter()
                    },
                    modifier = Modifier.weight(1f)
                )
                FilterButton(
                    text = "Rojo",
                    color = Color(0xFFDB162F),
                    onClick = {
                        colorFilter = redFilter()
                    },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun FilterButton(
    text: String,
    color: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        modifier = modifier.padding(horizontal = 4.dp),
        colors = androidx.compose.material3.ButtonDefaults.buttonColors(
            containerColor = color.copy(alpha = 0.85f),
            contentColor = Color.Black
        )
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
        )
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
        1f, 0f, 0f, 0f, 0f,
        0f, 1f, 0f, 0f, 0f,
        0f, 0f, 1f, 0f, 0f,
        0f, 0f, 0f, 1f, 0f
    )
    return ColorFilter.colorMatrix(androidx.compose.ui.graphics.ColorMatrix(matrix))
}
