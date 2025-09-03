package com.example.mistery.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mistery.screens.InicioConsolaScreen
import com.example.mistery.screens.NuevaCartaScreen
import com.example.mistery.screens.PuzzleResultScreen
import com.example.mistery.screens.PuzzleScreen
import com.example.mistery.screens.WelcomeScreen

sealed class Screen(val route: String) {
    object Welcome : Screen("welcome")
    object Puzzle : Screen("puzzle/{puzzleNumber}") {
        fun createRoute(puzzleNumber: Int) = "puzzle/$puzzleNumber"
    }
    object PuzzleResult : Screen("puzzle/result/{puzzleNumber}") {
        fun createRoute(puzzleNumber: Int) = "puzzle/result/$puzzleNumber"
    }
    object InicioConsola : Screen("InicioConsola")
    object NuevaCarta : Screen("nuevaCarta")   //
}

@Composable
fun Navigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Welcome.route
    ) {
        // Pantalla de bienvenida
        composable(Screen.Welcome.route) {
            WelcomeScreen(
                onNavigateToPuzzle = {
                    navController.navigate(Screen.Puzzle.createRoute(1)) {
                        // Quitar welcome del back stack
                        popUpTo(Screen.Welcome.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // Pantalla de puzzle
        composable(Screen.Puzzle.route) { backStackEntry ->
            val puzzleNumber =
                backStackEntry.arguments?.getString("puzzleNumber")?.toIntOrNull() ?: 1

            PuzzleScreen(
                puzzleNumber = puzzleNumber,
                onNavigateToResult = {
                    navController.navigate(Screen.PuzzleResult.createRoute(puzzleNumber))
                },
                onBack = { navController.popBackStack() }
            )
        }

        // Pantalla de resultado del puzzle
        composable(Screen.PuzzleResult.route) { backStackEntry ->
            val puzzleNumber =
                backStackEntry.arguments?.getString("puzzleNumber")?.toIntOrNull() ?: 1

            PuzzleResultScreen(
                correctAnswer = 21091999,
                onCorrect = {
                    navController.navigate(Screen.InicioConsola.route) {
                        popUpTo(Screen.Welcome.route) { inclusive = true }
                    }
                },
                onIncorrect = {
                    navController.navigate(Screen.Puzzle.createRoute(1)) {
                        popUpTo(0) { inclusive = true }
                    }
                },
                onBack = { navController.popBackStack() }
            )
        }
        // InicioConsola con dos opciones
        composable(Screen.InicioConsola.route) {
            InicioConsolaScreen(
                onNuevaCarta = { navController.navigate(Screen.NuevaCarta.route) },
                onNextPuzzle = { navController.navigate(Screen.Puzzle.createRoute(2)) },
                onBack = { navController.popBackStack() }
            )
        }

        // Nueva Carta (pantalla vacía con botón volver)
        composable(Screen.NuevaCarta.route) {
            NuevaCartaScreen(onBack = { navController.popBackStack() })
        }

    }
}