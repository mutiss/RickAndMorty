package com.mutissx.rickmorty.presentation.screens.character_detail.viewstate

import com.mutissx.rickmorty.domain.model.RickMortyCharacterDomain

sealed class CharacterDetailState {
    object CharacterDetailLoadingState : CharacterDetailState()
    data class CharacterDetailSuccessState(val characterDetail: RickMortyCharacterDomain?) : CharacterDetailState()
}