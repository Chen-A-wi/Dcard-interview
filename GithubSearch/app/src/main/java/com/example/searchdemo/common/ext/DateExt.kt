package com.example.searchdemo.common.ext

import com.example.searchdemo.common.DateTimeFormatPattern
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.util.*

val String.localDateTimeFromISO: LocalDateTime
    get() = LocalDateTime.parse(this, DateTimeFormatter.ISO_DATE_TIME)

/**
 * 年月日
 * May 4, 2021
 * 2021年5月4日
 * 2021年5月4日
 */
fun LocalDate.formatYMD(locale: Locale = Locale.getDefault()): String {
    val pattern = when (locale) {
        Locale.ENGLISH -> DateTimeFormatPattern.DATE_Y_M_D_EN
        Locale.TRADITIONAL_CHINESE -> DateTimeFormatPattern.DATE_Y_M_D_ZH
        Locale.JAPANESE -> DateTimeFormatPattern.DATE_Y_M_D_JP
        else -> DateTimeFormatPattern.DATE_Y_M_D_EN
    }
    return format(DateTimeFormatter.ofPattern(pattern, locale))
}
