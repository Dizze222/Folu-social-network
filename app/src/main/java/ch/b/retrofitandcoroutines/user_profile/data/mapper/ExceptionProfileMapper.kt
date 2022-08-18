package ch.b.retrofitandcoroutines.user_profile.data.mapper

import ch.b.retrofitandcoroutines.R
import ch.b.retrofitandcoroutines.utils.core.Abstract
import ch.b.retrofitandcoroutines.utils.core_network.ExceptionAuthMapper
import ch.b.retrofitandcoroutines.utils.core_ui.ResourceProvider
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException

interface ExceptionProfileMapper : Abstract.Mapper{

    fun map(exception: Exception): String

    class Base(private val resourceProvider: ResourceProvider) : ExceptionAuthMapper {
        override fun map(exception: Exception): String =
            when (exception) {
                is UnknownHostException -> resourceProvider.getString(R.string.service_unavailable)
                is HttpException -> resourceProvider.getString(R.string.something_went_wrong)
                is ConnectException -> resourceProvider.getString(R.string.filed_to_connection_server)
                else -> "неизвестная ошибка $exception"
            }
    }

}