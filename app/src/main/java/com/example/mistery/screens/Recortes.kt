import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mistery.R
import com.example.mistery.ui.theme.StarWhite
import kotlin.math.roundToInt

@SuppressLint("ResourceType")
@Composable
fun PuzzleLevelOne() {
    val baseText = """
        Diario del 12-10-2190
        
        Es mi cumpleaños y estamos perdidos en el espacio profundo. 
        Estoy aqui sentada con mi tarta y esperando a que Riebeck acabe de calibrar 
        los radares de la nave.
        Y es que hace ya 1 mes que partimos de la Tierra en busca de vida, hemos recorrido miles de años luz pero no hemos encontrado nada aún. 
        
        Ahora que todo está en orden hay que reiniciar la consola de la nave, pero no encuentro el codigo de activación 
        de la consola principal.      
        
        Entrada X-039899
    """.trimIndent()


    var offsetX1 by remember { mutableStateOf(0f) }
    var offsetY1 by remember { mutableStateOf(0f) }

    var offsetX2 by remember { mutableStateOf(0f) }
    var offsetY2 by remember { mutableStateOf(0f) }

    var showMask by remember { mutableStateOf(false) }

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

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = baseText,
                fontSize = 18.sp,
                lineHeight = 26.sp,
                color = StarWhite,
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

            Canvas(
                modifier = Modifier
                    .offset { IntOffset(offsetX1.roundToInt(), offsetY1.roundToInt()) }
                    .size(320.dp, 300.dp)
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


                val holePositions1 = listOf(
                    Offset(280f, 35f),   // 2
                    Offset(320f, 35f),   // 1
                    Offset(350f, 35f),   // 0
                    Offset(450f, 35f),   // 9
                    Offset(415f, 580f)  // 1
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


            Canvas(
                modifier = Modifier
                    .offset { IntOffset(offsetX2.roundToInt(), offsetY2.roundToInt()) }
                    .size(320.dp, 300.dp)
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


                val holePositions2 = listOf(

                    Offset(315f, 580f),  // 9
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
