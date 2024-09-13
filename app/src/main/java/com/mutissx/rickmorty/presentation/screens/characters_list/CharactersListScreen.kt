package com.mutissx.rickmorty.presentation.screens.characters_list

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.mutissx.rickmorty.presentation.common.components.Loader
import com.mutissx.rickmorty.presentation.screens.characters_list.components.CharacterItem
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun CharactersListScreen(
    navController: NavController,
    viewModel: CharactersListViewModel = koinViewModel(),
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    val charactersState = viewModel.charactersPagingFlow
        .collectAsLazyPagingItems()

    LaunchedEffect(key1 = charactersState.loadState.refresh) {
        if (charactersState.loadState.refresh is LoadState.Error) {
            scaffoldState.snackbarHostState.showSnackbar(
                (charactersState.loadState.refresh as LoadState.Error).error.message.toString()
            )
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                if (charactersState.loadState.refresh is LoadState.Loading) {
                    Loader()
                } else {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(charactersState) { character ->
                            if (character != null) {
                                CharacterItem(
                                    navController = navController,
                                    character = character,
                                    sharedTransitionScope = sharedTransitionScope,
                                    animatedVisibilityScope = animatedVisibilityScope
                                )
                            }
                        }
                        item {
                            if (charactersState.loadState.append is LoadState.Loading) {
                                Loader()
                            }
                        }
                    }
                }
            }
        }
    )
}
