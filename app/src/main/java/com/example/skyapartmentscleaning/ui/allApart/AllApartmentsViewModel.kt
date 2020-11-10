package com.example.skyapartmentscleaning.ui.allApart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.skyapartmentscleaning.MyApp
import com.example.skyapartmentscleaning.data.ViewState
import com.example.skycleaning.data.repository.ApartsRepository
import ru.terrakok.cicerone.Router


class AllApartmentsViewModel : ViewModel() {
    val router: Router = MyApp.instance.getRouter

    val allApartsTowerFederation: MutableLiveData<ViewState> = MutableLiveData()
    val allApartsTowerOKO: MutableLiveData<ViewState> = MutableLiveData()
    val allApartsTowerEmpery: MutableLiveData<ViewState> = MutableLiveData()
    val allApartsTowerGorodStolic: MutableLiveData<ViewState> = MutableLiveData()

    init {
        allApartsTowerFederation.value = ViewState(ApartsRepository.listApartsTowerFederationLevel49)
        allApartsTowerOKO.value = ViewState(ApartsRepository.listApartsTowerOKO)
        allApartsTowerEmpery.value = ViewState(ApartsRepository.listApartTowerImpery)
        allApartsTowerGorodStolic.value = ViewState(ApartsRepository.listApartTowerGorodStolic)
    }
}
