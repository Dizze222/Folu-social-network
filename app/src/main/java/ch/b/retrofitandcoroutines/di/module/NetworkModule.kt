package ch.b.retrofitandcoroutines.di.module

import ch.b.retrofitandcoroutines.data.all_posts.net.PhotographerService
import ch.b.retrofitandcoroutines.data.all_posts.net.PhotographerListCloudDataSource
import ch.b.retrofitandcoroutines.data.authorization.net.authorization.AuthenticationCloudDataSource
import ch.b.retrofitandcoroutines.data.authorization.net.authorization.AuthenticationService
import ch.b.retrofitandcoroutines.data.authorization.net.authorization.Authenticator
import ch.b.retrofitandcoroutines.data.authorization.net.update_token.UpdateTokenService
import ch.b.retrofitandcoroutines.data.certain_post.net.CertainPhotographerService
import ch.b.retrofitandcoroutines.data.certain_post.net.CertainPostDataSource
import ch.b.retrofitandcoroutines.data.registration.net.RegistrationCloudDataSource
import ch.b.retrofitandcoroutines.data.registration.net.RegistrationService
import ch.b.retrofitandcoroutines.data.core.TokenInterceptor
import ch.b.retrofitandcoroutines.data.core.authorization.cache.TokenToSharedPreferences
import ch.b.retrofitandcoroutines.data.splash.SplashService
import ch.b.retrofitandcoroutines.data.user_profile.network.UserProfileService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [CoroutinesScopeModule::class])
class NetworkModule {
    private companion object {
        private const val BASE_URL = "https://6168-85-26-232-186.ngrok.io/"
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().setLenient().create()
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(
        accessTokenFromShared: TokenToSharedPreferences,
        authenticator: Authenticator
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.MINUTES)
            .writeTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES)
            .authenticator(authenticator)
            .addInterceptor(TokenInterceptor.AccessToken(accessTokenFromShared))
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        gson: Gson,
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideUpdateTokenService(retrofit: Retrofit): UpdateTokenService {
        return retrofit.create(UpdateTokenService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserProfileService(retrofit: Retrofit) : UserProfileService{
        return retrofit.create(UserProfileService::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthenticator(accessTokenFromShared: TokenToSharedPreferences): Authenticator {
        return Authenticator(accessTokenFromShared)
    }

    @Provides
    @Singleton
    fun providePhotographerService(retrofit: Retrofit): PhotographerService {
        return retrofit.create(PhotographerService::class.java)
    }

    @Provides
    @Singleton
    fun provideSplashService(retrofit: Retrofit): SplashService {
        return retrofit.create(SplashService::class.java)
    }

    @Provides
    @Singleton
    fun provideRegistrationService(retrofit: Retrofit): RegistrationService {
        return retrofit.create(RegistrationService::class.java)
    }


    @Provides
    @Singleton
    fun provideAuthenticationService(retrofit: Retrofit): AuthenticationService {
        return retrofit.create(AuthenticationService::class.java)
    }

    @Provides
    @Singleton
    fun provideCertainPostService(retrofit: Retrofit): CertainPhotographerService {
        return retrofit.create(CertainPhotographerService::class.java)
    }

    @Provides
    @Singleton
    fun provideCloudDataSource(service: PhotographerService): PhotographerListCloudDataSource {
        return PhotographerListCloudDataSource.Base(service)
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