package com.mutissx.rickmorty.domain.usecase

import com.mutissx.rickmorty.base.api.ErrorParser
import com.mutissx.rickmorty.base.domain.common.BaseUseCase
import com.mutissx.rickmorty.base.domain.common.Resource
import com.mutissx.rickmorty.domain.model.RickMortyCharacterDomain
import com.mutissx.rickmorty.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCharactersInLocationUseCase(
    private val charactersRepository: CharactersRepository,
    private val errorParser: ErrorParser
) : BaseUseCase() {
    fun invokeWithParams(locationId: Int): Flow<Resource<List<RickMortyCharacterDomain>>> = flow {
        emit(Resource.Loading())
        emit(invoke(errorParser) { charactersRepository.getCharactersInLocation(locationId) })
    }
}