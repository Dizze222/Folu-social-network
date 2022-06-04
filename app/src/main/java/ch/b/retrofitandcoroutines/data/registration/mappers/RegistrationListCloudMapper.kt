package ch.b.retrofitandcoroutines.data.registration.mappers

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.registration.RegistrationData
import ch.b.retrofitandcoroutines.data.registration.net.RegistrationCloud

interface RegistrationListCloudMapper : Abstract.Mapper {
    fun map(cloudList: List<RegistrationCloud>) : List<RegistrationData>

    class Base(private val mapper: Abstract.ToRegisterMapper<RegistrationData>) : RegistrationListCloudMapper{
        override fun map(cloudList: List<RegistrationCloud>): List<RegistrationData> {
            return cloudList.map {
                it.map(mapper)
            }
        }

    }
}