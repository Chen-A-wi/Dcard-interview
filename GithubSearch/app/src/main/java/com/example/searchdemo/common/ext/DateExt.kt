package com.example.searchdemo.common.ext

import com.example.searchdemo.common.DateTimeFormatPattern
import org.threeten.bp.*
import org.threeten.bp.format.DateTimeFormatter
import java.sql.Timestamp
import java.util.*
import kotlin.math.abs

fun Long.betweenTime(time: Long): Long {
    return abs(this - time)
}

val LocalDateTime.timestamp: Long
    get() {
        return DateTimeUtils.toSqlTimestamp(this).time
    }

val Long.toUTCLocalDateTime: LocalDateTime
    get() {
        return LocalDateTime.ofInstant(
            Instant.ofEpochSecond(this), DateTimeUtils.toZoneId(TimeZone.getTimeZone("UTC"))
        )
    }

val LocalDateTime.utcTimestamp: Long
    get() {
        return timestamp.millisecondToSecond.toUTCLocalDateTime.timestamp
    }

val LocalDate.localDateTime: LocalDateTime
    get() {
        return LocalDateTime.of(this, LocalTime.MIN)
    }

val Long.localDateTime: LocalDateTime
    get() {
        return DateTimeUtils.toLocalDateTime(Timestamp(this))
    }

val Long.millisecondToSecond: Long
    get() {
        return this / 1000
    }

val Long.secondToMillisecond: Long
    get() {
        return this * 1000
    }

val String.zonedDateTimeFromISO: ZonedDateTime
    get() = ZonedDateTime.parse(this, DateTimeFormatter.ISO_ZONED_DATE_TIME)

val String.localDateTimeFromISO: LocalDateTime
    get() = LocalDateTime.parse(this, DateTimeFormatter.ISO_DATE_TIME)

val String.localDateFromISO: LocalDate
    get() = LocalDate.parse(this, DateTimeFormatter.ISO_DATE)

fun String.localDateTime(locale: Locale = Locale.getDefault()): LocalDateTime {
    val pattern = when (locale.language) {
        "en" -> DateTimeFormatPattern.DATE_TIME_FORMAT_EN
        else -> DateTimeFormatPattern.DATE_TIME_FORMAT
    }
    return LocalDateTime.parse(this, DateTimeFormatter.ofPattern(pattern, locale))
}

fun String.localDate(locale: Locale = Locale.getDefault()): LocalDate {
    val pattern = when (locale.language) {
        "en" -> DateTimeFormatPattern.DATE_FORMAT_EN
        else -> DateTimeFormatPattern.DATE_FORMAT
    }
    return LocalDate.parse(this, DateTimeFormatter.ofPattern(pattern, locale))
}

fun String.localDateToString(): String {
    return if (this.isNotBlank()) {
        this.localDate().toString()
    } else {
        ""
    }
}

/**
 * @return
 * 英文: 05/04/2021 11:22 (GMT+*)
 * 中/日 文: 2021/05/04 11:22 (GMT+*)
 */
fun ZonedDateTime.text(locale: Locale = Locale.getDefault()): String {
    val pattern = when (locale.language) {
        "en" -> DateTimeFormatPattern.DATE_TIME_FORMAT_GMT_EN
        else -> DateTimeFormatPattern.DATE_TIME_FORMAT_GMT
    }
    return format(DateTimeFormatter.ofPattern(pattern, locale))
}

/**
 * @return
 * 英文: 05/04/2021 11:22:33
 * 中/日 文: 2021/05/04 11:22:33
 */
fun LocalDateTime.text(locale: Locale = Locale.getDefault()): String {
    val pattern = when (locale.language) {
        "en" -> DateTimeFormatPattern.DATE_TIME_FORMAT_EN
        else -> DateTimeFormatPattern.DATE_TIME_FORMAT
    }
    return format(DateTimeFormatter.ofPattern(pattern, locale))
}

/**
 * @return
 * 英文: May 4, 2021 12:00 (GMT+8)
 * 中/日 文: 2021年5月4日 12:00 (GMT+8)
 */
fun ZonedDateTime.formatYMDHMO(locale: Locale = Locale.getDefault()): String {
    val pattern = when (locale.language) {
        "en" -> DateTimeFormatPattern.DATE_Y_M_D_TIME_FORMAT_GMT_EN
        else -> DateTimeFormatPattern.DATE_Y_M_D_TIME_FORMAT_GMT
    }
    return format(DateTimeFormatter.ofPattern(pattern, locale))
}

/**
 * @return
 * 英文: 05/04/2021 11:22
 * 中/日 文: 2021/05/04 11:22
 */
fun LocalDateTime.shortText(locale: Locale = Locale.getDefault()): String {
    val pattern = when (locale.language) {
        "en" -> DateTimeFormatPattern.DATE_TIME_SHORT_FORMAT_EN
        else -> DateTimeFormatPattern.DATE_TIME_SHORT_FORMAT
    }
    return format(DateTimeFormatter.ofPattern(pattern, locale))
}

/**
 * @return
 * 英文: May/04/2021
 * 中/日 文: 2021/05/04
 */
fun LocalDate.text(locale: Locale = Locale.getDefault()): String {
    val pattern = when (locale.language) {
        "en" -> DateTimeFormatPattern.DATE_FORMAT_EN
        else -> DateTimeFormatPattern.DATE_FORMAT
    }
    return format(DateTimeFormatter.ofPattern(pattern, locale))
}

fun LocalTime.text(): String {
    return format(DateTimeFormatter.ofPattern(DateTimeFormatPattern.TIME_FORMAT_H_M_S))
}

