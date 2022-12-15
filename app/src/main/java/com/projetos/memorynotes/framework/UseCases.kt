package com.projetos.memorynotes.framework

import com.projetos.core.usecases.AddNote
import com.projetos.core.usecases.GetAllNotes
import com.projetos.core.usecases.GetNote
import com.projetos.core.usecases.RemoveNote

data class UseCases(
    val addNote: AddNote,
    val getAllNotes: GetAllNotes,
    val getNote: GetNote,
    val removeNote: RemoveNote
)
