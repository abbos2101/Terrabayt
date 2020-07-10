package com.example.terrabayt.ui.activity.list.view

import androidx.lifecycle.MutableLiveData
import com.example.terrabayt.ui.activity.common.PostModelMain
import com.example.terrabayt.ui.base.BaseViewModel

class ListViewModel : BaseViewModel() {
    private val _listMain: MutableLiveData<ArrayList<PostModelMain>> = MutableLiveData()
    private val _progressVisibility: MutableLiveData<Boolean> = MutableLiveData()
    private val _postModel: MutableLiveData<PostModelMain> = MutableLiveData()


    fun setListMainValue(value: ArrayList<PostModelMain>) {
        _listMain.value = value
    }

    fun getListMain() = _listMain

    fun setProgressVisibilityValue(value: Boolean) {
        _progressVisibility.value = value
    }

    fun getProgressVisibility() = _progressVisibility

    fun setPostModelValue(value: PostModelMain) {
        _postModel.value = value
    }

    fun getPostModel() = _postModel
}