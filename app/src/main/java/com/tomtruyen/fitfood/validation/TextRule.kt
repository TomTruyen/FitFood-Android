package com.tomtruyen.fitfood.validation

import android.content.Context

interface TextRule {
    val errorMessage: (context : Context) -> String
    val validationRule: (String) -> Boolean
}
