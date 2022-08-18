package ch.b.retrofitandcoroutines.utils.core_network.authorization.mappers

import ch.b.retrofitandcoroutines.utils.core.Abstract
import ch.b.retrofitandcoroutines.utils.core_network.authorization.AuthorizationCloud
import ch.b.retrofitandcoroutines.utils.core_network.authorization.AuthorizationData

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