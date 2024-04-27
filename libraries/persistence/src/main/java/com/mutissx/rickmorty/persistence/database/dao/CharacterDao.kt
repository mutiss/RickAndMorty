package com.mutissx.rickmorty.persistence.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.mutissx.rickmorty.persistence.database.entities.CharacterEntity

@Dao
interface CharacterDao {

    @Upsert
    suspend fun upsertAll(characters: List<CharacterEntity>)

    @Query("SELECT * FROM characterentity")
    fun pagingSource(): PagingSource<Int, CharacterEntity>

    @Query("DELETE FROM characterentity")
    suspend fun clearAll()

    @Query("SELECT * FROM characterentity WHERE id=:id ")
    fun getDetailCharacter(id: Int): CharacterEntity

    @Query("SELECT * FROM characterentity WHERE locationId=:locationId ")
    fun getListCharactersInLocation(locationId: Int): List<CharacterEntity>

    @Query("SELECT * FROM characterentity")
    fun getAllCharacters(): List<CharacterEntity>
}