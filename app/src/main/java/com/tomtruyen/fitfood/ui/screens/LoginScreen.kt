package com.tomtruyen.fitfood.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.tomtruyen.fitfood.R

@RootNavGraph(start = true)
@Destination
@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // TODO: Alignments
    Column(
        // TODO: Add padding in a dimens file
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(id = R.string.title_login),
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = stringResource(id = R.string.subtitle_login),
            style = MaterialTheme.typography.headlineSmall
        )

        // TODO: Styling + Placeholders
        TextField(
            value = email,
            onValueChange = {
                email = it
            }
        )
        TextField(
            value = password,
            onValueChange = {
                password = it
            }
        )

        // TODO: Styling + Colors
        Button(
            onClick = { /*TODO*/ }
        ) {
            Text(
                text = stringResource(id = R.string.button_login)
            )
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}