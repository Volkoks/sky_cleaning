package com.example.skyapartmentscleaning.ui.allApart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.skyapartmentscleaning.application.MyApp
import com.example.skyapartmentscleaning.data.ViewState
import com.example.skyapartmentscleaning.data.repository.IReposirotyAparts
import com.example.skycleaning.data.repository.ApartsRepository
import ru.terrakok.cicerone.Router
import javax.inject.Inject


class AllApartmentsViewModel @Inject constructor(
    private val repo: IReposirotyAparts,
    val router: Router
) : ViewModel() {

    val allApartsTowerFederation: MutableLiveData<ViewState> = MutableLiveData()
    val allApartsTowerOKO: MutableLiveData<ViewState> = MutableLiveData()
    val allApartsTowerEmpery: MutableLiveData<ViewState> = MutableLiveData()
    val allApartsTowerGorodStolic: MutableLiveData<ViewState> = MutableLiveData()

    init {
        allApartsTowerFederation.value = ViewState.Succes(repo.getTowerFederation())
        allApartsTowerOKO.value = ViewState.Succes(repo.getTowerOKO())
        allApartsTowerEmpery.value = ViewState.Succes(repo.getTowerImpery())
        allApartsTowerGorodStolic.value = ViewState.Succes(repo.getToweGorodStolic())
    }

}
