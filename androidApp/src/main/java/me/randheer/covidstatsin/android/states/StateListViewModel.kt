package me.randheer.covidstatsin.android.states

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import me.randheer.covidstatsin.di.stateMetaDataUseCase
import me.randheer.covidstatsin.di.stateUseCase
import me.randheer.covidstatsin.domain.model.StateUiModel
import me.randheer.covidstatsin.domain.usecases.GetStateListUseCase

class StateListViewModel : ViewModel() {

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> = _loading

    private val _items: MutableLiveData<List<StateUiModel>> = MutableLiveData()
    val items: LiveData<List<StateUiModel>> = _items

    private var job: Job? = null

    init {
        getStates()
    }

    fun getMetaData() = stateMetaDataUseCase.run(Unit)

    fun getStates(searchText: String = "") {
        job?.cancel()
        _loading.postValue(searchText.isEmpty())
        job = viewModelScope.launch {
            val stateStats = stateUseCase.run(GetStateListUseCase.Param(searchText))
            _items.postValue(stateStats)
            _loading.postValue(false)
        }
    }
}