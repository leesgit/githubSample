package com.lee.githubsample.data.source

import com.lee.githubsample.data.GitHubRepositoryResponse
import com.lee.githubsample.util.RemoteResult

interface GithubRepository {

    suspend fun getRepositories(query:String) : GitHubRepositoryResponse?
}