package com.mutissx.rickmorty.domain.repository

import com.mutissx.rickmorty.domain.model.RickMortyCharacterDomain

interface CharactersRepository {
    suspend fun getCharacterDetail(characterId: Int) : RickMortyCharacterDomain
    suspend fun getCharactersInLocation(locationId: Int) : List<RickMortyCharacterDomain>

}