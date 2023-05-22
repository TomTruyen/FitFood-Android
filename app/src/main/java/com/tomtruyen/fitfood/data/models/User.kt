package com.tomtruyen.fitfood.data.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class User(
    @PrimaryKey
    var uid: String = "",
    var email: String = "",
    var name: String = "",
): RealmObject()