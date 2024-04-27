package com.mutissx.rickmorty.presentation.screens.character_detail.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mutissx.rickmorty.domain.model.RickMortyCharacterDomain
import com.mutissx.rickmorty.presentation.common.components.SpacerVerticalWithValue
import com.mutissx.rickmorty.presentation.screens.Screen
import com.mutissx.rickmorty.presentation.util.ColorUtils
import com.mutissx.rickmorty.presentation.util.orZero
import java.util.Locale

@Composable
fun CharacterInLocationRow(
    navController: NavController,
    character: RickMortyCharacterDomain?
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable {
                navController.navigate(
                    route = Screen.CharactersDetailScreen.setCharParams(
                        characterId = character?.id.orZero(),
                        locationId = character?.locationId.orZero()
                    )
                )
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(character?.imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .border(
                    2.dp, ColorUtils.getCharacterNameColor(
                        character?.name
                            ?.lowercase(Locale.getDefault())
                            .orEmpty()
                    ), CircleShape
                )
        )
        SpacerVerticalWithValue(value = 10)
        Text(
            text = character?.name.orEmpty(),
            modifier = Modifier
                .fillMaxWidth(),
            color = ColorUtils.getCharacterNameColor(
                character?.name.orEmpty().lowercase(Locale.getDefault())
            ),
            fontSize = 10.sp,
            fontWeight = FontWeight.Normal
        )
        SpacerVerticalWithValue(value = 2)
        Text(
            text = character?.species.orEmpty(),
            modifier = Modifier
                .fillMaxWidth(),
            color = ColorUtils.getColorFromSpecie(
                character?.species.orEmpty()
            ),
            fontSize = 9.sp,
            fontWeight = FontWeight.Normal
        )
    }

}