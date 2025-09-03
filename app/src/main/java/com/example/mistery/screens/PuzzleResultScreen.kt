package com.example.mistery.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mistery.navigation.Navigation
import com.example.mistery.ui.theme.*

@Composable
fun PuzzleResultScreen(
    correctAnswer: Int = 21091999,
    onCorrect: () -> Unit,
    onIncorrect: () -> Unit
) {
    // El usuario escribe texto, lo guardamos como String
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
        Text("Código de activación:", fontSize = 20.sp, color = StarWhite)

        Spacer(Modifier.height(16.dp))

        TextField(
            value = userAnswer,
            onValueChange = { input ->
                // Acepta solo números y máx. 8 dígitos
                userAnswer = input.filter { it.isDigit() }.take(8)
                showError = false
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = {
                showError = false

                // Convierte el texto a Int (null si no es válido)
                val typedInt = userAnswer.toIntOrNull()

                Log.d("PuzzleResult", "typed=$typedInt expected=$correctAnswer")

                if (typedInt == correctAnswer) {
                    onCorrect()
                } else {
                    showError = true
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = CosmicBlue)
        ) {
            Text("Iniciar Consola", color = StarWhite, fontWeight = FontWeight.Bold)
        }
    }

    if (showError) {
        AlertDialog(
            onDismissRequest = { showError = false },
            confirmButton = {
                Button(
                    onClick = {
                        showError = false
                        onIncorrect()
                    }
                ) { Text("Aceptar") }
            },
            title = { Text("Respuesta Incorrecta") },
            text = { Text("Inténtalo de nuevo") }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewPuzzleResultScreen() {
    PuzzleResultScreen(
        onCorrect = {},
        onIncorrect = {}
    )
}
