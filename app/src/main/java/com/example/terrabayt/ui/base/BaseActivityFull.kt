package com.example.terrabayt.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.cancel

abstract class BaseActivityFull<VB : ViewBinding, P : BasePresenter, VM : BaseViewModel> :
    AppCompatActivity() {
    internal var binding: VB? = null
    internal var presenter: P? = null
    internal var viewModel: VM? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setViewBinding(layoutInflater)
        viewModel = setViewModel()
        presenter = setPresenter()
        setContentView(binding?.root)
        create()
    }

    override fun onDestroy() {
        presenter?.cancel()
        presenter = null
        binding = null
        super.onDestroy()
    }

    abstract fun setViewBinding(inflater: LayoutInflater): VB
    abstract fun setPresenter(): P
    abstract fun setViewModel(): VM
    abstract fun create()
}