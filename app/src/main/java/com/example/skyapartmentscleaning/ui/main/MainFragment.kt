package com.example.skyapartmentscleaning.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skyapartmentscleaning.R
import com.example.skyapartmentscleaning.application.MyApp
import com.example.skyapartmentscleaning.data.ViewState
import com.example.skyapartmentscleaning.data.room.entites.Apart
import com.example.skyapartmentscleaning.databinding.ItemApartCardBinding
import com.example.skyapartmentscleaning.databinding.MainFragmentBinding
import com.example.skyapartmentscleaning.navigator.Screens
import com.example.skyapartmentscleaning.ui.activity.MainActivity
import com.example.skyapartmentscleaning.ui.adapter.apart.ApartsListAdapter
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

/**
 * @author Alexander Volkov (Volkoks)
 */
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }
    lateinit var listAdapter: ApartsListAdapter
    private var binding: MainFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.main_fragment, container, false)
        val toolbar = rootView.findViewById<Toolbar>(R.id.include_toolbar)
        toolbar.inflateMenu(R.menu.menu_main_fragment)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_setting -> {
                    viewModel.router.navigateTo(Screens.SettingScreen())
                    false
                }
                else -> false
            }
        }
        return rootView
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadData()
        listAdapter.notifyDataSetChanged()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        MyApp.instance.appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        binding = MainFragmentBinding.bind(view)



        binding?.floatingActionButton?.setOnClickListener {
            viewModel.router.navigateTo(Screens.AllApartmentsScreen())
        }

        listAdapter = ApartsListAdapter(
            /**
             * обработка единичного нажатия на карточку апартамента
             */
            {
                viewModel.router.navigateTo(Screens.CheckHistoryScreen(it))
            },
            /**
             * обработка долго нажатия на карточку апартамента
             */
            { bindingItem, apart ->
                visibleInvisibleBtnDeleteApartCard(bindingItem, apart)
            },
            /**
             * обработка нажатия на кнопку удаления карточки
             */
            {
                viewModel.deleteApart(it)
                showSnackBarDelete(view)
            })

        intitRV(initVerticalDecoration())
        viewModel.sunscribeLivedata().observe(viewLifecycleOwner, {
            when (it) {
                is ViewState.Loading -> showLoading(it.progress)
                is ViewState.Succes -> {
                    stopProgressLoading()
                    listAdapter.listAparts = it.listApart as MutableList<Apart>
                }
                is ViewState.Error -> showError(it.e)
            }
        })

    }

    private fun visibleInvisibleBtnDeleteApartCard(
        bindingItem: ItemApartCardBinding,
        apart: Apart
    ) {
        if (bindingItem.itemApartCardDeleteLayout.visibility == ViewGroup.VISIBLE) {
            apart.longSpecifiedStatus = false
            bindingItem.itemApartCardDeleteLayout.visibility = ViewGroup.GONE
        } else {
            apart.longSpecifiedStatus = true
            bindingItem.itemApartCardDeleteLayout.visibility = ViewGroup.VISIBLE
        }
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

    private fun showSnackBarDelete(view: View){
        Snackbar.make(view,getString(R.string.undo_delete),Snackbar.LENGTH_LONG)
            .setAction(getString(R.string.undo)) {
                viewModel.cancelJobCoroutines()
                viewModel.loadData()
            }.show()
    }

}