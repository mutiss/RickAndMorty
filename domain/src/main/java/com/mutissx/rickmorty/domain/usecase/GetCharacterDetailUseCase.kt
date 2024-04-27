package com.mutissx.rickmorty.domain.usecase

import com.mutissx.rickmorty.base.api.ErrorParser
import com.mutissx.rickmorty.base.domain.common.BaseUseCase
import com.mutissx.rickmorty.base.domain.common.Resource
import com.mutissx.rickmorty.domain.model.RickMortyCharacterDomain
import com.mutissx.rickmorty.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCharacterDetailUseCase(
    private val charactersRepository: CharactersRepository,
    private val errorParser: ErrorParser
) : BaseUseCase() {
    fun invokeWithParams(characterId: Int): Flow<Resource<RickMortyCharacterDomain>> = flow {
        emit(Resource.Loading())
        emit(invoke(errorParser) { charactersRepository.getCharacterDetail(characterId) })
    }
}