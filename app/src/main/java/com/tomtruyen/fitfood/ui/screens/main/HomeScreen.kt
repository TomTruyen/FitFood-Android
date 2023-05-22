package com.tomtruyen.fitfood.ui.screens.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.tomtruyen.fitfood.Dimens
import com.tomtruyen.fitfood.data.models.User
import com.tomtruyen.fitfood.managers.AuthManager
import com.tomtruyen.fitfood.managers.FirestoreManager
import com.tomtruyen.fitfood.navigateAndClearBackstack
import com.tomtruyen.fitfood.ui.screens.destinations.HomeScreenDestination
import com.tomtruyen.fitfood.ui.screens.destinations.LoginScreenDestination
import com.tomtruyen.fitfood.ui.screens.destinations.OnboardingScreenDestination
import com.tomtruyen.fitfood.ui.screens.shared.Buttons
import com.tomtruyen.fitfood.utils.rememberFirestoreManager

@OptIn(ExperimentalComposeUiApi::class)
@RootNavGraph
@Destination
@Composable
fun HomeScreen(navController: NavController) {
    val firestoreManager = rememberFirestoreManager()

    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        // Get user data
        AuthManager.getUser()?.let {
            firestoreManager.getUserData(it.uid, object: FirestoreManager.FirestoreUserCallback {
                override fun onSuccess(value: User) {
                    // Stop loading when we have the user data and it isn't a new user
                    isLoading = false
                }

                override fun onFailure(error: String?) {
                    // User data is not found -> new user -> Onboarding
                    navController.navigateAndClearBackstack(
                        direction = OnboardingScreenDestination,
                        untilDirection = HomeScreenDestination
                    )
                }
            })
        }
    }

    Scaffold {
        if (isLoading) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        } else {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(Dimens.PaddingNormal)
            ) {
                Buttons.Text(
                    text = "Sign out",
                    onClick = {
                        AuthManager.logout()
                        navController.navigateAndClearBackstack(
                            direction = LoginScreenDestination,
                            untilDirection = HomeScreenDestination
                        )
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = NavController(LocalContext.current))
}
