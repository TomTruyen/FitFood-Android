package com.tomtruyen.fitfood.ui.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.navigate
import com.tomtruyen.fitfood.Dimens
import com.tomtruyen.fitfood.managers.AuthManager
import com.tomtruyen.fitfood.ui.screens.destinations.LoginScreenDestination
import com.tomtruyen.fitfood.ui.screens.shared.Buttons

@RootNavGraph
@Destination
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold {
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
                    navController.navigate(LoginScreenDestination)
                }
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = NavController(LocalContext.current))
}
