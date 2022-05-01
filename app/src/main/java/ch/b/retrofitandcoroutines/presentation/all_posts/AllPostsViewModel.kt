package ch.b.retrofitandcoroutines.presentation.all_posts

import androidx.lifecycle.*
import ch.b.retrofitandcoroutines.domain.all_posts.PhotographerListDomainToUIMapper
import ch.b.retrofitandcoroutines.domain.all_posts.PhotographerInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AllPostsViewModel(
    private val photographerInteractor: PhotographerInteractor,
    private val mapper: PhotographerListDomainToUIMapper,
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