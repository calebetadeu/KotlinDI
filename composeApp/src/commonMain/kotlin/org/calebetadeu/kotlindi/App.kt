package org.calebetadeu.kotlindi

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview

import org.calebetadeu.kotlindi.navigation.Route
import org.calebetadeu.kotlindi.profile.presentation.ProfileScreenRoot
import org.calebetadeu.kotlindi.profile.presentation.viewModels.ProfileGitHubViewModel
import org.koin.compose.currentKoinScope

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = Route.Profile
        ){
            composable<Route.Profile> {
                val viewModel = ProfileGitHubViewModel(
                    profileGitHubRepository = TODO()
                )
                ProfileScreenRoot(
                    viewModel =viewModel,
                )
            }
        }


    }
}
@Composable
inline fun <reified T: ViewModel> koinViewModel(): T {
    val scope = currentKoinScope()
    return viewModel {
        scope.get<T>()
    }
}
