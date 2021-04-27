package com.lee.githubsample.data.source

import com.lee.githubsample.api.GithubService
import com.lee.githubsample.data.source.remote.GithubDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Singleton

@Module(includes = [RepositoryModuleBinds::class])
object RepositoryModule {
    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class StoreRemoteDataSource

    @JvmStatic
    @StoreRemoteDataSource
    @Singleton
    @Provides
    fun provideGithubDataSource(githubService: GithubService): GithubDataSource {
        return GithubDataSource(githubService)
    }
}

@Module
abstract class RepositoryModuleBinds {

    @Singleton
    @Binds
    abstract fun provideGitHubRepository(githubDataSource: GithubDataSource): GithubRepository
}
