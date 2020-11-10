package com.example.skyapartmentscleaning.ui.allApart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skyapartmentscleaning.R
import com.example.skyapartmentscleaning.data.entites.apart.Apart
import com.example.skyapartmentscleaning.ui.adapter.ApartsListAdapter
import com.example.skyapartmentscleaning.ui.apart.ApartFragment
import kotlinx.android.synthetic.main.all_apartments_fragment.*

/**
 * @author Alexander Volkov (Volkoks)
 */
class AllApartmentsFragment : Fragment() {

    companion object {
        fun newInstance(): AllApartmentsFragment {
            val fragment = AllApartmentsFragment()
            val bundle = Bundle()
            bundle.putString("test", "test")
            fragment.arguments = bundle
            return fragment
        }
    }

    lateinit var listAdapterForTowerFederation: ApartsListAdapter
    lateinit var listAdapterForTowerOKO: ApartsListAdapter
    lateinit var listAdapterForTowerEmpery: ApartsListAdapter
    lateinit var listAdapterForTowerGorodStolic: ApartsListAdapter

    private val viewModel: AllApartmentsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.all_apartments_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemDecoration = initVerticalDecoration()

        initRecyclerView(itemDecoration)

        listAdapterForTowerFederation = ApartsListAdapter {initFragment(it)}
        listAdapterForTowerOKO = ApartsListAdapter{ initFragment(it)}
        listAdapterForTowerEmpery = ApartsListAdapter{initFragment(it)}
        listAdapterForTowerGorodStolic = ApartsListAdapter{initFragment(it)}

        aparts_tower_federation_recycler_view.adapter = listAdapterForTowerFederation
        aparts_tower_OKO_recycler_view.adapter = listAdapterForTowerOKO
        aparts_tower_Empery_recycler_view.adapter = listAdapterForTowerEmpery
        aparts_tower_Gorod_stolic_recycler_view.adapter = listAdapterForTowerGorodStolic

        viewModel.allApartsTowerFederation.observe(viewLifecycleOwner, {
            it?.let {
                listAdapterForTowerFederation.listAparts = it.listApart
            }
        })
        viewModel.allApartsTowerOKO.observe(viewLifecycleOwner,{
            listAdapterForTowerOKO.listAparts = it.listApart
        })
        viewModel.allApartsTowerEmpery.observe(viewLifecycleOwner,{
            listAdapterForTowerEmpery.listAparts = it.listApart
        })
        viewModel.allApartsTowerGorodStolic.observe(viewLifecycleOwner,{
            listAdapterForTowerGorodStolic.listAparts = it.listApart
        })
    }

    /**
     * Инициализация RecyclerView для списка аппартаментов
     */
    private fun initRecyclerView(itemDecoration: DividerItemDecoration) {
        aparts_tower_federation_recycler_view.setHasFixedSize(true)
        aparts_tower_OKO_recycler_view.setHasFixedSize(true)
        aparts_tower_Empery_recycler_view.setHasFixedSize(true)
        aparts_tower_Gorod_stolic_recycler_view.setHasFixedSize(true)

        aparts_tower_federation_recycler_view.layoutManager = GridLayoutManager(activity, 3)
        aparts_tower_federation_recycler_view.addItemDecoration(itemDecoration)
        aparts_tower_OKO_recycler_view.layoutManager = GridLayoutManager(activity, 3)
        aparts_tower_OKO_recycler_view.addItemDecoration(itemDecoration)
        aparts_tower_Empery_recycler_view.layoutManager = GridLayoutManager(activity, 3)
        aparts_tower_Empery_recycler_view.addItemDecoration(itemDecoration)
        aparts_tower_Gorod_stolic_recycler_view.layoutManager = GridLayoutManager(activity, 3)
        aparts_tower_Gorod_stolic_recycler_view.addItemDecoration(itemDecoration)
    }

    /**
     * Инициализация декоратора
     */
    private fun initVerticalDecoration(): DividerItemDecoration {
        val itemDecoration = DividerItemDecoration(activity, RecyclerView.VERTICAL)
        itemDecoration.setDrawable(resources?.getDrawable(R.drawable.separator_vertical, activity?.theme))
        return itemDecoration
    }

    /**
     * Создание фрагмента чек листа
     */
    private fun initFragment(it: Apart) {
        activity?.supportFragmentManager!!.beginTransaction()
            .replace(R.id.fragment_container, ApartFragment.newInstance(it))
            .addToBackStack("Apart")
            .commit()
    }
}