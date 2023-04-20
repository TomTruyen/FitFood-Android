package com.tomtruyen.fitfood.ui.screens.shared

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import com.tomtruyen.fitfood.Dimens
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

object Buttons {
    @Composable
    fun Default(
        text: String,
        onClick: () -> Unit,
        shape: Shape = MaterialTheme.shapes.medium,
        modifier: Modifier = Modifier
            .fillMaxWidth()
            .padding(top = Dimens.PaddingNormal)
            .clip(MaterialTheme.shapes.medium)
    ) {
        Button(
            onClick = onClick,
            shape = shape,
            modifier = modifier

        ) {
            Text(
                text = text
            )
        }
    }
}