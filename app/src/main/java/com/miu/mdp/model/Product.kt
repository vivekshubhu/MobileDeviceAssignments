package com.miu.mdp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    val itemId: String,
    val title: String,
    val price: Double,
    val color: String,
    val desc: String,
    val image: Int
) : Parcelable