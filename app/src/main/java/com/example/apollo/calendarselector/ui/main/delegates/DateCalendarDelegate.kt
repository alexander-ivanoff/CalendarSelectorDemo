package com.example.apollo.calendarselector.ui.main.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import com.arrival.adp.adapterdelegates.delegates.base.ViewBindingDelegate
import com.example.apollo.calendarselector.R
import com.example.apollo.calendarselector.databinding.DateDelegateBinding
import com.example.apollo.calendarselector.ui.main.items.DateItem
import java.time.LocalDate
import java.util.*

class DateCalendarDelegate(
    private val action: (LocalDate) -> Unit
) : ViewBindingDelegate<DateItem, DateDelegateBinding>() {


    override fun createViewBinding(parent: ViewGroup): DateDelegateBinding {
        return DateDelegateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    override fun onBindViewHolder(holder: ViewHolder<DateDelegateBinding>, item: DateItem) {
        with(holder.binding) {
            day.text = item.date.dayOfMonth.toString()
            day.setCompoundDrawablesWithIntrinsicBounds(
                null,
                null,
                null,
                if (item.hasShift) root.context.getDrawable(R.drawable.shift_circle) else null
            )
            when(item.type) {
                DateItem.Type.NORMAL -> root.background = null
                DateItem.Type.START -> root.setBackgroundResource(R.drawable.select_start)
                DateItem.Type.IN -> root.setBackgroundResource(R.drawable.select_in)
                DateItem.Type.END -> root.setBackgroundResource(R.drawable.select_end)
                DateItem.Type.SINGLE -> root.setBackgroundResource(R.drawable.select_single)
            }
            root.setOnClickListener { action.invoke(item.date) }
        }
    }


    override fun areItemsTheSame(first: DateItem, second: DateItem): Boolean {
        return first.date == second.date
    }
}
