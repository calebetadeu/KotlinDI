package org.calebetadeu.kotlindi.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object Profile : Route
}