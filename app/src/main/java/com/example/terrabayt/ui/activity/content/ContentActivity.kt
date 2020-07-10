package com.example.terrabayt.ui.activity.content

import abbos2101.mvvmdemo.common.fromJsonObject
import abbos2101.mvvmdemo.common.lazyFast
import abbos2101.mvvmdemo.common.setSrc
import abbos2101.mvvmdemo.common.showSnackbar
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import com.example.terrabayt.App
import com.example.terrabayt.R
import com.example.terrabayt.databinding.ActivityContentBinding
import com.example.terrabayt.module.database.model.WrapperData
import com.example.terrabayt.ui.activity.common.PostModelMain
import com.example.terrabayt.ui.base.BaseActivity

class ContentActivity : BaseActivity<ActivityContentBinding>() {
    override fun setViewBinding(inflater: LayoutInflater) = ActivityContentBinding.inflate(inflater)

    override fun create() {
        init()
    }

    private val model by lazyFast {
        intent.getStringExtra("data")!!.fromJsonObject(PostModelMain::class.java)
    }
    private val postSaveDao by lazyFast { App.databaseProvider.postsaveDao() }

    private fun init() {
        try {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = model.category_name

            if (binding != null) {
                val content = model.content
                    .replace("\\r", "")
                    .replace("\\n", "")
                    .replace("data-src", "width=\"100%\" src")
                    .replace("{tap_to_load}", "")
                binding!!.imgTitle.setSrc(model.image)
                binding!!.tvTitle.text = model.title
                binding!!.tvDetail.text = model.post_modified
                binding!!.wbContent.webViewClient = WebViewClient()
                binding!!.wbContent.settings.javaScriptEnabled = true
                binding!!.wbContent.settings.allowContentAccess = true
                binding!!.wbContent.loadData(content, "text/html; charset=UTF-8", null)
            }
        } catch (e: Exception) {
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding!!.wbContent.destroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_content, menu)
        if (postSaveDao.getListById(model.id).size != 0)
            menu?.getItem(0)?.setIcon(R.drawable.ic_star)
        else
            menu?.getItem(0)?.setIcon(R.drawable.ic_star_off)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            finish()
        if (item.itemId == R.id.item_star) {
            if (postSaveDao.getListById(model.id).isEmpty()) {
                postSaveDao.insert(WrapperData.postMainToSave(model))
                item.setIcon(R.drawable.ic_star)
                showSnackbar("Saqlandi")
            } else {
                postSaveDao.delete(WrapperData.postMainToSave(model))
                item.setIcon(R.drawable.ic_star_off)
                showSnackbar("O'chirildi")
            }
        }
        return true
    }
}