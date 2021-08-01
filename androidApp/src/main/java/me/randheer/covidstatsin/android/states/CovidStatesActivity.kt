package me.randheer.covidstatsin.android.states

import android.os.Bundle
import androidx.activity.viewModels
import me.randheer.covidstatsin.android.BaseActivity
import me.randheer.covidstatsin.android.databinding.ActivityCovidStatesBinding

class CovidStatesActivity : BaseActivity<ActivityCovidStatesBinding>() {

    private val viewModel by viewModels<CovidStatesViewModel>()

    override fun bindView(): ActivityCovidStatesBinding {
        return ActivityCovidStatesBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadData()
    }

    override fun setListener() {
        viewModel.items.observe(this, {
            binding.tv.text = it.toString()
        })
    }
}
