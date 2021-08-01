package me.randheer.covidstatsin.android.states

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import me.randheer.covidstatsin.android.BaseActivity
import me.randheer.covidstatsin.android.databinding.ActivityCovidStatesBinding
import me.randheer.covidstatsin.android.details.CovidStateDetailsActivity

class CovidStatesActivity : BaseActivity<ActivityCovidStatesBinding>() {

    private val viewModel by viewModels<CovidStatesViewModel>()
    private val adapter by lazy {
        CovidStateInfoAdapter {
            CovidStateDetailsActivity.start(this, it)
        }
    }

    override fun bindView(): ActivityCovidStatesBinding {
        return ActivityCovidStatesBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpUI()
        viewModel.loadData()
    }

    private fun setUpUI() {
        binding.toolbar.title = "State/UT List (India)"
        binding.recyclerView.adapter = adapter
    }

    override fun setListener() {
        binding.searchBox.addTextChangedListener {
            viewModel.searchData(it.toString())
        }
        viewModel.loading.observe(this, {
            binding.loader.isVisible = it
        })
        viewModel.items.observe(this, {
            adapter.setItems(it.orEmpty())
        })
    }
}
