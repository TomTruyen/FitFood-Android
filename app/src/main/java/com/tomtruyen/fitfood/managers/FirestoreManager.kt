package com.tomtruyen.fitfood.managers

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.tomtruyen.fitfood.data.models.User
import com.tomtruyen.fitfood.repositories.UserRepository

class FirestoreManager {
    private val db = Firebase.firestore

    fun getUserData(uid: String, callback: FirestoreUserCallback) {
        db.collection(COLLECTION_USERS).document(uid).get().addOnSuccessListener { document ->
            if(document.exists()) {
                document.toObject(User::class.java)?.let { user ->
                    UserRepository.userDao.save(user)
                    callback.onSuccess(user)
                }
            } else {
                callback.onFailure()
            }
        }
    }

    companion object {
        private const val COLLECTION_USERS = "users"
    }

    interface FirestoreUserCallback {
        fun onSuccess(value: User)
        fun onFailure(error: String? = null)
    }
}