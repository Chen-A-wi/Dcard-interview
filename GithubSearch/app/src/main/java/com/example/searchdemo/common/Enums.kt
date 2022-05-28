package com.example.searchdemo.common

import com.example.searchdemo.R
import com.example.searchdemo.common.ext.fromType

enum class ErrorType {
    NOT_MODIFIED, VALIDATION_FAILED, SERVICE_UNAVAILABLE, FORBIDDEN,
    RESOURCE_NOT_FOUND, UNKNOWN_ERROR;

    val code
        get() = when (this) {
            NOT_MODIFIED -> 304
            VALIDATION_FAILED -> 422
            SERVICE_UNAVAILABLE -> 503
            FORBIDDEN -> 403
            RESOURCE_NOT_FOUND -> 404
            UNKNOWN_ERROR -> -1
        }

    val resString
        get() = when (this) {
            NOT_MODIFIED -> R.string.lb_api_error_304
            VALIDATION_FAILED -> R.string.lb_api_error_422
            SERVICE_UNAVAILABLE -> R.string.lb_api_error_503
            FORBIDDEN -> R.string.lb_api_error_403
            RESOURCE_NOT_FOUND -> R.string.lb_api_error_404
            UNKNOWN_ERROR -> R.string.lb_api_unknown_error
        }

    companion object {
        fun fromCode(code: Int?): ErrorType = ErrorType::code.fromType(code, UNKNOWN_ERROR)
    }
}