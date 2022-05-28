package com.example.searchdemo.common

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.searchdemo.common.Constants.AVOID_MULTIPLE_CLICK_MILLISECOND
import com.example.searchdemo.common.Constants.AVOID_MULTIPLE_CLICK_TIME_TYPE
import com.example.searchdemo.common.ext.hideKeyboard
import com.jakewharton.rxbinding2.view.RxView

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("android:src")
    fun setImageSrc(imageView: ImageView, resId: Int) {
        imageView.setImageResource(resId)
    }

    @JvmStatic
    @BindingAdapter("hideKeyboard")
    fun hideKeyboard(view: View, bool: Boolean) {
        RxView.clicks(view)
            .throttleFirst(AVOID_MULTIPLE_CLICK_MILLISECOND, AVOID_MULTIPLE_CLICK_TIME_TYPE)
            .subscribe {
                view.context.hideKeyboard(view)
            }
    }
}
