package app.pentastic.utils

import app.pentastic.data.AppCalendar
import app.pentastic.data.AppLanguage

/** A broken-down date in the active calendar. [month] is 1..12. */
data class CalendarDate(val year: Int, val month: Int, val day: Int)

/**
 * Turns dates into display text. Two independent settings decide the result:
 * the [calendar] decides which year/month/day a date has, and the [language]
 * decides how month names, weekdays and separators are written.
 *
 * Works on epoch days (days since 1970-01-01), the unit the app already stores
 * due dates in, so it needs no date library and stays easy to test.
 */
class DateTexts(val language: AppLanguage, val calendar: AppCalendar) {
    private val isFarsi = language == AppLanguage.FARSI
    private val isJalali = calendar == AppCalendar.JALALI

    /** Year, month and day of [epochDays] in the active calendar. */
    fun parts(epochDays: Int): CalendarDate =
        if (isJalali) {
            val d = JalaliCalendar.fromEpochDays(epochDays)
            CalendarDate(d.year, d.month, d.day)
        } else {
            gregorianFromEpochDays(epochDays)
        }

    fun monthFull(month: Int): String = monthNames()[(month - 1).coerceIn(0, 11)]

    /** Short month name. Persian month names are short already, so they stay whole. */
    fun monthAbbrev(month: Int): String {
        val full = monthFull(month)
        return if (isFarsi) full else full.take(3)
    }

    fun weekdayFull(epochDays: Int): String =
        weekdayNames()[JalaliCalendar.weekdayFromSaturday(epochDays)]

    fun weekdayAbbrev(epochDays: Int): String {
        val full = weekdayFull(epochDays)
        return if (isFarsi) full else full.take(3)
    }

    /** Hook for digit style. Latin digits for now. */
    fun num(value: Int): String = value.toString()

    /** Two-digit year for Gregorian ("26"); Jalali years stay whole ("1405"). */
    fun yearShort(year: Int): String =
        if (isJalali) num(year) else (year % 100).toString().padStart(2, '0')

    /** "3–9" in English, "3 تا 9" in Persian. */
    fun joinCompact(from: String, to: String): String =
        if (isFarsi) "$from تا $to" else "$from–$to"

    /** "30 Aug – 2 Sep" in English, "30 مرداد تا 2 شهریور" in Persian. */
    fun joinSpaced(from: String, to: String): String =
        if (isFarsi) "$from تا $to" else "$from – $to"

    /** "4 Aug" / "4 مرداد". */
    fun dayMonth(epochDays: Int): String {
        val p = parts(epochDays)
        return "${num(p.day)} ${monthAbbrev(p.month)}"
    }

    /**
     * Label for a due range, e.g. "12 Oct", "12–18 Oct", "30 Oct – 2 Nov".
     * The year is only shown when it is not the current one.
     */
    fun dueRangeLabel(startEpochDays: Int, endEpochDays: Int, todayEpochDays: Int): String {
        val start = parts(startEpochDays)
        val end = parts(endEpochDays)
        val currentYear = parts(todayEpochDays).year

        fun dayMonth(p: CalendarDate) = "${num(p.day)} ${monthAbbrev(p.month)}"

        if (start.year != end.year) {
            return joinSpaced(
                "${dayMonth(start)} ${yearShort(start.year)}",
                "${dayMonth(end)} ${yearShort(end.year)}",
            )
        }

        val yearSuffix = if (start.year != currentYear) " ${num(start.year)}" else ""
        return when {
            startEpochDays == endEpochDays -> "${dayMonth(start)}$yearSuffix"
            start.month == end.month ->
                "${joinCompact(num(start.day), num(end.day))} ${monthAbbrev(start.month)}$yearSuffix"

            else -> "${joinSpaced(dayMonth(start), dayMonth(end))}$yearSuffix"
        }
    }

    private fun monthNames(): List<String> = when {
        isFarsi && isJalali -> FARSI_JALALI_MONTHS
        isFarsi -> FARSI_GREGORIAN_MONTHS
        isJalali -> ENGLISH_JALALI_MONTHS
        else -> ENGLISH_GREGORIAN_MONTHS
    }

    /** Indexed from Saturday, the first day of the Persian week. */
    private fun weekdayNames(): List<String> = if (isFarsi) FARSI_WEEKDAYS else ENGLISH_WEEKDAYS

    private companion object {
        val ENGLISH_GREGORIAN_MONTHS = listOf(
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December",
        )
        val ENGLISH_JALALI_MONTHS = listOf(
            "Farvardin", "Ordibehesht", "Khordad", "Tir", "Mordad", "Shahrivar",
            "Mehr", "Aban", "Azar", "Dey", "Bahman", "Esfand",
        )
        val FARSI_GREGORIAN_MONTHS = listOf(
            "ژانویه", "فوریه", "مارس", "آوریل", "مه", "ژوئن",
            "ژوئیه", "اوت", "سپتامبر", "اکتبر", "نوامبر", "دسامبر",
        )
        val FARSI_JALALI_MONTHS = listOf(
            "فروردین", "اردیبهشت", "خرداد", "تیر", "مرداد", "شهریور",
            "مهر", "آبان", "آذر", "دی", "بهمن", "اسفند",
        )
        val ENGLISH_WEEKDAYS = listOf(
            "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
        )
        val FARSI_WEEKDAYS = listOf(
            "شنبه", "یکشنبه", "دوشنبه", "سه‌شنبه", "چهارشنبه", "پنجشنبه", "جمعه",
        )
    }
}

/** Gregorian year/month/day of an epoch day (Howard Hinnant's civil-from-days algorithm). */
internal fun gregorianFromEpochDays(epochDays: Int): CalendarDate {
    val z = epochDays + 719468
    val era = (if (z >= 0) z else z - 146096) / 146097
    val doe = z - era * 146097
    val yoe = (doe - doe / 1460 + doe / 36524 - doe / 146096) / 365
    val doy = doe - (365 * yoe + yoe / 4 - yoe / 100)
    val mp = (5 * doy + 2) / 153
    val day = doy - (153 * mp + 2) / 5 + 1
    val month = if (mp < 10) mp + 3 else mp - 9
    val year = yoe + era * 400 + if (month <= 2) 1 else 0
    return CalendarDate(year, month, day)
}