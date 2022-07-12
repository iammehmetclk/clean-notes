package com.compose.domain.model

data class Note(
    val id: Long,
    val subject: String,
    val content: String,
) {

    override fun toString(): String {
        return "$subject $content"
    }

}