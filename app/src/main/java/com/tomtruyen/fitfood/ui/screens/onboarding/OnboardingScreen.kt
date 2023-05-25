package com.tomtruyen.fitfood.ui.screens.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.viewpager2.widget.ViewPager2
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.tomtruyen.fitfood.Dimens
import com.tomtruyen.fitfood.ui.screens.shared.composables.IndicatorLineRow
import com.tomtruyen.fitfood.ui.theme.DarkGunMetal
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

    Scaffold {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            IndicatorLineRow(
                pagerState = pagerState,
                pageIndex = pagerState.pageCount
            )
            HorizontalPager(
                state = pagerState,
                userScrollEnabled = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(Dimens.PaddingNormal)
            ) { page ->
                when(page) {
                    0 -> OnboardingPersonalInformation(viewModel = viewModel) { onContinue() }
                    1 -> OnboardingPersonalMeasurements(viewModel = viewModel) { onContinue() }
                }
            }
        }
    }
}