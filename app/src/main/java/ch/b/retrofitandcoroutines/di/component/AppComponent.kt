package ch.b.retrofitandcoroutines.di.component

import android.app.Application
import android.content.Context
import ch.b.retrofitandcoroutines.di.module.*
import ch.b.retrofitandcoroutines.presentation.all_posts.PhotographersFragment
import ch.b.retrofitandcoroutines.presentation.authentication.AuthenticationFragment
import ch.b.retrofitandcoroutines.presentation.certain_post.PhotographerDetailFragment
import ch.b.retrofitandcoroutines.presentation.core.CustomService
import ch.b.retrofitandcoroutines.presentation.liked_and_favourites.favourites.FavouritesFragment
import ch.b.retrofitandcoroutines.presentation.registration.RegistrationFragment
import ch.b.retrofitandcoroutines.presentation.splash_screen.SplashFragment
import ch.b.retrofitandcoroutines.presentation.user_profile.UserProfileFragment
import ch.b.retrofitandcoroutines.presentation.galary_picker.ImagePickerBottomSheet
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

    fun inject(fragment: RegistrationFragment)
    fun inject(fragment: PhotographersFragment)
    fun inject(fragment: PhotographerDetailFragment)
    fun inject(fragment: AuthenticationFragment)
    fun inject(fragment: SplashFragment)
    fun inject(fragment: FavouritesFragment)
    fun inject(fragment: UserProfileFragment)
    fun inject(fragment: ImagePickerBottomSheet)
    fun inject(services: CustomService)

}