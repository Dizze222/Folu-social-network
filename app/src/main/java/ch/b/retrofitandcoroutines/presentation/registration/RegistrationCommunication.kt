package ch.b.retrofitandcoroutines.presentation.registration

import androidx.lifecycle.LifecycleOwner
import ch.b.retrofitandcoroutines.core.Abstract
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow

interface RegistrationCommunication : Abstract.Mapper {
    fun map(registration: List<RegistrationUI>)


    suspend fun observe(owner: LifecycleOwner, observer: FlowCollector<List<RegistrationUI>>)

    class Base : RegistrationCommunication {
        private val listOfPhotographers = MutableStateFlow<List<RegistrationUI>>(listOf())

        override fun map(registration: List<RegistrationUI>) {
            listOfPhotographers.value = registration
        }

        override suspend fun observe(
            owner: LifecycleOwner,
            observer: FlowCollector<List<RegistrationUI>>
        ) {
            listOfPhotographers.collect(observer)
        }
    }
}