fun LocalTime.shortText(): String {
    return format(DateTimeFormatter.ofPattern(DateTimeFormatPattern.TIME_FORMAT_H_M))
}

/**
 * 年月日週
 * Tue, May 4, 2021
 * 2021年5月4日 週二
 * 2021年5月4日(火)
 */
fun LocalDate.formatYMDE(locale: Locale = Locale.getDefault()): String {
    val pattern = when (locale) {
        Locale.ENGLISH -> DateTimeFormatPattern.DATE_Y_M_D_E_EN
        Locale.TRADITIONAL_CHINESE -> DateTimeFormatPattern.DATE_Y_M_D_E_ZH
        Locale.JAPANESE -> DateTimeFormatPattern.DATE_Y_M_D_E_JP
        else -> DateTimeFormatPattern.DATE_Y_M_D_E_EN
    }
    return format(DateTimeFormatter.ofPattern(pattern, locale))
}

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

/**
 * 年月
 * May, 2021
 * 2021年5月
 * 2021年5月
 */
fun LocalDate.formatYM(locale: Locale = Locale.getDefault()): String {
    val pattern = when (locale) {
        Locale.ENGLISH -> DateTimeFormatPattern.DATE_Y_M_EN
        Locale.TRADITIONAL_CHINESE -> DateTimeFormatPattern.DATE_Y_M_ZH
        Locale.JAPANESE -> DateTimeFormatPattern.DATE_Y_M_JP
        else -> DateTimeFormatPattern.DATE_Y_M_EN
    }
    return format(DateTimeFormatter.ofPattern(pattern, locale))
}

fun LocalDate.formatY(locale: Locale = Locale.getDefault()): String {
    val pattern = when (locale) {
        Locale.ENGLISH -> DateTimeFormatPattern.DATE_Y_EN
        Locale.TRADITIONAL_CHINESE -> DateTimeFormatPattern.DATE_Y_ZH
        Locale.JAPANESE -> DateTimeFormatPattern.DATE_Y_JP
        else -> DateTimeFormatPattern.DATE_Y_EN
    }
    return format(DateTimeFormatter.ofPattern(pattern, locale))
}

/**
 * 月日週
 * Tue, May 4
 * 5月4日 週二
 * 5月4日(火)
 */
fun LocalDate.formatMDE(locale: Locale = Locale.getDefault()): String {
    val pattern = when (locale) {
        Locale.ENGLISH -> DateTimeFormatPattern.DATE_M_D_E_EN
        Locale.TRADITIONAL_CHINESE -> DateTimeFormatPattern.DATE_M_D_E_ZH
        Locale.JAPANESE -> DateTimeFormatPattern.DATE_M_D_E_JP
        else -> DateTimeFormatPattern.DATE_M_D_E_EN
    }
    return format(DateTimeFormatter.ofPattern(pattern, locale))
}

/**
 * 月日
 * May 4
 * 5月4日
 * 5月4日
 */
fun LocalDate.formatMD(locale: Locale = Locale.getDefault()): String {
    val pattern = when (locale) {
        Locale.ENGLISH -> DateTimeFormatPattern.DATE_M_D_EN
        Locale.TRADITIONAL_CHINESE -> DateTimeFormatPattern.DATE_M_D_ZH
        Locale.JAPANESE -> DateTimeFormatPattern.DATE_M_D_JP
        else -> DateTimeFormatPattern.DATE_M_D_EN
    }
    return format(DateTimeFormatter.ofPattern(pattern, locale))
}

/**
 * 月
 * May
 * 5月
 * 5月
 */
fun LocalDate.formatM(locale: Locale = Locale.getDefault()): String {
    return format(DateTimeFormatter.ofPattern(DateTimeFormatPattern.DATE_M, locale))
}

/**
 * 日
 * 4
 * 4日
 * 4日
 */
fun LocalDate.formatD(locale: Locale = Locale.getDefault()): String {
    val pattern = when (locale) {
        Locale.ENGLISH -> DateTimeFormatPattern.DATE_D_EN
        Locale.TRADITIONAL_CHINESE -> DateTimeFormatPattern.DATE_D_ZH
        Locale.JAPANESE -> DateTimeFormatPattern.DATE_D_JP
        else -> DateTimeFormatPattern.DATE_D_EN
    }
    return format(DateTimeFormatter.ofPattern(pattern, locale))
}

/**
 * 週
 * Tue
 * 週二
 * 火
 */
fun LocalDate.formatE(locale: Locale = Locale.getDefault()): String {
    return format(DateTimeFormatter.ofPattern(DateTimeFormatPattern.DATE_E, locale))
}


/**
 * Format 0 h 0 m
 */
fun Int.formatHM(formatString: String): String {
    val h = (this / 60)
    val m = (this % 60)

    return String.format(formatString, h.toString(), m.toString())
}

/**
 * 跨日計算，DayOfMonth的差距，並非差距時間24小時的跨日
 */
fun LocalDateTime.getCrossDays(compareDateTime: LocalDateTime): Int {
    return Period.between(this.toLocalDate(), compareDateTime.toLocalDate()).days
}

/**
 * 跨日計算
 * @see getCrossDays
 * @return "+1","","-1"...etc more
 */
fun LocalDateTime.getCrossDaysWithText(compareDateTime: LocalDateTime): String {
    val crossDays = getCrossDays(compareDateTime)
    return when {
        crossDays > 0 -> {
            "+$crossDays"
        }
        crossDays < 0 -> {
            "-${abs(crossDays)}"
        }
        else -> {
            ""
        }
    }
}
