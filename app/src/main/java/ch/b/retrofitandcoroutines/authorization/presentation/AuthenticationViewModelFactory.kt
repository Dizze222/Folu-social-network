package ch.b.retrofitandcoroutines.authorization.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ch.b.retrofitandcoroutines.authorization.domain.AuthenticationInteractor
import ch.b.retrofitandcoroutines.registration.domain.RegistrationListDomainToUIMapper
import ch.b.retrofitandcoroutines.registration.presentation.RegistrationCommunication
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