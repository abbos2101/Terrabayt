package com.example.terrabayt.ui.activity.main.presenter

import abbos2101.mvvmdemo.common.NetInfo
import abbos2101.mvvmdemo.common.getNetInfo
import abbos2101.mvvmdemo.common.lazyFast
import com.example.terrabayt.App
import com.example.terrabayt.module.net.model.CategoryModelNet
import com.example.terrabayt.module.net.model.PostModelNet
import com.example.terrabayt.ui.activity.common.PostModelMain
import com.example.terrabayt.ui.activity.main.adapter.menu.model.CategoryChildModelMain
import com.example.terrabayt.ui.activity.main.adapter.menu.model.CategoryModelMain
import com.example.terrabayt.ui.activity.main.presenter.MainPresenterUtil.Companion.getCategoryList
import com.example.terrabayt.ui.activity.main.presenter.MainPresenterUtil.Companion.getPostList
import com.example.terrabayt.ui.activity.main.view.MainViewModel
import com.example.terrabayt.ui.base.BasePresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MainPresenter(private val viewModel: MainViewModel) : BasePresenter() {
    private val net by lazyFast { App.netProvider }

    fun loadPosts(first_update: Int = 0, last_update: Int = 0, category: Int = 0, limit: Int = 30) {
        launch {
            withContext(Dispatchers.Main) { viewModel.setProgressVisibilityValue(true) }

            var response: Response<ArrayList<PostModelNet>>? = null
            try {
                response = net.getPosts(first_update, last_update, category, limit)
            } catch (e: Exception) {
            }
            val list = getPostList(App.context!!, response)

            withContext(Dispatchers.Main) {
                viewModel.setProgressVisibilityValue(false)
                viewModel.setListMainValue(list)
            }
        }
    }

    fun loadCategories() {
        launch {
            withContext(Dispatchers.Main) { viewModel.setProgressVisibilityValue(true) }

            var response: Response<ArrayList<CategoryModelNet>>? = null
            try {
                response = net.getCategories()
            } catch (e: Exception) {
            }
            val list = getCategoryList(response)

            withContext(Dispatchers.Main) {
                viewModel.setProgressVisibilityValue(false)
                viewModel.setListCategoryValue(list)
                if (response?.getNetInfo() != NetInfo.SUCCESS)
                    viewModel.setErrorValue("Internet bilan muammo sodir bo'ldi:(")
            }
        }
    }

    fun categoryItemClick(model: CategoryModelMain) {
        viewModel.setCategoryModelValue(model)
    }

    fun categoryChildItemClick(model: CategoryChildModelMain) {
        viewModel.setCategoryChildModelValue(model)
    }

    fun postItemClick(model: PostModelMain) {
        viewModel.setPostModelValue(model)
    }

    fun activityShareApplication() {
        MainPresenterUtil.shareApplication(App.context!!)
    }
}