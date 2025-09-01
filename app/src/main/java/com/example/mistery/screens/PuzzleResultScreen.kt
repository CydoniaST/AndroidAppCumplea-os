// PuzzleResultScreen.kt
package com.example.mistery.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mistery.ui.theme.*
@Composable
fun PuzzleResultScreen(
    correctAnswer: String = "42", // respuesta correcta
    onCorrect: () -> Unit,        // si es correcta → siguiente puzzle
    onIncorrect: () -> Unit       // si es incorrecta → volver al puzzle 1
) {
    var userAnswer by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SpaceBlack)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Introduce tu solución:",
            fontSize = 20.sp,
            color = StarWhite
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = userAnswer,
            onValueChange = { userAnswer = it },
            label = { Text("Respuesta") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (userAnswer.trim() == correctAnswer) {
                    onCorrect()
                } else {
                    showError = true // solo mostramos el popup
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = CosmicBlue
            )
        ) {
            Text("Verificar", color = StarWhite, fontWeight = FontWeight.Bold)
        }
    }

    // Pop-up cuando la respuesta es incorrecta
    if (showError) {
        AlertDialog(
            onDismissRequest = { /* no hacer nada, solo cerrar con botón */ },
            confirmButton = {
                Button(
                    onClick = {
                        showError = false
                        onIncorrect() // ahora sí volvemos al puzzle 1
                    }
                ) {
                    Text("Aceptar")
                }
            },
            title = { Text("Respuesta Incorrecta") },
            text = { Text("Inténtalo de nuevo") }
        )
    }
}

