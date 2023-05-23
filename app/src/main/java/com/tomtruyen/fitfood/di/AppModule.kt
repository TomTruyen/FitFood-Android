package com.tomtruyen.fitfood.di

import com.tomtruyen.fitfood.managers.FirestoreManager
import com.tomtruyen.fitfood.ui.screens.onboarding.OnboardingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { FirestoreManager() }
    viewModel { OnboardingViewModel() }
}