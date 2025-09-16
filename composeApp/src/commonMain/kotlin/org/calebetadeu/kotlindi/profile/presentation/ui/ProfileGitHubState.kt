package org.calebetadeu.kotlindi.profile.presentation.ui

import org.calebetadeu.kotlindi.profile.domain.models.ProfileGitHub

data class ProfileGitHubState(
    val isLoading: Boolean = false,
    val profileGitHub: ProfileGitHub? = null,
    val error: String = ""
)