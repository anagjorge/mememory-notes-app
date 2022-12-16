package com.projetos.memorynotes.framework.di

import com.projetos.core.repository.NoteRepository
import com.projetos.core.usecases.*
import com.projetos.memorynotes.framework.UseCases
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {
    @Provides
    fun getUseCases(repository: NoteRepository) = UseCases(
        AddNote(repository),
        GetAllNotes(repository),
        GetNote(repository),
        RemoveNote(repository),
        GetWordCount()
    )
}