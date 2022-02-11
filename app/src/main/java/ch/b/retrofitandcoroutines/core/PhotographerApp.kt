package ch.b.retrofitandcoroutines.core

import android.app.Application
import ch.b.retrofitandcoroutines.data.mappers.PhotographerDataToDBMapper
import ch.b.retrofitandcoroutines.data.PhotographersRepository
import ch.b.retrofitandcoroutines.data.mappers.ToPhotographerMapper
import ch.b.retrofitandcoroutines.data.cache.PhotographersCacheDataSource
import ch.b.retrofitandcoroutines.data.cache.PhotographersCacheMapper
import ch.b.retrofitandcoroutines.data.cache.RealmProvider
import ch.b.retrofitandcoroutines.data.mappers.PhotographerDataToDomainMapper
import ch.b.retrofitandcoroutines.data.net.PhotographerService
import ch.b.retrofitandcoroutines.data.net.PhotographersCloudMapper
import ch.b.retrofitandcoroutines.data.net.PhotographersCloudDataSource
import ch.b.retrofitandcoroutines.domain.BasePhotographerDataToDomainMapper
import ch.b.retrofitandcoroutines.domain.BasePhotographersDataToDomainMapper
import ch.b.retrofitandcoroutines.domain.PhotographerDomainToUIMapper
import ch.b.retrofitandcoroutines.domain.PhotographersInteractor
import ch.b.retrofitandcoroutines.presentation.*
import io.realm.Realm
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PhotographerApp : Application() {
    lateinit var mainViewModel: MainViewModel

    private companion object {
        const val BASE_URL = "https://photographer-application.herokuapp.com/"
    }

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        val service = retrofit.create(PhotographerService::class.java)

        val cloudDataSource = PhotographersCloudDataSource.Base(service)
        val cacheDataSource = PhotographersCacheDataSource.Base(
            RealmProvider.Base(),
            PhotographerDataToDBMapper.Base()
        )
        val toBookMapper = ToPhotographerMapper.Base()
        val photographersCloudMapper = PhotographersCloudMapper.Base(toBookMapper)
        val photographersCacheMapper = PhotographersCacheMapper.Base(toBookMapper)

        val photographersRepository = PhotographersRepository.Base(
            cloudDataSource,
            cacheDataSource,
            photographersCloudMapper,
            photographersCacheMapper
        )

        val photographerInteractor = PhotographersInteractor.Base(
            photographersRepository, BasePhotographersDataToDomainMapper(
                BasePhotographerDataToDomainMapper()
            )
        )
        val communication = PhotographerCommunication.Base()

        mainViewModel = MainViewModel(
            photographerInteractor, BasePhotographersDomainToUIMapper(BasePhotographerDomainToUIMapper(),
                ResourceProvider.Base(this)), communication)
    }
}