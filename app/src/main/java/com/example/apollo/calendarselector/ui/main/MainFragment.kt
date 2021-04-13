package com.example.apollo.calendarselector.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.RecyclerView
import com.arrival.adp.adapterdelegates.ListDifferDelegateAdapter
import com.arrival.adp.adapterdelegates.delegatesManager
import com.example.apollo.calendarselector.R
import com.example.apollo.calendarselector.ui.main.delegates.DateCalendarDelegate
import com.example.apollo.calendarselector.ui.main.delegates.EmptyCalendarDelegate
import com.example.apollo.calendarselector.ui.main.delegates.MonthCalendarDelegate
import com.example.apollo.calendarselector.ui.main.items.MonthItem


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ListDifferDelegateAdapter
    private lateinit var manager: GridLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false).also {
            recyclerView = it.findViewById(R.id.calendar_recycler)
            manager = GridLayoutManager(it.context, 7)
            manager.spanSizeLookup = object : SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (adapter.getItem(position)) {
                        is MonthItem -> 7
                        else -> 1
                    }
                }

            }
            recyclerView.addItemDecoration(RowSpacingDecoration(resources.getDimensionPixelSize(R.dimen.spacing)))
            recyclerView.layoutManager = manager
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        adapter = ListDifferDelegateAdapter(
            delegatesManager {
                addDelegate(EmptyCalendarDelegate())
                addDelegate(DateCalendarDelegate(viewModel::selectDate))
                addDelegate(MonthCalendarDelegate())
            }
        )
        recyclerView.adapter = adapter
        viewModel.state.observe(this, { items -> adapter.setItems(items) })
    }


}
