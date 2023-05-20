package com.tomtruyen.fitfood.managers

import com.google.firebase.auth.FirebaseAuth
import com.tomtruyen.fitfood.models.AuthCallback

object AuthManager {
    private val auth = FirebaseAuth.getInstance()

    fun isLoggedIn(): Boolean {
        return auth.currentUser != null
    }

    fun loginWithEmailAndPassword(email: String, password: String, callback: AuthCallback) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful && auth.currentUser != null) {
                    callback.onSuccess(auth.currentUser!!)
                } else {
                    callback.onFailure(task.exception?.message.toString())
                }
            }
    }

    fun registerWithEmailAndPassword(email: String, password: String, callback: AuthCallback) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful && auth.currentUser != null) {
                    callback.onSuccess(auth.currentUser!!)
                } else {
                    callback.onFailure(task.exception?.message.toString())
                }
            }
    }

    fun logout() {
        auth.signOut()
    }
}