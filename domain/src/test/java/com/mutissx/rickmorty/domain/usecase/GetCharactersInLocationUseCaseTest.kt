package com.mutissx.rickmorty.domain.usecase

import com.mutissx.rickmorty.base.api.ErrorParser
import com.mutissx.rickmorty.base.domain.common.Resource
import com.mutissx.rickmorty.base.testing.CoroutinesTestRule
import com.mutissx.rickmorty.domain.model.RickMortyCharacterDomain
import com.mutissx.rickmorty.domain.repository.CharactersRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class GetCharactersInLocationUseCaseTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = CoroutinesTestRule()

    private lateinit var getCharactersInLocationUseCase: GetCharactersInLocationUseCase
    private var charactersRepository: CharactersRepository = mock()
    private var errorParser: ErrorParser = mock()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        getCharactersInLocationUseCase = GetCharactersInLocationUseCase(charactersRepository, errorParser)
    }

    @Test
    fun `when getCharactersInLocation returns success, then emit Resource Success`() = runBlocking {
        // Given
        val locationId = ANY_CHAR_LOCATION_ID
        val characters = listOf(anyCharacter)
        whenever(charactersRepository.getCharactersInLocation(locationId)).thenReturn(characters)

        // When
        val flow = getCharactersInLocationUseCase.invokeWithParams(locationId)
        val results = flow.take(2).toList()

        // Then
        val loadingResult = results[0]
        Assert.assertTrue(loadingResult is Resource.Loading)

        val successResult = results[1]
        Assert.assertTrue(successResult is Resource.Success)
        Assert.assertEquals(listOf(anyCharacter), successResult.data)
    }

    @Test
    fun `when getCharactersInLocation returns success and not contains characters with different location, then emit Resource Success`() = runBlocking {
        // Given
        val locationId = ANY_CHAR_LOCATION_ID
        val characters = listOf(anyCharacter)
        whenever(charactersRepository.getCharactersInLocation(locationId)).thenReturn(characters)

        // When
        val flow = getCharactersInLocationUseCase.invokeWithParams(locationId)
        val results = flow.take(2).toList()

        // Then
        val loadingResult = results[0]
        Assert.assertTrue(loadingResult is Resource.Loading)

        val successResult = results[1]
        Assert.assertTrue(successResult is Resource.Success)
        Assert.assertFalse(successResult.data?.contains(anyCharacterDifferentLocation) ?: false)
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

        private val anyCharacter = RickMortyCharacterDomain(
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