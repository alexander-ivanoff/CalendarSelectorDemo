package com.example.apollo.calendarselector.ui.main.items

import java.time.LocalDate

data class DateItem(
    val date: LocalDate,
    val type: Type,
    val hasShift: Boolean
) {

    enum class Type {
        NORMAL,
        START,
        IN,
        END,
        SINGLE
    }
}
