package com.example.terrabayt.ui.activity.splash

import android.content.Intent
import android.view.LayoutInflater
import com.example.terrabayt.databinding.ActivitySplashBinding
import com.example.terrabayt.ui.activity.main.view.MainActivity
import com.example.terrabayt.ui.base.BaseActivity
import kotlinx.coroutines.*

class SplashActivity : BaseActivity<ActivitySplashBinding>() {
    override fun setViewBinding(inflater: LayoutInflater) = ActivitySplashBinding.inflate(inflater)

    override fun create() {
        supportActionBar?.hide()
        GlobalScope.launch(Dispatchers.IO) {
            delay(1000)
            withContext(Dispatchers.Main) {
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}