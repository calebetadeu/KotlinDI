package org.calebetadeu.kotlindi.profile.domain.useCase

import org.calebetadeu.kotlindi.core.domain.networking.NetworkError
import org.calebetadeu.kotlindi.core.domain.networking.Result
import org.calebetadeu.kotlindi.profile.domain.models.ProfileGitHub
import org.calebetadeu.kotlindi.profile.domain.repository.ProfileGitHubRepository

class ProfileGitHubUseCase(
    private val repository: ProfileGitHubRepository
) {
    suspend operator fun invoke(
        username: String
    ): Result<ProfileGitHub, NetworkError> {
        return repository.fetchProfile(username)
    }
}