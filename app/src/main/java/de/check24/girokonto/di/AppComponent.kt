package de.check24.girokonto.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import de.check24.girokonto.ui.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun inject(activity: MainActivity)
}