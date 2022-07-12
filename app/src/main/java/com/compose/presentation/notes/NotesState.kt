package com.compose.presentation.notes

import com.compose.domain.model.Note

data class NotesState(
    val notes: List<Note>? = null,
    val loading: Boolean? = null,
    val success: Boolean? = null,
    val error: Boolean? = null,
    val message: String? = null,
)