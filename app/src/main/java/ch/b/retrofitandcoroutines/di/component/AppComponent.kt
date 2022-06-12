package ch.b.retrofitandcoroutines.di.component

import android.app.Application
import android.content.Context
import ch.b.retrofitandcoroutines.di.module.*
import ch.b.retrofitandcoroutines.presentation.all_posts.screen.PhotographersFragment
import ch.b.retrofitandcoroutines.presentation.registration.RegistrationFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class])
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

}