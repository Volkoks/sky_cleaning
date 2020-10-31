package com.example.skycleaning.ui.allApart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.skycleaning.R
import com.example.skycleaning.data.entity.apart.Apart
import com.example.skycleaning.ui.allCheckHistory.AllCheckHistoryFragment
import kotlinx.android.synthetic.main.apart_card.view.*

class AllApartsAdapter(val onItemClick: ((Apart) -> Unit)? = null) :
    RecyclerView.Adapter<AllApartsAdapter.ViewHolder>() {

    var listAparts: List<Apart> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.apart_card, parent, false)
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
                    LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT)
                check_date.text = apart.checkDate
            }

            itemView.setOnClickListener {
                onItemClick?.invoke(apart)
            }
        }
    }
}