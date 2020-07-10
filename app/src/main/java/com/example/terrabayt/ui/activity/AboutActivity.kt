package com.example.terrabayt.ui.activity

import android.view.LayoutInflater
import android.view.MenuItem
import com.example.terrabayt.databinding.ActivityAboutBinding
import com.example.terrabayt.ui.base.BaseActivity

class AboutActivity : BaseActivity<ActivityAboutBinding>() {
    override fun setViewBinding(inflater: LayoutInflater) = ActivityAboutBinding.inflate(inflater)

    override fun create() {
        supportActionBar?.title = "Биз ҳақимизда"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }
}