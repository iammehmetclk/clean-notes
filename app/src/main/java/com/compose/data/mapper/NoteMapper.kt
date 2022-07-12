package com.compose.data.mapper

import com.compose.data.cache.model.NoteEntity
import com.compose.domain.model.Note

fun List<NoteEntity>.toNoteList(): List<Note> {
    val list = ArrayList<Note>()
    for (item in this) {
        list.add(item.toNote())
    }
    return list.toList()
}

fun NoteEntity.toNote(): Note {
    return Note(
        id = this.id,
        subject = this.subject,
        content = this.content,
    )
}

fun Note.toNoteEntity(): NoteEntity {
    return NoteEntity(
        id = this.id,
        subject = this.subject,
        content = this.content,
    )
}