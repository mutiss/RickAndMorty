package com.mutissx.rickmorty.data.repository

import com.mutissx.rickmorty.data.mappers.toCharacterDomain
import com.mutissx.rickmorty.data.mappers.toCharacterListDomain
import com.mutissx.rickmorty.domain.model.RickMortyCharacterDomain
import com.mutissx.rickmorty.domain.repository.CharactersRepository
import com.mutissx.rickmorty.persistence.database.RickMortyCharactersDatabase

class CharactersRepositoryImpl(
    private val charactersDatabase: RickMortyCharactersDatabase
) : CharactersRepository {
    override suspend fun getCharacterDetail(characterId: Int): RickMortyCharacterDomain =
        charactersDatabase.characterDao.getDetailCharacter(characterId).toCharacterDomain()

    override suspend fun getCharactersInLocation(locationId: Int): List<RickMortyCharacterDomain> =
        charactersDatabase.characterDao.getListCharactersInLocation(locationId).toCharacterListDomain()

}


