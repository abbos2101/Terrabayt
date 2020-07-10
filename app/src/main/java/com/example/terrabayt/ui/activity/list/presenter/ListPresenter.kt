package com.example.terrabayt.ui.activity.list.presenter

import abbos2101.mvvmdemo.common.lazyFast
import com.example.terrabayt.App
import com.example.terrabayt.module.net.model.PostModelNet
import com.example.terrabayt.ui.activity.common.PostModelMain
import com.example.terrabayt.ui.activity.list.presenter.ListPresenterUtil.Companion.getPostList
import com.example.terrabayt.ui.activity.list.presenter.ListPresenterUtil.Companion.getPostSaveList
import com.example.terrabayt.ui.activity.list.view.ListViewModel
import com.example.terrabayt.ui.base.BasePresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class ListPresenter(private val viewModel: ListViewModel) : BasePresenter() {
    private val net by lazyFast { App.netProvider }

    fun loadPosts(first_update: Int = 0, last_update: Int = 0, category: Int = 0, limit: Int = 30) {
        launch {
            withContext(Dispatchers.Main) { viewModel.setProgressVisibilityValue(true) }

            var response: Response<ArrayList<PostModelNet>>? = null
            try {
                response = net.getPosts(first_update, last_update, category, limit)
            } catch (e: Exception) {
            }
            val list = getPostList(response, category)

            withContext(Dispatchers.Main) {
                viewModel.setProgressVisibilityValue(false)
                viewModel.setListMainValue(list)
            }
        }
    }

    fun loadPostSave() {
        viewModel.setListMainValue(getPostSaveList())
    }

    fun postItemClick(model: PostModelMain) {
        viewModel.setPostModelValue(model)
    }
}