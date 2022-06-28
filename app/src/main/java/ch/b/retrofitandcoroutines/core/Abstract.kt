package ch.b.retrofitandcoroutines.core


abstract class Abstract {

    interface Object<T, M : Mapper> {
        fun map(mapper: M): T
    }

    interface DataObject

    interface CloudObject

    interface ToPhotographerMapper<T> : Mapper {
        fun map(
            id: Int,
            author: String,
            URL: String,
            like: Long,
            theme: String,
            comments: List<String>,
            authorOfComments: List<String>
        ): T


    }

    interface ToFavouriteMapper<T> : Mapper {
        fun map(
            id: List<Int>
        ): T
    }

    interface ToRegisterMapper<T> : Mapper {
        fun map(
            accessToken: String,
            refreshToken: String,
            successRegister: Boolean
        ): T
    }


    interface Mapper {

        interface Data<S, R> : Mapper {
            fun map(data: S): R
        }

        interface DataToDomain<S, R> : Data<S, R> {
            fun map(error: String): R

            fun map(): R

        }

        interface DomainToUi<S, T> : Data<S, T> {
            fun map(error: String): T

            fun map(): T
        }

        class Empty : Mapper
    }
}