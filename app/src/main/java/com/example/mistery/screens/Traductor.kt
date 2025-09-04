package com.example.mistery.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mistery.navigation.Screen

@Composable
fun TranslatorScreen(navController: NavController) {
    var russianText by remember { mutableStateOf("") }
    var translatedText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text("Traductor Ruso → Español")

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = russianText,
            onValueChange = { russianText = it },
            label = { Text("Escribe en ruso") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            // Aquí deberías integrar una API real de traducción (ej: Google Translate API)
            translatedText = "Traducción simulada: $russianText"
        }) {
            Text("Traducir")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Resultado: $translatedText")

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.popBackStack() }) {
            Text("Volver")
        }
    }
}


