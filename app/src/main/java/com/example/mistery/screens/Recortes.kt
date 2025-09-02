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
        Estoy aqui sentada con mi tarta y esperando a que Erik acabe de calibrar 
        los radares de la nave.
        Y es que hace ya 1 mes que partimos de la tierra 
        para buscar una solucion para la Tierra. 
        
        Hay que darse prisa, pero no encuentro el codigo de activación 
        de la consola principal.      
        
        Entrada x-039899
    """.trimIndent()

    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    var showMask by remember { mutableStateOf(false) } // control de visibilidad del Canvas

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
            // Texto del diario
            Text(
                text = baseText,
                fontSize = 18.sp,
                lineHeight = 26.sp,
                color = Color(0xFF2d1b0d),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para mostrar/ocultar la máscara
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

        // Solo dibujar el canvas si showMask == true
        if (showMask) {
            Canvas(
                modifier = Modifier
                    .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
                    .size(320.dp, 620.dp)
                    .graphicsLayer(alpha = 0.99f)
                    .pointerInput(Unit) {
                        detectDragGestures { change, dragAmount ->
                            change.consume()
                            offsetX += dragAmount.x
                            offsetY += dragAmount.y
                        }
                    }
            ) {
                // Fondo negro
                drawRoundRect(
                    color = Color.Black.copy(alpha = 0.85f),
                    topLeft = Offset.Zero,
                    size = size,
                    cornerRadius = CornerRadius(32f, 32f)
                )

                // Agujeros sobre los números clave
                val holePositions = listOf(
                    Offset(280f, 35f),   // 2
                    Offset(320f, 35f),   // 1
                    Offset(350f, 35f),  // 0
                    Offset(450f, 35f),  // 9
                    Offset(420f, 590f),  // 1
                    Offset(300f, 1280f),  // 9
                    Offset(370f, 1280f),  // 9
                    Offset(390f, 1280f)   // 9
                )
                val starPath = Path().apply {
                    star(center = Offset(4500f, 1400f), radius = 30f)
                }

                drawPath(
                    path = starPath,
                    color = Color.Yellow,
                    style = Fill
                )

                holePositions.forEach { pos ->
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
fun Path.star(center: Offset, radius: Float, points: Int = 5) {
    val angle = (2.0 * Math.PI / points).toFloat()
    val halfAngle = angle / 2f

    moveTo(
        (center.x + radius * kotlin.math.cos(0.0)).toFloat(),
        (center.y + radius * kotlin.math.sin(0.0)).toFloat()
    )

    for (i in 1 until points * 2) {
        val r = if (i % 2 == 0) radius else radius / 2
        val x = center.x + r * kotlin.math.cos(i * halfAngle.toDouble())
        val y = center.y + r * kotlin.math.sin(i * halfAngle.toDouble())
        lineTo(x.toFloat(), y.toFloat())
    }

    close()
}
