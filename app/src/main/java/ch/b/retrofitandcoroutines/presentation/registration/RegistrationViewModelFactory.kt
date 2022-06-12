package ch.b.retrofitandcoroutines.presentation.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ch.b.retrofitandcoroutines.domain.registration.RegistrationInteractor
import ch.b.retrofitandcoroutines.domain.registration.RegistrationListDomainToUIMapper
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