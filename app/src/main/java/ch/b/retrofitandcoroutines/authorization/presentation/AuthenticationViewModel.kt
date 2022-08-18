package ch.b.retrofitandcoroutines.authorization.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.b.retrofitandcoroutines.authorization.domain.AuthenticationInteractor
import ch.b.retrofitandcoroutines.registration.domain.RegistrationListDomain
import ch.b.retrofitandcoroutines.registration.domain.RegistrationListDomainToUIMapper
import ch.b.retrofitandcoroutines.registration.presentation.RegistrationCommunication
import ch.b.retrofitandcoroutines.registration.presentation.RegistrationUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AuthenticationViewModel(
    private val interactor: AuthenticationInteractor,
    private val communication: RegistrationCommunication,
    private val mapper: RegistrationListDomainToUIMapper
) : ViewModel() {

    fun authentication(
        phoneNumber: Long,
        password: String
    ) {
        communication.map(listOf(RegistrationUI.Progress))
        viewModelScope.launch {
            val resultDomain: RegistrationListDomain =
                interactor.authentication(phoneNumber, password)
            withContext(Dispatchers.Main) {
                val resultUI = resultDomain.map(mapper)
                resultUI.map(communication)
            }
        }

    }

    suspend fun observer(owner: LifecycleOwner, observer: FlowCollector<List<RegistrationUI>>) {
        communication.observe(owner, observer)
    }

}