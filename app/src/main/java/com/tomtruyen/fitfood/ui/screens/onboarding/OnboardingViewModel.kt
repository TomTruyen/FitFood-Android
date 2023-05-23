package com.tomtruyen.fitfood.ui.screens.onboarding

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tomtruyen.fitfood.models.Gender
import kotlinx.coroutines.flow.MutableStateFlow

class OnboardingViewModel: ViewModel() {
    val gender = MutableStateFlow<Gender?>(null)
}