package com.mutissx.rickmorty.data.mappers

import com.mutissx.rickmorty.data.remote.model.CharacterDto
import com.mutissx.rickmorty.domain.model.RickMortyCharacterDomain
import com.mutissx.rickmorty.persistence.database.entities.CharacterEntity

fun CharacterDto.toCharacterEntity(): CharacterEntity {
    return CharacterEntity(
        id = id,
        name = name,
        imageUrl = image,
        status = status,
        species = species,
        type = type,
        gender = gender,
        origin = origin.name,
        location = location.name,
        locationId = if (location.url.isEmpty()) 0 else location.url.substringAfterLast("/").toInt()
    )
}

fun CharacterEntity.toCharacterDomain(): RickMortyCharacterDomain {
    return RickMortyCharacterDomain(
        id = id,
        name = name,
        imageUrl = imageUrl,
        status = status,
        species = species,
        type = type,
        gender = gender,
        origin = origin,
        location = location,
        locationId = locationId
    )
}

fun List<CharacterEntity>.toCharacterListDomain(): List<RickMortyCharacterDomain> {
    val listCharactersDomain: MutableList<RickMortyCharacterDomain> = mutableListOf()
    this.forEach { characterEntity ->
        listCharactersDomain.add(
            RickMortyCharacterDomain(
                id = characterEntity.id,
                name = characterEntity.name,
                imageUrl = characterEntity.imageUrl,
                status = characterEntity.status,
                species = characterEntity.species,
                type = characterEntity.type,
                gender = characterEntity.gender,
                origin = characterEntity.origin,
                location = characterEntity.location,
                locationId = characterEntity.locationId
            )
        )
    }
    return listCharactersDomain
}