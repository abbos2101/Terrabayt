package abbos2101.mvvmdemo.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post_save")
data class PostSaveModelData(
    @PrimaryKey
    val id: Int = 0,
    val title: String = "",
    val excerpt: String = "",
    val content: String = "",
    val published_at: Int = 0,
    val updated_at: Int = 0,
    val post_id: String = "",
    val post_modified: String = "",
    val category_id: Int = 0,
    val category_name: String = "",
    val image: String = "",
    val url: String = "",
    val priority: String = "",
    val order: String = ""
)