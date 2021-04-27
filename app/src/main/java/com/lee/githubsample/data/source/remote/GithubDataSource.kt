package com.lee.githubsample.data.source.remote

import android.util.Log
import com.lee.githubsample.api.GithubService
import com.lee.githubsample.data.GitHubRepositoryResponse
import com.lee.githubsample.data.source.GithubRepository
import com.lee.githubsample.util.BaseDataSource
import com.lee.githubsample.util.RemoteResult
import javax.inject.Inject

class GithubDataSource @Inject constructor(private val githubService: GithubService) :
    GithubRepository, BaseDataSource() {

    override suspend fun getRepositories(query: String): GitHubRepositoryResponse? {
        val result = getResult { githubService.getRepositories(query) }

        return when (result.status) {
            RemoteResult.Status.SUCCESS -> {
                result.data
            }
            else -> {
                Log.e("fail", result.message.toString())
                null
            }
        }
    }

}