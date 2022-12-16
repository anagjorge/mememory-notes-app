package com.projetos.memorynotes.framework.di

import com.projetos.core.repository.NoteRepository
import com.projetos.core.usecases.AddNote
import com.projetos.core.usecases.GetAllNotes
import com.projetos.core.usecases.GetNote
import com.projetos.core.usecases.RemoveNote
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
        RemoveNote(repository)
    )
}