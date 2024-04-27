package com.mutissx.rickmorty.presentation.screens.character_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mutissx.rickmorty.base.domain.common.Resource
import com.mutissx.rickmorty.domain.usecase.GetCharacterDetailUseCase
import com.mutissx.rickmorty.domain.usecase.GetCharactersInLocationUseCase
import com.mutissx.rickmorty.presentation.screens.character_detail.viewstate.CharacterDetailState
import com.mutissx.rickmorty.presentation.screens.character_detail.viewstate.CharactersInLocationState
import com.mutissx.rickmorty.presentation.util.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    private val getCharacterDetailUseCase: GetCharacterDetailUseCase,
    private val getCharactersInLocationUseCase: GetCharactersInLocationUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _characterDetailState =
        MutableStateFlow<CharacterDetailState>(CharacterDetailState.CharacterDetailLoadingState)
    val characterDetailState: StateFlow<CharacterDetailState> = _characterDetailState

    private val _charactersInLocationState =
        MutableStateFlow<CharactersInLocationState>(CharactersInLocationState.CharactersInLocationLoadingState)
    val charactersInLocationState: StateFlow<CharactersInLocationState> = _charactersInLocationState

    init {
        getCharacterDetail()
        getCharactersInLocation()
    }

    fun getCharacterDetail() {
        savedStateHandle.get<Int>(key = Constants.CHARACTER_ID_DETAIL_ARGUMENT_KEY)?.let {
            val characterId = it
            viewModelScope.launch {
                getCharacterDetailUseCase.invokeWithParams(characterId = characterId)
                    .collect { result ->
                        when (result) {
                            is Resource.Success -> {
                                _characterDetailState.value =
                                    CharacterDetailState.CharacterDetailSuccessState(
                                        characterDetail = result.data
                                    )
                            }
                            is Resource.Loading -> {
                                _characterDetailState.value =
                                    CharacterDetailState.CharacterDetailLoadingState
                            }
                            is Resource.Error -> {

                            }
                        }
                    }
            }
        }
    }

    fun getCharactersInLocation() {
        savedStateHandle.get<Int>(key = Constants.CHARACTER_LOCATION_IDDETAIL_ARGUMENT_KEY)?.let {
            val locationId = it
            viewModelScope.launch {
                getCharactersInLocationUseCase.invokeWithParams(locationId = locationId)
                    .collect { result ->
                        when (result) {
                            is Resource.Success -> {
                                _charactersInLocationState.value =
                                    CharactersInLocationState.CharactersInLocationSuccessState(
                                        charactersList = result.data
                                    )
                            }
                            is Resource.Loading -> {
                                _characterDetailState.value =
                                    CharacterDetailState.CharacterDetailLoadingState
                            }
                            is Resource.Error -> {

                            }
                        }
                    }
            }
        }
    }
}