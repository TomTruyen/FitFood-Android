package com.tomtruyen.fitfood.ui.screens.shared.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tomtruyen.fitfood.Dimens
import com.tomtruyen.fitfood.R
import com.tomtruyen.fitfood.models.Gender

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenderCard(gender: Gender, onClick: () -> Unit, selected: Boolean = false, modifier: Modifier = Modifier) {
    // TODO: Change Card color to the old secondary color (the lighter background one)
    // TODO: Center the image
    // TODO: Make the height of the card = 1.5x the width of the card
    

    val selectedModifier = if(selected) {
        Modifier.border(
            width = 1.dp,
            color = MaterialTheme.colorScheme.primary,
            shape = MaterialTheme.shapes.medium
        )
    } else {
        Modifier
    }

    Card(
        modifier = modifier.padding(Dimens.PaddingNormal)
            .then(selectedModifier),
        onClick = onClick
    ) {
        Image(
            painter = painterResource(id = if(gender == Gender.MALE) R.drawable.ic_male else R.drawable.ic_female),
            contentDescription = null,
            modifier = Modifier.size(64.dp)
        )
    }
}