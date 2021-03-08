package com.example.skyapartmentscleaning.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skyapartmentscleaning.R
import com.example.skyapartmentscleaning.data.ViewState
import com.example.skyapartmentscleaning.databinding.MainFragmentBinding
import com.example.skyapartmentscleaning.navigator.Screens
import com.example.skyapartmentscleaning.ui.adapter.ApartsListAdapter

/**
 * @author Alexander Volkov (Volkoks)
 */
class MainFragment : Fragment(R.layout.main_fragment) {

    companion object {
        fun newInstance() = MainFragment()
    }

    lateinit var listAdapter: ApartsListAdapter
    private val viewModel: MainViewModel by viewModels()
    private var binding: MainFragmentBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        binding = MainFragmentBinding.bind(view)

        binding?.floatingActionButton?.setOnClickListener {
            viewModel.router.navigateTo(Screens.AllApartmentsScreen())
        }

        listAdapter = ApartsListAdapter {
            viewModel.router.navigateTo(Screens.CheckHistoryScreen(it))
        }
        val itemDecoration = initVerticalDecoration()
        intitRV(itemDecoration)
        viewModel.verifiedApartments.observe(viewLifecycleOwner, {
            when (it) {
                is ViewState.Loading -> showLoading(it.progress)
                is ViewState.Succes -> {
                    stopProgressLoading()
                    listAdapter.listAparts = it.listApart
                }
                is ViewState.Error -> showError(it.e)
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main_fragment, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_setting -> {
                viewModel.router.navigateTo(Screens.SettingScreen())
            }
        }
        return super.onOptionsItemSelected(item)
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
        binding?.apartHistoryListRv?.setHasFixedSize(true)
        binding?.apartHistoryListRv?.layoutManager = GridLayoutManager(context, 3)
        binding?.apartHistoryListRv?.addItemDecoration(decor)
        binding?.apartHistoryListRv?.adapter = listAdapter
    }

    /**
     * Инициализация вертикального разделителя
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    private fun initVerticalDecoration(): DividerItemDecoration {
        val itemDecoration = DividerItemDecoration(activity, RecyclerView.VERTICAL)
        itemDecoration.setDrawable(
            resources.getDrawable(
                R.drawable.separator_vertical,
                activity?.theme
            )
        )
        return itemDecoration
    }

    private fun showLoading(progress: Int?) {
        when (progress) {
            1 -> startProgressLoading()
        }
    }

    private fun startProgressLoading() {
        binding?.progressMainFragment?.visibility = ViewGroup.VISIBLE
    }

    private fun stopProgressLoading() {
        binding?.progressMainFragment?.visibility = ViewGroup.GONE
    }

    private fun showError(e: Throwable) {
        Toast.makeText(activity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
    }
}