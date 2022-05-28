package com.example.searchdemo.common.utils

import com.google.gson.Gson
import java.lang.reflect.Type

object GsonUtil {

    private val g = Gson()

    enum class JsonType {
        JSON_OBJECT, JSON_ARRAY, JSON_TYPE_ERROR
    }

    fun getJSONType(str: String): JsonType {
        val strChar = str.substring(0, 1).toCharArray()
        val firstChar = strChar[0]
        return when (firstChar) {
            '{' -> {
                JsonType.JSON_OBJECT
            }
            '[' -> {
                JsonType.JSON_ARRAY
            }
            else -> {
                JsonType.JSON_TYPE_ERROR
            }
        }
    }

    fun <T> fromJson(json: String, clazz: Class<T>): T {
        return g.fromJson(json, clazz)
    }

    fun <T> fromJson(json: String, typeOfT: Type): T {
        return g.fromJson(json, typeOfT)
    }

    fun toJson(obj: Any): String {
        return g.toJson(obj)
    }
}