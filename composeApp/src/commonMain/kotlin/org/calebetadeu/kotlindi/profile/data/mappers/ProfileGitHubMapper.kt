package org.calebetadeu.kotlindi.profile.data.mappers

import org.calebetadeu.kotlindi.profile.data.dto.ProfileGitHubDto
import org.calebetadeu.kotlindi.profile.domain.models.ProfileGitHub

fun ProfileGitHubDto.toProfileGitHub(): ProfileGitHub {
    return ProfileGitHub(
        username = this.username,
        name = this.name,
        avatarUrl = this.avatarUrl,
        bio = this.bio,
        location = this.location,
        blog = this.blog,
        twitterUsername = this.twitterUsername,
        publicRepos = this.publicRepos,
        followers = this.followers,
        following = this.following
    )
}


