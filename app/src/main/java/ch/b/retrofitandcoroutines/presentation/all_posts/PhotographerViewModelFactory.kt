package ch.b.retrofitandcoroutines.presentation.all_posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ch.b.retrofitandcoroutines.domain.all_posts.PhotographerInteractor

class PhotographerViewModelFactory(
    private val interactor: PhotographerInteractor,
    private val resourceProvider: ResourceProvider
) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AllPostsViewModel(
                interactor, BasePhotographerListDomainToUIMapper(BasePhotographerDomainToUIMapper(),resourceProvider),
                PhotographerCommunication.Base(),PhotographerCommunication.Base(),resourceProvider) as T
        }

    }
