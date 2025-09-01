package com.example.mistery.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
                }
            )
        }

        // Pantalla de resultado del puzzle
        composable(Screen.PuzzleResult.route) { backStackEntry ->
            val puzzleNumber =
                backStackEntry.arguments?.getString("puzzleNumber")?.toIntOrNull() ?: 1

            PuzzleResultScreen(
                correctAnswer = "42", // Aquí defines la solución correcta
                onCorrect = {
                    val nextPuzzle = puzzleNumber + 1
                    if (nextPuzzle <= 3) {
                        navController.navigate(Screen.Puzzle.createRoute(nextPuzzle)) {
                            popUpTo(Screen.Puzzle.createRoute(puzzleNumber)) {
                                inclusive = true
                            }
                        }
                    } else {
                        // Si se acaban los puzzles, volver al inicio
                        navController.navigate(Screen.Welcome.route) {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                },
                onIncorrect = {
                    navController.navigate(Screen.Puzzle.createRoute(1)) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
    }
}
