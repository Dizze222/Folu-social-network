package ch.b.retrofitandcoroutines.user_profile.presentation.core

import androidx.lifecycle.LifecycleOwner
import ch.b.retrofitandcoroutines.utils.core.Abstract
import ch.b.retrofitandcoroutines.user_profile.presentation.UserProfileUi
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow


interface UserProfileCommunication  : Abstract.Mapper {

    fun map(photographer: List<UserProfileUi>)


    suspend fun observe(owner: LifecycleOwner, observer: FlowCollector<List<UserProfileUi>>)

    class Base : UserProfileCommunication {
        private val listOfPhotographers = MutableStateFlow<List<UserProfileUi>>(listOf())

        override fun map(photographer: List<UserProfileUi>) {
            listOfPhotographers.value = photographer
        }

        override suspend fun observe(
            owner: LifecycleOwner,
            observer: FlowCollector<List<UserProfileUi>>
        ) {
            listOfPhotographers.collect(observer)
        }
    }

}