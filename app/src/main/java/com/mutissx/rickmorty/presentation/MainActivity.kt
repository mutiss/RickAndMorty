package com.mutissx.rickmorty.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mutissx.rickmorty.presentation.screens.Screen
import com.mutissx.rickmorty.presentation.screens.character_detail.CharacterDetailScreen
import com.mutissx.rickmorty.presentation.screens.characters_list.CharactersListScreen
import com.mutissx.rickmorty.presentation.ui.theme.RickMortyTheme
import com.mutissx.rickmorty.presentation.util.Constants

@OptIn(ExperimentalSharedTransitionApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickMortyTheme {
                SharedTransitionLayout {
                    val scaffoldState = rememberScaffoldState()
                    Box(modifier = Modifier.fillMaxSize()) {
                        Scaffold(
                            scaffoldState = scaffoldState,
                            content = { paddingValues ->
                                Box(Modifier.padding(paddingValues)) {
                                    SharedTransitionLayout {
                                        val navController = rememberNavController()
                                        NavHost(
                                            navController = navController,
                                            startDestination = Screen.CharactersListScreen.route
                                        ) {
                                            composable(route = Screen.CharactersListScreen.route) {
                                                CharactersListScreen(
                                                    navController = navController,
                                                    sharedTransitionScope = this@SharedTransitionLayout,
                                                    animatedVisibilityScope = this@composable
                                                )
                                            }
                                            composable(
                                                route = Screen.CharactersDetailScreen.route,
                                                arguments = listOf(
                                                    navArgument(Constants.CHARACTER_ID_DETAIL_ARGUMENT_KEY) {
                                                        type = NavType.IntType
                                                    },
                                                    navArgument(Constants.CHARACTER_LOCATION_IDDETAIL_ARGUMENT_KEY) {
                                                        type = NavType.IntType
                                                    })
                                            ) {
                                                CharacterDetailScreen(
                                                    navController = navController,
                                                    sharedTransitionScope = this@SharedTransitionLayout,
                                                    animatedVisibilityScope = this@composable
                                                )
                                            }
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
}
