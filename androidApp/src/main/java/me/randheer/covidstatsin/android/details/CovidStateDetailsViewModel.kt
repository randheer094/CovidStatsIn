package me.randheer.covidstatsin.android.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.randheer.covidstatsin.data.repo.getCovidRepository
import me.randheer.covidstatsin.db.CovidDistrictStats

class CovidStateDetailsViewModel : ViewModel() {
    private val repository = getCovidRepository()

    private val _loading: MutableLiveData<Boolean> = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _items: MutableLiveData<List<CovidDistrictStats>> = MutableLiveData()
    val items: LiveData<List<CovidDistrictStats>> = _items

    private var job: Job? = null


    fun loadData(code: String) {
        _loading.postValue(true)
        viewModelScope.launch {
            val stateStats = repository.getDistricts(code)
            _items.postValue(stateStats)
            _loading.postValue(false)
        }
    }

    fun searchData(code: String, query: String) {
        job?.cancel()
        job = viewModelScope.launch {
            delay(500L)
            val stateStats = repository.searchDistrict(code, query)
            _items.postValue(stateStats)
        }
    }
}