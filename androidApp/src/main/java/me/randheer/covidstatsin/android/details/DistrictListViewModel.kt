package me.randheer.covidstatsin.android.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import me.randheer.covidstatsin.di.districtMetaDataUseCase
import me.randheer.covidstatsin.di.districtUseCase
import me.randheer.covidstatsin.domain.model.DistrictUiModel
import me.randheer.covidstatsin.domain.usecases.DistrictListMetaDataUseCase
import me.randheer.covidstatsin.domain.usecases.GetDistrictListUseCase

class DistrictListViewModel : ViewModel() {
    private val _loading: MutableLiveData<Boolean> = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _items: MutableLiveData<List<DistrictUiModel>> = MutableLiveData()
    val items: LiveData<List<DistrictUiModel>> = _items

    private var job: Job? = null

    fun getMetaData(stateCode: String) = districtMetaDataUseCase.run(
        DistrictListMetaDataUseCase.Param(stateCode)
    )

    fun getDistricts(stateCode: String, query: String = "") {
        job?.cancel()
        job = viewModelScope.launch {
            val stateStats = districtUseCase.run(
                GetDistrictListUseCase.Param(stateCode, query)
            )
            _items.postValue(stateStats)
        }
    }
}