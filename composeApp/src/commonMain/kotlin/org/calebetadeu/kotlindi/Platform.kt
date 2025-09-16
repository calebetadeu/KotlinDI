package org.calebetadeu.kotlindi

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform