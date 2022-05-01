package ch.b.retrofitandcoroutines.core

import android.app.Application
import ch.b.retrofitandcoroutines.data.all_posts.mappers.PhotographerDataToDBMapper
import ch.b.retrofitandcoroutines.data.all_posts.PhotographerRepository
import ch.b.retrofitandcoroutines.data.all_posts.mappers.ToPhotographerMapper
import ch.b.retrofitandcoroutines.data.all_posts.cache.PhotographerListCacheDataSource
import ch.b.retrofitandcoroutines.data.all_posts.cache.PhotographerListCacheMapper
import ch.b.retrofitandcoroutines.data.all_posts.cache.RealmProvider
import ch.b.retrofitandcoroutines.data.all_posts.net.PhotographerService
import ch.b.retrofitandcoroutines.data.all_posts.net.PhotographerListCloudMapper
import ch.b.retrofitandcoroutines.data.all_posts.net.PhotographersCloudDataSource
import ch.b.retrofitandcoroutines.data.certain_post.CertainPostRepository
import ch.b.retrofitandcoroutines.data.certain_post.net.CertainPhotographerService
import ch.b.retrofitandcoroutines.data.certain_post.net.CertainPostDataSource
import ch.b.retrofitandcoroutines.domain.all_posts.BasePhotographerDataToDomainMapper
import ch.b.retrofitandcoroutines.domain.all_posts.BasePhotographerListDataToDomainMapper
import ch.b.retrofitandcoroutines.domain.all_posts.PhotographerInteractor
import ch.b.retrofitandcoroutines.domain.certain_post.CertainPostInteractor
import ch.b.retrofitandcoroutines.presentation.all_posts.*
import ch.b.retrofitandcoroutines.presentation.certain_post.CertainPostViewModel
import io.realm.Realm
import io.realm.RealmConfiguration
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PhotographerApp : Application() {
    lateinit var mainViewModel: AllPostsViewModel
    lateinit var certainPost: CertainPostViewModel

    private companion object {
        const val BASE_URL = "https://4055-95-105-65-149.ngrok.io/"
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
        val serviceOfCertainPost = retrofit.create(CertainPhotographerService::class.java)
        val cloudDataSource = PhotographersCloudDataSource.Base(service)
        val cacheDataSource = PhotographerListCacheDataSource.Base(
            RealmProvider.Base(),
            PhotographerDataToDBMapper.Base()
        )
        val certainDataSource = CertainPostDataSource.Base(serviceOfCertainPost)
        val toBookMapper = ToPhotographerMapper.Base()
        val photographersCloudMapper = PhotographerListCloudMapper.Base(toBookMapper)
        val photographersCacheMapper = PhotographerListCacheMapper.Base(toBookMapper)

        val photographersRepository = PhotographerRepository.Base(
            cloudDataSource,
            cacheDataSource,
            photographersCloudMapper,
            photographersCacheMapper
        )
        val mapper = BasePhotographerListDataToDomainMapper(
            BasePhotographerDataToDomainMapper()
        )
        val certainPostRepository = CertainPostRepository.Base(
            certainDataSource, photographersCloudMapper
        )

        val photographerInteractor = PhotographerInteractor.Base(
            photographersRepository, mapper
        )

        val certainInteractor = CertainPostInteractor.Base(
            certainPostRepository, mapper
        )
        val communication = PhotographerCommunication.Base()

        mainViewModel = AllPostsViewModel(
            photographerInteractor, BasePhotographerListDomainToUIMapper(
                BasePhotographerDomainToUIMapper(),
                ResourceProvider.Base(this)
            ), communication
        )
        certainPost = CertainPostViewModel(
            certainInteractor, BasePhotographerListDomainToUIMapper(
                BasePhotographerDomainToUIMapper(),
                ResourceProvider.Base(this)
            ), communication
        )
    }
}