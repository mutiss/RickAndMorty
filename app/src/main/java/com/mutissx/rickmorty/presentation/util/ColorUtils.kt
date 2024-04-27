package com.mutissx.rickmorty.presentation.util

import androidx.compose.ui.graphics.Color
import com.mutissx.rickmorty.presentation.ui.theme.BlueRick
import com.mutissx.rickmorty.presentation.ui.theme.ColorAlien
import com.mutissx.rickmorty.presentation.ui.theme.ColorCreature
import com.mutissx.rickmorty.presentation.ui.theme.ColorDisease
import com.mutissx.rickmorty.presentation.ui.theme.ColorFemale
import com.mutissx.rickmorty.presentation.ui.theme.ColorHuman
import com.mutissx.rickmorty.presentation.ui.theme.ColorHumanoid
import com.mutissx.rickmorty.presentation.ui.theme.ColorMale
import com.mutissx.rickmorty.presentation.ui.theme.ColorPoopy
import com.mutissx.rickmorty.presentation.ui.theme.ColorRobot
import com.mutissx.rickmorty.presentation.ui.theme.GreenMoco
import com.mutissx.rickmorty.presentation.ui.theme.MortyTShirt

object ColorUtils {

    private const val RICK_NAME = "rick"
    private const val MORTY_NAME = "morty"
    private const val STATUS_ALIVE = "Alive"
    private const val STATUS_DEAD = "Dead"
    private const val SPECIE_HUMAN = "Human"
    private const val SPECIE_HUMANOID = "Humanoid"
    private const val SPECIE_ALIEN = "Alien"
    private const val SPECIE_ROBOT = "Robot"
    private const val SPECIE_POOPY = "Poopybutthole"
    private const val SPECIE_CREATURE = "Mythological Creature"
    private const val SPECIE_DISEASE = "Disease"
    private const val GENDER_MALE = "Male"
    private const val GENDER_FEMALE = "Female"

    fun getCharacterNameColor(characterName: String): Color =
        if (characterName.contains(RICK_NAME)) {
            BlueRick
        } else if (characterName.contains(MORTY_NAME)) {
            MortyTShirt
        } else {
            GreenMoco
        }

    fun getColorFromStatus(status: String): Color =
        when (status) {
            STATUS_ALIVE -> Color.Green
            STATUS_DEAD -> Color.Red
            else -> Color.Yellow
        }

    fun getColorFromSpecie(specie: String): Color =
        when (specie) {
            SPECIE_HUMAN -> ColorHuman
            SPECIE_HUMANOID -> ColorHumanoid
            SPECIE_ALIEN -> ColorAlien
            SPECIE_ROBOT -> ColorRobot
            SPECIE_POOPY -> ColorPoopy
            SPECIE_CREATURE -> ColorCreature
            SPECIE_DISEASE -> ColorDisease
            else -> Color.White
        }

    fun getColorFromGender(gender: String): Color =
        when (gender) {
            GENDER_MALE -> ColorMale
            GENDER_FEMALE -> ColorFemale
            else -> Color.White
        }

}
