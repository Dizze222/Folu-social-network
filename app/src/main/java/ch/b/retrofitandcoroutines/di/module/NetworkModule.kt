package ch.b.retrofitandcoroutines.di.module

import ch.b.retrofitandcoroutines.utils.core.FeatureScope
import ch.b.retrofitandcoroutines.all_posts.data.net.PhotographerService
import ch.b.retrofitandcoroutines.all_posts.data.net.PhotographerListCloudDataSource
import ch.b.retrofitandcoroutines.authorization.data.net.authorization.AuthenticationCloudDataSource
import ch.b.retrofitandcoroutines.authorization.data.net.authorization.AuthenticationService
import ch.b.retrofitandcoroutines.authorization.data.net.authorization.Authenticator
import ch.b.retrofitandcoroutines.authorization.data.net.update_token.UpdateTokenService
import ch.b.retrofitandcoroutines.certain_post.data.net.CertainPhotographerService
import ch.b.retrofitandcoroutines.certain_post.data.net.CertainPostDataSource
import ch.b.retrofitandcoroutines.registration.data.net.RegistrationCloudDataSource
import ch.b.retrofitandcoroutines.registration.data.net.RegistrationService
import ch.b.retrofitandcoroutines.utils.core_network.TokenInterceptor
import ch.b.retrofitandcoroutines.utils.core_network.authorization.cache.TokenToSharedPreferences
import ch.b.retrofitandcoroutines.splash.data.SplashService
import ch.b.retrofitandcoroutines.user_profile.data.network.UserProfileService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {
    private companion object {
        private const val BASE_URL = "https://photographer-application.herokuapp.com/"
    }

    @Provides
    @FeatureScope
    fun provideGson(): Gson {
        return GsonBuilder().setLenient().create()
    }


    @Provides
    @FeatureScope
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
    @FeatureScope
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
    @FeatureScope
    fun provideUpdateTokenService(retrofit: Retrofit): UpdateTokenService {
        return retrofit.create(UpdateTokenService::class.java)
    }

    @Provides
    @FeatureScope
    fun provideUserProfileService(retrofit: Retrofit) : UserProfileService {
        return retrofit.create(UserProfileService::class.java)
    }

    @Provides
    @FeatureScope
    fun provideAuthenticator(accessTokenFromShared: TokenToSharedPreferences): Authenticator {
        return Authenticator(accessTokenFromShared)
    }

    @Provides
    @FeatureScope
    fun providePhotographerService(retrofit: Retrofit): PhotographerService {
        return retrofit.create(PhotographerService::class.java)
    }

    @Provides
    @FeatureScope
    fun provideSplashService(retrofit: Retrofit): SplashService {
        return retrofit.create(SplashService::class.java)
    }

    @Provides
    @FeatureScope
    fun provideRegistrationService(retrofit: Retrofit): RegistrationService {
        return retrofit.create(RegistrationService::class.java)
    }


    @Provides
    @FeatureScope
    fun provideAuthenticationService(retrofit: Retrofit): AuthenticationService {
        return retrofit.create(AuthenticationService::class.java)
    }

    @Provides
    @FeatureScope
    fun provideCertainPostService(retrofit: Retrofit): CertainPhotographerService {
        return retrofit.create(CertainPhotographerService::class.java)
    }

    @Provides
    @FeatureScope
    fun provideCloudDataSource(service: PhotographerService): PhotographerListCloudDataSource {
        return PhotographerListCloudDataSource.Base(service)
    }

    @Provides
    @FeatureScope
    fun provideAuthenticationCloudDataSource(service: AuthenticationService): AuthenticationCloudDataSource {
        return AuthenticationCloudDataSource.Base(service)
    }


    @Provides
    @FeatureScope
    fun provideCertainPostDataSource(service: CertainPhotographerService): CertainPostDataSource {
        return CertainPostDataSource.Base(service)
    }

    @Provides
    @FeatureScope
    fun provideRegistrationDataSource(service: RegistrationService): RegistrationCloudDataSource {
        return RegistrationCloudDataSource.Base(service)
    }

}