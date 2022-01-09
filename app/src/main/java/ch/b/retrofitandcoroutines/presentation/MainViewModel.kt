package ch.b.retrofitandcoroutines.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.core.PhotographerParameters
import ch.b.retrofitandcoroutines.domain.PhotographersDomainToUIMapper
import ch.b.retrofitandcoroutines.domain.PhotographersInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class MainViewModel(
    private val photographerInteractor: PhotographersInteractor,
    private val mapper: PhotographersDomainToUIMapper,
    private val communicate: PhotographerCommunication
) : ViewModel(){

   fun getPhotographers() = viewModelScope.launch(Dispatchers.IO) {
        val resultDomain = photographerInteractor.getPhotographers()
        withContext(Dispatchers.Main){
            val resultUI = resultDomain.map(mapper)
            resultUI.map(Abstract.Mapper.Empty())
        }
    }
    fun observe(owner: LifecycleOwner,observe: Observer<List<PhotographerParameters>>){
        communicate.observeSuccess(owner,observe)

    }

}