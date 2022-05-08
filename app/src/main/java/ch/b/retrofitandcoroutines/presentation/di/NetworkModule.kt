package ch.b.retrofitandcoroutines.presentation.di

import ch.b.retrofitandcoroutines.data.all_posts.net.PhotographerService
import ch.b.retrofitandcoroutines.data.all_posts.net.PhotographersCloudDataSource
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
        private const val BASE_URL = "https://photographer-application.herokuapp.com/"
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
    fun provideCloudDataSource(service: PhotographerService): PhotographersCloudDataSource {
        return PhotographersCloudDataSource.Base(service)
    }

}