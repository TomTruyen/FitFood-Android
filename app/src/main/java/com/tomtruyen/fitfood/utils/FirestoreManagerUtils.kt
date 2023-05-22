package com.tomtruyen.fitfood.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.tomtruyen.fitfood.managers.FirestoreManager
import org.koin.java.KoinJavaComponent.get
import org.koin.java.KoinJavaComponent.inject

@Composable
fun rememberFirestoreManager(): FirestoreManager {
    return remember { get(FirestoreManager::class.java) }
}