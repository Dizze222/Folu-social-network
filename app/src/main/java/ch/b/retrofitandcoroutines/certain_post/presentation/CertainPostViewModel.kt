package ch.b.retrofitandcoroutines.certain_post.presentation


import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.b.retrofitandcoroutines.all_posts.domain.PhotographerListDomainToUIMapper
import ch.b.retrofitandcoroutines.certain_post.domain.CertainPostInteractor
import ch.b.retrofitandcoroutines.all_posts.presentation.core.PhotographerCommunication
import ch.b.retrofitandcoroutines.all_posts.presentation.PhotographerUI
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