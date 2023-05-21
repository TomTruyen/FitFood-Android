package com.tomtruyen.fitfood.managers

import android.content.Context
import android.content.res.Resources

object ResourceProvider {
    private lateinit var resources: Resources

    fun initialize(context: Context) {
        this.resources = context.resources
    }

    fun getString(id: Int) = resources.getString(id)
}