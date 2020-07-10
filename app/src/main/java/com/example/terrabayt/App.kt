package com.example.terrabayt

import abbos.uzeu.database.DatabaseProvider
import abbos2101.mvvmdemo.common.lazyFast
import android.app.Application
import android.content.Context
import com.example.module.common.NetProvider

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    companion object {
        var context: Context? = null
        val netProvider by lazyFast { NetProvider.instance() }
        val databaseProvider by lazyFast {
            DatabaseProvider.instance(context!!, "base.db", true)
        }
    }
}