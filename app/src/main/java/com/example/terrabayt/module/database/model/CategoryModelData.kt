package abbos2101.mvvmdemo.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class CategoryModelData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "",
    val slug: String = "",
    val child: String = ""
)