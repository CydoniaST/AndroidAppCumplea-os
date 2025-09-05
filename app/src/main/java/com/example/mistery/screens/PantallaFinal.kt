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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mistery.ui.theme.CosmicBlue
import com.example.mistery.ui.theme.StarWhite
import kotlin.random.Random
import com.example.mistery.R
import androidx.compose.ui.graphics.drawscope.rotate


@Composable
fun ConfettiScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.cum),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.1f))
        )


        ConfettiAnimation()


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(32.dp)
        ) {

            val scale by rememberInfiniteTransition(label = "title-scale")
                .animateFloat(
                    initialValue = 1f,
                    targetValue = 1.05f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(1200, easing = EaseInOut),
                        repeatMode = RepeatMode.Reverse
                    ),
                    label = "scale"
                )

            Text(
                text = "¡Felicidades! 🎉",
                color = Color.Black,
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.scale(scale)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { navController.navigate("welcome?showSecret=true") },
                colors = ButtonDefaults.buttonColors(containerColor = CosmicBlue),
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth(0.6f),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)
            ) {
                Text("Celebrar cumpleaños", color = StarWhite, fontWeight = FontWeight.Bold, fontSize = 18.sp)
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
                color = listOf(
                    Color(0xFFFF6F61),
                    Color(0xFF6BCB77),
                    Color(0xFF4D96FF),
                    Color(0xFFF7D060),
                    Color(0xFFE96479)
                ).random(),
                size = Random.nextFloat() * 10f + 8f,
                speed = Random.nextFloat() * 300f + 200f,
                rotationSpeed = Random.nextFloat() * 360f
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
            val x = (confetti.x * width + kotlin.math.sin(time * 10 + confetti.x * 20) * 40) % width
            val y = (confetti.y * height + time * confetti.speed) % height
            val rotation = time * confetti.rotationSpeed

            rotate(rotation, pivot = Offset(x, y)) {
                drawRoundRect(
                    color = confetti.color,
                    topLeft = Offset(x, y),
                    size = androidx.compose.ui.geometry.Size(confetti.size, confetti.size * 2),
                    cornerRadius = CornerRadius(6f, 6f)
                )
            }
        }
    }
}

data class ConfettiPiece(
    val x: Float,
    val y: Float,
    val color: Color,
    val size: Float,
    val speed: Float,
    val rotationSpeed: Float
)