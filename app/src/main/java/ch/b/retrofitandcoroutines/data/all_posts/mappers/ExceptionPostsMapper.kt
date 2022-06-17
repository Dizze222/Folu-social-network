package ch.b.retrofitandcoroutines.data.all_posts.mappers

import ch.b.retrofitandcoroutines.R
import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.presentation.core.ResourceProvider
import java.lang.Exception
import java.lang.NullPointerException
import java.net.ConnectException

interface ExceptionPostsMapper : Abstract.Mapper {

    fun mapper(exception: Exception): String

    class Base(private val resourceProvider: ResourceProvider) : ExceptionPostsMapper {
        override fun mapper(exception: Exception): String =
            when (exception) {
                is NullPointerException -> resourceProvider.getString(R.string.token_expired)
                is ConnectException -> resourceProvider.getString(R.string.filed_to_connection_server)
                else -> resourceProvider.getString(R.string.something_went_wrong)
            }
    }
    class Test : ExceptionPostsMapper {
        override fun mapper(exception: Exception): String =
            when (exception) {
                is NullPointerException -> "Токен доступа истек срок действия"
                is ConnectException -> "Невозможно подключиться к серверу, попробуйте еще раз"
                else -> "Что-то пошло не так"
            }
    }
}
