package org.calebetadeu.kotlindi.profile.data.dto

import kotlinx.serialization.Serializable

@Serializable(with = ProfileDtoSerializer::class)
data class ProfileGitHubDto (
    val username: String,
    val name: String?,
    val avatarUrl: String?,
    val bio: String?,
    val location: String?,
    val blog: String?,
    val twitterUsername: String?,
    val publicRepos: Int,
    val followers: Int,
    val following: Int
)