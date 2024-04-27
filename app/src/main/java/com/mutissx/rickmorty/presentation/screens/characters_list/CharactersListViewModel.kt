package com.mutissx.rickmorty.presentation.screens.characters_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.mutissx.rickmorty.data.mappers.toCharacterDomain
import com.mutissx.rickmorty.persistence.database.entities.CharacterEntity
import kotlinx.coroutines.flow.map

class CharactersListViewModel (
    pager: Pager<Int, CharacterEntity>
): ViewModel() {

    val charactersPagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toCharacterDomain() }
        }
        .cachedIn(viewModelScope)
}