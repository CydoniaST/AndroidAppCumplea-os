package com.example.mistery.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
    onNavigateToNext: () -> Unit,
    onNavigateBack: () -> Unit
) {
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
        // Header
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = CardDefaults.cardColors(
                containerColor = BlackHoleGray.copy(alpha = 0.8f)
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "🛸 PUZZLE CÓSMICO #$puzzleNumber 🛸",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = OrbitOrange,
                    textAlign = TextAlign.Center
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = getPuzzleDescription(puzzleNumber),
                    fontSize = 14.sp,
                    color = StarWhite,
                    textAlign = TextAlign.Center
                )
            }
        }
        
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
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                // Placeholder para el puzzle
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "🌌",
                        fontSize = 80.sp
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Text(
                        text = "ÁREA DEL PUZZLE",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = TimeLoopGreen,
                        textAlign = TextAlign.Center
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Text(
                        text = "Aquí irá el contenido del puzzle ${puzzleNumber}",
                        fontSize = 16.sp,
                        color = StarWhite.copy(alpha = 0.8f),
                        textAlign = TextAlign.Center
                    )
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    // Herramientas del puzzle
                    getPuzzleTools(puzzleNumber)
                }
            }
        }
        
        // Botones de navegación
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = onNavigateBack,
                colors = ButtonDefaults.buttonColors(
                    containerColor = BlackHoleGray
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Text(
                    text = "← VOLVER",
                    color = StarWhite,
                    fontWeight = FontWeight.Bold
                )
            }
            
            Button(
                onClick = onNavigateToNext,
                colors = ButtonDefaults.buttonColors(
                    containerColor = CosmicBlue
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = "SIGUIENTE →",
                    color = StarWhite,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun getPuzzleTools(puzzleNumber: Int): Unit {
    when (puzzleNumber) {
        1 -> PuzzleOneTools()
        2 -> PuzzleTwoTools()
        3 -> PuzzleThreeTools()
        else -> DefaultPuzzleTools()
    }
}

@Composable
private fun PuzzleOneTools() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = NebulaPurple.copy(alpha = 0.6f)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "🔧 HERRAMIENTAS ESPACIALES",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = OrbitOrange
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ToolButton("🛸", "Nave")
                ToolButton("⚡", "Energía")
                ToolButton("🌀", "Portal")
            }
        }
    }
}

@Composable
private fun PuzzleTwoTools() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = TimeLoopGreen.copy(alpha = 0.6f)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "⏰ HERRAMIENTAS TEMPORALES",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = StarWhite
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ToolButton("⏮️", "Rebobinar")
                ToolButton("⏸️", "Pausar")
                ToolButton("⏭️", "Avanzar")
            }
        }
    }
}

@Composable
private fun PuzzleThreeTools() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = OrbitOrange.copy(alpha = 0.6f)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "🧭 HERRAMIENTAS DE EXPLORACIÓN",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = SpaceBlack
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ToolButton("🗺️", "Mapa")
                ToolButton("🔍", "Scanner")
                ToolButton("📡", "Señal")
            }
        }
    }
}

@Composable
private fun DefaultPuzzleTools() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = CosmicBlue.copy(alpha = 0.6f)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "🌌 HERRAMIENTAS UNIVERSALES",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = StarWhite
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ToolButton("⭐", "Estrella")
                ToolButton("🌎", "Planeta")
                ToolButton("🌙", "Luna")
            }
        }
    }
}

@Composable
private fun ToolButton(emoji: String, name: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { /* Aquí irá la lógica del puzzle */ },
            colors = ButtonDefaults.buttonColors(
                containerColor = StarWhite.copy(alpha = 0.2f)
            ),
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(12.dp))
                .border(
                    2.dp,
                    StarWhite.copy(alpha = 0.5f),
                    RoundedCornerShape(12.dp)
                )
        ) {
            Text(
                text = emoji,
                fontSize = 24.sp
            )
        }
        
        Spacer(modifier = Modifier.height(4.dp))
        
        Text(
            text = name,
            fontSize = 10.sp,
            color = StarWhite.copy(alpha = 0.8f),
            textAlign = TextAlign.Center
        )
    }
}

private fun getPuzzleDescription(puzzleNumber: Int): String {
    return when (puzzleNumber) {
        1 -> "Navega por el sistema solar y encuentra los fragmentos de memoria perdidos"
        2 -> "Manipula el tiempo para resolver paradojas temporales"
        3 -> "Explora planetas misteriosos y descifra sus secretos"
        else -> "Resuelve este enigma cósmico para continuar tu aventura"
    }
}
