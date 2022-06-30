package ch.b.retrofitandcoroutines.presentation.liked_and_favourites.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ch.b.retrofitandcoroutines.domain.all_posts.PhotographerListDomainToUIMapper
import ch.b.retrofitandcoroutines.domain.favourite_post.FavouritePostInteractor
import ch.b.retrofitandcoroutines.presentation.all_posts.PhotographerCommunication
import javax.inject.Inject

class FavouriteViewModelFactory @Inject constructor(
    private val interactor: FavouritePostInteractor,
    private val communicate: PhotographerCommunication,
    private val mapper: PhotographerListDomainToUIMapper
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavouriteViewModel(interactor, communicate, mapper) as T
    }
}