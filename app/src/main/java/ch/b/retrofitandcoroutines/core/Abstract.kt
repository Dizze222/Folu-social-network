package ch.b.retrofitandcoroutines.core

import ch.b.retrofitandcoroutines.R
import ch.b.retrofitandcoroutines.domain.all_posts.ErrorType
import ch.b.retrofitandcoroutines.presentation.all_posts.ResourceProvider
import retrofit2.HttpException
import java.net.UnknownHostException


abstract class Abstract {

    interface Object<T, M : Mapper> {
        fun map(mapper: M): T
    }

    interface ToPhotographerMapper<T> : Mapper {
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

        interface Data<S, R> : Mapper {
            fun map(data: S): R
        }

        interface DataToDomain<S, R> : Data<S, R> {
            fun map(e: Exception): R

            fun map() : R

            abstract class Base<S, R> : DataToDomain<S, R> {
                protected fun errorType(e: Exception) = when (e) {
                    is UnknownHostException -> ErrorType.GENERIC_ERROR
                    is HttpException -> ErrorType.SERVICE_UNAVAILABLE
                    else -> ErrorType.GENERIC_ERROR
                }
            }
        }

        interface DomainToUi<S, T> : Data<S, T> {
            fun map(errorType: ErrorType): T

            fun map() : T

            abstract class Base<S, T>(private val resourceProvider: ResourceProvider) :
                DomainToUi<S, T> {

                protected fun errorMessage(errorType: ErrorType) = resourceProvider.getString(
                    when (errorType) {
                        ErrorType.NO_CONNECTION -> R.string.no_connection_message
                        ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unavailable
                        else -> R.string.something_went_wrong
                    }
                )
            }
        }

        class Empty : Mapper
    }
}