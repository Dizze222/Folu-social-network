package ch.b.retrofitandcoroutines.data.all_posts.cache

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [CachePhotographer.Base::class], version = 2, exportSchema = true)
@TypeConverters(Convertors::class)
abstract class PhotographerDataBase : RoomDatabase() {

    abstract fun photographerDao(): PhotographerDao

    companion object {
        private var instance: PhotographerDataBase? = null

        @Synchronized
        fun database(context: Context): PhotographerDataBase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    PhotographerDataBase::class.java,
                    DATABASE_NAME
                ).fallbackToDestructiveMigration().build()
            }
            return instance!!
        }

        private const val DATABASE_NAME = "photographer_db"
    }
}