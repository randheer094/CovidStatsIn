package me.randheer.covidstatsin.android.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import me.randheer.covidstatsin.android.BaseActivity
import me.randheer.covidstatsin.android.R
import me.randheer.covidstatsin.android.databinding.ActivityDistrictLilstBinding
import me.randheer.covidstatsin.domain.model.DistrictListMetaData


class DistrictListActivity : BaseActivity<ActivityDistrictLilstBinding>() {

    companion object {
        private const val EXTRA_CODE = "extra_code"
        fun start(ctx: Context, code: String) {
            ctx.startActivity(Intent(ctx, DistrictListActivity::class.java).apply {
                putExtra(EXTRA_CODE, code)
            })
        }
    }

    private val viewModel by viewModels<DistrictListViewModel>()
    private val adapter by lazy { DistrictListAdapter() }
    private val code by lazy { intent.getStringExtra(EXTRA_CODE)!! }

    override fun bindView(): ActivityDistrictLilstBinding {
        return ActivityDistrictLilstBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpUI(viewModel.getMetaData(code))
        viewModel.getDistricts(code)
    }

    private fun setUpUI(metaData: DistrictListMetaData) {
        binding.toolbar.title = metaData.title
        binding.searchBox.hint = metaData.searchPlaceholder
        binding.toolbar.setNavigationIcon(R.drawable.ic_back)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
        binding.recyclerView.adapter = adapter
    }

    override fun setListener() {
        viewModel.loading.observe(this, {
            binding.loader.isVisible = it
        })
        viewModel.items.observe(this, {
            adapter.setItems(it.orEmpty())
        })
        binding.searchBox.addTextChangedListener {
            viewModel.getDistricts(code, it.toString())
        }
    }
}
