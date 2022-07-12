package com.compose.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.domain.model.Note
import com.compose.domain.model.Resource
import com.compose.domain.use_case.DeleteNoteUseCase
import com.compose.domain.use_case.GetNotesUseCase
import com.compose.domain.use_case.InsertNoteUseCase
import com.compose.presentation.notes.NotesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase,
    private val insertNoteUseCase: InsertNoteUseCase,
    private val deleteNotesUseCase: DeleteNoteUseCase,
) : ViewModel() {

    var notesState by mutableStateOf(NotesState())
    var addNoteState by mutableStateOf<String?>(null)
    var subject by mutableStateOf("")
    var content by mutableStateOf("")
    var searchQuery by mutableStateOf("")
    var noteToDelete by mutableStateOf<Note?>(null)

    fun getNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            getNotesUseCase.invoke().collect { resource ->
                when(resource) {
                    is Resource.Loading -> {
                        notesState = NotesState(loading = true)
                        Log.i("my_log", "loading")
                    }
                    is Resource.Success -> {
                        notesState = NotesState(success = true, notes = resource.data ?: listOf())
                        Log.i("my_log", "success")
                    }
                    is Resource.Error -> {
                        notesState = NotesState(error = true, message = resource.message)
                        Log.i("my_log", "error")
                    }
                }
            }
        }
    }

    fun insertNote() {
        viewModelScope.launch(Dispatchers.IO) {
            val note = Note(
                id = System.currentTimeMillis(),
                subject = subject,
                content = content,
            )
            insertNoteUseCase.invoke(note).collect { resource ->
                when(resource) {
                    is Resource.Loading -> {
                        addNoteState = resource.message
                    }
                    is Resource.Success -> {
                        addNoteState = resource.message
                        getNotes()
                    }
                    is Resource.Error -> {
                        addNoteState = resource.message
                    }
                }
            }
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteNotesUseCase.invoke(note).collect { resource ->
                when(resource) {
                    is Resource.Loading -> {

                    }
                    is Resource.Success -> {
                        getNotes()
                    }
                    is Resource.Error -> {

                    }
                }
            }
        }
    }

}