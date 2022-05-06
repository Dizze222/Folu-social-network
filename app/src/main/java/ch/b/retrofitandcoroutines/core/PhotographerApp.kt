package ch.b.retrofitandcoroutines.core

import android.app.Application
import ch.b.retrofitandcoroutines.data.all_posts.PhotographerRepository
import ch.b.retrofitandcoroutines.data.all_posts.mappers.ToPhotographerMapper
import ch.b.retrofitandcoroutines.data.all_posts.cache.PhotographerListCacheDataSource
import ch.b.retrofitandcoroutines.data.all_posts.mappers.Cache
import ch.b.retrofitandcoroutines.data.all_posts.mappers.ToRoomMapper
import ch.b.retrofitandcoroutines.data.all_posts.net.PhotographerService
import ch.b.retrofitandcoroutines.data.all_posts.net.PhotographerListCloudMapper
import ch.b.retrofitandcoroutines.data.all_posts.net.PhotographersCloudDataSource
import ch.b.retrofitandcoroutines.data.all_posts.cache.PhotographerDataBase
import ch.b.retrofitandcoroutines.data.all_posts.cache.PhotographerListCacheMapper
import ch.b.retrofitandcoroutines.data.certain_post.CertainPostRepository
import ch.b.retrofitandcoroutines.data.certain_post.net.CertainPhotographerService
import ch.b.retrofitandcoroutines.data.certain_post.net.CertainPostDataSource
import ch.b.retrofitandcoroutines.domain.all_posts.BasePhotographerDataToDomainMapper
import ch.b.retrofitandcoroutines.domain.all_posts.BasePhotographerListDataToDomainMapper
import ch.b.retrofitandcoroutines.domain.all_posts.PhotographerInteractor
import ch.b.retrofitandcoroutines.domain.certain_post.CertainPostInteractor
import ch.b.retrofitandcoroutines.presentation.navigate.MainViewModel
import ch.b.retrofitandcoroutines.presentation.navigate.Navigator
import ch.b.retrofitandcoroutines.presentation.all_posts.*
import ch.b.retrofitandcoroutines.presentation.certain_post.CertainPostViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PhotographerApp : Application() {
    lateinit var mainViewModel: MainViewModel
    lateinit var allPostsViewModel: AllPostsViewModel
    lateinit var certainPost: CertainPostViewModel

    private companion object {
        const val BASE_URL = "https://photographer-application.herokuapp.com/"
    }
    override fun onCreate() {
        super.onCreate()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        val service = retrofit.create(PhotographerService::class.java)
        val serviceOfCertainPost = retrofit.create(CertainPhotographerService::class.java)
        val cloudDataSource = PhotographersCloudDataSource.Base(service)
        val toRoomMapper = ToRoomMapper.Base()
        val test = Cache()
        val cacheDataSource = PhotographerListCacheDataSource.Base(
            PhotographerDataBase.database(applicationContext).photographerDao(),
            test
        )
        val certainDataSource = CertainPostDataSource.Base(serviceOfCertainPost)
        val toBookMapper = ToPhotographerMapper()
        val photographersCloudMapper = PhotographerListCloudMapper.Base(toBookMapper)
        val photographerCacheMapper = PhotographerListCacheMapper.Base(toRoomMapper)


        val photographersRepository = PhotographerRepository.Base(
            cloudDataSource,
            cacheDataSource,
            photographersCloudMapper,
            toRoomMapper
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
        val navigator = Navigator.Base(this)
        val navigationCommunication = NavigationCommunication.Base()
        mainViewModel = MainViewModel(navigator, navigationCommunication)
        allPostsViewModel = AllPostsViewModel(
            photographerInteractor, BasePhotographerListDomainToUIMapper(
                BasePhotographerDomainToUIMapper(),
                ResourceProvider.Base(this)
            ), communication, ResourceProvider.Base(this)
        )
        certainPost = CertainPostViewModel(
            certainInteractor, BasePhotographerListDomainToUIMapper(
                BasePhotographerDomainToUIMapper(),
                ResourceProvider.Base(this)
            ), communication
        )
    }
}