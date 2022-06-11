package ch.b.retrofitandcoroutines.data.core.authorization.mappers

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.core.authorization.AuthorizationData

class ToAuthorizationMapper : Abstract.ToRegisterMapper<AuthorizationData>{
    override fun map(
        accessToken: String,
        refreshToken: String,
        successRegister: Boolean
    ): AuthorizationData {
        return AuthorizationData.Base(accessToken,refreshToken,successRegister)
    }


}