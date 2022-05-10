package ch.b.retrofitandcoroutines.presentation.di

import ch.b.retrofitandcoroutines.data.all_posts.net.PhotographerService
import ch.b.retrofitandcoroutines.data.all_posts.net.PhotographersCloudDataSource
import ch.b.retrofitandcoroutines.data.certain_post.net.CertainPhotographerService
import ch.b.retrofitandcoroutines.data.certain_post.net.CertainPostDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    private companion object {
        private const val BASE_URL = "https://bc79-84-39-247-98.ngrok.io/"
    }


    @Provides
    @Singleton
    fun provideGson(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun providePhotographerService(retrofit: Retrofit): PhotographerService {
        return retrofit.create(PhotographerService::class.java)
    }

    @Provides
    @Singleton
    fun provideCertainPostService(retrofit: Retrofit) : CertainPhotographerService{
        return retrofit.create(CertainPhotographerService::class.java)
    }

    @Provides
    @Singleton
    fun provideCloudDataSource(service: PhotographerService): PhotographersCloudDataSource {
        return PhotographersCloudDataSource.Base(service)
    }

    @Provides
    @Singleton
    fun provideCertainPostDataSource(service: CertainPhotographerService): CertainPostDataSource {
        return CertainPostDataSource.Base(service)
    }
}