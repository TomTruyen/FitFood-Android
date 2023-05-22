package com.tomtruyen.fitfood.managers

import android.content.Context
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.*
import com.tomtruyen.fitfood.BuildConfig
import com.tomtruyen.fitfood.R
import com.tomtruyen.fitfood.models.AuthCallback
import io.realm.Realm
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object AuthManager {
    private val auth = FirebaseAuth.getInstance()

    fun getUser() = auth.currentUser

    fun isLoggedIn(): Boolean {
        return auth.currentUser != null
    }

    fun logout() {
        auth.signOut()

        CoroutineScope(Dispatchers.IO).launch {
            Realm.getDefaultInstance().deleteAll()
        }
    }

    fun loginWithEmailAndPassword(email: String, password: String, callback: AuthCallback) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful && auth.currentUser != null) {
                    callback.onSuccess(auth.currentUser!!)
                } else {
                    callback.onFailure(formatException(task.exception!!))
                }
            }
    }

    fun registerWithEmailAndPassword(email: String, password: String, callback: AuthCallback) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful && auth.currentUser != null) {
                    callback.onSuccess(auth.currentUser!!)
                } else {
                    callback.onFailure(formatException(task.exception!!))
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
                    onSignInResult.onFailure(formatException(task.exception!!))
                }
            }
    }

    private fun formatException(exception: Exception): String {
        return when(exception) {
            is FirebaseAuthWeakPasswordException -> {
                // Weak Password
                ResourceProvider.getString(R.string.error_weak_password)
            }
            is FirebaseAuthInvalidUserException -> {
                // Wrong Credentials
                ResourceProvider.getString(R.string.error_wrong_credentials)
            }
            is FirebaseAuthUserCollisionException -> {
                // User already exists
                ResourceProvider.getString(R.string.error_email_already_exists)
            }
            else -> {
                // Default
                exception.message.toString()
            }
        }
    }
}