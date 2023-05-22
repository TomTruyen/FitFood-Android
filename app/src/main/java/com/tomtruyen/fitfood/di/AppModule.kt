package com.tomtruyen.fitfood.di

import com.tomtruyen.fitfood.managers.FirestoreManager
import org.koin.dsl.module

val appModule = module {
    single { FirestoreManager() }
}