package com.mutissx.rickmorty.data.services.api

import com.mutissx.rickmorty.data.remote.model.CharactersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RickMortyApi {

    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): CharactersResponse
}