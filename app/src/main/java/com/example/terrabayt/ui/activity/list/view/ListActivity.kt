package com.example.terrabayt.ui.activity.list.view

import abbos2101.mvvmdemo.common.getViewModel
import abbos2101.mvvmdemo.common.lazyFast
import abbos2101.mvvmdemo.common.setVisible
import abbos2101.mvvmdemo.common.toJsonString
import android.content.Intent
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.lifecycle.Observer
import com.example.terrabayt.databinding.ActivityListBinding
import com.example.terrabayt.ui.activity.content.ContentActivity
import com.example.terrabayt.ui.activity.list.adapter.ListAdapter
import com.example.terrabayt.ui.activity.list.presenter.ListPresenter
import com.example.terrabayt.ui.base.BaseActivityFull

class ListActivity : BaseActivityFull<ActivityListBinding, ListPresenter, ListViewModel>() {
    override fun create() {
        init()
        initViewModel()
        setEvent()
    }

    private val adapter by lazyFast { ListAdapter(presenter!!, categoryId) }
    private val categoryId by lazyFast { intent.getIntExtra("category_id", 0) }
    private val categoryName by lazyFast { intent.getStringExtra("category_name") }
    private val type by lazyFast { intent.getStringExtra("ListActivity") }

    private fun init() {
        binding?.rvList?.adapter = adapter
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = categoryName
    }

    private fun initViewModel() {
        if (viewModel != null && binding != null) {
            viewModel!!.getProgressVisibility().observe(this, Observer {
                binding!!.pb.setVisible(it)
            })
            viewModel!!.getListMain().observe(this, Observer {
                adapter.addList(it)
            })
            viewModel!!.getPostModel().observe(this, Observer {
                val intent = Intent(this, ContentActivity::class.java)
                intent.putExtra("data", it.toJsonString())
                startActivity(intent)
            })
        }
    }

    private fun setEvent() {
        if (type == "list") presenter?.loadPosts(category = categoryId)
        else presenter?.loadPostSave()
    }

    override fun onResume() {
        super.onResume()
        if (type != "list") {
            adapter.clear()
            presenter?.loadPostSave()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }

    override fun setViewBinding(inflater: LayoutInflater) = ActivityListBinding.inflate(inflater)
    override fun setPresenter() = ListPresenter(viewModel!!)
    override fun setViewModel() = getViewModel(ListViewModel::class.java)
}