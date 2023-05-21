package com.tomtruyen.fitfood.validation.rules

import android.content.Context
import com.tomtruyen.fitfood.R
import com.tomtruyen.fitfood.validation.TextRule

class PasswordRule(
    override val errorMessage: (context: Context) -> String = { it.getString(R.string.error_weak_password) },
) : TextRule {
    companion object {
        private const val PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,}$"
    }

    override val validationRule: (String) -> Boolean = { it.matches(Regex(PASSWORD_REGEX)) }
}