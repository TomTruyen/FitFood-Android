package com.tomtruyen.fitfood

import android.app.Application
import com.tomtruyen.fitfood.di.appModule
import com.tomtruyen.fitfood.managers.ResourceProvider
import io.realm.Realm
import io.realm.RealmConfiguration
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        ResourceProvider.initialize(this)

        setupKoin()
        setupRealm()
    }

    private fun setupKoin() {
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(appModule)
        }
    }

    private fun setupRealm() {
        Realm.init(this)

        val config = RealmConfiguration.Builder()
            .allowQueriesOnUiThread(false)
            .schemaVersion(1)
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(config)
    }
}