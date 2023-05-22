package com.tomtruyen.fitfood.repositories

import com.tomtruyen.fitfood.data.dao.UserDao
import io.realm.Realm

object UserRepository {
    val userDao = UserDao(Realm.getDefaultInstance())
}