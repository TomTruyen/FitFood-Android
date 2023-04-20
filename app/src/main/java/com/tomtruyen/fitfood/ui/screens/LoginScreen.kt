package com.tomtruyen.fitfood.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.tomtruyen.fitfood.Dimens
import com.tomtruyen.fitfood.R
import com.tomtruyen.fitfood.ui.screens.shared.Buttons
import com.tomtruyen.fitfood.ui.screens.shared.TextFields
import com.tomtruyen.fitfood.ui.theme.DarkGunMetal

@RootNavGraph(start = true)
@Destination
@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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
                modifier = Modifier.fillMaxWidth()
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
                modifier = Modifier.fillMaxWidth()
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
                modifier = Modifier.fillMaxWidth()
                    .padding(top = Dimens.PaddingSmall)
            )

            Buttons.Default(
                text = stringResource(id = R.string.button_login),
                onClick = { /*TODO*/ },
            )

            // TODO: Add register Button
            // TODO: Add Google Login Button
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}