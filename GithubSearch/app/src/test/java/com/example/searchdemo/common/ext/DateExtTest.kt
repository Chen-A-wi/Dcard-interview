package com.example.searchdemo.common.ext

import com.google.common.truth.Truth
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.Month
import org.threeten.bp.format.TextStyle
import java.util.*

class DateExtTest {

    lateinit var localDateTime: LocalDateTime
    lateinit var localDate: LocalDate

    @BeforeEach
    fun setup() {
        localDateTime = LocalDateTime.of(2021, 5, 4, 11, 11, 11)
        localDate = LocalDate.of(2021, 5, 4)
    }

    @Test
    @DisplayName("LocalDate To ISO 測試")
    fun isoDateTimeStringToLocalDateTimeTest() {
        val dateTime = "2021-05-04T20:40:00+02:00".localDateTimeFromISO
        dateTime.year shouldBe 2021
        dateTime.month.value shouldBe 5
        dateTime.dayOfMonth shouldBe 4
        dateTime.dayOfWeek.value shouldBe 2
        dateTime.hour shouldBe 20
        dateTime.minute shouldBe 40
        dateTime.second shouldBe 0

        printMonthDisplayName(dateTime.month, Locale.CHINESE)
        printMonthDisplayName(dateTime.month, Locale.ENGLISH)
        printMonthDisplayName(dateTime.month, Locale.JAPAN)
        printWeekDayDisplayName(dateTime.dayOfWeek, Locale.CHINESE)
        printWeekDayDisplayName(dateTime.dayOfWeek, Locale.ENGLISH)
        printWeekDayDisplayName(dateTime.dayOfWeek, Locale.JAPAN)
    }

    @Test
    @DisplayName("LocalDate FormatYMD測試")
    fun localDateFormatYMDTest() {
        Truth.assertThat(localDate.formatYMD(Locale.ENGLISH)).isEqualTo("May 4, 2021")
        Truth.assertThat(localDate.formatYMD(Locale.TRADITIONAL_CHINESE)).isEqualTo("2021年5月4日")
        Truth.assertThat(localDate.formatYMD(Locale.JAPANESE)).isEqualTo("2021年5月4日")
    }

    private fun printWeekDayDisplayName(dayOfWeek: DayOfWeek, locale: Locale) {
        println("${TextStyle.SHORT} ${dayOfWeek.getDisplayName(TextStyle.SHORT, locale)}")
        println(
            "${TextStyle.SHORT_STANDALONE} ${
            dayOfWeek.getDisplayName(
                TextStyle.SHORT_STANDALONE,
                locale
            )
            }"
        )
        println("${TextStyle.NARROW} ${dayOfWeek.getDisplayName(TextStyle.NARROW, locale)}")
        println(
            "${TextStyle.NARROW_STANDALONE} ${
            dayOfWeek.getDisplayName(
                TextStyle.NARROW_STANDALONE,
                locale
            )
            }"
        )
        println("${TextStyle.FULL} ${dayOfWeek.getDisplayName(TextStyle.FULL, locale)}")
        println(
            "${TextStyle.FULL_STANDALONE} ${
            dayOfWeek.getDisplayName(
                TextStyle.FULL_STANDALONE,
                locale
            )
            }"
        )
    }

    private fun printMonthDisplayName(month: Month, locale: Locale) {
        println("${TextStyle.SHORT} ${month.getDisplayName(TextStyle.SHORT, locale)}")
        println(
            "${TextStyle.SHORT_STANDALONE} ${
            month.getDisplayName(
                TextStyle.SHORT_STANDALONE,
                locale
            )
            }"
        )
        println("${TextStyle.NARROW} ${month.getDisplayName(TextStyle.NARROW, locale)}")
        println(
            "${TextStyle.NARROW_STANDALONE} ${
            month.getDisplayName(
                TextStyle.NARROW_STANDALONE,
                locale
            )
            }"
        )
        println("${TextStyle.FULL} ${month.getDisplayName(TextStyle.FULL, locale)}")
        println(
            "${TextStyle.FULL_STANDALONE} ${
            month.getDisplayName(
                TextStyle.FULL_STANDALONE,
                locale
            )
            }"
        )
    }
}
