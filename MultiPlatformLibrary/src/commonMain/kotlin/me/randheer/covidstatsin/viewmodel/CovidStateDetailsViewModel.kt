package me.randheer.covidstatsin.viewmodel

import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch
import me.randheer.covidstatsin.data.repo.getCovidRepository
import me.randheer.covidstatsin.db.CovidDistrictStats

private typealias CovidStateDetailsType = List<CovidDistrictStats>

class CovidStateDetailsViewModel() : ViewModel() {

    private val repository = getCovidRepository()

    private val _data: MutableLiveData<CovidStateDetailsType> = MutableLiveData(emptyList())
    val data: LiveData<CovidStateDetailsType> = _data

    fun loadData(stateCode: String) {
        viewModelScope.launch {
            val stateDetailsStats = repository.getCovidStateData(stateCode)
            _data.postValue(stateDetailsStats)
        }
    }
}