package com.tomtruyen.fitfood.validation.rules

import android.content.Context
import com.tomtruyen.fitfood.R
import com.tomtruyen.fitfood.validation.TextRule

class MatchingPasswordsRule(
    otherValue: String = "",
    override val errorMessage: (context: Context) -> String = { it.getString(R.string.error_passwords_match) },
): TextRule {
    override val validationRule: (String) -> Boolean = { it == otherValue }
}
