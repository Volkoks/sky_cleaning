package com.example.skyapartmentscleaning.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.skyapartmentscleaning.data.entites.checklist.DataCheckList

abstract class BaseAdapterForCheckList(itemView:View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(data: DataCheckList)

}