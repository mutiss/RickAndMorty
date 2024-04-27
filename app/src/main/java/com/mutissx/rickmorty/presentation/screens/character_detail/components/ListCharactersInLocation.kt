package com.mutissx.rickmorty.presentation.screens.character_detail.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.mutissx.rickmorty.presentation.common.components.Loader
import com.mutissx.rickmorty.presentation.screens.character_detail.CharacterDetailViewModel
import com.mutissx.rickmorty.presentation.screens.character_detail.viewstate.CharactersInLocationState

@Composable
fun ListCharactersInLocation(detailCharacterSeen: Int,navController: NavController, viewModel: CharacterDetailViewModel) {
    when (val charactersInLocationState =
        viewModel.charactersInLocationState.collectAsState().value) {
        is CharactersInLocationState.CharactersInLocationLoadingState -> {
            Loader()
        }
        is CharactersInLocationState.CharactersInLocationSuccessState -> {
            val characters = charactersInLocationState.charactersList.orEmpty()
            LazyRow(modifier = Modifier.fillMaxWidth()) {
                items(items = characters.filter { it.id != detailCharacterSeen }, key = { it.id }) { character ->
                    CharacterInLocationRow(
                        navController = navController,
                        character = character
                    )
                }
            }
        }
    }
}