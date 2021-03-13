package com.example.skyapartmentscleaning.ui.adapter.apart

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.skyapartmentscleaning.data.room.entites.Apart
import com.example.skyapartmentscleaning.databinding.ItemApartCardBinding

/**
 * @author Alexander Volkov (Volkoks)
 */
class ApartsListAdapter(
    val onItemClick: (
        (Apart) -> Unit)? = null,
    val onItemLongClick:
    ((ItemApartCardBinding,Apart) -> Unit)? = null,
    val onClickBtnDel: ((Apart) -> Unit)? = null
) :
    RecyclerView.Adapter<ApartsListAdapter.ViewHolder>() {

    var listAparts: MutableList<Apart> = mutableListOf()
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
            when (apart.longSpecifiedStatus) {
                false -> binding.itemApartCardDeleteLayout.visibility = ViewGroup.GONE
                true -> binding.itemApartCardDeleteLayout.visibility = ViewGroup.VISIBLE
            }
            binding.numberApartCardView.text = apart.numberApart.toString()
            if (apart.checkDate != null && apart.checkTime != null) {
                binding.checkDate.layoutParams = visibleDataAndTime()
                binding.checkTime.layoutParams = visibleDataAndTime()
                binding.checkDate.text = apart.checkDate
                binding.checkTime.text = apart.checkTime
            }

            binding.apartCard.setOnClickListener {
                onItemClick?.invoke(apart)
            }
            binding.apartCard.setOnLongClickListener {
                onItemLongClick?.invoke(binding,apart)
                true
            }
            binding.itemApartFabDeleteCard.setOnClickListener {
                listAparts.removeAt(layoutPosition)
                notifyDataSetChanged()
                onClickBtnDel?.invoke(apart)
            }
        }

        private fun visibleDataAndTime() = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

}