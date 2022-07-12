package com.compose.domain.use_case

import com.compose.domain.model.Note
import com.compose.domain.model.Resource
import com.compose.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(private val repository: NoteRepository) {

    suspend operator fun invoke(): Flow<Resource<List<Note>>> {
        return flow<Resource<List<Note>>> {
            emit(Resource.Loading(null, "Please wait while the notes are loaded."))
            try {
                val notes = repository.getNotes()
                emit(Resource.Success(notes, "Notes loaded successfully."))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(null, "Something went wrong!"))
            }
        }
    }

}