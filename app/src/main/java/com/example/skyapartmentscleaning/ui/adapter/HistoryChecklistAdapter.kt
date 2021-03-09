package com.example.skyapartmentscleaning.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.skyapartmentscleaning.R
import com.example.skyapartmentscleaning.data.hystory_checklist.HistoryChecklistPoint
import com.example.skyapartmentscleaning.data.room.entites.Apart
import com.example.skyapartmentscleaning.databinding.ItemHistoryCheckListPointBinding

class HistoryChecklistAdapter : RecyclerView.Adapter<HistoryChecklistAdapter.ViewHolder>() {

    var listData: List<HistoryChecklistPoint> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemHistoryCheckListPointBinding =
            ItemHistoryCheckListPointBinding.inflate(inflater, parent, false)
        return ViewHolder(itemHistoryCheckListPointBinding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount() = listData.size

    inner class ViewHolder(val binding: ItemHistoryCheckListPointBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: HistoryChecklistPoint) {
            binding.tvItemHistoryClName.text = data.namePoint
            binding.tvItemHistoryClDescription.text = data.descriptionPoint
        }
    }
}
