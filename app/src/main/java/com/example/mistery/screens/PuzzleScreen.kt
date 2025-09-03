package com.example.mistery.screens

import PuzzleLevelOne
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mistery.R
import com.example.mistery.ui.theme.*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PuzzleScreen(
    puzzleNumber: Int,
    onNavigateToResult: () -> Unit,
    onBack: () -> Unit
) {
    var showIntro by remember { mutableStateOf(true) }

    SpaceBackground()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        SpaceBlack,
                        DeepSpace,
                        NebulaPurple.copy(alpha = 0.3f)
                    )
                )
            )
            .padding(16.dp)
    ) {
        BackButton(onBack = onBack)

        // Área principal del puzzle
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(bottom = 16.dp),
            colors = CardDefaults.cardColors(
                containerColor = DeepSpace.copy(alpha = 0.9f)
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
                    .clickable { if (showIntro) showIntro = false },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.interfaz),
                    contentDescription = "Interfaz",
                    modifier = Modifier
                        .matchParentSize()
                        .clip(RoundedCornerShape(32.dp))
                        .shadow(12.dp, RoundedCornerShape(32.dp))
                        .graphicsLayer(alpha = 0.8f),
                    contentScale = ContentScale.Crop
                )

                if (puzzleNumber == 1) {
                    if (showIntro) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .background(
                                    color = androidx.compose.ui.graphics.Color.Black.copy(alpha = 0.6f), // 🔹 blanco semitransparente
                                    shape = RoundedCornerShape(16.dp)
                                )
                                .padding(16.dp)
                        ) {
                            Text(
                                text = "Eres la capitana de la nave Outer y te encuentras en el espacio profundo con el objetivo de encontrar civilizaciones inteligentes. \n \n Hace unos días sufristeis un accidente por culpa de un asteroide y justo te toca solucionarlo el dia de tu cumpleaños, asique decides ir al puente y ver el diario de abordo antes de ponerte en marcha.",
                                fontSize = 18.sp,
                                color = StarWhite,
                                textAlign = TextAlign.Center
                            )
                        }
                    } else {
                        PuzzleLevelOne()
                    }
                } else {
                    PuzzleLevelTwo()
                }
            }
        }

        // Botones inferiores
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { showIntro = true },
                colors = ButtonDefaults.buttonColors(
                    containerColor = BlackHoleGray
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Text(
                    text = "Texto",
                    color = StarWhite,
                    fontWeight = FontWeight.Bold
                )
            }

            Button(
                onClick = onNavigateToResult,
                colors = ButtonDefaults.buttonColors(
                    containerColor = CosmicBlue
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = "Solución",
                    color = StarWhite,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
