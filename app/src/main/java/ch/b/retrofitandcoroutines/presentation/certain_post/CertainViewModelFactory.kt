package ch.b.retrofitandcoroutines.presentation.certain_post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ch.b.retrofitandcoroutines.domain.all_posts.PhotographerListDomainToUIMapper
import ch.b.retrofitandcoroutines.domain.certain_post.CertainPostInteractor
import ch.b.retrofitandcoroutines.presentation.all_posts.core.PhotographerCommunication
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