package com.mutissx.rickmorty.persistence.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mutissx.rickmorty.persistence.database.dao.CharacterDao
import com.mutissx.rickmorty.persistence.database.entities.CharacterEntity

@Database(
    entities = [CharacterEntity::class],
    version = 1
)
abstract class RickMortyCharactersDatabase: RoomDatabase() {
    abstract val characterDao: CharacterDao
}