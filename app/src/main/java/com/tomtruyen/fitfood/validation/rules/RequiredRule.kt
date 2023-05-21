package com.tomtruyen.fitfood.validation.rules

import android.content.Context
import com.tomtruyen.fitfood.R
import com.tomtruyen.fitfood.validation.TextRule

class RequiredRule(
    override val errorMessage: (context: Context) -> String = { it.getString(R.string.error_required) }
): TextRule {
    override val validationRule: (String) -> Boolean = { it.isNotEmpty() && it.isNotBlank() }
}
