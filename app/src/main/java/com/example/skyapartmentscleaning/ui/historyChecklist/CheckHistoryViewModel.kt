package com.example.skyapartmentscleaning.ui.historyChecklist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.skyapartmentscleaning.data.hystory_checklist.HistoryChecklistPoint
import com.example.skyapartmentscleaning.data.repository.IRepository
import com.example.skyapartmentscleaning.data.repository.IRepositoryHistory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


/**
 * @author Alexander Volkov (Volkoks)
 */
class CheckHistoryViewModel @Inject constructor(
    val repo: IRepositoryHistory<List<HistoryChecklistPoint>>
) : ViewModel(), CoroutineScope {

    private val livedataHistoryChecklist: MutableLiveData<List<HistoryChecklistPoint>> =
        MutableLiveData()

    override val coroutineContext: CoroutineContext by lazy {
        Dispatchers.IO
    }

    fun subscribe(): LiveData<List<HistoryChecklistPoint>> {
        return livedataHistoryChecklist
    }

    fun loadData(apartId: String) {
        launch {
            livedataHistoryChecklist.postValue(repo.getData(apartId))
        }
    }

}