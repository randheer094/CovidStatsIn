package me.randheer.covidstatsin.android.states

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
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

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = _error

    private var job: Job? = null

    private val exHandler = CoroutineExceptionHandler { _, exception ->
        _error.postValue(exception.localizedMessage)
        _loading.postValue(false)
    }

    init {
        getStates()
    }

    fun getMetaData() = stateMetaDataUseCase.run(Unit)

    fun getStates(searchText: String = "") {
        job?.cancel()
        _loading.postValue(searchText.isEmpty())
        _error.postValue("")
        job = viewModelScope.launch(exHandler) {
            val stateStats = stateUseCase.run(GetStateListUseCase.Param(searchText))
            _items.postValue(stateStats)
            _loading.postValue(false)
        }
    }
}