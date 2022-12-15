package com.projetos.memorynotes.framework.di

import android.widget.ListView
import com.projetos.memorynotes.framework.ListViewModel
import com.projetos.memorynotes.framework.NoteViewModel
import dagger.Component

@Component(modules = [ApplicationModule::class, RepositoryModule::class, UseCasesModule::class])
interface ViewModelComponent {

    fun inject(noteViewModel: NoteViewModel)
    fun inject(listViewModel: ListViewModel)
}