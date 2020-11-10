package com.example.skyapartmentscleaning.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skyapartmentscleaning.R
import com.example.skyapartmentscleaning.navigator.Screens
import com.example.skyapartmentscleaning.ui.allApart.AllApartmentsFragment
import com.example.skyapartmentscleaning.ui.adapter.ApartsListAdapter
import kotlinx.android.synthetic.main.main_fragment.*

/**
 * @author Alexander Volkov (Volkoks)
 */
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    lateinit var listAdapter: ApartsListAdapter
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = View.inflate(context, R.layout.main_fragment, null)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        floatingActionButton.setOnClickListener {
            viewModel.router.navigateTo(Screens.AllApartmentsScreen())
        }

        listAdapter = ApartsListAdapter {}
        val itemDecoration = initVerticalDecoration()
        intitRV(itemDecoration)
        viewModel.verifiedApartments.observe(viewLifecycleOwner, {
            it?.let {
                listAdapter?.listAparts = it.listApart
            }
        })

    }

    override fun onResume() {
        super.onResume()
        viewModel.loadData()
        listAdapter.notifyDataSetChanged()
    }

    /**
     * Инициализация RecyclerView
     */
    private fun intitRV(decor: DividerItemDecoration) {
        apart_history_list_rv.setHasFixedSize(true)
        apart_history_list_rv.layoutManager = GridLayoutManager(context, 3)
        apart_history_list_rv.addItemDecoration(decor)
        apart_history_list_rv.adapter = listAdapter
    }

    /**
     * Инициализация вертикального разделителя
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    private fun initVerticalDecoration(): DividerItemDecoration {
        val itemDecoration = DividerItemDecoration(activity, RecyclerView.VERTICAL)
        itemDecoration.setDrawable(
            resources?.getDrawable(
                R.drawable.separator_vertical,
                activity?.theme
            )
        )
        return itemDecoration
    }

}