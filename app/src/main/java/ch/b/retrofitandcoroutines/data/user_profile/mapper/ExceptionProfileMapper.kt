package ch.b.retrofitandcoroutines.data.user_profile.mapper

import ch.b.retrofitandcoroutines.R
import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.core.ExceptionAuthMapper
import ch.b.retrofitandcoroutines.presentation.core.ResourceProvider
import retrofit2.HttpException
import java.lang.NullPointerException
import java.net.ConnectException
import java.net.UnknownHostException

interface ExceptionProfileMapper : Abstract.Mapper{

    fun map(exception: Exception): String

    class Registration(private val resourceProvider: ResourceProvider) : ExceptionAuthMapper {
        override fun map(exception: Exception): String =
            when (exception) {
                is UnknownHostException -> resourceProvider.getString(R.string.service_unavailable)
                is HttpException -> resourceProvider.getString(R.string.something_went_wrong)
                is ConnectException -> resourceProvider.getString(R.string.filed_to_connection_server)
                else -> "неизвестная ошибка $exception"
            }
    }

}