package com.tomtruyen.fitfood.managers

import android.content.Context
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.tomtruyen.fitfood.BuildConfig
import com.tomtruyen.fitfood.R
import com.tomtruyen.fitfood.models.AuthCallback

object AuthManager {
    private val auth = FirebaseAuth.getInstance()

    fun getUser() = auth.currentUser

    fun isLoggedIn(): Boolean {
        return auth.currentUser != null
    }

    fun logout() {
        auth.signOut()
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

    fun getGoogleSignInClient(context: Context): SignInClient = Identity.getSignInClient(context)

    fun getGoogleSignInRequest(context: Context): BeginSignInRequest {
        return BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId(context.getString(R.string.google_cloud_platform_id))
                    .setFilterByAuthorizedAccounts(false)
                    .build()
            ).build()
    }

    fun loginWithGoogle(idToken: String, onSignInResult: AuthCallback) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if(task.isSuccessful && auth.currentUser != null) {
                    onSignInResult.onSuccess(auth.currentUser!!)
                } else {
                    onSignInResult.onFailure(task.exception?.message.toString())
                }
            }
    }
}