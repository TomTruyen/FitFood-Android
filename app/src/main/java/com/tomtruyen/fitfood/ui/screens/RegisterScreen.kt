package com.tomtruyen.fitfood.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.auth.FirebaseUser
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.tomtruyen.fitfood.Dimens
import com.tomtruyen.fitfood.R
import com.tomtruyen.fitfood.managers.AuthManager
import com.tomtruyen.fitfood.models.AuthCallback
import com.tomtruyen.fitfood.ui.screens.shared.Buttons
import com.tomtruyen.fitfood.ui.screens.shared.TextFields

@RootNavGraph
@Destination
@Composable
fun RegisterScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var repeatPassword by remember { mutableStateOf("") }

    val authCallback = remember {
        object: AuthCallback {
            override fun onSuccess(user: FirebaseUser) {
                Log.d("@@@", "Register Success: ${user.email}")
            }

            override fun onFailure(error: String) {
                Log.d("@@@", "Register Failure: $error")
            }

        }
    }

    Scaffold {
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

            TextFields.Default(
                value = repeatPassword,
                onValueChange = { repeatPassword = it },
                placeholder = stringResource(id = R.string.placeholder_password_repeat),
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
                onClick = { AuthManager.registerWithEmailAndPassword(email, password, authCallback) },
            )

            Buttons.Text(
                text = stringResource(id = R.string.have_an_account),
                onClick = {
                }
            )
        }
    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    RegisterScreen()
}