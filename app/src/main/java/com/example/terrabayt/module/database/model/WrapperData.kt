package com.example.terrabayt.module.database.model

import abbos2101.mvvmdemo.common.fromJsonObject
import abbos2101.mvvmdemo.common.toJsonString
import abbos2101.mvvmdemo.database.model.PostSaveModelData
import com.example.terrabayt.ui.activity.common.PostModelMain
import com.example.terrabayt.ui.activity.main.adapter.menu.model.CategoryChildModelMain

class WrapperData {
    companion object {
        fun childStringToList(child: String) = child.fromJsonObject(categoryArray::class.java).list

        fun childListToString(child: ArrayList<CategoryChildModelMain>) =
            categoryArray(child).toJsonString()

        fun postMainToSave(model: PostModelMain): PostSaveModelData = PostSaveModelData(
            id = model.id,
            title = model.title,
            excerpt = model.excerpt,
            content = model.content,
            published_at = model.published_at,
            updated_at = model.updated_at,
            post_id = model.post_id,
            post_modified = model.post_modified,
            category_id = model.category_id,
            category_name = model.category_name,
            image = model.image,
            url = model.url,
            priority = model.priority,
            order = model.order
        )
    }
}

data class categoryArray(
    val list: ArrayList<CategoryChildModelMain> = arrayListOf()
)