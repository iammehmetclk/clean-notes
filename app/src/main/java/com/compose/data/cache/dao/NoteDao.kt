package com.compose.data.cache.dao

import androidx.room.*
import com.compose.data.cache.model.NoteEntity

@Dao
interface NoteDao {

    @Query("SELECT * FROM note_table")
    fun getNotes() : List<NoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: NoteEntity)

    @Delete
    fun deleteNote(note: NoteEntity)

}