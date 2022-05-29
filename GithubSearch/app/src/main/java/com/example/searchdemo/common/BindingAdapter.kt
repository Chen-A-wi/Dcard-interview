package com.example.searchdemo.common

import android.view.View
import android.widget.EditText
import androidx.databinding.BindingAdapter

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("onFocusChange")
    fun onFocusChange(edit: EditText, listener: View.OnFocusChangeListener) {
        edit.onFocusChangeListener = listener
    }
}
