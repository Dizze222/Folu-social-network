package ch.b.retrofitandcoroutines.presentation.di

import android.content.Context
import ch.b.retrofitandcoroutines.domain.all_posts.PhotographerListDomainToUIMapper
import ch.b.retrofitandcoroutines.domain.certain_post.CertainPostInteractor
import ch.b.retrofitandcoroutines.presentation.all_posts.BasePhotographerDomainToUIMapper
import ch.b.retrofitandcoroutines.presentation.all_posts.BasePhotographerListDomainToUIMapper
import ch.b.retrofitandcoroutines.presentation.all_posts.PhotographerCommunication
import ch.b.retrofitandcoroutines.presentation.all_posts.ResourceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(ViewModelComponent::class)
class PresentationModule() {


    @Provides
    fun provideResourceProvider(@ApplicationContext context: Context): ResourceProvider {
        return ResourceProvider.Base(context)
    }

    @Provides
    fun provideCommunication(): PhotographerCommunication {
        return PhotographerCommunication.Base()
    }

    @Provides
    fun providePhotographerListDomainToUiMapper(@ApplicationContext context: Context): PhotographerListDomainToUIMapper {
        return BasePhotographerListDomainToUIMapper(
            BasePhotographerDomainToUIMapper(),ResourceProvider.Base(context))
    }


}
