package ch.b.retrofitandcoroutines.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import ch.b.retrofitandcoroutines.core.Abstract

interface PhotographerCommunication : Abstract.Mapper {

    fun map(photographer: List<PhotographerUI>)

    fun observe(owner: LifecycleOwner,observer: Observer<List<PhotographerUI>>)

    class Base : PhotographerCommunication{
        private val liveData = MutableLiveData<List<PhotographerUI>>()

        override fun map(photographer: List<PhotographerUI>) {
            liveData.value = photographer
        }

        override fun observe(
            owner: LifecycleOwner,
            observer: Observer<List<PhotographerUI>>
        ) {
            liveData.observe(owner,observer)
        }

    }

}