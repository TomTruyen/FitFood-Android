package com.tomtruyen.fitfood.data.dao

import com.tomtruyen.fitfood.data.models.User
import io.realm.Realm
import io.realm.kotlin.toFlow
import kotlinx.coroutines.flow.Flow

class UserDao(private val realm: Realm) {
    companion object {
        private const val USER_ID = "uid"
    }

    fun save(user: User) {
        realm.executeTransactionAsync {
            it.insertOrUpdate(user)
        }
    }

    fun findById(uid: String): Flow<User?> {
        return realm.where(User::class.java).equalTo(USER_ID, uid).findFirstAsync().toFlow()
    }
}