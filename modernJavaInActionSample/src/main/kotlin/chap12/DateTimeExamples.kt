package chap12

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.*
import java.time.chrono.JapaneseDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField
import java.time.temporal.ChronoUnit
import java.time.temporal.Temporal
import java.time.temporal.TemporalAdjuster
import java.time.temporal.TemporalAdjusters.lastDayOfMonth
import java.time.temporal.TemporalAdjusters.nextOrSame
import java.util.*

fun main() {
    useOldDate()
    useLocalDate()
    useTemporalAdjuster()
    useDateFormatter()
}

private val FORMATTERS: ThreadLocal<DateFormat> = ThreadLocal.withInitial { SimpleDateFormat("dd-MMM-yyyy") }

private fun useOldDate() {
    val date: Date = Date(120, 0, 27)
    println(date)

    println(FORMATTERS.get().format(date))

    val calendar: Calendar = Calendar.getInstance()
    calendar.set(2020, Calendar.JANUARY, 27)
    println(calendar)
}

private fun useLocalDate() {
    val date: LocalDate = LocalDate.of(2020, 1, 27)
    val year = date.year    // 2020
    val month = date.month  // JAN
    val day = date.dayOfMonth   // 27
    val dayOfWeek = date.dayOfWeek  // Mon
    val len = date.lengthOfMonth()  // 31
    val leap = date.isLeapYear  // true
    println(date)
    println("$year-$month-$day ($dayOfWeek), lengthOfMonth: $len, leap: $leap")

    val y = date.get(ChronoField.YEAR)
    val m = date.get(ChronoField.MONTH_OF_YEAR)
    val d = date.get(ChronoField.DAY_OF_MONTH)
    println("$y-$m-$d")

    val time = LocalTime.of(13, 45, 20)
    val hour = time.hour
    val minute = time.minute
    val second = time.second
    println("$time - $hour:$minute:$second")

    val dt1 = LocalDateTime.of(2020, Month.JANUARY, 27, 13, 45, 20)
    val dt2 = LocalDateTime.of(date, time)
    val dt3 = date.atTime(13, 45, 20)
    val dt4 = time.atDate(date)
    println("$dt1, $dt2, $dt3, $dt4")

    val date1 = dt1.toLocalDate()
    println(date1)
    val time1 = dt1.toLocalTime()
    println(time1)

    val instant = Instant.ofEpochSecond(44 * 365 * 86400)
    val now = Instant.now()
    println("instant: $instant, now: $now")

    val d1 = Duration.between(LocalTime.of(13, 45, 10), time)
    val d2 = Duration.between(instant, now)
    println(d1.seconds)
    println(d2.seconds)

    val threeMinutes = Duration.of(3, ChronoUnit.MINUTES)
    println(threeMinutes)

    val japaneseDate = JapaneseDate.from(date)
    println(japaneseDate)
}

private fun useTemporalAdjuster() {
    var date = LocalDate.of(2020, 1, 27)
    date = date.with(nextOrSame(DayOfWeek.SUNDAY))
    println(date)
    date = date.with(lastDayOfMonth())
    println(date)

    date = date.with(NextWorkingDay())
    println(date)
    date = date.with(nextOrSame(DayOfWeek.FRIDAY))
    println(date)
    date = date.with(NextWorkingDay())
    println(date)

    date = date.with(nextOrSame(DayOfWeek.FRIDAY))
    println(date)
    date = date.with { temporal ->
        val dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK))
        var dayToAdd = 1L
        if (dow == DayOfWeek.FRIDAY) dayToAdd = 3L
        else if (dow == DayOfWeek.SATURDAY) dayToAdd = 2L
        temporal.plus(dayToAdd, ChronoUnit.DAYS)
    }
    println(date)
}

private class NextWorkingDay : TemporalAdjuster {
    override fun adjustInto(temporal: Temporal): Temporal {
        val dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK))
        var dayToAdd = 1L    // base
        if (dow == DayOfWeek.FRIDAY) dayToAdd = 3L
        else if (dow == DayOfWeek.SATURDAY) dayToAdd = 2L
        return temporal.plus(dayToAdd, ChronoUnit.DAYS)
    }
}

private fun useDateFormatter() {
    val date = LocalDate.of(2020, 1, 27)
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val italianFormatter = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.ITALIAN)

    println(date.format(DateTimeFormatter.ISO_LOCAL_DATE))
    println(date.format(formatter))
    println(date.format(italianFormatter))

    val complexFormatter = DateTimeFormatterBuilder()
        .appendText(ChronoField.DAY_OF_MONTH)
        .appendLiteral(". ")
        .appendText(ChronoField.MONTH_OF_YEAR)
        .appendLiteral(" ")
        .appendText(ChronoField.YEAR)
        .parseCaseInsensitive()
        .toFormatter(Locale.ITALIAN)
    println(date.format(complexFormatter))
}