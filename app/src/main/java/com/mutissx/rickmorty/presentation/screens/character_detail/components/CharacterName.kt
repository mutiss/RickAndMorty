package com.mutissx.rickmorty.presentation.screens.character_detail.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mutissx.rickmorty.domain.model.RickMortyCharacterDomain
import com.mutissx.rickmorty.presentation.ui.theme.GreenMoco
import com.mutissx.rickmorty.presentation.util.ColorUtils
import java.util.Locale

@Composable
fun CharacterName(character: RickMortyCharacterDomain?) {
        Text(
            text = character?.name.orEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            color = ColorUtils.getCharacterNameColor(character?.name.orEmpty().lowercase(Locale.getDefault())),
            fontSize = 28.sp,
            fontWeight = FontWeight.Black
        )
}