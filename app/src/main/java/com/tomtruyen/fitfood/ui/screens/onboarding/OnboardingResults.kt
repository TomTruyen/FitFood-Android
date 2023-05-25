package com.tomtruyen.fitfood.ui.screens.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.tomtruyen.fitfood.R
import com.tomtruyen.fitfood.ui.screens.shared.Buttons

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun OnboardingResults(viewModel: OnboardingViewModel, onContinue: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // TODO: Add results of calculations (KCAL, Protein, Carbs, Fat) for user to review
        // TODO: Also allow user to change the values if they want to

        Buttons.Default(
            text = stringResource(id = R.string.next),
            enabled = true,
            onClick = { onContinue() }
        )
    }
}