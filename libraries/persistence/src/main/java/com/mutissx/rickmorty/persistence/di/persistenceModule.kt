package com.mutissx.rickmorty.persistence.di

import android.app.Application
import androidx.room.Room
import com.mutissx.rickmorty.persistence.database.RickMortyCharactersDatabase
import com.mutissx.rickmorty.persistence.util.Constants
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val persistenceModule = module {
    single { provideRickMortyDatabase(androidApplication()) }
}

private fun provideRickMortyDatabase(application: Application): RickMortyCharactersDatabase {
    return Room.databaseBuilder(
        application,
        RickMortyCharactersDatabase::class.java,
        Constants.DATABASE_NAME
    ).build()
}
