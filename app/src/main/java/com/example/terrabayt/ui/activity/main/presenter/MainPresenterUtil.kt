package com.example.terrabayt.ui.activity.main.presenter

import abbos2101.mvvmdemo.common.NetInfo
import abbos2101.mvvmdemo.common.getNetInfo
import abbos2101.mvvmdemo.database.model.CategoryModelData
import abbos2101.mvvmdemo.database.model.PostModelData
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.example.terrabayt.App
import com.example.terrabayt.module.database.model.WrapperData
import com.example.terrabayt.module.net.model.CategoryModelNet
import com.example.terrabayt.module.net.model.PostModelNet
import com.example.terrabayt.module.util.Wrapper
import com.example.terrabayt.ui.activity.common.PostModelMain
import com.example.terrabayt.ui.activity.main.adapter.menu.model.CategoryModelMain
import com.example.terrabayt.ui.activity.main.view.MainViewModel
import retrofit2.Response
import java.io.*

class MainPresenterUtil {
    companion object {
        fun getPostList(
            context: Context,
            response: Response<ArrayList<PostModelNet>>?
        ): ArrayList<PostModelMain> {
            var list: ArrayList<PostModelMain> = arrayListOf()
            if (response?.getNetInfo() == NetInfo.SUCCESS) {
                list = Wrapper.ArrayListPostModelNetToMain(response.body()!!)
                postCacheSafeClearDB(context)
                postInsertDB(list)
            } else
                list = postListDB(list)
            return list
        }

        fun getCategoryList(
            response: Response<ArrayList<CategoryModelNet>>?
        ): ArrayList<CategoryModelMain> {
            var list: ArrayList<CategoryModelMain> = arrayListOf()
            if (response?.getNetInfo() == NetInfo.SUCCESS) {
                list = Wrapper.ArrayListCategoryModelNetToMain(response.body()!!)
                categoryInsertDB(list)
            } else {
                categoryListDB(list)
            }
            return list
        }

        fun shareApplication(context: Context) {
            val app = context.applicationInfo
            val filePath = app.sourceDir
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "*/*"
            val originalApk = File(filePath)
            try {
                var tempFile = File(context.externalCacheDir.toString() + "/ExtractedApk")
                if (!tempFile.isDirectory()) if (!tempFile.mkdirs()) return
                tempFile = File(
                    tempFile.getPath().toString() + "/" + context.getString(app.labelRes)
                        .replace(" ", "")
                        .toLowerCase() + ".apk"
                )
                if (!tempFile.exists()) {
                    if (!tempFile.createNewFile()) {
                        return
                    }
                }
                val input: InputStream = FileInputStream(originalApk)
                val output: OutputStream = FileOutputStream(tempFile)
                val buf = ByteArray(1024)
                var len = 0
                while (input.read(buf).also({ len = it }) > 0) {
                    output.write(buf, 0, len)
                }
                input.close()
                output.close()
                println("File copied.")
                intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(tempFile))
                context.startActivity(Intent.createChooser(intent, "Иловани улашиш"))
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        private fun postInsertDB(list: ArrayList<PostModelMain>) {
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

        private fun postCacheSafeClearDB(context: Context) {
            if (App.databaseProvider.postDao().getList().size > 1000) {
                App.databaseProvider.postDao().deleteAll()
                context.cacheDir.deleteRecursively()
            }
        }

        private fun postListDB(list: ArrayList<PostModelMain>): ArrayList<PostModelMain> {
            App.databaseProvider.postDao().getList().forEach {
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

        private fun categoryInsertDB(list: ArrayList<CategoryModelMain>) {
            App.databaseProvider.categoryDao().deleteAll()
            list.forEach {
                val child = WrapperData.childListToString(it.child)
                val model = CategoryModelData(it.id, it.name, it.slug, child)
                App.databaseProvider.categoryDao().insert(model)
            }
        }

        private fun categoryListDB(list: ArrayList<CategoryModelMain>): ArrayList<CategoryModelMain> {
            val listData = App.databaseProvider.categoryDao().getList()
            listData.forEach {
                val child = WrapperData.childStringToList(it.child)
                list.add(CategoryModelMain(it.id, it.name, it.slug, child))
            }
            return list
        }
    }
}