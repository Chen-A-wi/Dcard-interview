package com.example.searchdemo.common

object DateTimeFormatPattern {

    private const val PATTERN_WEEK = "EEE"
    private const val PATTERN_YEAR = "yyyy"
    private const val PATTERN_MONTH = "MMM"
    private const val PATTERN_DAY = "d"

    const val TIME_FORMAT_H_M_S = "HH:mm:ss"
    const val TIME_FORMAT_H_M = "HH:mm"

    const val DATE_FORMAT = "yyyy/MM/dd"
    const val DATE_FORMAT_EN = "$PATTERN_MONTH/dd/yyyy"

    const val DATE_TIME_FORMAT = "$DATE_FORMAT $TIME_FORMAT_H_M_S"
    const val DATE_TIME_FORMAT_EN = "$DATE_FORMAT_EN $TIME_FORMAT_H_M_S"

    const val DATE_TIME_FORMAT_GMT = "$DATE_FORMAT $TIME_FORMAT_H_M (O)"
    const val DATE_TIME_FORMAT_GMT_EN = "$DATE_FORMAT_EN $TIME_FORMAT_H_M (O)"

    const val DATE_TIME_SHORT_FORMAT = "$DATE_FORMAT $TIME_FORMAT_H_M"
    const val DATE_TIME_SHORT_FORMAT_EN = "$DATE_FORMAT_EN $TIME_FORMAT_H_M"

    /**
     * 年月日週
     * Tue, May 4, 2021
     * 2021年5月4日 週二
     * 2021年5月4日(火)
     */
    const val DATE_Y_M_D_E_EN = "$PATTERN_WEEK, $PATTERN_MONTH $PATTERN_DAY, $PATTERN_YEAR"
    const val DATE_Y_M_D_E_ZH = "${PATTERN_YEAR}年${PATTERN_MONTH}${PATTERN_DAY}日 $PATTERN_WEEK"
    const val DATE_Y_M_D_E_JP = "${PATTERN_YEAR}年${PATTERN_MONTH}${PATTERN_DAY}日($PATTERN_WEEK)"

    /**
     * 年月日
     * May 4, 2021
     * 2021年5月4日
     * 2021年5月4日
     */
    const val DATE_Y_M_D_EN = "$PATTERN_MONTH $PATTERN_DAY, $PATTERN_YEAR"
    const val DATE_Y_M_D_ZH = "${PATTERN_YEAR}年${PATTERN_MONTH}${PATTERN_DAY}日"
    const val DATE_Y_M_D_JP = "${PATTERN_YEAR}年${PATTERN_MONTH}${PATTERN_DAY}日"

    /**
     * 年月
     * May, 2021
     * 2021年5月
     * 2021年5月
     */
    const val DATE_Y_M_EN = "$PATTERN_MONTH, $PATTERN_YEAR"
    const val DATE_Y_M_ZH = "${PATTERN_YEAR}年$PATTERN_MONTH"
    const val DATE_Y_M_JP = "${PATTERN_YEAR}年$PATTERN_MONTH"

    /**
     * 年
     * 2021
     * 2021年
     * 2021年
     */
    const val DATE_Y_EN = PATTERN_YEAR
    const val DATE_Y_ZH = "${PATTERN_YEAR}年"
    const val DATE_Y_JP = "${PATTERN_YEAR}年"

    /**
     * 月
     * May
     * 5月
     * 5月
     */
    const val DATE_M = PATTERN_MONTH

    /**
     * 日
     * 4
     * 4日
     * 4日
     */
    const val DATE_D_EN = PATTERN_DAY
    const val DATE_D_ZH = "${PATTERN_DAY}日"
    const val DATE_D_JP = "${PATTERN_DAY}日"

    /**
     * 週
     * Tue
     * 週二
     * 火
     */
    const val DATE_E = PATTERN_WEEK

    /**
     * 月日週
     * Tue, May 4
     * 5月4日 週二
     * 5月4日(火)
     */
    const val DATE_M_D_E_EN = "$PATTERN_WEEK, $PATTERN_MONTH $PATTERN_DAY"
    const val DATE_M_D_E_ZH = "${PATTERN_MONTH}${PATTERN_DAY}日 $PATTERN_WEEK"
    const val DATE_M_D_E_JP = "${PATTERN_MONTH}${PATTERN_DAY}日($PATTERN_WEEK)"

    /**
     * 月日
     * May 4
     * 5月4日
     * 5月4日
     */
    const val DATE_M_D_EN = "$PATTERN_MONTH $PATTERN_DAY"
    const val DATE_M_D_ZH = "${PATTERN_MONTH}${PATTERN_DAY}日"
    const val DATE_M_D_JP = "${PATTERN_MONTH}${PATTERN_DAY}日"

    /**
     * 年月日
     * May 4, 2021 12:00 (GMT+8)
     * 2021年5月4日 12:00 (GMT+8)
     */
    const val DATE_Y_M_D_TIME_FORMAT_GMT = "$DATE_Y_M_D_ZH $TIME_FORMAT_H_M (O)"
    const val DATE_Y_M_D_TIME_FORMAT_GMT_EN = "$DATE_Y_M_D_EN $TIME_FORMAT_H_M (O)"
}
