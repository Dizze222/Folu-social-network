package ch.b.retrofitandcoroutines.data.registration

import android.util.Log
import ch.b.retrofitandcoroutines.core.logTo
import ch.b.retrofitandcoroutines.data.core.ExceptionMapper
import ch.b.retrofitandcoroutines.data.core.TokenToSharedPreferences
import ch.b.retrofitandcoroutines.data.registration.mappers.RegistrationListCloudMapper
import ch.b.retrofitandcoroutines.data.registration.net.RegistrationCloudDataSource

interface RegistrationRepository {
    suspend fun register(
        phoneNumber: Long,
        name: String,
        secondName: String,
        password: String
    ): RegistrationListData

    class Base(
        private val dataSource: RegistrationCloudDataSource,
        private val cloudMapper: RegistrationListCloudMapper,
        private val exceptionMapper: ExceptionMapper,
        private val tokenToSharedPreferences: TokenToSharedPreferences
    ) : RegistrationRepository {
        override suspend fun register(
            phoneNumber: Long,
            name: String,
            secondName: String,
            password: String
        ): RegistrationListData = try {
            val listOfCloud = dataSource.register(phoneNumber, name, secondName, password)
            val registerList: List<RegistrationData> = cloudMapper.map(listOfCloud)
            tokenToSharedPreferences.save(registerList.logTo()[1])
            //Log.i("REF",tokenToSharedPreferences.read())
            RegistrationListData.Success(registerList)
        } catch (e: Exception) {
            val errorMessage = exceptionMapper.mapper(e)
            RegistrationListData.Fail(errorMessage)
        }
    }

}
