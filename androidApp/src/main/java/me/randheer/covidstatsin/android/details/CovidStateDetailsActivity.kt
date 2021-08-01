package me.randheer.covidstatsin.android.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import me.randheer.covidstatsin.android.BaseActivity
import me.randheer.covidstatsin.android.R
import me.randheer.covidstatsin.android.databinding.ActivityCovidStateDetailsBinding


class CovidStateDetailsActivity : BaseActivity<ActivityCovidStateDetailsBinding>() {

    companion object {
        private const val EXTRA_CODE = "extra_code"
        fun start(ctx: Context, code: String) {
            ctx.startActivity(Intent(ctx, CovidStateDetailsActivity::class.java).apply {
                putExtra(EXTRA_CODE, code)
            })
        }
    }

    private val viewModel by viewModels<CovidStateDetailsViewModel>()
    private val adapter by lazy { CovidStateDetailsAdapter() }
    private val code by lazy { intent.getStringExtra(EXTRA_CODE)!! }

    override fun bindView(): ActivityCovidStateDetailsBinding {
        return ActivityCovidStateDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpUI()
        viewModel.loadData(code)
    }

    private fun setUpUI() {
        binding.toolbar.title = "District List ($code)"
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
            viewModel.searchData(code, it.toString())
        }
    }
}
