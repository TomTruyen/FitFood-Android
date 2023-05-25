package com.tomtruyen.fitfood.ui.screens.onboarding

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tomtruyen.fitfood.Dimens
import com.tomtruyen.fitfood.R
import com.tomtruyen.fitfood.models.Gender
import com.tomtruyen.fitfood.ui.screens.shared.Buttons
import com.tomtruyen.fitfood.ui.screens.shared.composables.GenderCard

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun OnboardingPersonalInformation(viewModel: OnboardingViewModel, onContinue: () -> Unit) {
    val gender by viewModel.gender.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.onboarding_personal_information_title),
            style = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(vertical = Dimens.PaddingNormal)
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            GenderCard(
                gender = Gender.MALE,
                selected = gender == Gender.MALE,
                onClick = { viewModel.setGender(Gender.MALE) },
                modifier = Modifier.weight(1f).padding(end = Dimens.PaddingSmall)
            )

            GenderCard(
                gender = Gender.FEMALE,
                selected = gender == Gender.FEMALE,
                onClick = { viewModel.setGender(Gender.FEMALE) },
                modifier = Modifier.weight(1f).padding(start = Dimens.PaddingSmall)
            )
        }

        Buttons.Default(
            text = stringResource(id = R.string.next),
            enabled = gender != null,
            onClick = { onContinue() }
        )
    }
}