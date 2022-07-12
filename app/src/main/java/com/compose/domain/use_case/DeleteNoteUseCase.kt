package com.compose.domain.use_case

import com.compose.domain.model.Note
import com.compose.domain.model.Resource
import com.compose.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(private val repository: NoteRepository) {

    suspend operator fun invoke(note: Note): Flow<Resource<Boolean>> {
        return flow<Resource<Boolean>> {
            emit(Resource.Loading(null, "Please wait while note is being deleted."))
            try {
                repository.deleteNote(note)
                emit(Resource.Success(null, "Note deleted successfully."))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(null, "Something went wrong!"))
            }
        }
    }

}