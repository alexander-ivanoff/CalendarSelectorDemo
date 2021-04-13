package com.example.apollo.calendarselector.ui.main.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import com.arrival.adp.adapterdelegates.delegates.base.ViewBindingDelegate
import com.example.apollo.calendarselector.databinding.MonthDelegateBinding
import com.example.apollo.calendarselector.ui.main.items.MonthItem
import java.time.format.TextStyle
import java.util.*

class MonthCalendarDelegate : ViewBindingDelegate<MonthItem, MonthDelegateBinding>() {

    override fun createViewBinding(parent: ViewGroup): MonthDelegateBinding {
        return MonthDelegateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    override fun onBindViewHolder(holder: ViewHolder<MonthDelegateBinding>, item: MonthItem) {
        with(holder.binding) {
            month.text = item.yearMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault()) + " " + item.yearMonth.year
        }
    }
}
