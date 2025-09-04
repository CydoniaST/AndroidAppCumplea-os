package com.example.mistery.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mistery.R
import com.example.mistery.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun PuzzleLevelThree(navController: NavController, stickerResId: Int? = null) {
    val finalSticker = stickerResId ?: R.drawable.flor

    val messages = listOf(
        "👽: Hola humana, nos hemos enterado telepaticamente de que hoy es tu cumpleaños.",
        "👽: Feliz cumpleaños.",
        finalSticker,
        "👽: Que es eso. ",
        "👽: Una imagen terricola bien rara.",
        "👽: Como regalo queremos darte un codigo que te llevara a los secretos de la galaxia.",
        "👽: 123",
        "👽: Usalo sabiamente",
        "👩‍🚀: Muchas gracias aliensitos.",
        "👩‍🚀: Lo usaré con sabiduria, Besis.",
    )

    var visibleMessages by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        messages.forEachIndexed { index, _ ->
            delay(2500)
            visibleMessages = index + 1
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SpaceBlack)
            .padding(16.dp)
    ) {
        Text(
            text = "Whatsapp del espacio",
            fontSize = 20.sp,
            color = StarWhite,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(DeepSpace)
                .padding(12.dp)
        ) {

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom
            ) {
                messages.take(visibleMessages).forEach { item ->
                    AnimatedVisibility(visible = true) {
                        when (item) {
                            is String -> ChatBubble(
                                message = item,
                                isUser = item.startsWith("👩‍🚀")
                            )
                            is Int -> Row( // el sticker
                                horizontalArrangement = Arrangement.End,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Image(
                                    painter = painterResource(id = item),
                                    contentDescription = "Sticker enviado",
                                    modifier = Modifier
                                        .size(120.dp)
                                        .clip(RoundedCornerShape(16.dp))
                                )
                            }
                        }
                    }
                    Spacer(Modifier.height(8.dp))
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = { navController.navigate("puzzle/result/3") },
            colors = ButtonDefaults.buttonColors(containerColor = CosmicBlue),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Usar codigo secreto", color = StarWhite, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun ChatBubble(message: String, isUser: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(if (isUser) CosmicBlue else BlackHoleGray)
                .padding(12.dp)
        ) {
            Text(
                text = message,
                color = StarWhite,
                fontSize = 16.sp
            )
        }
    }
}
