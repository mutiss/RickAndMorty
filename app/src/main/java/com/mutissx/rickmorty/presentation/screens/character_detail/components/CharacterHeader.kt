package com.mutissx.rickmorty.presentation.screens.character_detail.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mutissx.rickmorty.domain.model.RickMortyCharacterDomain

@Composable
fun CharacterHeader(
    character: RickMortyCharacterDomain?
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(character?.imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
    )
}