package ch.b.retrofitandcoroutines.data.all_posts.cache

import androidx.room.*

@Dao
interface PhotographerDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(cacheWord: List<CachePhotographer>)

    @Query("SELECT * FROM photographer_table ORDER BY id ASC")
    suspend fun readAllData(): List<CachePhotographer>

    @Query("DELETE  FROM photographer_table")
    suspend fun delete()

    @Query("SELECT * FROM photographer_table WHERE author LIKE :searchQuery ")
    suspend fun searchDatabase(searchQuery: String): List<CachePhotographer>


}