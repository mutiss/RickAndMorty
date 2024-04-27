package com.mutissx.rickmorty.data.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.mutissx.rickmorty.base.api.ErrorParser
import com.mutissx.rickmorty.data.BuildConfig
import com.mutissx.rickmorty.data.paging.CharactersRemoteMediator
import com.mutissx.rickmorty.data.repository.CharactersRepositoryImpl
import com.mutissx.rickmorty.data.services.api.RickMortyApi
import com.mutissx.rickmorty.domain.repository.CharactersRepository
import com.mutissx.rickmorty.persistence.database.RickMortyCharactersDatabase
import com.mutissx.rickmorty.persistence.database.entities.CharacterEntity
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single<CharactersRepository> { CharactersRepositoryImpl(get()) }
    single<RickMortyApi> { get<Retrofit>().create(RickMortyApi::class.java) }

    single { provideRetrofit(get()) }
    single { provideOkHttpClient() }
    single { provideCharacterPager(get(), get(), get()) }
}


private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl(BuildConfig.BASE_URL_SERVICE)
    .addConverterFactory(GsonConverterFactory.create())
    .client(okHttpClient)
    .build()

private fun provideOkHttpClient(): OkHttpClient {
    val logging = HttpLoggingInterceptor()
    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
    return OkHttpClient.Builder()
        .addInterceptor(logging)
        .readTimeout(1, TimeUnit.MINUTES)
        .connectTimeout(1, TimeUnit.MINUTES)
        .retryOnConnectionFailure(true)
        .build()
}

@OptIn(ExperimentalPagingApi::class)
private fun provideCharacterPager(
    rickMortyDb: RickMortyCharactersDatabase,
    rickMortyApi: RickMortyApi,
    errorParser: ErrorParser
): Pager<Int, CharacterEntity> {
    return Pager(
        config = PagingConfig(pageSize = 20),
        remoteMediator = CharactersRemoteMediator(
            characterDb = rickMortyDb,
            characterApi = rickMortyApi,
            errorParser = errorParser
        ),
        pagingSourceFactory = {
            rickMortyDb.characterDao.pagingSource()
        }
    )
}
