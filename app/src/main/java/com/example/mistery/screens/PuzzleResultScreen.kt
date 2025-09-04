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
    puzzleNumber: Int,
    correctAnswer: Int = 21091999,
    correctText: String = "felicidades",
    onCorrect: () -> Unit,
    onIncorrect: () -> Unit,
    onBack: () -> Unit
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
        BackButton(onBack = onBack)

        Text(
            text = if (puzzleNumber == 1) "Código de activación:" else "Traducción:",
            fontSize = 20.sp,
            color = StarWhite
        )

        Spacer(Modifier.height(16.dp))

        TextField(
            value = userAnswer,
            onValueChange = { input ->
                if (puzzleNumber == 1) {

                    userAnswer = input.filter { it.isDigit() }.take(8)
                } else {

                    userAnswer = input
                }
                showError = false
            },
            singleLine = true,
            keyboardOptions = if (puzzleNumber == 1) {
                KeyboardOptions(keyboardType = KeyboardType.Number)
            } else {
                KeyboardOptions.Default
            }
        )

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = {
                showError = false
                if (puzzleNumber == 1) {

                    val typedInt = userAnswer.toIntOrNull()
                    if (typedInt == correctAnswer) {
                        onCorrect()
                    } else {
                        showError = true
                    }
                } else {

                    if (userAnswer.trim().equals(correctText, ignoreCase = true)) {
                        onCorrect()
                    } else {
                        showError = true
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = CosmicBlue)
        ) {
            Text(
                if (puzzleNumber == 1) "Iniciar Consola" else "Comprobar",
                color = StarWhite,
                fontWeight = FontWeight.Bold
            )
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

@Preview(showBackground = true, name = "Puzzle 1")
@Composable
private fun PreviewPuzzleResultScreenP1() {
    PuzzleResultScreen(
        puzzleNumber = 1,
        onCorrect = {},
        onIncorrect = {},
        onBack = {}
    )
}

@Preview(showBackground = true, name = "Puzzle 2")
@Composable
private fun PreviewPuzzleResultScreenP2() {
    PuzzleResultScreen(
        puzzleNumber = 2,
        correctText = "felicidades",
        onCorrect = {},
        onIncorrect = {},
        onBack = {}
    )
}
