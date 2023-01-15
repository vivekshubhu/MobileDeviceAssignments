package com.miu.mdp.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.setImageUrl(url: String) {
    Glide.with(this)
        .load(url)
        .error(android.R.drawable.stat_notify_error)
        .into(this)
}