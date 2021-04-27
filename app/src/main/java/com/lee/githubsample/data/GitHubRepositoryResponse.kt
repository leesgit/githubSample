package com.lee.githubsample.data

import com.google.gson.annotations.SerializedName

data class GitHubRepositoryResponse(@SerializedName("total_count") val totalCount: Int, @SerializedName("items") val items: List<Repository>)
