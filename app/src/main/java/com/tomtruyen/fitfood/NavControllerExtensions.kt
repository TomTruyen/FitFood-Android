package com.tomtruyen.fitfood

import androidx.navigation.NavController
import com.ramcosta.composedestinations.spec.Direction

fun NavController.navigateAndClearBackstack(
    direction: Direction,
    untilDirection: Direction
) {
    navigate(direction.route) {
        popUpTo(untilDirection.route) {
            inclusive = true
        }
    }
}