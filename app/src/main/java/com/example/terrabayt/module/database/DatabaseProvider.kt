package abbos.uzeu.database

import android.content.Context
import androidx.room.Room
import com.example.module.data.database.DatabaseService

class DatabaseProvider {
    companion object {
        private var instance: DatabaseService? = null

        fun instance(
            context: Context, baseName: String = "base.db", fromAssets: Boolean = false
        ): DatabaseService {
            if (instance == null) {
                val builder =
                    Room.databaseBuilder(context, DatabaseService::class.java, "$baseName")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                if (fromAssets)
                    builder.createFromAsset("$baseName")
                instance = builder.build()
            }
            return instance!!
        }

        fun clear() {
            instance = null
        }
    }
}