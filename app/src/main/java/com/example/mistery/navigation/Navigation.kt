package com.example.mistery.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mistery.screens.InicioConsolaScreen
import com.example.mistery.screens.NuevaCartaScreen
import com.example.mistery.screens.PuzzleLevelThree
import com.example.mistery.screens.PuzzleResultScreen
import com.example.mistery.screens.PuzzleScreen
import com.example.mistery.screens.TranslatorScreen
import com.example.mistery.screens.WelcomeScreen
import com.example.mistery.R
sealed class Screen(val route: String) {
    object Welcome : Screen("welcome")
    object Puzzle : Screen("puzzle/{puzzleNumber}") {
        fun createRoute(puzzleNumber: Int) = "puzzle/$puzzleNumber"
    }
    object PuzzleResult : Screen("puzzle/result/{puzzleNumber}") {
        fun createRoute(puzzleNumber: Int) = "puzzle/result/$puzzleNumber"
    }
    object InicioConsola : Screen("InicioConsola")
    object NuevaCarta : Screen("nuevaCarta")
    object Translator : Screen("translator")
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

        composable(Screen.Puzzle.route) { backStackEntry ->
            val puzzleNumber =
                backStackEntry.arguments?.getString("puzzleNumber")?.toIntOrNull() ?: 1

            PuzzleScreen(
                puzzleNumber = puzzleNumber,
                onNavigateToResult = {
                    navController.navigate(Screen.PuzzleResult.createRoute(puzzleNumber))
                },
                onBack = { navController.popBackStack() },
                navController = navController
            )
        }


        // Pantalla de resultado del puzzle
        composable(Screen.PuzzleResult.route) { backStackEntry ->
            val puzzleNumber =
                backStackEntry.arguments?.getString("puzzleNumber")?.toIntOrNull() ?: 1

            PuzzleResultScreen(
                puzzleNumber = puzzleNumber,
                onCorrect = {
                    when (puzzleNumber) {
                        1 -> {

                            navController.navigate(Screen.InicioConsola.route) {
                                popUpTo(Screen.Welcome.route) { inclusive = true }
                            }
                        }
                        2 -> {

                            navController.navigate(Screen.Puzzle.createRoute(3)) {
                                popUpTo(Screen.Puzzle.route) { inclusive = false }
                            }
                        }
                        else -> {
                            navController.navigate(Screen.Puzzle.createRoute(3)) {
                                popUpTo(Screen.Puzzle.route) { inclusive = false }
                            }
                        }
                    }
                },
                onIncorrect = {

                    navController.navigate(Screen.Puzzle.createRoute(puzzleNumber)) {
                        popUpTo(Screen.Puzzle.route) { inclusive = false }
                    }
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(Screen.InicioConsola.route) {
            InicioConsolaScreen(
                onNuevaCarta = { navController.navigate(Screen.NuevaCarta.route) },
                onNextPuzzle = { navController.navigate(Screen.Puzzle.createRoute(2)) },
                onBack = { navController.popBackStack() }
            )
        }


        composable(Screen.NuevaCarta.route) {
            NuevaCartaScreen(onBack = { navController.popBackStack() })
        }

        composable(Screen.Translator.route) {
            TranslatorScreen(navController)
        }
        composable("puzzle/3/{stickerResId}") { backStackEntry ->
            val stickerResId = backStackEntry.arguments?.getString("stickerResId")?.toIntOrNull() ?: 0
            PuzzleLevelThree(navController = navController, stickerResId = stickerResId)
        }

    }
}