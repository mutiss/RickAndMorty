package com.mutissx.rickmorty.data.remote.model

import com.google.gson.annotations.SerializedName

data class CharactersResponse(
    @SerializedName("results") val data: List<CharacterDto>
)

data class CharacterDto(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)