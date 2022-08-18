package ch.b.retrofitandcoroutines.utils.core


abstract class Abstract {

    interface Object<T, M : Mapper> {
        fun map(mapper: M): T
    }

    interface DataObject


    interface ToPhotographerMapper<T> : Mapper {
        fun map(
            id: Int,
            author: String,
            URL: String,
            like: Long,
            theme: String,
            comments: List<String>,
            authorOfComments: List<String>,
            favourite: Boolean
        ): T


    }

    interface ToProfileMapper<T> : Mapper {
        fun map(
            name: String,
            secondName: String,
            bio: String,
            image: String
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