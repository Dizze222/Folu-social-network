package ch.b.retrofitandcoroutines.registration.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ch.b.retrofitandcoroutines.registration.domain.RegistrationInteractor
import ch.b.retrofitandcoroutines.registration.domain.RegistrationListDomainToUIMapper
import javax.inject.Inject

class RegistrationViewModelFactory @Inject constructor(
    private val interactor: RegistrationInteractor,
    private val mapper: RegistrationListDomainToUIMapper,
    private val communicate: RegistrationCommunication
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RegisterViewModel(interactor, mapper, communicate) as T
    }

}