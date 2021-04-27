package com.lee.githubsample.di

import android.content.Context
import com.lee.githubsample.MainApplication
import com.lee.githubsample.api.ApiModule
import com.lee.githubsample.data.source.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        RepositoryModule::class,
        MainModule::class,
        AndroidSupportInjectionModule::class
    ]
)

interface ApplicationComponent : AndroidInjector<MainApplication> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }
}