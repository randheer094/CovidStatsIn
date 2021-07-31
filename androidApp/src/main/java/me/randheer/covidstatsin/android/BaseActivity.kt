package me.randheer.covidstatsin.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import dev.icerock.moko.mvvm.viewmodel.ViewModel

abstract class BaseActivity<VB : ViewBinding, VM : ViewModel> : AppCompatActivity() {

    protected lateinit var viewModel: VM
    protected lateinit var binding: VB

    protected abstract val viewModelClass: Class<VM>

    protected abstract fun viewModelFactory(): ViewModelProvider.Factory
    protected abstract fun bindView(): VB
    protected abstract fun onViewModelCreated()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindView()
        setContentView(binding.root)
        val provider = ViewModelProvider(this, viewModelFactory())
        viewModel = provider.get(viewModelClass)
        onViewModelCreated()
    }
}