package com.lee.githubsample.data

import com.google.gson.annotations.SerializedName

data class Repository(
    @SerializedName("id")
    val id: Long,

    @SerializedName("full_name")
    val fullName: String,

    @SerializedName("html_url")
    val htmlUrl: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("stargazers_count")
    val stargazersCount: Int,

    @SerializedName("owner")
    val owner: GithubRepositoryOwner
) {

    data class GithubRepositoryOwner(
        @SerializedName("avatar_url")
        val avatarUrl: String
    )

}