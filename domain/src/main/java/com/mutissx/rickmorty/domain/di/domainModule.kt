package com.mutissx.rickmorty.domain.di

import com.mutissx.rickmorty.domain.usecase.GetCharacterDetailUseCase
import com.mutissx.rickmorty.domain.usecase.GetCharactersInLocationUseCase
import org.koin.dsl.module

val domainModule = module {
    single { GetCharacterDetailUseCase(get(), get()) }
    single { GetCharactersInLocationUseCase(get(), get()) }
}