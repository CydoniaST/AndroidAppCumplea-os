package com.example.mistery.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mistery.ui.theme.CosmicBlue
import com.example.mistery.ui.theme.StarWhite
import kotlin.random.Random
import com.example.mistery.R
@Composable
fun ConfettiScreen(
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black), // se sustituirá con tu imagen
        contentAlignment = Alignment.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.cum),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize()
        )


        ConfettiAnimation()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.BottomCenter).padding(32.dp)
        ) {
            Text(
                text = "¡Felicidades! 🎂",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate("welcome") },
                colors = ButtonDefaults.buttonColors(containerColor = CosmicBlue)
            ) {
                Text("Finalizar", color = StarWhite, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun ConfettiAnimation() {
    val confettiCount = 120
    val confettiList = remember {
        List(confettiCount) {
            ConfettiPiece(
                x = Random.nextFloat(),
                y = Random.nextFloat(),
                color = listOf(Color.Red, Color.Yellow, Color.Green, Color.Cyan, Color.Magenta, Color.White).random(),
                size = Random.nextFloat() * 12f + 6f,
                speed = Random.nextFloat() * 200f + 150f,
                rotation = Random.nextFloat() * 360f
            )
        }
    }

    val infiniteTransition = rememberInfiniteTransition(label = "confetti")
    val time by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(6000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "time"
    )

    Canvas(modifier = Modifier.fillMaxSize()) {
        val width = size.width
        val height = size.height

        confettiList.forEach { confetti ->
            val x = confetti.x * width
            val y = (confetti.y * height + time * confetti.speed) % height

            drawRoundRect(
                color = confetti.color,
                topLeft = Offset(x, y),
                size = androidx.compose.ui.geometry.Size(confetti.size, confetti.size * 2),
                cornerRadius = CornerRadius(4f, 4f)
            )
        }
    }
}

data class ConfettiPiece(
    val x: Float,
    val y: Float,
    val color: Color,
    val size: Float,
    val speed: Float,
    val rotation: Float
)
