package ch.b.retrofitandcoroutines.core

import android.app.Application
import ch.b.retrofitandcoroutines.di.component.AppComponent
import ch.b.retrofitandcoroutines.di.component.DaggerAppComponent


class PhotoApp : Application() {

    lateinit var appComponent: AppComponent


    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .context(this)
            .build()
    }
}