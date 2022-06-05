package ch.b.retrofitandcoroutines.data.registration.mappers

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.registration.RegistrationData

class ToRegistrationMapper : Abstract.ToRegisterMapper<RegistrationData>{
    override fun map(
        accessToken: String,
        refreshToken: String,
        successRegister: Boolean
    ): RegistrationData {
        return RegistrationData.Base(accessToken,refreshToken,successRegister)
    }


}