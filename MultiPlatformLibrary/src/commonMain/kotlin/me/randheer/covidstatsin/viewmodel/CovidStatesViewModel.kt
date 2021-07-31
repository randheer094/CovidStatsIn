package me.randheer.covidstatsin.viewmodel

import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch
import me.randheer.covidstatsin.data.repo.getCovidRepository
import me.randheer.covidstatsin.db.CovidStateStats

private typealias CovidStatesType = List<CovidStateStats>

class CovidStateViewModel() : ViewModel() {

    private val repository = getCovidRepository()

    private val _data: MutableLiveData<CovidStatesType> = MutableLiveData(emptyList())
    val data: LiveData<CovidStatesType> = _data

    private val _count: MutableLiveData<String> = MutableLiveData("0")
    val count: LiveData<String> = _count

    fun loadData() {
        viewModelScope.launch {
            val stateStats = repository.getCovidData()
            _data.postValue(stateStats)
            _count.postValue(stateStats.size.toString())
        }
    }
}