package com.example.mistery.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import com.example.mistery.R
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mistery.ui.theme.*
import kotlin.math.*
import kotlin.random.Random

data class Star(
    val x: Float,
    val y: Float,
    val size: Float,
    val brightness: Float,
    val twinkleSpeed: Float
)

data class BlackHoleParticle(
    val angle: Float,
    val distance: Float,
    val speed: Float,
    val size: Float
)

@Composable
fun WelcomeScreen(
    onNavigateToPuzzle: () -> Unit,
    showSecret: Boolean
) {
    var stars by remember { mutableStateOf(generateStars()) }
    var blackHoleParticles by remember { mutableStateOf(generateBlackHoleParticles()) }

    var showDialog by remember { mutableStateOf(false) }

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

    val textAlpha by infiniteTransition.animateFloat(
        initialValue = 0.7f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "text_alpha"
    )

    Box(
        modifier = Modifier
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
            .clickable { onNavigateToPuzzle() }
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawStars(stars, time)
            drawBlackHole(center, time)
            drawBlackHoleParticles(blackHoleParticles, center, time)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(200.dp))

            Text(
                text = "Viaje",
                fontSize = 32.sp,
                color = StarWhite,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Center,
                modifier = Modifier.alpha(textAlpha)
            )

            Text(
                text = "Estelar",
                fontSize = 40.sp,
                color = OrbitOrange,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.alpha(textAlpha)
            )

            Spacer(modifier = Modifier.height(48.dp))


            if (showSecret) {
                androidx.compose.material3.Button(
                    onClick = { showDialog = true },
                    modifier = Modifier.fillMaxWidth(),
                    colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                        containerColor = CosmicBlue
                    )
                ) {
                    Text(
                        text = "Código secreto",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = StarWhite
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }

            Spacer(modifier = Modifier.weight(10f))

            Text(
                text = "Toca la pantalla",
                fontSize = 16.sp,
                color = CosmicBlue,
                textAlign = TextAlign.Center,
                modifier = Modifier.alpha(textAlpha * 0.8f)
            )
        }

        if (showDialog) {
            SecretCodePanel(
                code = "023",
                onClose = { showDialog = false }
            )
        }
    }
}

@Composable
fun SecretCodePanel(
    code: String,
    onClose: () -> Unit
) {

    var visible by remember { mutableStateOf(false) }


    LaunchedEffect(Unit) {
        visible = true
    }

    val scale by animateFloatAsState(
        targetValue = if (visible) 1f else 0.3f,
        animationSpec = tween(
            durationMillis = 600,
            easing = FastOutSlowInEasing
        ),
        label = "scaleAnim"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.6f))
            .clickable { onClose() },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .scale(scale)
        ) {
            androidx.compose.foundation.Image(
                painter = painterResource(id = R.drawable.pad),
                contentDescription = "Panel secreto",
                modifier = Modifier.size(300.dp)
            )

            Text(
                text = code,
                fontSize = 48.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Cyan,
                modifier = Modifier
                    .align(Alignment.Center)
            )
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
