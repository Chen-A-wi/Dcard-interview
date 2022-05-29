package com.example.searchdemo.common

import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.searchdemo.common.Constants.AVOID_MULTIPLE_CLICK_MILLISECOND
import com.example.searchdemo.common.Constants.AVOID_MULTIPLE_CLICK_TIME_TYPE
import com.example.searchdemo.common.ext.hideKeyboard
import com.jakewharton.rxbinding2.view.RxView

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("onFocusChange")
    fun onFocusChange(edit: EditText, listener: View.OnFocusChangeListener) {
        edit.onFocusChangeListener = listener
    }
}
