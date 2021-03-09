package com.example.skyapartmentscleaning.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.skyapartmentscleaning.R
import com.example.skyapartmentscleaning.data.room.entites.Apart
import com.example.skyapartmentscleaning.databinding.ItemApartCardBinding
import com.example.skyapartmentscleaning.databinding.ItemHistoryCheckListPointBinding
import kotlinx.android.synthetic.main.item_apart_card.view.*

/**
 * @author Alexander Volkov (Volkoks)
 */
class ApartsListAdapter(val onItemClick: ((Apart) -> Unit)? = null) :
    RecyclerView.Adapter<ApartsListAdapter.ViewHolder>() {

    var listAparts: List<Apart> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemApartCardBinding = ItemApartCardBinding.inflate(inflater, parent, false)
        return ViewHolder(itemApartCardBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listAparts[position])
    }

    override fun getItemCount() = listAparts.size

    inner class ViewHolder(val binding: ItemApartCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(apart: Apart) = with(itemView) {
            binding.numberApartCardView.text = apart.numberApart.toString()
            if (apart.checkDate != null && apart.checkTime != null) {
                binding.checkDate.layoutParams = visibleDataAndTime()
                binding.checkTime.layoutParams = visibleDataAndTime()
                binding.checkDate.text = apart.checkDate
                binding.checkTime.text = apart.checkTime
            }

            itemView.setOnClickListener {
                onItemClick?.invoke(apart)
            }
        }

        private fun visibleDataAndTime() = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }
}