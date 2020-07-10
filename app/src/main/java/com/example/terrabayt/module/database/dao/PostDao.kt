package abbos.uzeu.database

import abbos2101.mvvmdemo.database.model.PostModelData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PostDao {
    @Insert
    fun insert(model: PostModelData): Long

    @Delete
    fun delete(model: PostModelData)

    @Query("delete from post")
    fun deleteAll()

    @Query("select * from post order by id desc")
    fun getList(): List<PostModelData>

    @Query("select * from post where id=:postId order by id desc")
    fun getListById(postId: Int): List<PostModelData>

    @Query("select * from post where category_id=:categoryId order by id desc")
    fun getListByCategoryId(categoryId: Int): List<PostModelData>
}