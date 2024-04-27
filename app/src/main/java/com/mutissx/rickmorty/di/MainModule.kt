package com.mutissx.rickmorty.di

import androidx.lifecycle.SavedStateHandle
import com.mutissx.rickmorty.presentation.screens.character_detail.CharacterDetailViewModel
import com.mutissx.rickmorty.presentation.screens.characters_list.CharactersListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel { CharactersListViewModel(get()) }
    viewModel { (handle: SavedStateHandle) -> CharacterDetailViewModel(get(), get(),handle) }
}