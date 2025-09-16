package org.calebetadeu.kotlindi.profile.data.datasource.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import org.calebetadeu.kotlindi.core.domain.networking.NetworkError
import org.calebetadeu.kotlindi.core.domain.networking.Result
import org.calebetadeu.kotlindi.profile.data.dto.ProfileGitHubDto

private const val BASE_URL = "https://api.github.com"

class ProfileGItHubApiImpl(
    private val httpClient: HttpClient
) {
    suspend fun getProfile(username: String): Result<ProfileGitHubDto, NetworkError>{
        return try {
            val response: HttpResponse = httpClient.get("$BASE_URL/users/$username")
            if (response.status.value in 200..299) {
                val profileGitHubDto: ProfileGitHubDto = response.body()
                Result.Success(profileGitHubDto)
            } else {
                Result.Error(
                    NetworkError.SERIALIZATION
                    )
            }
        } catch (e: Exception) {
            Result.Error(
                NetworkError.SERVER_ERROR
            )
        }
    }
}