package ch.b.retrofitandcoroutines.presentation.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ch.b.retrofitandcoroutines.data.core.authorization.cache.TokenToSharedPreferences
import ch.b.retrofitandcoroutines.domain.authorization.AuthenticationInteractor
import ch.b.retrofitandcoroutines.domain.registration.RegistrationListDomainToUIMapper
import ch.b.retrofitandcoroutines.presentation.registration.RegistrationCommunication
import javax.inject.Inject

class AuthenticationViewModelFactory @Inject constructor(
    private val interactor: AuthenticationInteractor,
    private val communication: RegistrationCommunication,
    private val mapper: RegistrationListDomainToUIMapper
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AuthenticationViewModel(interactor, communication, mapper) as T
    }

}