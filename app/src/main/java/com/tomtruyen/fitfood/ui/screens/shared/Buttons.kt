package com.tomtruyen.fitfood.ui.screens.shared

import android.app.Activity
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import com.tomtruyen.fitfood.Dimens
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.tomtruyen.fitfood.R
import com.tomtruyen.fitfood.managers.AuthManager
import com.tomtruyen.fitfood.models.AuthCallback

object Buttons {
    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    fun Default(
        text: String,
        onClick: () -> Unit,
        keyboardController: SoftwareKeyboardController? = null,
        enabled: Boolean = true,
        shape: Shape = MaterialTheme.shapes.medium,
        modifier: Modifier = Modifier
            .fillMaxWidth()
            .padding(top = Dimens.PaddingNormal)
            .clip(MaterialTheme.shapes.medium)
    ) {
        Button(
            onClick = {
                keyboardController?.hide()
                onClick()
            },
            enabled = enabled,
            shape = shape,
            modifier = modifier

        ) {
            Text(
                text = text
            )
        }
    }

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    fun Text(
        text: String,
        onClick: () -> Unit,
        keyboardController: SoftwareKeyboardController? = null,
        shape: Shape = MaterialTheme.shapes.medium,
        modifier: Modifier = Modifier
            .fillMaxWidth()
            .padding(top = Dimens.PaddingNormal)
            .clip(MaterialTheme.shapes.medium)
    ) {
        TextButton(
            onClick = {
                keyboardController?.hide()
                onClick()
            },
            shape = shape,
            modifier = modifier
        ) {
            Text(
                text = text
            )
        }
    }

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    fun Google(
        text: String,
        keyboardController: SoftwareKeyboardController? = null,
        onClick: () -> Unit,
        onSignInResult: AuthCallback,
        shape: Shape = MaterialTheme.shapes.medium,
        modifier: Modifier = Modifier
            .fillMaxWidth()
            .padding(top = Dimens.PaddingTiny)
            .clip(MaterialTheme.shapes.medium)
    ) {
        val context = LocalContext.current

        val client = remember { AuthManager.getGoogleSignInClient(context) }
        val request = remember { AuthManager.getGoogleSignInRequest(context) }
        
        val signInResultLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
            if(result.resultCode == Activity.RESULT_OK) {
                val response = result.data

                if(response != null) {
                    val credential = client.getSignInCredentialFromIntent(response)
                    val idToken = credential.googleIdToken

                    when {
                        idToken != null -> {
                            AuthManager.loginWithGoogle(idToken, onSignInResult)
                        }
                        else -> onSignInResult.onFailure(context.getString(R.string.error_google_sign_in_failed))
                    }
                } else {
                    onSignInResult.onFailure(context.getString(R.string.error_google_sign_in_failed))
                }
            } else {
                onSignInResult.onFailure(context.getString(R.string.error_google_sign_in_cancelled))
            }
        }

        Button(
            onClick = {
                keyboardController?.hide()
                onClick()
                client.beginSignIn(request).addOnCompleteListener { task ->
                    if(task.isSuccessful) {
                        val intentSender = task.result.pendingIntent.intentSender
                        val intentSenderRequest = IntentSenderRequest.Builder(intentSender).build()
                        signInResultLauncher.launch(intentSenderRequest)
                    } else {
                        onSignInResult.onFailure(context.getString(R.string.error_google_sign_in_failed))
                    }
                }
            },
            shape = shape,
            modifier = modifier,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            )
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo_google),
                contentDescription = null,
                modifier = Modifier.size(20.dp),
            )
            Spacer(modifier = Modifier.width(Dimens.PaddingTiny))
            Text(
                text = text,
                style = TextStyle(
                    color = Color.Black
                )
            )
        }
    }
}