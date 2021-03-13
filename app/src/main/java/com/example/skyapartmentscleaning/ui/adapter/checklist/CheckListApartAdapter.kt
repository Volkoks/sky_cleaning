package com.example.skyapartmentscleaning.ui.adapter.checklist

import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.skyapartmentscleaning.R
import com.example.skyapartmentscleaning.data.*
import com.example.skyapartmentscleaning.data.checklist.DataPointCheckList
import com.example.skyapartmentscleaning.databinding.*
import com.example.skyapartmentscleaning.ui.adapter.checklist.IItemChekListListener


class CheckListApartAdapter(
    private val data: MutableList<DataPointCheckList>,
    private val itemListener: IItemChekListListener
) :
    RecyclerView.Adapter<BaseHolderForCheckList>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseHolderForCheckList {
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
            VT_POINT_ENTRY_FIELD -> {
                val itemEntryField = ItemCheckListEntryFieldBinding.inflate(inflater, parent, false)
                ItemCheckListEntryFieldHolder(itemEntryField)
            }
            VT_BTN -> {
                val itemBtn = ItemCheckListBtnBinding.inflate(inflater, parent, false)
                ItemCheckListBtnHolder(itemBtn)
            }
            else -> {
                val itemHeading = ItemCheckListPlugBinding.inflate(inflater, parent, false)
                ItemCheckListPlugViewHolder(itemHeading)
            }


        }
    }

    override fun onBindViewHolder(holder: BaseHolderForCheckList, position: Int) {
        holder.bind(data[position])

    }

    override fun getItemCount() = data.size

    override fun getItemViewType(position: Int): Int {
        return when (data[position].viewType) {
            VT_POINT -> VT_POINT
            VT_HEADING_POINT -> VT_HEADING_POINT
            VT_POINT_ENTRY_FIELD -> VT_POINT_ENTRY_FIELD
            VT_BTN -> VT_BTN
            else -> VT_PLUG
        }
    }

    inner class ItemCheckheckListHeadingViewHolder(private val itemHeading: ItemChekListHeadingBinding) :
        BaseHolderForCheckList(itemHeading.root) {
        override fun bind(dataPoint: DataPointCheckList) {
            itemHeading.tvItemCheckListHeading.text = dataPoint.dataHeadingPoint?.textHeading
        }

    }

    inner class ItemCheckListPlainViewHolder(private val binding: ItemCheckListPlainBinding) :
        BaseHolderForCheckList(binding.root) {

        override fun bind(dataPoint: DataPointCheckList) {

            when (dataPoint.dataCheckPoint?.chipSelection) {
                1 -> binding.yesChipItemCheckList.isChecked = true
                2 -> binding.noChipItemCheckList.isChecked = true
                else -> {
                    binding.yesChipItemCheckList.isChecked = false
                    binding.noChipItemCheckList.isChecked = false
                }
            }
            binding.tvItemCheckListPlain.text = dataPoint.dataCheckPoint?.textPoint
            binding.yesChipItemCheckList.text = dataPoint.dataCheckPoint?.textChipYes
            binding.noChipItemCheckList.text = dataPoint.dataCheckPoint?.textChipNo

            binding.cgItemCheckListPlain.setOnCheckedChangeListener { group, checkedId ->

                when (checkedId) {
                    R.id.yes_chip_item_check_list -> {
                        data[layoutPosition].dataCheckPoint?.chipSelection = 1
                        itemListener.clickChip(
                            dataPoint.dataCheckPoint?.textPoint.toString(),
                            DONE_DAW
                        )
                    }
                    R.id.no_chip_item_check_list -> {
                        data[layoutPosition].dataCheckPoint?.chipSelection = 2
                        itemListener.clickChip(
                            dataPoint.dataCheckPoint?.textPoint.toString(),
                            NOT_DONE_CROSS
                        )
                    }
                }
            }
        }

    }

    inner class ItemCheckListEntryFieldHolder(private val binding: ItemCheckListEntryFieldBinding) :
        BaseHolderForCheckList(binding.root) {

        override fun bind(dataPoint: DataPointCheckList) {
            if (dataPoint.dataEntryFieldPoint?.text != null) {
                binding.tietClItem.setText(dataPoint.dataEntryFieldPoint?.text)
            } else {
                binding.tietClItem.text = null
            }
            binding.tietClItem.hint = dataPoint.dataEntryFieldPoint?.hintEditText
            binding.tietClItem.setOnKeyListener { v, keyCode, event ->
                return@setOnKeyListener when (keyCode) {
                    KeyEvent.KEYCODE_BACK -> {
                        data[layoutPosition].dataEntryFieldPoint?.text =
                            binding.tietClItem.text.toString()
                        itemListener.sendTextEditText(
                            dataPoint.dataEntryFieldPoint?.hintEditText.toString(),
                            binding?.tietClItem.text.toString()
                        )
                        binding.tietClItem?.clearFocus()
                        true
                    }
                    else -> false
                }
            }
        }
    }

    inner class ItemCheckListBtnHolder(private val binding: ItemCheckListBtnBinding) :
        BaseHolderForCheckList(binding.root) {
        override fun bind(dataPoint: DataPointCheckList) {
            binding.btnClItem.text = dataPoint.dataBtnPoint?.textBtn
            binding.btnClItem.setOnClickListener { itemListener.sendReport() }
        }

    }

    inner class ItemCheckListPlugViewHolder(private val itemCheckList: ItemCheckListPlugBinding) :
        BaseHolderForCheckList(itemCheckList.root) {
        override fun bind(dataPoint: DataPointCheckList) {
        }
    }
}
