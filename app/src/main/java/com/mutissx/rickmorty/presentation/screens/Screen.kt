package com.mutissx.rickmorty.presentation.screens

import com.mutissx.rickmorty.presentation.util.Constants

sealed class Screen(val route: String) {
    companion object {
        private const val CHARACTER_LIST_SCREEN = "characters_list_screen"
        private const val CHARACTER_DETAIL_SCREEN = "character_detail_screen"
    }

    object CharactersListScreen : Screen(CHARACTER_LIST_SCREEN)

    object CharactersDetailScreen : Screen("$CHARACTER_DETAIL_SCREEN/{${Constants.CHARACTER_ID_DETAIL_ARGUMENT_KEY}}/{${Constants.CHARACTER_LOCATION_IDDETAIL_ARGUMENT_KEY}}") {
        fun setCharParams(characterId: Int, locationId: Int): String =
            "$CHARACTER_DETAIL_SCREEN/$characterId/$locationId"
    }
}