package com.miu.mdp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class User(
    val firstName: String,
    val lastName: String,
    val username: String,
    val password: String
) : Parcelable {
    override fun toString(): String {
        return "User(firstName='$firstName', lastName='$lastName', username='$username', password='$password')"
    }
}