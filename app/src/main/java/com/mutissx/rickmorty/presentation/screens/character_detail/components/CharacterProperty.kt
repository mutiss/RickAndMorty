package com.mutissx.rickmorty.presentation.screens.character_detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mutissx.rickmorty.presentation.ui.theme.ColorLabel

@Composable
fun CharacterProperty(label: String, value: String, color: Color) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = label,
                fontSize = 20.sp,
                color = ColorLabel,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = value,
                modifier = Modifier
                    .padding(horizontal = 16.dp).align(Alignment.CenterVertically),
                fontSize = 18.sp,
                color = color,
                fontWeight = FontWeight.Normal,
            )
        }
    }
}