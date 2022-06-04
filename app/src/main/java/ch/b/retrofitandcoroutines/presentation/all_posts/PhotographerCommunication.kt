package ch.b.retrofitandcoroutines.presentation.all_posts

import androidx.lifecycle.LifecycleOwner
import ch.b.retrofitandcoroutines.core.Abstract
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow

interface PhotographerCommunication : Abstract.Mapper {

    fun map(photographer: List<PhotographerUI>)


    suspend fun observe(owner: LifecycleOwner, observer: FlowCollector<List<PhotographerUI>>)

    class Base : PhotographerCommunication {
        private val listOfPhotographers = MutableStateFlow<List<PhotographerUI>>(listOf())

        override fun map(photographer: List<PhotographerUI>) {
            listOfPhotographers.value = photographer
        }

        override suspend fun observe(
            owner: LifecycleOwner,
            observer: FlowCollector<List<PhotographerUI>>
        ) {
            listOfPhotographers.collect(observer)
        }
    }

}