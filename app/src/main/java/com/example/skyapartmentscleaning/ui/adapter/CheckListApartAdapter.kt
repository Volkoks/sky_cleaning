package com.example.skyapartmentscleaning.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.skyapartmentscleaning.R
import com.example.skyapartmentscleaning.data.*
import com.example.skyapartmentscleaning.data.entites.checklist.DataPointCheckList
import com.example.skyapartmentscleaning.databinding.ItemCheckListPlainBinding
import com.example.skyapartmentscleaning.databinding.ItemCheckListPlugBinding
import com.example.skyapartmentscleaning.databinding.ItemChekListHeadingBinding


class CheckListApartAdapter(
    private val data: MutableList<DataPointCheckList>,
    private val chipClick: IClickChipItemCheckList
) :
    RecyclerView.Adapter<BaseAdapterForCheckList>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseAdapterForCheckList {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VT_POINT -> {
                val itemCheckList = ItemCheckListPlainBinding.inflate(inflater, parent, false)
                ItemCheckListPlainViewHolder(itemCheckList)
            }
            VT_HEADING_POINT -> {
                val itemHeading = ItemChekListHeadingBinding.inflate(inflater, parent, false)
                ItemCheckheckListHeadingViewHolder(itemHeading)
            }
            else -> {
                val itemHeading = ItemCheckListPlugBinding.inflate(inflater, parent, false)
                ItemCheckListPlugViewHolder(itemHeading)
            }


        }
    }

    override fun onBindViewHolder(holder: BaseAdapterForCheckList, position: Int) {
        holder.bind(data[position])

    }

    override fun getItemCount() = data.size

    override fun getItemViewType(position: Int): Int {
        return when (data[position].viewType) {
            VT_POINT -> VT_POINT
            VT_HEADING_POINT -> VT_HEADING_POINT
            else -> VT_PLUG
        }
    }

    fun updateData(newData: MutableList<DataPointCheckList>) {
        data.addAll(newData)
    }

    inner class ItemCheckheckListHeadingViewHolder(private val itemHeading: ItemChekListHeadingBinding) :
        BaseAdapterForCheckList(itemHeading.root) {
        override fun bind(dataPoint: DataPointCheckList) {
            itemHeading.tvItemCheckListHeading.text = dataPoint.dataHeadingPoints?.textHeading
        }

    }

    inner class ItemCheckListPlainViewHolder(private val binding: ItemCheckListPlainBinding) :
        BaseAdapterForCheckList(binding.root) {

        override fun bind(dataPoint: DataPointCheckList) {

            when (dataPoint.dataPoint?.chipSelection) {
                1 -> binding.yesChipItemCheckList.isChecked = true
                2 -> binding.noChipItemCheckList.isChecked = true
                else -> {
                    binding.yesChipItemCheckList.isChecked = false
                    binding.noChipItemCheckList.isChecked = false
                }
            }
            binding.tvItemCheckListPlain.text = dataPoint.dataPoint?.textPoint
            binding.yesChipItemCheckList.text = dataPoint.dataPoint?.textChipYes
            binding.noChipItemCheckList.text = dataPoint.dataPoint?.textChipNo

            binding.cgItemCheckListPlain.setOnCheckedChangeListener { group, checkedId ->

                when (checkedId) {
                    R.id.yes_chip_item_check_list -> {
                        data[layoutPosition].dataPoint?.chipSelection = 1
                        chipClick.clickChip(dataPoint.dataPoint?.textPoint.toString(), DONE_DAW)
                    }
                    R.id.no_chip_item_check_list -> {
                        data[layoutPosition].dataPoint?.chipSelection = 2
                        chipClick.clickChip(
                            dataPoint.dataPoint?.textPoint.toString(),
                            NOT_DONE_CROSS
                        )
                    }
                }
            }
        }

    }

    inner class ItemCheckListPlugViewHolder(private val itemCheckList: ItemCheckListPlugBinding) :
        BaseAdapterForCheckList(itemCheckList.root) {
        override fun bind(dataPoint: DataPointCheckList) {
        }
    }
}
