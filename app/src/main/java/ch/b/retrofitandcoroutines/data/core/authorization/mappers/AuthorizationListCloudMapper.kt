package ch.b.retrofitandcoroutines.data.core.authorization.mappers

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.core.authorization.AuthorizationCloud
import ch.b.retrofitandcoroutines.data.core.authorization.AuthorizationData

interface AuthorizationListCloudMapper : Abstract.Mapper {
    fun map(cloudList: List<AuthorizationCloud>): List<AuthorizationData>

    class Base(private val mapper: Abstract.ToRegisterMapper<AuthorizationData>) :
        AuthorizationListCloudMapper {
        override fun map(cloudList: List<AuthorizationCloud>): List<AuthorizationData> {
            return cloudList.map {
                it.map(mapper)
            }
        }

    }

}