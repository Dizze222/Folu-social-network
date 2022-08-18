package ch.b.retrofitandcoroutines.di.component

import android.app.Application
import android.content.Context
import ch.b.retrofitandcoroutines.core.FeatureScope
import ch.b.retrofitandcoroutines.di.module.*
import ch.b.retrofitandcoroutines.all_posts.presentation.PhotographersFragment
import ch.b.retrofitandcoroutines.presentation.authentication.AuthenticationFragment
import ch.b.retrofitandcoroutines.presentation.certain_post.PhotographerDetailFragment
import ch.b.retrofitandcoroutines.presentation.liked_and_favourites.favourites.FavouritesFragment
import ch.b.retrofitandcoroutines.presentation.registration.RegistrationFragment
import ch.b.retrofitandcoroutines.presentation.splash_screen.SplashFragment
import ch.b.retrofitandcoroutines.presentation.user_profile.UserProfileFragment
import ch.b.retrofitandcoroutines.presentation.galary_picker.ImagePickerBottomSheet
import dagger.BindsInstance
import dagger.Component


@Component(modules = [AppModule::class])
@FeatureScope
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

}