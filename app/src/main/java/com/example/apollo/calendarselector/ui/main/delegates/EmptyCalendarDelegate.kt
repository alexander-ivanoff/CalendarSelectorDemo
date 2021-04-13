package com.example.apollo.calendarselector.ui.main.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import com.arrival.adp.adapterdelegates.delegates.base.ViewBindingDelegate
import com.example.apollo.calendarselector.databinding.EmptyDelegateBinding
import com.example.apollo.calendarselector.ui.main.items.EmptyItem

class EmptyCalendarDelegate : ViewBindingDelegate<EmptyItem, EmptyDelegateBinding>() {

    override fun createViewBinding(parent: ViewGroup): EmptyDelegateBinding {
        return EmptyDelegateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    override fun onBindViewHolder(holder: ViewHolder<EmptyDelegateBinding>, item: EmptyItem) {
        // DO NOTHING
    }
}
