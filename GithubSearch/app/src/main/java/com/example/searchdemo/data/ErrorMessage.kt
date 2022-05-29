package com.example.searchdemo.data

import android.content.Context
import com.example.searchdemo.common.ErrorType

data class ErrorMessage(
    val errorCode:Int,
) {
    fun message(context: Context): String {
        return "(${errorCode}) ${context.getString(ErrorType.fromCode(errorCode).resString)}"
    }
}

