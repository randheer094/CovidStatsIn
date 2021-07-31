package me.randheer.covidstatsin.android

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import dev.icerock.moko.mvvm.MvvmActivity
import dev.icerock.moko.mvvm.createViewModelFactory
import me.randheer.covidstatsin.android.databinding.ActivityCovidStatesBinding
import me.randheer.covidstatsin.viewmodel.CovidStateViewModel

class CovidStatesActivity : MvvmActivity<ActivityCovidStatesBinding, CovidStateViewModel>() {

    override val layoutId: Int
        get() = R.layout.activity_covid_states
    override val viewModelClass: Class<CovidStateViewModel>
        get() = CovidStateViewModel::class.java
    override val viewModelVariableId: Int
        get() = BR.states

    override fun viewModelFactory(): ViewModelProvider.Factory {
        return createViewModelFactory { CovidStateViewModel() }
    }

    @SuppressLint("MissingSuperCall")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadData()
    }

}
