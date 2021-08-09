package me.randheer.covidstatsin.android.details

import androidx.lifecycle.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import me.randheer.covidstatsin.di.districtMetaDataUseCase
import me.randheer.covidstatsin.di.districtUseCase
import me.randheer.covidstatsin.domain.model.DistrictUiModel
import me.randheer.covidstatsin.domain.usecases.DistrictListMetaDataUseCase
import me.randheer.covidstatsin.domain.usecases.GetDistrictListUseCase

class DistrictListViewModel(
    private val stateCode: String
) : ViewModel() {
    private val _loading: MutableLiveData<Boolean> = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _items: MutableLiveData<List<DistrictUiModel>> = MutableLiveData()
    val items: LiveData<List<DistrictUiModel>> = _items

    private var job: Job? = null

    init {
        getDistricts()
    }

    fun getMetaData() = districtMetaDataUseCase.run(
        DistrictListMetaDataUseCase.Param(stateCode)
    )

    fun getDistricts(query: String = "") {
        job?.cancel()
        _loading.postValue(query.isEmpty())
        job = viewModelScope.launch {
            val districtStats = districtUseCase.run(
                GetDistrictListUseCase.Param(stateCode, query)
            )
            _items.postValue(districtStats)
            _loading.postValue(false)
        }
    }
}

class DistrictListViewModelFactory(private val stateCode: String) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        DistrictListViewModel(stateCode = stateCode) as T
}