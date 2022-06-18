package ch.b.retrofitandcoroutines.presentation.all_posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ch.b.retrofitandcoroutines.domain.all_posts.PhotographerInteractor
import ch.b.retrofitandcoroutines.domain.all_posts.PhotographerListDomainToUIMapper
import ch.b.retrofitandcoroutines.presentation.core.ResourceProvider
import javax.inject.Inject

class AllPostsViewModelFactory @Inject constructor(
    private val interactor: PhotographerInteractor,
    private val mapper: PhotographerListDomainToUIMapper,
    private val communicateSearchAuthor: PhotographerCommunication,
    private val communicate: PhotographerCommunication,
    private val resourceProvider: ResourceProvider
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AllPostsViewModel(
            interactor,
            mapper,
            communicateSearchAuthor,
            communicate,
            resourceProvider
        ) as T
    }


}