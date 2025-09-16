package org.calebetadeu.kotlindi.profile.domain.models

data class ProfileGitHub(
    val username: String,
    val name: String?,
    val avatarUrl: String?,
    val bio: String?,
    val location: String?,
    val blog: String?,
    val twitterUsername: String?,
    val publicRepos: Int,
    val followers: Int,
    val following: Int,
)
