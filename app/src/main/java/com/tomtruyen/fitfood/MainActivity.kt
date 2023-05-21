package com.tomtruyen.fitfood

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.dependency
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.navigation.popUpTo
import com.tomtruyen.fitfood.managers.AuthManager
import com.tomtruyen.fitfood.ui.screens.NavGraphs
import com.tomtruyen.fitfood.ui.screens.destinations.HomeScreenDestination
import com.tomtruyen.fitfood.ui.screens.destinations.LoginScreenDestination
import com.tomtruyen.fitfood.ui.theme.FitFoodTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent {
            FitFoodTheme {
                val navController = rememberNavController()

                LaunchedEffect(Unit) {
                    if(AuthManager.isLoggedIn()) {
                        navController.navigateAndClearBackstack(
                            direction = HomeScreenDestination,
                            untilDirection = LoginScreenDestination
                        )
                    }
                }

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DestinationsNavHost(
                        navGraph = NavGraphs.root,
                        navController = navController,
                        dependenciesContainerBuilder = {
                            dependency(navController)
                        }
                    )
                }
            }
        }
    }
}