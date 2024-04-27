package com.mutissx.rickmorty.presentation.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mutissx.rickmorty.R
import com.mutissx.rickmorty.presentation.ui.theme.BlueBackground


@Composable
fun Loader() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loader_morty))
    val lottieProgress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )
    Column(
        modifier = Modifier
            .fillMaxSize().background(BlueBackground),
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            composition,
            modifier = Modifier.size(140.dp),
            progress = { lottieProgress })
    }
}

@Composable
fun LoaderDetail() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loader_morty))
    val lottieProgress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )
    Column(
        modifier = Modifier
            .fillMaxSize().background(BlueBackground),
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.End
    ) {
        LottieAnimation(
            composition,
            modifier = Modifier.size(100.dp),
            progress = { lottieProgress })
    }
}
