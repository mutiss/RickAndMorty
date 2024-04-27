package com.mutissx.rickmorty.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.mutissx.rickmorty.presentation.screens.Screen
import com.mutissx.rickmorty.presentation.screens.character_detail.CharacterDetailScreen
import com.mutissx.rickmorty.presentation.screens.characters_list.CharactersListScreen
import com.mutissx.rickmorty.presentation.ui.theme.RickMortyTheme
import com.mutissx.rickmorty.presentation.util.Constants

@OptIn(ExperimentalAnimationApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RickMortyTheme {
                val navController = rememberAnimatedNavController()
                val scaffoldState = rememberScaffoldState()
                Box(modifier = Modifier.fillMaxSize()) {
                    Scaffold(
                        scaffoldState = scaffoldState,
                        content = { paddingValues ->
                            Box(Modifier.padding(paddingValues)) {
                                AnimatedNavHost(
                                    navController = navController,
                                    startDestination = Screen.CharactersListScreen.route
                                ) {
                                    composable(route = Screen.CharactersListScreen.route,
                                        enterTransition = {
                                            slideInHorizontally(
                                                initialOffsetX = { 0 }
                                            )
                                        }, popExitTransition = {
                                            slideOutHorizontally(
                                                targetOffsetX = { 1800 }
                                            )
                                        }) {
                                        CharactersListScreen(navController = navController)
                                    }
                                    composable(
                                        route = Screen.CharactersDetailScreen.route,
                                        enterTransition = {
                                            slideInHorizontally(
                                                initialOffsetX = { 1800 }
                                            )
                                        },
                                        arguments = listOf(
                                            navArgument(Constants.CHARACTER_ID_DETAIL_ARGUMENT_KEY) {
                                                type = NavType.IntType
                                            },
                                            navArgument(Constants.CHARACTER_LOCATION_IDDETAIL_ARGUMENT_KEY) {
                                                type = NavType.IntType
                                            })
                                    ) {
                                        CharacterDetailScreen(navController = navController)
                                    }
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}