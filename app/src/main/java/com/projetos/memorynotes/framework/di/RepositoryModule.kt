package com.projetos.memorynotes.framework.di

import android.app.Application
import com.projetos.core.repository.NoteRepository
import com.projetos.memorynotes.framework.RoomNoteDataSource
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideRepository(app: Application) = NoteRepository(RoomNoteDataSource(app))
}