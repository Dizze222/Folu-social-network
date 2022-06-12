package ch.b.retrofitandcoroutines.presentation.registration

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.b.retrofitandcoroutines.domain.registration.RegistrationInteractor
import ch.b.retrofitandcoroutines.domain.registration.RegistrationListDomainToUIMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class RegisterViewModel(
    private val interactor: RegistrationInteractor,
    private val mapper: RegistrationListDomainToUIMapper,
    private val communicate: RegistrationCommunication
) : ViewModel() {

    fun registration(
        name: String,
        secondName: String,
        numberOfPhone: Long,
        password: String
    ) {
        communicate.map(listOf(RegistrationUI.Progress))
        viewModelScope.launch {
            val resultDomain = interactor.register(numberOfPhone, name, secondName, password)
            withContext(Dispatchers.Main) {
                val resultUi = resultDomain.map(mapper)
                resultUi.map(communicate)
            }
        }
    }

    suspend fun observe(
        owner: LifecycleOwner,
        observer: FlowCollector<List<RegistrationUI>>
    ) {
        communicate.observe(owner, observer)
    }

}