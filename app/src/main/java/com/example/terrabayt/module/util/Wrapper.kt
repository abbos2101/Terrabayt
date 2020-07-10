package com.example.terrabayt.module.util

import com.example.terrabayt.module.net.model.CategoryModelNet
import com.example.terrabayt.module.net.model.PostModelNet
import com.example.terrabayt.ui.activity.common.PostModelMain
import com.example.terrabayt.ui.activity.main.adapter.menu.model.CategoryChildModelMain
import com.example.terrabayt.ui.activity.main.adapter.menu.model.CategoryModelMain

class Wrapper {
    companion object {
        fun ArrayListCategoryModelNetToMain(list: ArrayList<CategoryModelNet>): ArrayList<CategoryModelMain> {
            val newList: ArrayList<CategoryModelMain> = arrayListOf()
            list.forEach {
                val child: ArrayList<CategoryChildModelMain> = arrayListOf()
                it.child.forEach {
                    child.add(CategoryChildModelMain(id = it.id, name = it.name, slug = it.slug))
                }
                val model =
                    CategoryModelMain(
                        id = it.id, name = it.name, slug = it.slug, child = child
                    )
                newList.add(model)
            }
            return newList
        }

//        fun ArrayListCategoryModelDataToMain(list: ArrayList<CategoryModelData>): ArrayList<CategoryModelMain> {
//            val newList: ArrayList<CategoryModelMain> = arrayListOf()
//            list.forEach {
//                val child = it.child as List<CategoryChildModelMain>
//                val model = CategoryModelMain(
//                    id = it.id, name = it.name, slug = it.slug, child = child
//                )
//                newList.add(model)
//            }
//            return newList
//        }

        fun ArrayListPostModelNetToMain(list: ArrayList<PostModelNet>): ArrayList<PostModelMain> {
            val newList: ArrayList<PostModelMain> = arrayListOf()
            list.forEach {
                val modelMain =
                    PostModelMain(
                        id = it.id,
                        title = it.title,
                        excerpt = it.excerpt,
                        content = it.content,
                        published_at = it.published_at,
                        updated_at = it.updated_at,
                        post_id = it.post_id,
                        post_modified = it.post_modified,
                        category_id = it.category_id,
                        category_name = it.category_name,
                        image = it.image,
                        url = it.url,
                        priority = it.priority,
                        order = it.order
                    )
                newList.add(modelMain)
            }
            return newList
        }
    }
}