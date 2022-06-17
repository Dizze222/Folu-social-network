package ch.b.retrofitandcoroutines.data.core

import ch.b.retrofitandcoroutines.R
import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.presentation.core.ResourceProvider
import retrofit2.HttpException
import java.lang.NullPointerException
import java.net.ConnectException
import java.net.UnknownHostException

interface ExceptionAuthMapper : Abstract.Mapper {

    fun map(exception: Exception): String

    class Base(private val resourceProvider: ResourceProvider) : ExceptionAuthMapper {
        override fun map(exception: Exception): String =
            when (exception) {
                is NullPointerException -> resourceProvider.getString(R.string.invalid_register)
                is UnknownHostException -> resourceProvider.getString(R.string.service_unavailable)
                is HttpException -> resourceProvider.getString(R.string.something_went_wrong)
                is ConnectException -> resourceProvider.getString(R.string.filed_to_connection_server)
                else -> "неизвестная ошибка $exception"
            }
    }

    class Test : ExceptionAuthMapper {
        override fun map(exception: Exception): String =
            when (exception) {
                is NullPointerException -> "Ошибка регистрации, скорее всего, такой номер телефона уже зареган в базе"
                is UnknownHostException -> "No connection"
                is HttpException -> "Что-то пошло не так"
                is ConnectException -> "Невозможно подключиться к серверу, попробуйте еще раз"
                else -> "неизвестная ошибка $exception"
            }

    }
}