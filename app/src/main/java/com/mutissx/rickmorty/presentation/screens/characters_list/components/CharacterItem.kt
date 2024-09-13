package com.mutissx.rickmorty.presentation.screens.characters_list.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mutissx.rickmorty.domain.model.RickMortyCharacterDomain
import com.mutissx.rickmorty.presentation.screens.Screen
import com.mutissx.rickmorty.presentation.ui.theme.BlueBackground
import com.mutissx.rickmorty.presentation.ui.theme.GreenMoco
import com.mutissx.rickmorty.presentation.ui.theme.MortyTShirt
import com.mutissx.rickmorty.presentation.util.ColorUtils
import java.util.Locale

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun CharacterItem(
    navController: NavController, character: RickMortyCharacterDomain,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    with(sharedTransitionScope) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(BlueBackground)
                .clickable {
                    navController.navigate(
                        route = Screen.CharactersDetailScreen.setCharParams(
                            characterId = character.id,
                            locationId = character.locationId
                        )
                    )
                }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(character.imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .size(width = 150.dp, height = 150.dp)
                        .sharedElement(
                            state = rememberSharedContentState(key = "char-${character.id}"),
                            animatedVisibilityScope = animatedVisibilityScope
                        )
                        .clip(CircleShape)
                        .border(
                            2.dp,
                            ColorUtils.getCharacterNameColor(character.name.lowercase(Locale.getDefault())),
                            CircleShape
                        )
                )
                Column(
                    Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = character.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        fontSize = 20.sp,
                        color = ColorUtils.getCharacterNameColor(character.name.lowercase(Locale.getDefault())),
                        fontWeight = FontWeight.Black
                    )
                    Text(
                        text = character.status,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        fontSize = 16.sp,
                        color = ColorUtils.getColorFromStatus(character.status),
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        text = character.species,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        fontSize = 16.sp,
                        color = ColorUtils.getColorFromSpecie(character.species),
                        fontWeight = FontWeight.Normal
                    )
                }
            }
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp), color = MortyTShirt
        )
    }
}
