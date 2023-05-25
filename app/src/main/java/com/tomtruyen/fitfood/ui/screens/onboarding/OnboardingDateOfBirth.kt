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
fun OnboardingDateOfBirth(viewModel: OnboardingViewModel, onContinue: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // TODO: DoB Selector (save as Long)

        Buttons.Default(
            text = stringResource(id = R.string.next),
            enabled = true,
            onClick = { onContinue() }
        )
    }
}