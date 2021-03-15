package com.example.skyapartmentscleaning.ui.historyChecklist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skyapartmentscleaning.R
import com.example.skyapartmentscleaning.application.MyApp
import com.example.skyapartmentscleaning.data.APART
import com.example.skyapartmentscleaning.data.hystory_checklist.HistoryChecklistPoint
import com.example.skyapartmentscleaning.data.room.entites.Apart
import com.example.skyapartmentscleaning.databinding.HistoryCheckListFragmentBinding
import com.example.skyapartmentscleaning.ui.adapter.history_checklist.HistoryChecklistAdapter
import javax.inject.Inject

/**
 * @author Alexander Volkov (Volkoks)
 */
class CheckHistoryFragment : Fragment(R.layout.history_check_list_fragment) {

    companion object {
        fun newInstance(apart: Apart) = CheckHistoryFragment().apply {
            arguments = Bundle().apply {
                putParcelable(APART, apart)
            }
        }
    }


    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: CheckHistoryViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(CheckHistoryViewModel::class.java)
    }
    private var apart: Apart? = null
    private var adapter: HistoryChecklistAdapter? = null
    private var binding: HistoryCheckListFragmentBinding? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        MyApp.instance.appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)

        apart = arguments?.getParcelable(APART)
        binding = HistoryCheckListFragmentBinding.bind(view)

        viewModel.subscribe().observe(viewLifecycleOwner, {
            renderData(it)
        })
        apart?.id?.let { viewModel.loadData(it) }
    }

    private fun renderData(listData: List<HistoryChecklistPoint>) {
        if (adapter == null) {
            adapter = HistoryChecklistAdapter()
            binding?.rvHistoryChecklistFragment?.setHasFixedSize(true)
            binding?.rvHistoryChecklistFragment?.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            binding?.rvHistoryChecklistFragment?.addItemDecoration(initDecorator())
            binding?.rvHistoryChecklistFragment?.adapter = adapter
            listData?.let {
                adapter?.listData = it
            }
        } else {
            listData?.let {
                adapter?.listData = it
            }
        }

    }
    private fun initDecorator():DividerItemDecoration{
        val decorator = DividerItemDecoration(activity,RecyclerView.VERTICAL)
        decorator.setDrawable(resources.getDrawable(R.drawable.item_decoration_,null))
        return decorator
    }
}