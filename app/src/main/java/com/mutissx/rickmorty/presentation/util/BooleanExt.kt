package com.mutissx.rickmorty.presentation.util

fun Boolean?.orFalse() =
    this ?: false