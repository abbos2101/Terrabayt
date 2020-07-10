package com.example.terrabayt.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {
    internal var binding: VB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setViewBinding(layoutInflater)
        setContentView(binding?.root)
        create()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    abstract fun setViewBinding(inflater: LayoutInflater): VB
    abstract fun create()
}