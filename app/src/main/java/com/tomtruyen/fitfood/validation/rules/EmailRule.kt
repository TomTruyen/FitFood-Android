package com.tomtruyen.fitfood.validation.rules

import android.content.Context
import com.tomtruyen.fitfood.R
import com.tomtruyen.fitfood.validation.TextRule

class EmailRule(
    override val errorMessage: (context: Context) -> String = { it.getString(R.string.error_invalid_email) },
) : TextRule {
    companion object {
        private const val EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]+$"
    }

    override val validationRule: (String) -> Boolean = { it.matches(Regex(EMAIL_REGEX)) }
}
