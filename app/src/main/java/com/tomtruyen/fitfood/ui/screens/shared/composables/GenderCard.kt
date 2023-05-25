package com.tomtruyen.fitfood.ui.screens.shared.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.tomtruyen.fitfood.Dimens
import com.tomtruyen.fitfood.R
import com.tomtruyen.fitfood.models.Gender
import com.tomtruyen.fitfood.ui.theme.DarkGunMetal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenderCard(gender: Gender, onClick: () -> Unit, selected: Boolean = false, modifier: Modifier = Modifier) {
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
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(0.75f)
            .then(selectedModifier),
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = DarkGunMetal
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = if (gender == Gender.MALE) R.drawable.ic_male else R.drawable.ic_female),
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = null,
                modifier = Modifier.size(64.dp)
            )
        }
    }
}