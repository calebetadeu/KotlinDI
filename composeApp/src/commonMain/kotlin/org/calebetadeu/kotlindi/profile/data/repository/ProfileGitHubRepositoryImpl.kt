package org.calebetadeu.kotlindi.profile.data.repository

import org.calebetadeu.kotlindi.core.domain.networking.NetworkError
import org.calebetadeu.kotlindi.core.domain.networking.Result
import org.calebetadeu.kotlindi.core.domain.networking.map
import org.calebetadeu.kotlindi.profile.data.datasource.remote.ProfileGItHubApiImpl
import org.calebetadeu.kotlindi.profile.data.mappers.toProfileGitHub
import org.calebetadeu.kotlindi.profile.domain.models.ProfileGitHub
import org.calebetadeu.kotlindi.profile.domain.repository.ProfileGitHubRepository

class ProfileGitHubRepositoryImpl(
    private val api: ProfileGItHubApiImpl
    //private val userDao : UserDado
) : ProfileGitHubRepository {
    override suspend fun fetchProfile(username: String): Result<ProfileGitHub, NetworkError> {

        //userDao.getUser(username)?.let {
        return api.getProfile(
            username =username
        ).map {
            it.toProfileGitHub()
        }
    }

}


/// Verficar se ja tem dados locais antes de chamar a api

//fun getPRofile(){