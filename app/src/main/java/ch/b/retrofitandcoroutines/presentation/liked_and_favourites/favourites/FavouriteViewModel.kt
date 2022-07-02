package ch.b.retrofitandcoroutines.presentation.liked_and_favourites.favourites

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.b.retrofitandcoroutines.domain.all_posts.PhotographerListDomainToUIMapper
import ch.b.retrofitandcoroutines.domain.favourite_post.FavouritePostInteractor
import ch.b.retrofitandcoroutines.presentation.all_posts.PhotographerCommunication
import ch.b.retrofitandcoroutines.presentation.all_posts.PhotographerUI
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
                val resultUi = resultDomain.map(mapper)
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