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
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class GetCharacterDetailUseCaseTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = CoroutinesTestRule()

    private var charactersRepository: CharactersRepository = mock()
    private var errorParser: ErrorParser = mock()

    private lateinit var getCharacterDetailUseCase: GetCharacterDetailUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        getCharacterDetailUseCase = GetCharacterDetailUseCase(charactersRepository, errorParser)
    }

    @Test
    fun `invokeWithParams() returns Resource Success`() = runBlocking {
        // Given
        givenGetCharactersRepositoryIsSuccess()

        // When
        val flow = getCharacterDetailUseCase.invokeWithParams(ANY_CHAR_ID)
        val results = flow.take(2).toList()

        // Then
        val loadingResult = results[0]
        assertTrue(loadingResult is Resource.Loading)

        val successResult = results[1]
        assertTrue(successResult is Resource.Success)
        assertEquals(anyCharacter, successResult.data)
    }

    private suspend fun givenGetCharactersRepositoryIsSuccess() {
        whenever(charactersRepository.getCharacterDetail(ANY_CHAR_ID)) doReturn anyCharacter
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
    }
}