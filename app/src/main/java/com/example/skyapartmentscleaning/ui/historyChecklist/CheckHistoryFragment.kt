package com.example.skyapartmentscleaning.ui.historyChecklist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.skyapartmentscleaning.R
import com.example.skyapartmentscleaning.data.APART
import com.example.skyapartmentscleaning.data.hystory_checklist.HistoryChecklistPoint
import com.example.skyapartmentscleaning.data.repository.HistoryChecklistRepository
import com.example.skyapartmentscleaning.data.room.entites.Apart
import com.example.skyapartmentscleaning.databinding.HistoryCheckListFragmentBinding
import com.example.skyapartmentscleaning.ui.adapter.HistoryChecklistAdapter

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

    private var apart: Apart? = null
    private var adapter: HistoryChecklistAdapter? = null
    private val viewModel: CheckHistoryViewModel by lazy {
        CheckHistoryViewModel(HistoryChecklistRepository())
    }
    private var binding: HistoryCheckListFragmentBinding? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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

}