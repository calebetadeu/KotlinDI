package org.calebetadeu.kotlindi.profile.data.dto

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonObject

object ProfileDtoSerializer : KSerializer<ProfileGitHubDto> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("ProfileGitHubDto") {
        element<String>("login")
        element<String?>("name")
        element<String?>("avatar_url")
        element<String?>("bio")
        element<String?>("location")
        element<String?>("blog")
        element<String?>("twitter_username")
        element<Int>("public_repos")
        element<Int>("followers")
        element<Int>("following")
    }

    override fun serialize(encoder: Encoder, value: ProfileGitHubDto) {
        throw SerializationException("Serialization is not supported for ProfileGitHubDto")
    }

    override fun deserialize(decoder: Decoder): ProfileGitHubDto {
        require(decoder is JsonDecoder)
        val jsonObject = decoder.decodeJsonElement() as JsonObject

        return ProfileGitHubDto(
            username = jsonObject["login"]?.toString()?.replace("\"", "") ?: "",
            name = jsonObject["name"]?.toString()?.replace("\"", ""),
            avatarUrl = jsonObject["avatar_url"]?.toString()?.replace("\"", ""),
            bio = jsonObject["bio"]?.toString()?.replace("\"", ""),
            location = jsonObject["location"]?.toString()?.replace("\"", ""),
            blog = jsonObject["blog"]?.toString()?.replace("\"", ""),
            twitterUsername = jsonObject["twitter_username"]?.toString()?.replace("\"", ""),
            publicRepos = jsonObject["public_repos"]?.toString()?.toIntOrNull() ?: 0,
            followers = jsonObject["followers"]?.toString()?.toIntOrNull() ?: 0,
            following = jsonObject["following"]?.toString()?.toIntOrNull() ?: 0
        )
    }
}