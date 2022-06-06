package ch.b.retrofitandcoroutines.data.registration

import android.util.Log
import ch.b.retrofitandcoroutines.core.Abstract
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
        private val cloudMapper: RegistrationListCloudMapper
    ) : RegistrationRepository {
        override suspend fun register(
            phoneNumber: Long,
            name: String,
            secondName: String,
            password: String
        ): RegistrationListData = try {
            val listOfCloud = dataSource.register(phoneNumber, name, secondName, password)
            val registerList: List<RegistrationData> = cloudMapper.map(listOfCloud)
           for (i in registerList.logTo()){
               Log.i("TAGG",i)
           }
            RegistrationListData.Success(registerList)
        } catch (e: Exception) {
            RegistrationListData.Fail(e)
        }
    }

    fun List<Abstract.Object<Unit, RegistrationData.StringMapper>>.logTo() : ArrayList<String>{
        val array = ArrayList<String>()
        this.map {
            it.map(object : RegistrationData.StringMapper{
                override fun map(
                    accessToken: String,
                    refreshToken: String,
                    successRegister: Boolean
                ) {
                    array.add(accessToken)
                    array.add(refreshToken)
                    array.add(successRegister.toString())
                }

                override fun map(message: String) {
                    array.add(message)
                }

            })
        }
        return array
    }
}
