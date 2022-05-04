package ch.b.retrofitandcoroutines.core




abstract class Abstract {

    interface Object<T, M : Mapper> {
        fun map(mapper: M): T
    }

    interface ToCachePhotographerMapper<T> : Mapper {
        fun map(
            id: Int,
            author: String,
            URL: String,
            like: Long,
            theme: String,
            comments: List<String>,
            authorOfComments: List<String>
        ) : T
    }

    interface Mapper {
        class Empty : Mapper
    }
}