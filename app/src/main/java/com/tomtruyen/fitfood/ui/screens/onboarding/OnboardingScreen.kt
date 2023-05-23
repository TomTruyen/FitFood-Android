package com.tomtruyen.fitfood.ui.screens.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.viewpager2.widget.ViewPager2
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.tomtruyen.fitfood.Dimens
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get
import org.koin.compose.koinInject
import org.koin.java.KoinJavaComponent.get
import org.koin.java.KoinJavaComponent.inject

@OptIn(ExperimentalFoundationApi::class)
@RootNavGraph
@Destination
@Composable
fun OnboardingScreen(navController: NavController) {
    val viewModel: OnboardingViewModel = viewModel()

    val scope = rememberCoroutineScope()

    val pagerState = rememberPagerState { 2 }

    fun onContinue() {
        scope.launch {
            if(pagerState.currentPage != pagerState.pageCount - 1) {
                pagerState.animateScrollToPage(pagerState.currentPage + 1)
            }
        }
    }

    // TODO: Implement onboarding screen --> Use ViewPager to swipe through the screen and allow users to go back
    // TODO: Also add a progressbar/indicator to show the current progress of the onboarding screen (example: Trenara Calibration/Execution steps)
    Scaffold {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(Dimens.PaddingNormal)
        ) {
            LinearProgressIndicator(
                progress = pagerState.currentPage.toFloat() / pagerState.pageCount.toFloat(),
                color = MaterialTheme.colorScheme.primary
            )
            HorizontalPager(
                state = pagerState,
                userScrollEnabled = false,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                when(page) {
                    0 -> OnboardingPersonalInformation(viewModel = viewModel) { onContinue() }
                    1 -> OnboardingPersonalMeasurements(viewModel = viewModel) { onContinue() }
                }
            }
        }
    }
}