package ch.b.retrofitandcoroutines.data.all_posts.cache

import androidx.room.*

@Dao
interface PhotographerDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(cacheWord: List<CachePhotographer.Base>)

    @Query("SELECT * FROM photographer_table ORDER BY id ASC")
    suspend fun readAllData(): List<CachePhotographer.Base>

    @Query("DELETE  FROM photographer_table")
    suspend fun delete()

    @Query("SELECT * FROM photographer_table WHERE author LIKE :searchQuery ")
    suspend fun searchDatabase(searchQuery: String): List<CachePhotographer.Base>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavouritesPost(post: List<CachePhotographer.Base>)

    @Query("SELECT * FROM photographer_table ORDER BY id ASC")
    suspend fun readFavouritePost(): List<CachePhotographer.Base>
}