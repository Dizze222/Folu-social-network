package ch.b.retrofitandcoroutines.data.all_posts.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.all_posts.PhotographerData
import ch.b.retrofitandcoroutines.data.all_posts.mappers.ToRoomMapper

@Entity(tableName = "photographer_table")
data class CachePhotographer(
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
) : Abstract.Object<PhotographerData,ToRoomMapper>{
    override fun map(mapper: ToRoomMapper): PhotographerData = mapper.map(id, author, URL, like, theme, comments, authorOfComments)
}