package com.example.apollo.calendarselector.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apollo.calendarselector.ui.main.items.DateItem
import com.example.apollo.calendarselector.ui.main.items.EmptyItem
import com.example.apollo.calendarselector.ui.main.items.MonthItem
import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.ChronoUnit
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class MainViewModel : ViewModel() {

    private val _state = MutableLiveData<List<Any>>()
    val state: LiveData<List<Any>> = _state

    private val calendarStart: YearMonth = YearMonth.of(2018, 11)
    private val calendarEnd: YearMonth = YearMonth.of(2021, 4)

    private var selectStart: LocalDate? = null
    private var selectEnd: LocalDate? = null

    init {
        updateCalendar()
    }

    private fun updateCalendar() {
        var curMonth: YearMonth = calendarStart
        val items = mutableListOf<Any>()
        while ( curMonth <= calendarEnd ) {
            items.add(MonthItem(curMonth))
            val firstDay = curMonth.atDay(1)
            val emptyDays = firstDay.dayOfWeek.ordinal
            for (i in 0 until emptyDays) { items.add(EmptyItem) }
            var day = 1
            while (curMonth.isValidDay(day)) {
                items.add(
                    DateItem(
                        curMonth.atDay(day),
                        getType(curMonth.atDay(day)),
                        false
                    )
                )
                day++
            }
            curMonth = curMonth.plusMonths(1)
        }
        _state.postValue(items)
    }

    private fun getType(date: LocalDate) : DateItem.Type {
        return when {
            selectStart == null && selectEnd == null -> DateItem.Type.NORMAL
            date != selectStart && selectEnd == null -> DateItem.Type.NORMAL
            date == selectStart && selectEnd == null -> DateItem.Type.SINGLE
            (date < selectStart || date > selectEnd) -> DateItem.Type.NORMAL
            date > selectStart && date < selectEnd -> DateItem.Type.IN
            date == selectStart -> DateItem.Type.START
            else -> DateItem.Type.END
        }
    }

    fun selectDate(date: LocalDate) {
        when {
            selectStart == null -> selectStart = date
            selectStart != null && selectEnd == null && date != selectStart  -> {
                if (date < selectStart) {
                    selectEnd = selectStart
                    selectStart = date
                } else if (date > selectStart) {
                    selectEnd = date
                }
            }
            selectStart != null && date < selectStart -> selectStart = date
            selectEnd != null && date > selectEnd -> selectEnd = date
            else -> {
                selectStart = null
                selectEnd = null
            }
        }
        updateCalendar()
    }

}
