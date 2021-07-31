package me.randheer.covidstatsin.android

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import dev.icerock.moko.mvvm.createViewModelFactory
import me.randheer.covidstatsin.android.databinding.ActivityCovidStatesBinding
import me.randheer.covidstatsin.db.CovidStateStats
import me.randheer.covidstatsin.viewmodel.CovidStateViewModel

class CovidStatesActivity : BaseActivity<ActivityCovidStatesBinding, CovidStateViewModel>() {

    override val viewModelClass: Class<CovidStateViewModel>
        get() = CovidStateViewModel::class.java

    override fun viewModelFactory(): ViewModelProvider.Factory {
        return createViewModelFactory { CovidStateViewModel() }
    }

    override fun bindView(): ActivityCovidStatesBinding {
        return ActivityCovidStatesBinding.inflate(layoutInflater)
    }

    override fun onViewModelCreated() {
        viewModel.data.ld().observe(this, {
            setupView(it)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadData()
    }

    private fun setupView(it: List<CovidStateStats>?) {
        binding.tv.text = it.toString()
    }
}
