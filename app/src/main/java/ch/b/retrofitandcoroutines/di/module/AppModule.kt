package ch.b.retrofitandcoroutines.di.module

import android.content.Context
import ch.b.retrofitandcoroutines.presentation.core.ResourceProvider
import dagger.Module
import dagger.Provides

@Module(includes = [CacheModule::class, DataModule::class, DomainModule::class, NetworkModule::class, PresentationModule::class])
class AppModule {

    @Provides
    fun provideResourceProvider(context: Context): ResourceProvider {
        return ResourceProvider.Base(context)
    }


}