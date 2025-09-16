package org.calebetadeu.kotlindi.profile.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.calebetadeu.kotlindi.profile.presentation.ui.ProfileGitHubAction
import org.calebetadeu.kotlindi.profile.presentation.ui.ProfileGitHubState
import org.calebetadeu.kotlindi.profile.presentation.viewModels.ProfileGitHubViewModel

@Composable
fun ProfileScreenRoot(
    viewModel: ProfileGitHubViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ProfileScreen(
        state = state,
        onAction = { action ->
            viewModel.onAction(action)
        }
    )


}

@Composable
private fun ProfileScreen(
    state: ProfileGitHubState,
    onAction: (ProfileGitHubAction) -> Unit,
) {
    val input = rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = input.value,
            onValueChange = { input.value = it },
            label = {
                Text("Nome de usuário do GitHub")
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardActions = KeyboardActions(
                onDone = {
                    val username = input.value.trim()
                    if (username.isNotEmpty()) {
                        onAction(ProfileGitHubAction.FetchProfileGitHub(username))
                    }

                }
            )
        )
        Button(
            onClick = {
                val username = input.value.trim()
                if (username.isNotEmpty()) {
                    onAction(ProfileGitHubAction.FetchProfileGitHub(username))
                }
            },
            enabled = input.value.isNotBlank() && !state.isLoading
        ) {
            Text("Buscar")
        }

        if (state.isLoading) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                CircularProgressIndicator()
            }
        }

        state.error?.let { message ->
            Text(
                text = message,
                color = MaterialTheme.colorScheme.error
            )
        }

        state.profileGitHub?.let { profile ->
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = profile.name ?: profile.username,
                        style = MaterialTheme.typography.titleLarge
                    )
                    if (profile.name != null) {
                        Text(
                            text = "@" + profile.username,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Row(
                        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text("Repos: ${profile.publicRepos}")
                        Text("Followers: ${profile.followers}")
                        Text("Following: ${profile.following}")
                    }
                    profile.location?.let { Text("Local: $it") }
                    profile.blog?.let { Text("Site: $it") }
                    profile.twitterUsername?.let { Text("Twitter: @$it") }
                }
            }
        }
    }
}