package org.calebetadeu.kotlindi.profile.domain.repository

import org.calebetadeu.kotlindi.core.domain.networking.NetworkError
import org.calebetadeu.kotlindi.core.domain.networking.Result
import org.calebetadeu.kotlindi.profile.domain.models.ProfileGitHub

interface ProfileGitHubRepository {
    suspend fun fetchProfile(
        username: String
    ): Result<ProfileGitHub, NetworkError>
}