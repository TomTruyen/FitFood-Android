package com.tomtruyen.fitfood.ui.screens.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.tomtruyen.fitfood.Dimens

@RootNavGraph
@Destination
@Composable
fun OnboardingScreen(navController: NavController) {
    // TODO: Implement onboarding screen --> Use ViewPager to swipe through the screen and allow users to go back
    // TODO: Also add a progressbar/indicator to show the current progress of the onboarding screen (example: Trenara Calibration/Execution steps)
    Scaffold {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(Dimens.PaddingNormal)
        ) {
            Text(text = "Onboarding Screen", color = Color.Red)
        }
    }
}