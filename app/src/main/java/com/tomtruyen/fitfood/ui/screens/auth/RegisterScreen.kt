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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import be.appwise.core.validation.ValidationResult
import com.google.firebase.auth.FirebaseUser
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.navigate
import com.tomtruyen.fitfood.Dimens
import com.tomtruyen.fitfood.R
import com.tomtruyen.fitfood.managers.AuthManager
import com.tomtruyen.fitfood.models.AuthCallback
import com.tomtruyen.fitfood.ui.screens.destinations.LoginScreenDestination
import com.tomtruyen.fitfood.ui.screens.shared.Buttons
import com.tomtruyen.fitfood.ui.screens.shared.TextFields
import com.tomtruyen.fitfood.validation.TextValidator
import com.tomtruyen.fitfood.validation.errorMessage
import com.tomtruyen.fitfood.validation.isValid
import com.tomtruyen.fitfood.validation.rules.EmailRule
import com.tomtruyen.fitfood.validation.rules.MatchingPasswordsRule
import com.tomtruyen.fitfood.validation.rules.PasswordRule
import com.tomtruyen.fitfood.validation.rules.RequiredRule
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@RootNavGraph
@Destination
@Composable
fun RegisterScreen(navController: NavController) {
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val keyboardController = LocalSoftwareKeyboardController.current

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var repeatPassword by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    val emailValidator = remember {
        TextValidator.withRules(EmailRule())
    }

    val passwordValidator = remember {
        TextValidator.withRules(PasswordRule())
    }

    var emailValidationResult by remember { mutableStateOf(null as ValidationResult?) }
    var passwordValidationResult by remember { mutableStateOf(null as ValidationResult?) }
    var repeatPasswordValidationResult by remember { mutableStateOf(null as ValidationResult?) }

    DisposableEffect(email) {
        onDispose {
            emailValidationResult = emailValidator.validate(context, email)
        }
    }

    DisposableEffect(password, repeatPassword) {
        onDispose {
            passwordValidationResult = passwordValidator.validate(context, password)
            repeatPasswordValidationResult = TextValidator.withRules(MatchingPasswordsRule(password)).validate(context, repeatPassword)
        }
    }

    val authCallback = remember {
        object: AuthCallback {
            override fun onSuccess(user: FirebaseUser) {
                navController.navigate(LoginScreenDestination)
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
            if (isLoading) {
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
                    text = stringResource(id = R.string.lets_get_started),
                    style = MaterialTheme.typography.headlineLarge.copy(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.SemiBold
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = stringResource(id = R.string.subtitle_register),
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
                    error = emailValidationResult.errorMessage(),
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
                    error = passwordValidationResult.errorMessage(),
                    obscureText = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = Dimens.PaddingSmall)
                )

                TextFields.Default(
                    value = repeatPassword,
                    onValueChange = { repeatPassword = it },
                    placeholder = stringResource(id = R.string.placeholder_password_repeat),
                    error = repeatPasswordValidationResult.errorMessage(),
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
                    text = stringResource(id = R.string.button_register),
                    keyboardController = keyboardController,
                    enabled = emailValidationResult?.isValid() == true
                            && passwordValidationResult?.isValid() == true
                            && repeatPasswordValidationResult?.isValid() == true,
                    onClick = {
                        isLoading = true
                        AuthManager.registerWithEmailAndPassword(email, password, authCallback)
                    },
                )

                Buttons.Text(
                    text = stringResource(id = R.string.have_an_account),
                    keyboardController = keyboardController,
                    onClick = {
                        navController.navigate(LoginScreenDestination)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(
        navController = NavController(LocalContext.current)
    )
}