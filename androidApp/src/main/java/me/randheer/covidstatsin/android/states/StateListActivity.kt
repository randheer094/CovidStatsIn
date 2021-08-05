package me.randheer.covidstatsin.android.states

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import me.randheer.covidstatsin.android.BaseActivity
import me.randheer.covidstatsin.android.databinding.ActivityStateListBinding
import me.randheer.covidstatsin.android.details.DistrictListActivity
import me.randheer.covidstatsin.domain.model.StateListMetaData

class StateListActivity : BaseActivity<ActivityStateListBinding>() {

    private val viewModel by viewModels<StateListViewModel>()
    private val adapter by lazy {
        StateInfoAdapter {
            DistrictListActivity.start(this, it)
        }
    }

    override fun bindView(): ActivityStateListBinding {
        return ActivityStateListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpUI(viewModel.getMetaData())
        viewModel.getStates()
    }

    private fun setUpUI(metaData: StateListMetaData) {
        binding.toolbar.title = metaData.title
        binding.searchBox.hint = metaData.searchPlaceholder
        binding.recyclerView.adapter = adapter
    }

    override fun setListener() {
        binding.searchBox.addTextChangedListener {
            viewModel.getStates(it.toString())
        }
        viewModel.loading.observe(this, {
            binding.loader.isVisible = it
        })
        viewModel.items.observe(this, {
            adapter.setItems(it.orEmpty())
        })
    }
}
