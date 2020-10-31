package com.example.skyapartmentscleaning.ui.allCheckHistory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.skyapartmentscleaning.R
import com.example.skyapartmentscleaning.data.entites.apart.Apart


class AllCheckHistoryAdapter : RecyclerView.Adapter<AllCheckHistoryAdapter.ViewHolder>() {

    var listAllVerifyApart: List<Apart> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.check_history_fragment, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount()= listAllVerifyApart.size


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}