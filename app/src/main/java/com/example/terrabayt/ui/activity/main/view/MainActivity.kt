package com.example.terrabayt.ui.activity.main.view

import abbos2101.mvvmdemo.common.*
import android.content.Intent
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.lifecycle.Observer
import com.example.terrabayt.R
import com.example.terrabayt.databinding.ActivityMainBinding
import com.example.terrabayt.ui.activity.AboutActivity
import com.example.terrabayt.ui.activity.content.ContentActivity
import com.example.terrabayt.ui.activity.list.view.ListActivity
import com.example.terrabayt.ui.activity.main.adapter.main.MainAdapter
import com.example.terrabayt.ui.activity.main.adapter.menu.MainCategoryAdapter
import com.example.terrabayt.ui.activity.main.presenter.MainPresenter
import com.example.terrabayt.ui.base.BaseActivityFull

class MainActivity :
    BaseActivityFull<ActivityMainBinding, MainPresenter, MainViewModel>() {

    override fun create() {
        init()
        initViewModel()
        setEvent()
    }

    private var toggle: ActionBarDrawerToggle? = null
    private val adapterMain: MainAdapter by lazyFast { MainAdapter(presenter!!) }
    private val adapterMenu: MainCategoryAdapter by lazyFast { MainCategoryAdapter(presenter!!) }


    private fun init() {
        supportActionBar?.show()
        toggle = ActionBarDrawerToggle(
            this, binding?.dlRoot,
            R.string.Open,
            R.string.Close
        )
        binding?.dlRoot?.addDrawerListener(toggle!!)
        toggle!!.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding!!.rvList.adapter = adapterMain
        binding!!.rvMenu.adapter = adapterMenu
    }

    private fun initViewModel() {
        if (viewModel != null && binding != null) {
            viewModel!!.getProgressVisibility().observe(this, Observer {
                binding!!.pb.setVisible(it)
            })
            viewModel!!.getListCategory().observe(this, Observer {
                adapterMenu.addList(it)
            })
            viewModel!!.getListMain().observe(this, Observer {
                adapterMain.addList(it)
            })
            viewModel!!.getCategoryModel().observe(this, Observer {
                val intent = Intent(this, ListActivity::class.java)
                intent.putExtra("category_id", it.id)
                intent.putExtra("category_name", it.name)
                intent.putExtra("category_slug", it.slug)
                intent.putExtra("ListActivity", "list")
                startActivity(intent)
            })
            viewModel!!.getCategoryChildModel().observe(this, Observer {
                val intent = Intent(this, ListActivity::class.java)
                intent.putExtra("category_id", it.id)
                intent.putExtra("category_name", it.name)
                intent.putExtra("category_slug", it.slug)
                intent.putExtra("ListActivity", "list")
                startActivity(intent)
            })
            viewModel!!.getPostModel().observe(this, Observer {
                val intent = Intent(this, ContentActivity::class.java)
                intent.putExtra("data", it.toJsonString())
                startActivity(intent)
            })
            viewModel!!.getError().observe(this, Observer {
                showSnackbar(it)
            })
        }
    }

    private fun setEvent() {
        presenter?.loadCategories()
        presenter?.loadPosts()
        binding?.llShare?.setOnClickListener {
            presenter?.activityShareApplication()
        }
        binding?.llStar?.setOnClickListener {
            val intent = Intent(this, ListActivity::class.java)
            intent.putExtra("ListActivity", "star")
            intent.putExtra("category_name", "Танланганлар")
            startActivity(intent)
        }
        binding?.llInfo?.setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        if (toggle!!.onOptionsItemSelected(item)) true else super.onOptionsItemSelected(item)

    override fun setViewBinding(inflater: LayoutInflater) = ActivityMainBinding.inflate(inflater)
    override fun setViewModel() = getViewModel(MainViewModel::class.java)
    override fun setPresenter() =
        MainPresenter(viewModel!!)
}