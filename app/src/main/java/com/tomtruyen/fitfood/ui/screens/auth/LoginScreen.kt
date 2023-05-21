package com.tomtruyen.fitfood.ui.screens.auth

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseUser
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.navigate
import com.tomtruyen.fitfood.Dimens
import com.tomtruyen.fitfood.R
import com.tomtruyen.fitfood.managers.AuthManager
import com.tomtruyen.fitfood.models.AuthCallback
import com.tomtruyen.fitfood.ui.screens.destinations.RegisterScreenDestination
import com.tomtruyen.fitfood.ui.screens.destinations.HomeScreenDestination
import com.tomtruyen.fitfood.ui.screens.shared.Buttons
import com.tomtruyen.fitfood.ui.screens.shared.TextFields
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@RootNavGraph(start = true)
@Destination
@Composable
fun LoginScreen(navController: NavController) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val keyboardController = LocalSoftwareKeyboardController.current

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    val authCallback = remember {
        object: AuthCallback {
            override fun onSuccess(user: FirebaseUser) {
                navController.navigate(HomeScreenDestination)
            }

            override fun onFailure(error: String) {
                isLoading = false
                // Display Snackbar with Error
                scope.launch {
                    snackbarHostState.currentSnackbarData?.dismiss()
                    snackbarHostState.showSnackbar(error)
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
        ) {
            if(isLoading) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Start)
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(Dimens.PaddingNormal)
            ) {
                Text(
                    text = stringResource(id = R.string.title_login),
                    style = MaterialTheme.typography.headlineLarge.copy(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.SemiBold
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = stringResource(id = R.string.subtitle_login),
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = Dimens.PaddingNormal)
                )

                TextFields.Default(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = stringResource(id = R.string.placeholder_email),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = Dimens.PaddingNormal)
                )

                TextFields.Default(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = stringResource(id = R.string.placeholder_password),
                    obscureText = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = Dimens.PaddingSmall)
                )

                Buttons.Default(
                    text = stringResource(id = R.string.button_login),
                    keyboardController = keyboardController,
                    onClick = {
                        isLoading = true
                        AuthManager.loginWithEmailAndPassword(email, password, authCallback)
                    },
                )

                Buttons.Google(
                    text = stringResource(id = R.string.button_login_google),
                    keyboardController = keyboardController,
                    onClick = { isLoading = true },
                    onSignInResult = authCallback
                )

                Buttons.Text(
                    text = stringResource(id = R.string.need_account),
                    keyboardController = keyboardController,
                    onClick = {
                        keyboardController?.hide()
                        navController.navigate(RegisterScreenDestination)
                    }
                )

                // TODO: Add validation on SignIn Click so we don't have to make a request to the server
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        navController = NavController(LocalContext.current)
    )
}