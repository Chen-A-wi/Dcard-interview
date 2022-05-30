package com.example.searchdemo.common.ext

import androidx.lifecycle.LiveData
import org.junit.jupiter.api.Assertions.assertEquals

infix fun <A> A.shouldBe(that: A) = assertEquals(that, this)

infix fun <A> A.shouldBeOne(list: List<A>) = assertEquals(true, list.any { this == it })

infix fun <A> LiveData<A>.shouldBe(that: A) = assertEquals(that, this.value)
