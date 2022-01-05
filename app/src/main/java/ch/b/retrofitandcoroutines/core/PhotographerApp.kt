package ch.b.retrofitandcoroutines.core

import android.app.Application
import ch.b.retrofitandcoroutines.data.PhotographersRepository
import ch.b.retrofitandcoroutines.data.cache.PhotographerCacheMapper
import ch.b.retrofitandcoroutines.data.cache.PhotographersCacheDataSource
import ch.b.retrofitandcoroutines.data.cache.PhotographersCacheMapper
import ch.b.retrofitandcoroutines.data.cache.RealmProvider
import ch.b.retrofitandcoroutines.data.net.PhotographerCloudMapper
import ch.b.retrofitandcoroutines.data.net.PhotographerService
import ch.b.retrofitandcoroutines.data.net.PhotographersCloudMapper
import ch.b.retrofitandcoroutines.data.net.PhotographersDataSource
import ch.b.retrofitandcoroutines.domain.BasePhotographersDataToDomainMapper
import ch.b.retrofitandcoroutines.domain.PhotographersInteractor
import ch.b.retrofitandcoroutines.presentation.BasePhotographersDomainToUIMapper
import ch.b.retrofitandcoroutines.presentation.MainViewModel
import ch.b.retrofitandcoroutines.presentation.PhotographerCommunication
import ch.b.retrofitandcoroutines.presentation.ResourceProvider
import io.realm.Realm
import retrofit2.Retrofit

class PhotographerApp : Application(){
    lateinit var mainViewModel: MainViewModel

    private companion object{
        const val BASE_URL = "https://picsum.photos/v2/"
    }
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .build()
        val service = retrofit.create(PhotographerService::class.java)

        val cloudDataSource = PhotographersDataSource.Base(service)
        val cacheDataSource = PhotographersCacheDataSource.Base(RealmProvider.Base())
        val photographersCloudMapper = PhotographersCloudMapper.Base(PhotographerCloudMapper.Base())
        val photographersCacheMapper = PhotographersCacheMapper.Base(PhotographerCacheMapper.Base())

        val photographersRepository = PhotographersRepository.Base(
            cloudDataSource,
            cacheDataSource,
            photographersCloudMapper,
            photographersCacheMapper
        )

        val photographerInteractor = PhotographersInteractor.Base(photographersRepository,BasePhotographersDataToDomainMapper())
        val communication = PhotographerCommunication.Base()

        mainViewModel = MainViewModel(photographerInteractor, BasePhotographersDomainToUIMapper(
            communication,ResourceProvider.Base(this)),
            communication)
    }
}