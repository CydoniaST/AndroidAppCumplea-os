package com.example.mistery.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.example.mistery.ui.theme.*
import kotlin.math.*
import kotlin.random.Random

@Composable
fun SpaceBackground(modifier: Modifier = Modifier) {
    var stars by remember { mutableStateOf(generateStars()) }
    var blackHoleParticles by remember { mutableStateOf(generateBlackHoleParticles()) }

    val infiniteTransition = rememberInfiniteTransition(label = "space_animation")
    val time by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 2 * PI.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(20000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "time"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.radialGradient(
                    colors = listOf(
                        DeepSpace,
                        SpaceBlack,
                        Color.Black
                    ),
                    radius = 1000f
                )
            )
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawStars(stars, time)
            drawBlackHole(center, time)
            drawBlackHoleParticles(blackHoleParticles, center, time)
        }
    }
}

private fun generateStars(): List<Star> {
    return (1..150).map {
        Star(
            x = Random.nextFloat(),
            y = Random.nextFloat(),
            size = Random.nextFloat() * 3f + 1f,
            brightness = Random.nextFloat() * 0.8f + 0.2f,
            twinkleSpeed = Random.nextFloat() * 2f + 1f
        )
    }
}

private fun generateBlackHoleParticles(): List<BlackHoleParticle> {
    return (1..50).map {
        BlackHoleParticle(
            angle = Random.nextFloat() * 2 * PI.toFloat(),
            distance = Random.nextFloat() * 200f + 50f,
            speed = Random.nextFloat() * 0.5f + 0.2f,
            size = Random.nextFloat() * 2f + 1f
        )
    }
}

private fun DrawScope.drawStars(stars: List<Star>, time: Float) {
    stars.forEach { star ->
        val twinkle = sin(time * star.twinkleSpeed) * 0.3f + 0.7f
        val alpha = (star.brightness * twinkle).coerceIn(0f, 1f)

        drawCircle(
            color = Color.White.copy(alpha = alpha),
            radius = star.size,
            center = Offset(
                star.x * size.width,
                star.y * size.height
            )
        )
    }
}

private fun DrawScope.drawBlackHole(center: Offset, time: Float) {
    val blackHoleRadius = 120f

    drawCircle(
        brush = Brush.radialGradient(
            colors = listOf(
                Color.Black,
                BlackHoleGray.copy(alpha = 0.8f),
                NebulaPurple.copy(alpha = 0.4f),
                Color.Transparent
            ),
            radius = blackHoleRadius * 2
        ),
        radius = blackHoleRadius * 2,
        center = center
    )

    drawCircle(
        color = Color.Black,
        radius = blackHoleRadius * 0.4f,
        center = center
    )
}

private fun DrawScope.drawBlackHoleParticles(
    particles: List<BlackHoleParticle>,
    center: Offset,
    time: Float
) {
    particles.forEach { particle ->
        val currentAngle = particle.angle + time * particle.speed
        val spiralRadius = particle.distance * (1f - (time * 0.1f) % 1f)

        val x = center.x + cos(currentAngle) * spiralRadius
        val y = center.y + sin(currentAngle) * spiralRadius

        val alpha = (spiralRadius / particle.distance).coerceIn(0f, 1f)

        drawCircle(
            color = OrbitOrange.copy(alpha = alpha * 0.7f),
            radius = particle.size,
            center = Offset(x, y)
        )
    }
}
