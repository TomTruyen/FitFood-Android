package com.tomtruyen.fitfood.ui.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.tomtruyen.fitfood.R
import com.tomtruyen.fitfood.models.Gender
import com.tomtruyen.fitfood.ui.screens.shared.Buttons

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun OnboardingPersonalMeasurements(viewModel: OnboardingViewModel, onContinue: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // TODO: Add height and weight

        Buttons.Default(
            text = stringResource(id = R.string.next),
            enabled = viewModel.gender != null,
            onClick = { onContinue() }
        )
    }
}