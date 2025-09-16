package org.calebetadeu.kotlindi.profile.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.calebetadeu.kotlindi.core.domain.networking.onSuccess
import org.calebetadeu.kotlindi.profile.domain.repository.ProfileGitHubRepository
import org.calebetadeu.kotlindi.profile.presentation.ui.ProfileGitHubAction
import org.calebetadeu.kotlindi.profile.presentation.ui.ProfileGitHubState

class ProfileGitHubViewModel(
    private val profileGitHubRepository: ProfileGitHubRepository
) : ViewModel() {
    private val _state = MutableStateFlow(ProfileGitHubState())
    val state = _state

    fun onAction(action: ProfileGitHubAction) {
        when (action) {
            is ProfileGitHubAction.FetchProfileGitHub -> {
                fetchProfileGitHub(action.username)
            }
        }
    }

    private fun fetchProfileGitHub(username: String) {
        _state.value = ProfileGitHubState(isLoading = true)
        viewModelScope.launch {
            profileGitHubRepository.fetchProfile(username)
                .onSuccess {
                    _state.value = ProfileGitHubState(
                        profileGitHub = it,
                        isLoading = false,
                        error = ""
                    )
                }
        }
    }

}