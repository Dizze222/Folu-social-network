package ch.b.retrofitandcoroutines.di.module

import android.content.Context
import ch.b.retrofitandcoroutines.utils.core.FeatureScope
import ch.b.retrofitandcoroutines.utils.core_ui.ResourceProvider
import dagger.Module
import dagger.Provides

@Module(includes = [CacheModule::class, DataModule::class, DomainModule::class, NetworkModule::class, PresentationModule::class])
class AppModule {

    @Provides
    @FeatureScope
    fun provideResourceProvider(context: Context): ResourceProvider {
        return ResourceProvider.Base(context)
    }
}