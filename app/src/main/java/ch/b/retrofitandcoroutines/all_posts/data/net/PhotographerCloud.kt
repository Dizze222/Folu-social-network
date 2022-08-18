package ch.b.retrofitandcoroutines.all_posts.data.net

import ch.b.retrofitandcoroutines.utils.core.Abstract

import com.google.gson.annotations.SerializedName


interface PhotographerCloud {
    fun <T> map(mapper: Abstract.ToPhotographerMapper<T>): T

    /*data*/class Base(
        @SerializedName("idPhotographer")
        private val id: Int,
        private val author: String,
        @SerializedName("url")
        private val URL: String,
        private val like: Long,
        private val theme: String,
        private val comments: List<String>,
        private val authorOfComments: List<String>,
        private val favourite: Boolean
    ) : PhotographerCloud {
        override fun <T> map(mapper: Abstract.ToPhotographerMapper<T>): T =
             mapper.map(id, author, URL, like, theme, comments, authorOfComments, favourite)
    }


}

class Story(
    val id: Int? = null,
    val profileName: String? = null,
    val profileImageUrl: String? = null,
    val hasUncheckedStory: Boolean = false,
    val storyType: Int = Stories.DEFAULT
)

class Stories {
    companion object {
        const val MY_STORY = 0
        const val DEFAULT = 1
    }
}