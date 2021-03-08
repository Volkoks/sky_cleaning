package com.example.skyapartmentscleaning.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.skyapartmentscleaning.data.checklist.DataPointCheckList

abstract class BaseHolderForCheckList(itemView:View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(dataPoint: DataPointCheckList)

}