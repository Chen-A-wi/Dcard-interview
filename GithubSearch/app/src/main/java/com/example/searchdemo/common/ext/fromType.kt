package com.example.searchdemo.common.ext

inline fun <reified T : Enum<T>, V> ((T) -> V).fromType(value: V, default: T): T {
    return enumValues<T>().firstOrNull { this(it) == value } ?: default
}