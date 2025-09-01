package com.example.mistery.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mistery.ui.theme.*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PuzzleScreen(
    puzzleNumber: Int,
    onNavigateToResult: () -> Unit
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
                if (puzzleNumber == 1) {
                    if (showIntro) {
                        Text(
                            text = "Aquí podrás escribir lo que quieras más adelante.\n\n(Toca la pantalla para continuar)",
                            fontSize = 18.sp,
                            color = StarWhite,
                            textAlign = TextAlign.Center
                        )
                    } else {
                        Text(
                            text = "Aquí aparecerá el puzzle del nivel 1 🧩",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = TimeLoopGreen,
                            textAlign = TextAlign.Center
                        )
                    }
                } else {
                    Text(
                        text = "Puzzle $puzzleNumber",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = TimeLoopGreen,
                        textAlign = TextAlign.Center
                    )
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
