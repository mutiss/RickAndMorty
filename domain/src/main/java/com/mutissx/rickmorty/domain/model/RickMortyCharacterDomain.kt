package com.mutissx.rickmorty.domain.model

data class RickMortyCharacterDomain(
    val id: Int,
    val name: String,
    val imageUrl: String?,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: String,
    val location: String,
    val locationId: Int
)