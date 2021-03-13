package com.example.skyapartmentscleaning.ui.historyChecklist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.skyapartmentscleaning.data.ViewState
import com.example.skyapartmentscleaning.data.hystory_checklist.HistoryChecklistPoint
import com.example.skyapartmentscleaning.data.repository.IRepository
import com.example.skyapartmentscleaning.data.repository.IRepositoryHistory
import com.example.skyapartmentscleaning.ui.base.BaseViewModel
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
) : BaseViewModel<ViewState>() {

    fun subscribe(): LiveData<ViewState> {
        return liveDataToObserve
    }

    fun loadData(apartId: String) {
        viewModelCoroutineScope.launch {
            liveDataToObserve.postValue(ViewState.SuccesDataHistoryCheckList(repo.getData(apartId)))
        }
    }

    override fun errorReturned(t: Throwable) {
        liveDataToObserve.value = ViewState.Error(t)
    }

}