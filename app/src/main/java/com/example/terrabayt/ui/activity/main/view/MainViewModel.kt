package com.example.terrabayt.ui.activity.main.view

import androidx.lifecycle.MutableLiveData
import com.example.terrabayt.ui.activity.common.PostModelMain
import com.example.terrabayt.ui.activity.main.adapter.menu.model.CategoryChildModelMain
import com.example.terrabayt.ui.activity.main.adapter.menu.model.CategoryModelMain
import com.example.terrabayt.ui.base.BaseViewModel

class MainViewModel : BaseViewModel() {
    private val _listMain: MutableLiveData<ArrayList<PostModelMain>> = MutableLiveData()
    private val _listCategory: MutableLiveData<ArrayList<CategoryModelMain>> = MutableLiveData()
    private val _progressVisibility: MutableLiveData<Boolean> = MutableLiveData()
    private val _categoryModel: MutableLiveData<CategoryModelMain> = MutableLiveData()
    private val _categoryChildModel: MutableLiveData<CategoryChildModelMain> = MutableLiveData()
    private val _postModel: MutableLiveData<PostModelMain> = MutableLiveData()
    private val _error: MutableLiveData<String> = MutableLiveData()


    fun setListMainValue(value: ArrayList<PostModelMain>) {
        _listMain.value = value
    }

    fun getListMain() = _listMain

    fun setListCategoryValue(value: ArrayList<CategoryModelMain>) {
        _listCategory.value = value
    }

    fun getListCategory() = _listCategory

    fun setProgressVisibilityValue(value: Boolean) {
        _progressVisibility.value = value
    }

    fun getProgressVisibility() = _progressVisibility

    fun setCategoryModelValue(value: CategoryModelMain) {
        _categoryModel.value = value
    }

    fun getCategoryModel() = _categoryModel

    fun setCategoryChildModelValue(value: CategoryChildModelMain) {
        _categoryChildModel.value = value
    }

    fun getCategoryChildModel() = _categoryChildModel

    fun setPostModelValue(value: PostModelMain) {
        _postModel.value = value
    }

    fun getPostModel() = _postModel

    fun setErrorValue(value: String) {
        _error.value = value
    }

    fun getError() = _error
}