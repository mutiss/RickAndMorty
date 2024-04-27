package com.mutissx.rickmorty.presentation.screens.character_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.mutissx.rickmorty.presentation.common.components.Loader
import com.mutissx.rickmorty.presentation.common.components.LoaderDetail
import com.mutissx.rickmorty.presentation.common.components.SpacerVerticalWithValue
import com.mutissx.rickmorty.presentation.screens.character_detail.components.CharacterContent
import com.mutissx.rickmorty.presentation.screens.character_detail.components.CharacterHeader
import com.mutissx.rickmorty.presentation.screens.character_detail.components.ListCharactersInLocation
import com.mutissx.rickmorty.presentation.screens.character_detail.viewstate.CharacterDetailState
import com.mutissx.rickmorty.presentation.ui.theme.BlueBackground
import com.mutissx.rickmorty.presentation.util.orZero
import org.koin.androidx.compose.koinViewModel

@Composable
fun CharacterDetailScreen(
    navController: NavController,
    viewModel: CharacterDetailViewModel = koinViewModel()
) {
    val characterDetailState by viewModel.characterDetailState.collectAsState()

    when (characterDetailState) {
        is CharacterDetailState.CharacterDetailLoadingState -> {
            Loader()
        }
        is CharacterDetailState.CharacterDetailSuccessState -> {
            val character =
                (characterDetailState as CharacterDetailState.CharacterDetailSuccessState).characterDetail

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(BlueBackground)
                    .verticalScroll(rememberScrollState())
            ) {
                CharacterHeader(character)
                CharacterContent(character)
                SpacerVerticalWithValue(10)
                ListCharactersInLocation(
                    detailCharacterSeen = character?.id.orZero(),
                    navController = navController,
                    viewModel = viewModel
                )
                Box(modifier = Modifier.fillMaxWidth()) {
                    LoaderDetail()
                }
                SpacerVerticalWithValue(10)
            }
        }
    }
}
