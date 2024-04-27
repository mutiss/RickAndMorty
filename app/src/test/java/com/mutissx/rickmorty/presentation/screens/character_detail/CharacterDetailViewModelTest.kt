package com.mutissx.rickmorty.presentation.screens.character_detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.mutissx.rickmorty.base.domain.common.Resource
import com.mutissx.rickmorty.base.testing.CoroutinesTestRule
import com.mutissx.rickmorty.domain.model.RickMortyCharacterDomain
import com.mutissx.rickmorty.domain.usecase.GetCharacterDetailUseCase
import com.mutissx.rickmorty.domain.usecase.GetCharactersInLocationUseCase
import com.mutissx.rickmorty.presentation.screens.character_detail.viewstate.CharacterDetailState
import com.mutissx.rickmorty.presentation.screens.character_detail.viewstate.CharactersInLocationState
import com.mutissx.rickmorty.presentation.util.Constants
import com.mutissx.rickmorty.presentation.util.orFalse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class CharacterDetailViewModelTest {
    @get:Rule
    var instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private var getCharacterDetailUseCase: GetCharacterDetailUseCase = mock()
    private var getCharactersInLocationUseCase: GetCharactersInLocationUseCase = mock()
    private var savedStateHandle: SavedStateHandle = mock()

    private lateinit var viewModel: CharacterDetailViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        viewModel = CharacterDetailViewModel(
            getCharacterDetailUseCase,
            getCharactersInLocationUseCase,
            savedStateHandle
        )
        givenSavedStateHandlerCharacterId()
        givenSavedStateHandlerCharacterLocationId()
    }

    @Test
    fun `getCharacterDetail invoke return success state`() =
        runTest {
            givenGetCharacterDetailSuccess()
            whenGetCharacterDetailIsInvoked()
            thenGetCharacterDetailUseCaseIsInvoked()
            thenGetCharacterDetailReturnsSuccess()
        }

    @Test
    fun `getCharactersInLocation invoke return correct list of characters given location id and returns success state`() =
        runTest {
            givenGetCharactersInLocationSuccess()
            whenGetCharactersInLocationIsInvoked()
            thenGetCharactersInLocationUseCaseIsInvoked()
            thenGetCharactersInLocationReturnsCorrectListOfCharacters()
        }

    @Test
    fun `getCharactersInLocation invoke return list of characters which not contains a character with different location`() =
        runTest {
            givenGetCharactersInLocationSuccess()
            whenGetCharactersInLocationIsInvoked()
            thenGetCharactersInLocationUseCaseIsInvoked()
            thenGetCharactersInLocationReturnsListNotContainsCharactersWithDifferentLocationThatGiven()
        }

    private fun givenSavedStateHandlerCharacterId() {
        whenever(savedStateHandle.get<Int>(Constants.CHARACTER_ID_DETAIL_ARGUMENT_KEY)) doReturn ANY_CHAR_ID
    }

    private fun givenSavedStateHandlerCharacterLocationId() {
        whenever(savedStateHandle.get<Int>(Constants.CHARACTER_LOCATION_IDDETAIL_ARGUMENT_KEY)) doReturn ANY_CHAR_LOCATION_ID
    }

    private suspend fun givenGetCharacterDetailSuccess() {
        whenever(getCharacterDetailUseCase.invokeWithParams(ANY_CHAR_ID)) doReturn flow {
            emit(
                Resource.Success(
                    anyCharacterDomain
                )
            )
        }
    }

    private suspend fun givenGetCharactersInLocationSuccess() {
        whenever(getCharactersInLocationUseCase.invokeWithParams(ANY_CHAR_LOCATION_ID)) doReturn flow {
            emit(
                Resource.Success(
                    listOf(anyCharacterDomain)
                )
            )
        }
    }

    private fun whenGetCharacterDetailIsInvoked() {
        viewModel.getCharacterDetail()
    }

    private fun whenGetCharactersInLocationIsInvoked() {
        viewModel.getCharactersInLocation()
    }

    private fun thenGetCharacterDetailUseCaseIsInvoked() {
        verify(getCharacterDetailUseCase, times(1)).invokeWithParams(ANY_CHAR_ID)
    }

    private fun thenGetCharactersInLocationUseCaseIsInvoked() {
        verify(getCharactersInLocationUseCase, times(1)).invokeWithParams(ANY_CHAR_LOCATION_ID)
    }

    private fun thenGetCharacterDetailReturnsSuccess() {
        assert(
            viewModel.characterDetailState.value == CharacterDetailState.CharacterDetailSuccessState(
                anyCharacterDomain
            )
        )
    }

    private fun thenGetCharactersInLocationReturnsCorrectListOfCharacters() {
        assert(
            viewModel.charactersInLocationState.value == CharactersInLocationState.CharactersInLocationSuccessState(
                listOf(anyCharacterDomain)
            )
        )
    }

    private suspend fun thenGetCharactersInLocationReturnsListNotContainsCharactersWithDifferentLocationThatGiven() {
        getCharactersInLocationUseCase.invokeWithParams(locationId = ANY_CHAR_LOCATION_ID)
            .collect{
                val result = it
                assert(!result.data?.contains(anyCharacterDifferentLocation).orFalse())
            }
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

        private val anyCharacterDomain = RickMortyCharacterDomain(
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
            anyCharacterDomain.copy(locationId = ANY_CHAR_LOCATION_ID_2)
    }
}