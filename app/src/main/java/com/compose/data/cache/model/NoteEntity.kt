package com.compose.data.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "note_table")
data class NoteEntity(
    @PrimaryKey
    val id: Long,
    val subject: String,
    val content: String,
) : Serializable