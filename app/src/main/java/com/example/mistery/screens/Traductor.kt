package com.example.mistery.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TranslatorScreen(navController: NavController) {
    var russianText by remember { mutableStateOf("") }
    var translatedText by remember { mutableStateOf("") }
    var isTranslating by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black) // fondo oscuro
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            "Traductor Alienigena",
            color = Color.White
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = russianText,
            onValueChange = { russianText = it },
            label = { Text("Escribe en ruso", color = Color.White) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.Gray,
                cursorColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (russianText.isNotBlank()) {
                    isTranslating = true
                    TranslatorHelper.translate(
                        text = russianText,
                        onResult = {
                            translatedText = it
                            isTranslating = false
                        },
                        onError = {
                            translatedText = "Error al traducir"
                            isTranslating = false
                        }
                    )
                }
            }
        ) {
            Text("Traducir")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = if (isTranslating) "Traduciendo..." else "Resultado: $translatedText",
            color = Color.White
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.popBackStack() }) {
            Text("Volver")
        }
    }
}
