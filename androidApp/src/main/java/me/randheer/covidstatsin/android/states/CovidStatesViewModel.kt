package me.randheer.covidstatsin.android.states

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.randheer.covidstatsin.data.repo.getCovidRepository
import me.randheer.covidstatsin.db.CovidStateStats

class CovidStatesViewModel : ViewModel() {
    private val repository = getCovidRepository()

    private val _items: MutableLiveData<List<CovidStateStats>> = MutableLiveData()
    val items: LiveData<List<CovidStateStats>> = _items

    fun loadData() {
        viewModelScope.launch {
            val stateStats = repository.getCovidData()
            _items.postValue(stateStats)
        }
    }

}