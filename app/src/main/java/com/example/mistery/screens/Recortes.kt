import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt
import com.example.mistery.R
@SuppressLint("ResourceType")
@Composable
fun PuzzleLevelOne() {
    val baseText = """
        Diario del 12-10-2190
        
        Es mi cumpleaños y estamos perdidos en el espacio profundo. 
        Estoy aqui sentada con mi tarta y esperando a que Riebeck acabe de calibrar 
        los radares de la nave.
        Y es que hace ya 1 mes que partimos de la Tierra en busca de vida, hemos recorrido miles de años luz pero no hemos encontrado nada aún. 
        
        Primero debo reiniciar la nave despues de lso arreglos que hemos hecho, pero no encuentro el codigo de activación 
        de la consola principal.      
        
        Entrada X-039899
    """.trimIndent()

    // Estados de cada canvas
    var offsetX1 by remember { mutableStateOf(0f) }
    var offsetY1 by remember { mutableStateOf(0f) }

    var offsetX2 by remember { mutableStateOf(0f) }
    var offsetY2 by remember { mutableStateOf(0f) }

    var showMask by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFFd2b48c), // pergamino
                        Color(0xFFa67c52)  // marrón envejecido
                    )
                )
            )
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = baseText,
                fontSize = 18.sp,
                lineHeight = 26.sp,
                color = Color(0xFF2d1b0d),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            IconButton(
                onClick = { showMask = !showMask },
                modifier = Modifier.size(64.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.button),
                    contentDescription = if (showMask) "Ocultar Plantilla" else "Mostrar Plantilla"
                )
            }
        }

        if (showMask) {
            // ---------- CANVAS 1 ----------
            Canvas(
                modifier = Modifier
                    .offset { IntOffset(offsetX1.roundToInt(), offsetY1.roundToInt()) }
                    .size(320.dp, 300.dp) // la mitad superior
                    .graphicsLayer(alpha = 0.99f)
                    .pointerInput(Unit) {
                        detectDragGestures { change, dragAmount ->
                            change.consume()
                            offsetX1 += dragAmount.x
                            offsetY1 += dragAmount.y
                        }
                    }
            ) {
                drawRoundRect(
                    color = Color.Black.copy(alpha = 0.85f),
                    topLeft = Offset.Zero,
                    size = size,
                    cornerRadius = CornerRadius(32f, 32f)
                )

                // Agujeros de la parte superior
                val holePositions1 = listOf(
                    Offset(280f, 35f),   // 2
                    Offset(320f, 35f),   // 1
                    Offset(350f, 35f),   // 0
                    Offset(450f, 35f),   // 9
                    Offset(420f, 590f)  // 1
                )
                holePositions1.forEach { pos ->
                    drawCircle(
                        color = Color.Transparent,
                        radius = 20f,
                        center = pos,
                        blendMode = BlendMode.Clear
                    )
                }
            }

            // ---------- CANVAS 2 ----------
            Canvas(
                modifier = Modifier
                    .offset { IntOffset(offsetX2.roundToInt(), offsetY2.roundToInt()) }
                    .size(320.dp, 300.dp) // la mitad inferior
                    .graphicsLayer(alpha = 0.99f)
                    .pointerInput(Unit) {
                        detectDragGestures { change, dragAmount ->
                            change.consume()
                            offsetX2 += dragAmount.x
                            offsetY2 += dragAmount.y
                        }
                    }
            ) {
                drawRoundRect(
                    color = Color.Black.copy(alpha = 0.85f),
                    topLeft = Offset.Zero,
                    size = size,
                    cornerRadius = CornerRadius(32f, 32f)
                )

                // Agujeros de la parte inferior
                val holePositions2 = listOf(

                    Offset(300f, 580f),  // 9
                    Offset(370f, 580f),  // 9
                    Offset(390f, 580f)   // 9
                )
                holePositions2.forEach { pos ->
                    drawCircle(
                        color = Color.Transparent,
                        radius = 20f,
                        center = pos,
                        blendMode = BlendMode.Clear
                    )
                }
            }
        }
    }
}
