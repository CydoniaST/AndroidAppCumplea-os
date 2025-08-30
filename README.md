![Badge en Desarrollo](https://img.shields.io/badge/STATUS-En%20desarrollo-green)

# 🌌 Aplicación para compleaños con tematica de Outer Wilds

Una aplicación de Android con temática de **Outer Wilds** para celebrar un cumpleaños, con puzles.

## 📱 Características

### ✨ Pantalla de Bienvenida
- **Animación de agujero negro** con partículas orbitando
- **Campo de estrellas animadas** con efecto de parpadeo
- **Texto de bienvenida** con animaciones suaves
- **Tema espacial** inspirado en Outer Wilds

### 🧩 Sistema de Puzzles
- **Múltiples pantallas de puzzles** con diferentes temáticas
- **Herramientas específicas** para cada puzzle:
  - **Puzzle 1**: Herramientas espaciales (Nave, Energía, Portal)
  - **Puzzle 2**: Herramientas temporales (Rebobinar, Pausar, Avanzar)
  - **Puzzle 3**: Herramientas de exploración (Mapa, Scanner, Señal)
- **Navegación fluida** entre puzzles

### 🎨 Diseño Temático
- **Paleta de colores** inspirada en el espacio:
  - `SpaceBlack`: Negro espacial profundo
  - `DeepSpace`: Azul espacial oscuro
  - `NebulaPurple`: Púrpura nebulosa
  - `OrbitOrange`: Naranja orbital
  - `CosmicBlue`: Azul cósmico
  - `TimeLoopGreen`: Verde temporal
- **Gradientes cósmicos** y efectos visuales
- **Experiencia inmersiva** con pantalla completa

## 📁 Estructura del Proyecto

```
app/src/main/java/com/example/mistery/
├── MainActivity.kt                 # Actividad principal
├── navigation/
│   └── Navigation.kt              # Sistema de navegación
├── screens/
│   ├── WelcomeScreen.kt          # Pantalla de bienvenida con animaciones
│   └── PuzzleScreen.kt           # Pantallas de puzzles
└── ui/theme/
    ├── Color.kt                  # Colores temáticos
    ├── Theme.kt                  # Tema de la aplicación
    └── Type.kt                   # Tipografía
```

## 🛠️ Tecnologías Utilizadas

- **Jetpack Compose** - UI moderna y declarativa
- **Navigation Compose** - Navegación entre pantallas
- **Material 3** - Componentes de diseño
- **Canvas API** - Animaciones personalizadas del agujero negro y estrellas
- **Kotlin** - Lenguaje de programación

## 🎮 Cómo Usar

1. **Pantalla de Bienvenida**: Toca en cualquier parte de la pantalla para comenzar
2. **Puzzles**: Cada puzzle tiene herramientas específicas preparadas para implementar lógica personalizada
3. **Navegación**: Usa los botones "Volver" y "Siguiente" para moverte entre puzzles

## 🔧 Personalización de Puzzles

Para añadir lógica a los puzzles, modifica las funciones en `PuzzleScreen.kt`:

```kotlin
// En ToolButton component
Button(
    onClick = { 
        // Aquí irá la lógica específica del puzzle
        // Ejemplo: cuando se resuelva, llamar onNavigateToNext()
    }
) { ... }
```

- 🧩 Desafíos mentales
- ✨ Efectos visuales impresionantes

¡Disfruta explorando el cosmos en tu día especial! 🎉👨‍🚀
