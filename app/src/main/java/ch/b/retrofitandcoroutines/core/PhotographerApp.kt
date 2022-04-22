package ch.b.retrofitandcoroutines.core

import android.app.Application
import ch.b.retrofitandcoroutines.data.mappers.PhotographerDataToDBMapper
import ch.b.retrofitandcoroutines.data.PhotographerRepository
import ch.b.retrofitandcoroutines.data.mappers.ToPhotographerMapper
import ch.b.retrofitandcoroutines.data.cache.PhotographerListCacheDataSource
import ch.b.retrofitandcoroutines.data.cache.PhotographerListCacheMapper
import ch.b.retrofitandcoroutines.data.cache.RealmProvider
import ch.b.retrofitandcoroutines.data.net.PhotographerService
import ch.b.retrofitandcoroutines.data.net.PhotographerListCloudMapper
import ch.b.retrofitandcoroutines.data.net.PhotographersCloudDataSource
import ch.b.retrofitandcoroutines.domain.BasePhotographerDataToDomainMapper
import ch.b.retrofitandcoroutines.domain.BasePhotographerListDataToDomainMapper
import ch.b.retrofitandcoroutines.domain.PhotographerInteractor
import ch.b.retrofitandcoroutines.presentation.*
import io.realm.Realm
import io.realm.RealmConfiguration
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PhotographerApp : Application() {
    lateinit var mainViewModel: MainViewModel

    private companion object {
        const val BASE_URL = "https://photographer-application.herokuapp.com/"
    }

    private val REALM_SCHEMA_VERSION: Long = 1
    private val REALM_DB_NAME = "rMigrationSample.db"

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)

        val config = RealmConfiguration.Builder()
            .name(REALM_DB_NAME)
            .schemaVersion(REALM_SCHEMA_VERSION)
            .build()
        Realm.setDefaultConfiguration(config)


        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        val service = retrofit.create(PhotographerService::class.java)

        val cloudDataSource = PhotographersCloudDataSource.Base(service)
        val cacheDataSource = PhotographerListCacheDataSource.Base(
            RealmProvider.Base(),
            PhotographerDataToDBMapper.Base()
        )
        val toBookMapper = ToPhotographerMapper.Base()
        val photographersCloudMapper = PhotographerListCloudMapper.Base(toBookMapper)
        val photographersCacheMapper = PhotographerListCacheMapper.Base(toBookMapper)

        val photographersRepository = PhotographerRepository.Base(
            cloudDataSource,
            cacheDataSource,
            photographersCloudMapper,
            photographersCacheMapper
        )

        val photographerInteractor = PhotographerInteractor.Base(
            photographersRepository, BasePhotographerListDataToDomainMapper(
                BasePhotographerDataToDomainMapper()
            )
        )
        val communication = PhotographerCommunication.Base()

        mainViewModel = MainViewModel(
            photographerInteractor, BasePhotographerListDomainToUIMapper(
                BasePhotographerDomainToUIMapper(),
                ResourceProvider.Base(this)
            ), communication
        )
    }
}