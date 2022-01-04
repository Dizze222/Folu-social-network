package ch.b.retrofitandcoroutines.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import ch.b.retrofitandcoroutines.core.PhotographerParameters

interface PhotographerCommunication {

    fun show(photographer: List<PhotographerParameters>)
    fun show(errorMessage: String)

    fun observeSuccess(owner: LifecycleOwner,observer: Observer<List<PhotographerParameters>>)
    fun observeFail(owner: LifecycleOwner, observer: Observer<String>)

    class Base : PhotographerCommunication{
        private val successLiveData = MutableLiveData<List<PhotographerParameters>>()
        private val failLiveData =MutableLiveData<String>()

        override fun show(photographer: List<PhotographerParameters>) {
            successLiveData.value = photographer
        }

        override fun show(errorMessage: String) {
            failLiveData.value = errorMessage
        }

        override fun observeSuccess(
            owner: LifecycleOwner,
            observer: Observer<List<PhotographerParameters>>
        ) {
            successLiveData.observe(owner,observer)
        }

        override fun observeFail(owner: LifecycleOwner, observer: Observer<String>) {
            failLiveData.observe(owner,observer)
        }

    }

}