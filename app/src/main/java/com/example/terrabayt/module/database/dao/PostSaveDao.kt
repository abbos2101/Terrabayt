package abbos.uzeu.database

import abbos2101.mvvmdemo.database.model.PostSaveModelData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PostSaveDao {
    @Insert
    fun insert(model: PostSaveModelData): Long

    @Delete
    fun delete(model: PostSaveModelData)

    @Query("delete from post_save")
    fun deleteAll()

    @Query("select * from post_save order by id desc")
    fun getList(): List<PostSaveModelData>

    @Query("select * from post_save where id=:postId order by id desc")
    fun getListById(postId: Int): List<PostSaveModelData>
}