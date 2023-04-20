package com.tomtruyen.fitfood.ui.screens.shared

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.tomtruyen.fitfood.ui.theme.DarkGunMetal
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.tomtruyen.fitfood.R



object TextFields {
    @Composable
    fun Default(
        value: String,
        onValueChange: (String) -> Unit,
        placeholder: String = "",
        obscureText: Boolean = false,
        keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
        colors: TextFieldColors = TextFieldDefaults.colors(
            focusedContainerColor = DarkGunMetal,
            unfocusedContainerColor = DarkGunMetal,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedTextColor = MaterialTheme.colorScheme.onSurface,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
        ),
        shape: Shape = MaterialTheme.shapes.medium,
        modifier: Modifier = Modifier
    ) {
        var obscureTextVisible by rememberSaveable { mutableStateOf(false) }

        TextField(
            value = value,
            onValueChange = onValueChange,
            visualTransformation = if (!obscureText || obscureTextVisible) VisualTransformation.None else PasswordVisualTransformation(),
            placeholder = {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                )
            },
            keyboardOptions = keyboardOptions,
            singleLine = true,
            colors = colors,
            shape = shape,
            modifier = modifier,
            trailingIcon = {
                if(obscureText) {
                    IconButton(
                        onClick = { obscureTextVisible = !obscureTextVisible },
                    ) {
                        val icon = if(obscureTextVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                        val contentDescription = if(obscureTextVisible) {
                            stringResource(id = R.string.content_description_hide_password)
                        } else {
                            stringResource(id = R.string.content_description_show_password)
                        }

                        Icon(
                            imageVector = icon,
                            contentDescription = contentDescription,
                            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    }
                }
            }
        )
    }
}