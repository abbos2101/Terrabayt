package abbos.uzeu.database

import abbos2101.mvvmdemo.database.model.CategoryModelData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CategoryDao {
    @Insert
    fun insert(model: CategoryModelData): Long

    @Delete
    fun delete(model: CategoryModelData)

    @Query("delete from category")
    fun deleteAll()

    @Query("select * from category")
    fun getList(): List<CategoryModelData>
}