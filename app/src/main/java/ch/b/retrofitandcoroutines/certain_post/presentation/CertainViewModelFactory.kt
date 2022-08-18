package ch.b.retrofitandcoroutines.certain_post.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ch.b.retrofitandcoroutines.all_posts.domain.PhotographerListDomainToUIMapper
import ch.b.retrofitandcoroutines.certain_post.domain.CertainPostInteractor
import ch.b.retrofitandcoroutines.all_posts.presentation.core.PhotographerCommunication
import javax.inject.Inject

class CertainViewModelFactory @Inject constructor(
    private val interactor: CertainPostInteractor,
    private val mapper: PhotographerListDomainToUIMapper,
    private val communicate: PhotographerCommunication
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CertainPostViewModel(interactor, mapper, communicate) as T
    }

}