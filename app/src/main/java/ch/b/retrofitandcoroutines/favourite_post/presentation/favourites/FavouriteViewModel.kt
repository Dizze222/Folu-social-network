package ch.b.retrofitandcoroutines.favourite_post.presentation.favourites

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.b.retrofitandcoroutines.all_posts.domain.PhotographerListDomainToUIMapper
import ch.b.retrofitandcoroutines.favourite_post.domain.FavouritePostInteractor
import ch.b.retrofitandcoroutines.all_posts.presentation.PhotographerListUI
import ch.b.retrofitandcoroutines.all_posts.presentation.core.PhotographerCommunication
import ch.b.retrofitandcoroutines.all_posts.presentation.PhotographerUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavouriteViewModel(
    private val interactor: FavouritePostInteractor,
    private val communicate: PhotographerCommunication,
    private val mapper: PhotographerListDomainToUIMapper
) : ViewModel() {

    fun getFavouritePost() {
        viewModelScope.launch {
            val resultDomain = interactor.readFavouritePost()
            withContext(Dispatchers.Main) {
                val resultUi: PhotographerListUI = resultDomain.map(mapper)
                resultUi.map(communicate)
            }
        }
    }

    fun deleteFavouritePost(id: Int){
        viewModelScope.launch {
            interactor.delete(id)
        }
    }

    suspend fun observe(
        owner: LifecycleOwner,
        observer: FlowCollector<List<PhotographerUI>>
    ) {
        communicate.observe(owner, observer)
    }
}