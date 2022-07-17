package ch.b.retrofitandcoroutines.presentation.all_posts

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.lifecycle.*
import ch.b.retrofitandcoroutines.domain.all_posts.PhotographerDomain
import ch.b.retrofitandcoroutines.domain.all_posts.PhotographerListDomainToUIMapper
import ch.b.retrofitandcoroutines.domain.all_posts.PhotographerInteractor
import ch.b.retrofitandcoroutines.domain.favourite_post.FavouritePostInteractor
import ch.b.retrofitandcoroutines.presentation.all_posts.core.PhotographerCommunication
import ch.b.retrofitandcoroutines.presentation.core.ResourceProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AllPostsViewModel @Inject constructor(
    private val interactor: PhotographerInteractor,
    private val mapper: PhotographerListDomainToUIMapper,
    private val favouriteInteractor: FavouritePostInteractor,
    private val communicateSearchAuthor: PhotographerCommunication,
    private val communicate: PhotographerCommunication,
    private val resourceProvider: ResourceProvider
) : ViewModel() {

    fun getPhotographers() {
        communicate.map(listOf(PhotographerUI.Progress))
        viewModelScope.launch(Dispatchers.IO) {
            val resultDomain = interactor.readDataFromCloud()
            withContext(Dispatchers.Main) {
                val resultUi = resultDomain.map(mapper)
                resultUi.map(communicate)
            }
        }

    }

    suspend fun observeAllPhotographers(
        owner: LifecycleOwner,
        observer: FlowCollector<List<PhotographerUI>>
    ) {
        communicate.observe(owner, observer)
    }

    suspend fun searchPhotographers(author: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val resultDomain = interactor.searchPhotographers(author)
            withContext(Dispatchers.Main) {
                val resultUi = resultDomain.map(mapper)
                resultUi.map(communicateSearchAuthor)
            }
        }
    }
    fun saveFavouritePost(post: List<PhotographerDomain>) {
        viewModelScope.launch {
            favouriteInteractor.saveFavouritePost(post)
        }
    }

    suspend fun observeSearchPhotographer(
        owner: LifecycleOwner,
        observer: FlowCollector<List<PhotographerUI>>
    ) {
        communicateSearchAuthor.observe(owner, observer)
    }

    fun like(author: String, idPhotographer: Int, like: Int, theme: String, url: String) {
        viewModelScope.launch {
            interactor.like(author, idPhotographer, like, theme, url)
        }
    }
    fun deleteFavouritePost(id: Int){
        viewModelScope.launch {
            favouriteInteractor.delete(id)
        }
    }

    private fun checkInternetConnection(): Boolean {
        val connectivityManager = resourceProvider.provideContext()
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork =
                connectivityManager.getNetworkCapabilities(network) ?: return false
            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("VM","onCleared")
    }
}
