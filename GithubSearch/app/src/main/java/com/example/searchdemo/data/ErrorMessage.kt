package com.example.searchdemo.data

import android.content.Context
import com.example.searchdemo.common.ErrorType

data class ErrorMessage(
    val errorCode: Int = -1,
) {
    fun message(context: Context): String {
        return if (errorCode == -1) {
            context.getString(ErrorType.fromCode(errorCode).resString)
        } else {
            "(${errorCode}) ${context.getString(ErrorType.fromCode(errorCode).resString)}"
        }
    }
}

