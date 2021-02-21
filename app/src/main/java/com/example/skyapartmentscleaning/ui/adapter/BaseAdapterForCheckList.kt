package com.example.skyapartmentscleaning.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.skyapartmentscleaning.data.entites.checklist.DataPointCheckList

abstract class BaseAdapterForCheckList(itemView:View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(dataPoint: DataPointCheckList)

}