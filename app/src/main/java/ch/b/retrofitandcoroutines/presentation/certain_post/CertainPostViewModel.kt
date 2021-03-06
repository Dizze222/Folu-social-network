package ch.b.retrofitandcoroutines.presentation.certain_post


import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.b.retrofitandcoroutines.domain.all_posts.PhotographerListDomainToUIMapper
import ch.b.retrofitandcoroutines.domain.certain_post.CertainPostInteractor
import ch.b.retrofitandcoroutines.presentation.all_posts.core.PhotographerCommunication
import ch.b.retrofitandcoroutines.presentation.all_posts.PhotographerUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CertainPostViewModel(
    private val interactor: CertainPostInteractor,
    private val mapper: PhotographerListDomainToUIMapper,
    private val communicate: PhotographerCommunication
) : ViewModel() {

    fun getCertainPost(postId: Int){
        communicate.map(listOf(PhotographerUI.Progress))
        viewModelScope.launch(Dispatchers.IO) {
            val resultDomain = interactor.getCertainPost(postId)
            withContext(Dispatchers.Main){
                val resultUi = resultDomain.map(mapper)
                resultUi.map(communicate)
            }
        }
    }
    suspend fun observeCertainPost(owner: LifecycleOwner, observer: FlowCollector<List<PhotographerUI>>) {
        communicate.observe(owner, observer)
    }

}