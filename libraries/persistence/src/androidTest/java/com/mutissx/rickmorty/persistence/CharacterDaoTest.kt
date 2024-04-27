package com.mutissx.rickmorty.persistence

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.mutissx.rickmorty.persistence.database.RickMortyCharactersDatabase
import com.mutissx.rickmorty.persistence.database.dao.CharacterDao
import com.mutissx.rickmorty.persistence.database.entities.CharacterEntity
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@SmallTest
@RunWith(AndroidJUnit4::class)
class CharacterDaoTest : TestCase() {
    private lateinit var database: RickMortyCharactersDatabase
    private lateinit var charactersDao: CharacterDao

    @Before
    public override fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            RickMortyCharactersDatabase::class.java
        ).allowMainThreadQueries().build()
        charactersDao = database.characterDao
    }

    @After
    fun closeDatabase() {
        database.close()
    }

    @Test
    fun clearAllCharactersReturnsTrue() = runBlocking {
        charactersDao.upsertAll(listOf(anyCharacter, anyCharacter.copy(id = 4)))
        charactersDao.clearAll()

        val listCharacters = charactersDao.getAllCharacters()
        assertFalse(listCharacters.contains(anyCharacter))
        assertFalse(listCharacters.contains(anyCharacter))
    }

    @Test
    fun upsertAllCharactersReturnsTrue() = runBlocking {
        charactersDao.upsertAll(listOf(anyCharacter, anyCharacter.copy(id = 4)))

        val listCharacters = charactersDao.getAllCharacters()
        assertTrue(listCharacters.contains(anyCharacter))
    }

    @Test
    fun getListCharactersInLocationContainsCorrectCharacter() = runBlocking {
        charactersDao.upsertAll(listOf(anyCharacter, anyCharacterDifferentLocation))

        val listCharactersInLocation = charactersDao.getListCharactersInLocation(
            ANY_CHAR_LOCATION_ID_2
        )

        assertFalse(listCharactersInLocation.contains(anyCharacter))
        assertTrue(listCharactersInLocation.contains(anyCharacterDifferentLocation))
    }

    companion object {
        private const val ANY_CHAR_ID = 2
        private const val ANY_CHAR_NAME = "Char name"
        private const val ANY_CHAR_IMAGE = "Char Image"
        private const val ANY_CHAR_TYPE = "Char type"
        private const val ANY_CHAR_STATUS = "Alive"
        private const val ANY_CHAR_SPECIE = "Human"
        private const val ANY_CHAR_GENDER = "Male"
        private const val ANY_CHAR_ORIGIN = "Earth"
        private const val ANY_CHAR_LOCATION = "Earth"
        private const val ANY_CHAR_LOCATION_ID = 1
        private const val ANY_CHAR_LOCATION_ID_2 = 4

        private val anyCharacter = CharacterEntity(
            id = ANY_CHAR_ID,
            name = ANY_CHAR_NAME,
            imageUrl = ANY_CHAR_IMAGE,
            status = ANY_CHAR_STATUS,
            species = ANY_CHAR_SPECIE,
            type = ANY_CHAR_TYPE,
            gender = ANY_CHAR_GENDER,
            origin = ANY_CHAR_ORIGIN,
            location = ANY_CHAR_LOCATION,
            locationId = ANY_CHAR_LOCATION_ID
        )
        private val anyCharacterDifferentLocation =
            anyCharacter.copy(locationId = ANY_CHAR_LOCATION_ID_2)
    }
}