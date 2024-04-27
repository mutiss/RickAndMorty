package com.mutissx.rickmorty.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.mutissx.rickmorty.base.api.ErrorParser
import com.mutissx.rickmorty.data.mappers.toCharacterEntity
import com.mutissx.rickmorty.data.services.api.RickMortyApi
import com.mutissx.rickmorty.persistence.database.RickMortyCharactersDatabase
import com.mutissx.rickmorty.persistence.database.entities.CharacterEntity

@OptIn(ExperimentalPagingApi::class)
class CharactersRemoteMediator(
    private val characterDb: RickMortyCharactersDatabase,
    private val characterApi: RickMortyApi,
    private val errorParser: ErrorParser
) : RemoteMediator<Int, CharacterEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        1
                    } else {
                        (lastItem.id / state.config.pageSize) + 1
                    }
                }
            }

            val characters = characterApi.getCharacters(
                page = loadKey
            )

            characterDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    characterDb.characterDao.clearAll()
                }
                val charactersEntities = characters.data.map { it.toCharacterEntity() }
                characterDb.characterDao.upsertAll(charactersEntities)
            }

            MediatorResult.Success(
                endOfPaginationReached = characters.data.isEmpty()
            )
        } catch(e: Exception){
            MediatorResult.Error(errorParser.parseError(e))
        }
    }
}