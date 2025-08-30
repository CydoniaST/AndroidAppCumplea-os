package com.example.mistery.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mistery.screens.PuzzleScreen
import com.example.mistery.screens.WelcomeScreen

sealed class Screen(val route: String) {
    object Welcome : Screen("welcome")
    object Puzzle : Screen("puzzle/{puzzleNumber}") {
        fun createRoute(puzzleNumber: Int) = "puzzle/$puzzleNumber"
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
        composable(Screen.Welcome.route) {
            WelcomeScreen(
                onNavigateToPuzzle = {
                    navController.navigate(Screen.Puzzle.createRoute(1)) {
                        // Remove welcome screen from back stack
                        popUpTo(Screen.Welcome.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        
        composable(Screen.Puzzle.route) { backStackEntry ->
            val puzzleNumber = backStackEntry.arguments?.getString("puzzleNumber")?.toIntOrNull() ?: 1
            
            PuzzleScreen(
                puzzleNumber = puzzleNumber,
                onNavigateToNext = {
                    val nextPuzzle = puzzleNumber + 1
                    if (nextPuzzle <= 3) { // Límite de 3 puzzles por ahora
                        navController.navigate(Screen.Puzzle.createRoute(nextPuzzle))
                    } else {
                        // Volver al inicio cuando se terminen todos los puzzles
                        navController.navigate(Screen.Welcome.route) {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                },
                onNavigateBack = {
                    if (puzzleNumber > 1) {
                        navController.popBackStack()
                    } else {
                        // Si es el primer puzzle, volver a la pantalla de bienvenida
                        navController.navigate(Screen.Welcome.route) {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                }
            )
        }
    }
}
