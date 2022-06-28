package ch.b.retrofitandcoroutines.data.all_posts.cache


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ch.b.retrofitandcoroutines.core.Abstract


interface CachePhotographer {
    fun <T> map(mapper: Abstract.ToPhotographerMapper<T>): T

    @Entity(tableName = "photographer_table")
    data class Base(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        @ColumnInfo(name = "author")
        val author: String,
        @ColumnInfo(name = "URL")
        val URL: String,
        @ColumnInfo(name = "like")
        val like: Long,
        @ColumnInfo(name = "theme")
        val theme: String,
        @ColumnInfo(name = "comments")
        val comments: List<String>,
        @ColumnInfo(name = "authorOfComments")
        val authorOfComments: List<String>
    ) : CachePhotographer {

        override fun <T> map(mapper: Abstract.ToPhotographerMapper<T>): T {
            return mapper.map(id, author, URL, like, theme, comments, authorOfComments)
        }
    }

    @Entity(tableName = "photographer_table_favourite")
    data class Favourite(
        @PrimaryKey
        val id: List<Int>,
    ) {
        open fun <T> map(mapper: Abstract.ToFavouriteMapper<T>): T {
            return mapper.map(id)
        }


    }

}
