package ch.b.retrofitandcoroutines.utils.core_network.authorization.mappers

import ch.b.retrofitandcoroutines.utils.core.Abstract
import ch.b.retrofitandcoroutines.utils.core_network.authorization.AuthorizationData

class ToAuthorizationMapper : Abstract.ToRegisterMapper<AuthorizationData>{
    override fun map(
        accessToken: String,
        refreshToken: String,
        successRegister: Boolean
    ): AuthorizationData {
        return AuthorizationData.Base(accessToken,refreshToken,successRegister)
    }


}