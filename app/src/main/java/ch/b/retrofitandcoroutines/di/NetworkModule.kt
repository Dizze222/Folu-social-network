package ch.b.retrofitandcoroutines.di


import ch.b.retrofitandcoroutines.data.all_posts.net.PhotographerService
import ch.b.retrofitandcoroutines.data.all_posts.net.PhotographersCloudDataSource
import ch.b.retrofitandcoroutines.data.authorization.net.AuthenticationCloudDataSource
import ch.b.retrofitandcoroutines.data.authorization.net.AuthenticationService
import ch.b.retrofitandcoroutines.data.certain_post.net.CertainPhotographerService
import ch.b.retrofitandcoroutines.data.certain_post.net.CertainPostDataSource
import ch.b.retrofitandcoroutines.data.registration.net.RegistrationCloudDataSource
import ch.b.retrofitandcoroutines.data.registration.net.RegistrationService
import ch.b.retrofitandcoroutines.data.core.TokenInterceptor
import ch.b.retrofitandcoroutines.data.core.TokenToSharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    private companion object {
        private const val BASE_URL = "https://8403-84-39-247-98.ngrok.io/"
    }


    @Provides
    @Singleton
    fun provideGson(): GsonConverterFactory = GsonConverterFactory.create()


    @Provides
    @Singleton
    fun provideOkHttpClient(accessTokenFromShared: TokenToSharedPreferences): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(TokenInterceptor(accessTokenFromShared))
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
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
    fun provideRegistrationService(retrofit: Retrofit): RegistrationService {
        return retrofit.create(RegistrationService::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthenticationService(retrofit: Retrofit) : AuthenticationService{
        return retrofit.create(AuthenticationService::class.java)
    }

    @Provides
    @Singleton
    fun provideCertainPostService(retrofit: Retrofit): CertainPhotographerService {
        return retrofit.create(CertainPhotographerService::class.java)
    }

    @Provides
    @Singleton
    fun provideCloudDataSource(service: PhotographerService): PhotographersCloudDataSource {
        return PhotographersCloudDataSource.Base(service)
    }

    @Provides
    @Singleton
    fun provideAuthenticationCloudDataSource(service: AuthenticationService): AuthenticationCloudDataSource {
        return AuthenticationCloudDataSource.Base(service)
    }


    @Provides
    @Singleton
    fun provideCertainPostDataSource(service: CertainPhotographerService): CertainPostDataSource {
        return CertainPostDataSource.Base(service)
    }

    @Provides
    @Singleton
    fun provideRegistrationDataSource(service: RegistrationService): RegistrationCloudDataSource {
        return RegistrationCloudDataSource.Base(service)
    }


}