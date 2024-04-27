package com.mutissx.rickmorty.presentation.screens.character_detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mutissx.rickmorty.R
import com.mutissx.rickmorty.domain.model.RickMortyCharacterDomain
import com.mutissx.rickmorty.presentation.ui.theme.GreenMoco
import com.mutissx.rickmorty.presentation.util.ColorUtils
import com.mutissx.rickmorty.presentation.util.orFalse
import java.util.Locale

@Composable
fun CharacterContent(character: RickMortyCharacterDomain?) {
    Column {
        Spacer(modifier = Modifier.height(8.dp))
        CharacterName(character = character)
        Spacer(modifier = Modifier.height(8.dp))
        CharacterProperty(
            label = stringResource(id = R.string.status),
            value = character?.status.orEmpty(),
            color = ColorUtils.getColorFromStatus(character?.status.orEmpty())
        )
        CharacterProperty(
            label = stringResource(id = R.string.specie),
            value = character?.species.orEmpty(),
            color = ColorUtils.getColorFromSpecie(character?.species.orEmpty())
        )
        if(character?.type?.isNotEmpty().orFalse()) {
            CharacterProperty(
                label = stringResource(id = R.string.type),
                value = character?.type.orEmpty(),
                color = Color.Cyan
            )
        }
        CharacterProperty(
            label = stringResource(id = R.string.gender),
            value = character?.gender.orEmpty(),
            color = ColorUtils.getColorFromGender(character?.gender.orEmpty())
        )
        CharacterProperty(
            label = stringResource(id = R.string.origin),
            value = character?.origin.orEmpty(),
            color = Color.White
        )
        CharacterProperty(
            label = stringResource(id = R.string.last_known_location),
            value = character?.location.orEmpty(),
            color = Color.White
        )
        Text(text = stringResource(id = R.string.more_characters_on_this_location),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            color = GreenMoco,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold)
    }
}
