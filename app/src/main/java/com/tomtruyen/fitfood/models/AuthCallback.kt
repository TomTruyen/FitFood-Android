package com.tomtruyen.fitfood.models

import com.google.firebase.auth.FirebaseUser

interface AuthCallback {
    fun onSuccess(user: FirebaseUser)
    fun onFailure(error: String)
}