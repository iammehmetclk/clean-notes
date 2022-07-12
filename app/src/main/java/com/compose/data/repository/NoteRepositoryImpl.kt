package com.compose.data.repository

import com.compose.data.cache.dao.NoteDao
import com.compose.data.cache.model.NoteEntity
import com.compose.data.mapper.toNoteEntity
import com.compose.data.mapper.toNoteList
import com.compose.domain.model.Note
import com.compose.domain.repository.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(private val noteDao: NoteDao) : NoteRepository {

    override suspend fun getNotes(): List<Note> {
        return noteDao.getNotes().toNoteList()
    }

    override suspend fun insertNote(note: Note) {
        noteDao.insertNote(note.toNoteEntity())
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note.toNoteEntity())
    }

}