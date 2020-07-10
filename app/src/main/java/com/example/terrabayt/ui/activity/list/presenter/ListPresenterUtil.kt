package com.example.terrabayt.ui.activity.list.presenter

import abbos2101.mvvmdemo.common.NetInfo
import abbos2101.mvvmdemo.common.getNetInfo
import abbos2101.mvvmdemo.database.model.PostModelData
import com.example.terrabayt.App
import com.example.terrabayt.module.net.model.PostModelNet
import com.example.terrabayt.module.util.Wrapper
import com.example.terrabayt.ui.activity.common.PostModelMain
import retrofit2.Response

class ListPresenterUtil {
    companion object {
        fun getPostSaveList(
        ): ArrayList<PostModelMain> {
            val list: ArrayList<PostModelMain> = arrayListOf()
            App.databaseProvider.postsaveDao().getList().forEach {
                val model = PostModelMain(
                    it.id,
                    it.title,
                    it.excerpt,
                    it.content,
                    it.published_at,
                    it.updated_at,
                    it.post_id,
                    it.post_modified,
                    it.category_id,
                    it.category_name,
                    it.image,
                    it.url,
                    it.priority,
                    it.order
                )
                list.add(model)
            }
            return list
        }

        fun getPostList(
            response: Response<ArrayList<PostModelNet>>?,
            category: Int
        ): ArrayList<PostModelMain> {
            var list: ArrayList<PostModelMain> = arrayListOf()
            if (response?.getNetInfo() == NetInfo.SUCCESS) {
                list = Wrapper.ArrayListPostModelNetToMain(response.body()!!)
                postCacheSafeClear()
                postInsertDB(list)
            } else list = postListDB(list, category)
            return list
        }

        private fun postCacheSafeClear() {
            if (App.databaseProvider.postDao().getList().size > 1000) {
                App.databaseProvider.postDao().deleteAll()
                App.context!!.cacheDir.deleteRecursively()
            }
        }

        private fun postInsertDB(
            list: ArrayList<PostModelMain>
        ) {
            list.forEach {
                if (App.databaseProvider.postDao().getListById(it.id).size == 0) {
                    val model = PostModelData(
                        it.id,
                        it.title,
                        it.excerpt,
                        it.content,
                        it.published_at,
                        it.updated_at,
                        it.post_id,
                        it.post_modified,
                        it.category_id,
                        it.category_name,
                        it.image,
                        it.url,
                        it.priority,
                        it.order
                    )
                    App.databaseProvider.postDao().insert(model)
                }
            }
        }

        private fun postListDB(
            list: ArrayList<PostModelMain>,
            category: Int
        ): ArrayList<PostModelMain> {
            App.databaseProvider.postDao().getListByCategoryId(category).forEach {
                val model = PostModelMain(
                    it.id,
                    it.title,
                    it.excerpt,
                    it.content,
                    it.published_at,
                    it.updated_at,
                    it.post_id,
                    it.post_modified,
                    it.category_id,
                    it.category_name,
                    it.image,
                    it.url,
                    it.priority,
                    it.order
                )
                list.add(model)
            }
            return list
        }
    }
}