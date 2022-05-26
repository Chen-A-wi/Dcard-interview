package com.example.searchdemo.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("android:src")
    fun setImageSrc(imageView: ImageView, resId: Int) {
        imageView.setImageResource(resId)
    }
}