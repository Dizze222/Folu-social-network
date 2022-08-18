package ch.b.retrofitandcoroutines.favourite_post.presentation.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ch.b.retrofitandcoroutines.all_posts.domain.PhotographerListDomainToUIMapper
import ch.b.retrofitandcoroutines.favourite_post.domain.FavouritePostInteractor
import ch.b.retrofitandcoroutines.all_posts.presentation.core.PhotographerCommunication
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