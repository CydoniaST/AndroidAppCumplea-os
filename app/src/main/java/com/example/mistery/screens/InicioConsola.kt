package com.example.mistery.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun InicioConsolaScreen(
    onNextPuzzle: () -> Unit
) {
    var showFinalText by remember { mutableStateOf(false) }

    // Cambia el texto después de 3 segundos
    LaunchedEffect(Unit) {
        delay(3000)
        showFinalText = true
    }

    val infiniteTransition = rememberInfiniteTransition(label = "hudAnim")

    // Pulso de brillo en los bordes
    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            tween(1500, easing = LinearEasing),
            RepeatMode.Reverse
        ),
        label = "glowAlpha"
    )

    // Línea de escaneo que baja
    val scanOffset by infiniteTransition.animateFloat(
        initialValue = -300f,
        targetValue = 300f,
        animationSpec = infiniteRepeatable(
            tween(2500, easing = LinearEasing),
            RepeatMode.Restart
        ),
        label = "scanLine"
    )

    // Progreso para las ondas múltiples
    val baseProgress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            tween(2500, easing = LinearEasing),
            RepeatMode.Restart
        ),
        label = "circleExpansion"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF001018)),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val glowColor = Color.Cyan.copy(alpha = glowAlpha)

            // Marco rectangular estilo HUD
            drawRect(
                brush = Brush.linearGradient(
                    listOf(glowColor, Color.Transparent, glowColor)
                ),
                style = Stroke(width = 4f),
                size = size
            )

            // Línea de escaneo
            drawLine(
                color = Color.Cyan.copy(alpha = 0.4f),
                start = Offset(0f, size.height / 2 + scanOffset),
                end = Offset(size.width, size.height / 2 + scanOffset),
                strokeWidth = 2f
            )

            // Ondas concéntricas múltiples (3 círculos desfasados)
            val center = Offset(size.width / 2, size.height / 2)
            val maxRadius = size.minDimension / 2.2f
            val waveCount = 3
            val delayBetween = 1f / waveCount

            for (i in 0 until waveCount) {
                val progress = (baseProgress + i * delayBetween) % 1f
                val radius = maxRadius * progress
                val alpha = 1f - progress

                drawCircle(
                    color = Color.Cyan.copy(alpha = alpha * 0.5f),
                    radius = radius,
                    center = center,
                    style = Stroke(width = 2f)
                )
            }
        }

        // Texto en pantalla
        if (!showFinalText) {
            Text(
                text = "Iniciando Consola...",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Green,
                textAlign = TextAlign.Center
            )
        } else {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .shadow(8.dp, RoundedCornerShape(12.dp))
                    .background(Color(0xFF001822).copy(alpha = 0.85f), RoundedCornerShape(12.dp))
                    .border(2.dp, Color.Cyan, RoundedCornerShape(12.dp))
                    .clickable { onNextPuzzle() }
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Nueva Carta Disponible",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Cyan,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewInicioConsolaScreen() {
    InicioConsolaScreen(
        onNextPuzzle = {}
    )
}
