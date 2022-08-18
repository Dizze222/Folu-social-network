package ch.b.retrofitandcoroutines.di.component

import android.app.Application
import android.content.Context
import ch.b.retrofitandcoroutines.utils.core.FeatureScope
import ch.b.retrofitandcoroutines.di.module.*
import ch.b.retrofitandcoroutines.all_posts.presentation.PhotographersFragment
import ch.b.retrofitandcoroutines.authorization.presentation.AuthenticationFragment
import ch.b.retrofitandcoroutines.certain_post.presentation.PhotographerDetailFragment
import ch.b.retrofitandcoroutines.favourite_post.presentation.favourites.FavouritesFragment
import ch.b.retrofitandcoroutines.registration.presentation.RegistrationFragment
import ch.b.retrofitandcoroutines.splash.presentation.SplashFragment
import ch.b.retrofitandcoroutines.user_profile.presentation.UserProfileFragment
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