package ch.b.retrofitandcoroutines.presentation

import androidx.lifecycle.*
import ch.b.retrofitandcoroutines.domain.PhotographersDomainToUIMapper
import ch.b.retrofitandcoroutines.domain.PhotographerInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val photographerInteractor: PhotographerInteractor,
    private val mapper: PhotographersDomainToUIMapper,
    private val communicate: PhotographerCommunication,
) : ViewModel() {

    fun getPhotographers() {
        communicate.map(listOf(PhotographerUI.Progress))
        viewModelScope.launch(Dispatchers.IO) {
            val resultDomain = photographerInteractor.getPhotographers()
            withContext(Dispatchers.Main) {
                val resultUI = resultDomain.map(mapper)
                resultUI.map(communicate)
            }
        }
    }

    fun observe(owner: LifecycleOwner, observe: Observer<List<PhotographerUI>>) {
        communicate.observe(owner, observe)
    }

    fun pushPost(author: String, idPhotographer: Int, like: Int, theme: String, url: String) {
        viewModelScope.launch {
            photographerInteractor.post(author, idPhotographer, like, theme, url)
        }
    }
}