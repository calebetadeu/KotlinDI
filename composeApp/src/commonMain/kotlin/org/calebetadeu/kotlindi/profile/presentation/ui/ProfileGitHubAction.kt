package org.calebetadeu.kotlindi.profile.presentation.ui

sealed interface ProfileGitHubAction {
    data class FetchProfileGitHub(
        val username: String
    ) : ProfileGitHubAction
}