package com.compose.di

import android.content.Context
import com.compose.data.cache.ApplicationDatabase
import com.compose.data.repository.NoteRepositoryImpl
import com.compose.domain.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplicationDatabase(@ApplicationContext context: Context): ApplicationDatabase {
        return ApplicationDatabase.getApplicationDatabase(context)
    }

    @Singleton
    @Provides
    fun provideNoteRepository(applicationDatabase: ApplicationDatabase): NoteRepository {
        return NoteRepositoryImpl(applicationDatabase.noteDao())
    }

}