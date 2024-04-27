package com.mutissx.rickmorty.presentation.screens.character_detail.viewstate

import com.mutissx.rickmorty.domain.model.RickMortyCharacterDomain

sealed class CharactersInLocationState {
    object CharactersInLocationLoadingState : CharactersInLocationState()
    data class CharactersInLocationSuccessState(val charactersList: List<RickMortyCharacterDomain>?) : CharactersInLocationState()
}