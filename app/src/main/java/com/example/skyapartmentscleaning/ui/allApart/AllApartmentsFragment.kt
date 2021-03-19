package com.example.skyapartmentscleaning.ui.allApart

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skyapartmentscleaning.R
import com.example.skyapartmentscleaning.application.MyApp
import com.example.skyapartmentscleaning.data.*
import com.example.skyapartmentscleaning.data.room.entites.Apart
import com.example.skyapartmentscleaning.databinding.AllApartmentsFragmentBinding
import com.example.skyapartmentscleaning.navigator.Screens
import com.example.skyapartmentscleaning.ui.adapter.apart.ApartsListAdapter
import javax.inject.Inject

/**
 * @author Alexander Volkov (Volkoks)
 */
class AllApartmentsFragment : Fragment(R.layout.all_apartments_fragment) {

    companion object {
        fun newInstance() = AllApartmentsFragment()
    }


    lateinit var listAdapterForTowerFederation: ApartsListAdapter
    lateinit var listAdapterForTowerOKO: ApartsListAdapter
    lateinit var listAdapterForTowerEmpery: ApartsListAdapter
    lateinit var listAdapterForTowerGorodStolic: ApartsListAdapter


    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: AllApartmentsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(AllApartmentsViewModel::class.java)
    }
    private var binding: AllApartmentsFragmentBinding? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        MyApp.instance.appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)
        binding = AllApartmentsFragmentBinding.bind(view)
        val itemDecoration = initVerticalDecoration()

        initRecyclerView(itemDecoration)

        listAdapterForTowerFederation =
            ApartsListAdapter({viewModel.router.navigateTo(Screens.CheckListScreen(it))})
        listAdapterForTowerOKO =
            ApartsListAdapter ({ viewModel.router.navigateTo(Screens.CheckListScreen(it)) })
        listAdapterForTowerEmpery =
            ApartsListAdapter ({ viewModel.router.navigateTo(Screens.CheckListScreen(it)) })
        listAdapterForTowerGorodStolic =
            ApartsListAdapter ({ viewModel.router.navigateTo(Screens.CheckListScreen(it)) })

        initAdapters()

        viewModel.allApartsTowerFederation.observe(viewLifecycleOwner, {
            when (it) {
                is ViewState.Loading -> showLoading(it.progress, FEDERATION_TOWER)
                is ViewState.SuccesApart -> {
                    stopProgressLoading(FEDERATION_TOWER)
                    listAdapterForTowerFederation.listAparts = it.listApart as MutableList<Apart>
                }
                is ViewState.Error -> showError(it.e)
            }
        })
        viewModel.allApartsTowerOKO.observe(viewLifecycleOwner, {
            when (it) {
                is ViewState.Loading -> showLoading(it.progress, OKO_TOWER)
                is ViewState.SuccesApart -> {
                    stopProgressLoading(OKO_TOWER)
                    listAdapterForTowerOKO.listAparts = it.listApart as MutableList<Apart>
                }
                is ViewState.Error -> showError(it.e)
            }
        })
        viewModel.allApartsTowerEmpery.observe(viewLifecycleOwner, {
            when (it) {
                is ViewState.Loading -> showLoading(it.progress, EMPERY_TOWER)
                is ViewState.SuccesApart -> {
                    stopProgressLoading(EMPERY_TOWER)
                    listAdapterForTowerEmpery.listAparts = it.listApart as MutableList<Apart>
                }
                is ViewState.Error -> showError(it.e)
            }
        })
        viewModel.allApartsTowerGorodStolic.observe(viewLifecycleOwner,
            {
                when (it) {
                    is ViewState.Loading -> showLoading(it.progress, GOROD_STOLIC)
                    is ViewState.SuccesApart -> {
                        stopProgressLoading(GOROD_STOLIC)
                        listAdapterForTowerGorodStolic.listAparts = it.listApart as MutableList<Apart>
                    }
                    is ViewState.Error -> showError(it.e)
                }
            })
    }

    private fun initAdapters() {
        binding?.apartsTowerFederationRecyclerView?.adapter = listAdapterForTowerFederation
        binding?.apartsTowerOKORecyclerView?.adapter = listAdapterForTowerOKO
        binding?.apartsTowerEmperyRecyclerView?.adapter = listAdapterForTowerEmpery
        binding?.apartsTowerGorodStolicRecyclerView?.adapter = listAdapterForTowerGorodStolic
    }

    /**
     * Инициализация RecyclerView для списка аппартаментов
     */
    private fun initRecyclerView(itemDecoration: DividerItemDecoration) {
        binding?.apartsTowerFederationRecyclerView?.setHasFixedSize(true)
        binding?.apartsTowerOKORecyclerView?.setHasFixedSize(true)
        binding?.apartsTowerGorodStolicRecyclerView?.setHasFixedSize(true)
        binding?.apartsTowerEmperyRecyclerView?.setHasFixedSize(true)

        binding?.apartsTowerFederationRecyclerView?.layoutManager = GridLayoutManager(activity, 3)
        binding?.apartsTowerFederationRecyclerView?.addItemDecoration(itemDecoration)
        binding?.apartsTowerOKORecyclerView?.layoutManager = GridLayoutManager(activity, 3)
        binding?.apartsTowerOKORecyclerView?.addItemDecoration(itemDecoration)
        binding?.apartsTowerEmperyRecyclerView?.layoutManager = GridLayoutManager(activity, 3)
        binding?.apartsTowerEmperyRecyclerView?.addItemDecoration(itemDecoration)
        binding?.apartsTowerGorodStolicRecyclerView?.layoutManager = GridLayoutManager(activity, 3)
        binding?.apartsTowerGorodStolicRecyclerView?.addItemDecoration(itemDecoration)
    }

    /**
     * Инициализация декоратора
     */
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

    private fun showLoading(progress: Int?, tower: String) {
        when (progress) {
            1 -> startProgressLoading(tower)
        }
    }

    private fun startProgressLoading(tower: String) {
        when (tower) {
            FEDERATION_TOWER -> binding?.progressAllApartsFederation?.visibility = ViewGroup.VISIBLE
            OKO_TOWER -> binding?.progressAllApartsOko?.visibility = ViewGroup.VISIBLE
            EMPERY_TOWER -> binding?.progressAllApartsImpery?.visibility = ViewGroup.VISIBLE
            GOROD_STOLIC -> binding?.progressAllApartsGorodStolic?.visibility = ViewGroup.VISIBLE
        }

    }

    private fun stopProgressLoading(tower: String) {
        when (tower) {
            FEDERATION_TOWER -> binding?.progressAllApartsFederation?.visibility = ViewGroup.GONE
            OKO_TOWER -> binding?.progressAllApartsOko?.visibility = ViewGroup.GONE
            EMPERY_TOWER -> binding?.progressAllApartsImpery?.visibility = ViewGroup.GONE
            GOROD_STOLIC -> binding?.progressAllApartsGorodStolic?.visibility = ViewGroup.GONE
        }
    }

    private fun showError(e: Throwable) {
        Toast.makeText(activity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
    }
}

