package ch.b.retrofitandcoroutines.presentation.user_profile.core

import androidx.lifecycle.LifecycleOwner
import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.presentation.all_posts.PhotographerUI
import ch.b.retrofitandcoroutines.presentation.user_profile.UserProfileUi
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