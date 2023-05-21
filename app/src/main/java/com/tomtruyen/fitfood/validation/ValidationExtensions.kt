package com.tomtruyen.fitfood.validation

import be.appwise.core.validation.ValidationResult

fun ValidationResult?.errorMessage(): String? = (this as? ValidationResult.Invalid)?.messages?.firstOrNull()

fun ValidationResult?.isValid(): Boolean = this?.passed == true
