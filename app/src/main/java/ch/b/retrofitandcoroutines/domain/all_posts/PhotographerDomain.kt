package ch.b.retrofitandcoroutines.domain.all_posts


interface PhotographerDomain {
    fun <T> map(mapper: PhotographerDomainToUIMapper<T>) : T

    class Base(
        private val id: Int,
        private val author: String,
        private val URL: String,
        private val like: Long,
        private val theme: String,
        private val comments: List<String>,
        private val authorOfComments: List<String>
    ) : PhotographerDomain{
        override fun <T> map(mapper: PhotographerDomainToUIMapper<T>) : T{
            return mapper.map(id, author, URL, like, theme, comments, authorOfComments)
        }

    }

}