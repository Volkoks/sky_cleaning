package com.example.skyapartmentscleaning.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.skyapartmentscleaning.R
import com.example.skyapartmentscleaning.data.room.entites.Apart
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_apart_card, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listAparts[position])
    }

    override fun getItemCount() = listAparts.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(apart: Apart) = with(itemView) {
            number_apart_cardView.text = apart.numberApart.toString()
            if (apart.checkDate != null) {
                check_date.layoutParams =
                    LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                check_date.text = apart.checkDate
            }

            itemView.setOnClickListener {
                onItemClick?.invoke(apart)
            }
        }
    }
}