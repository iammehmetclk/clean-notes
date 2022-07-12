package com.compose.domain.repository

import com.compose.domain.model.Note

interface NoteRepository {

    suspend fun getNotes(): List<Note>

    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note: Note)

}