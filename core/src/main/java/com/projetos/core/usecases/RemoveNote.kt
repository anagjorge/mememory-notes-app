package com.projetos.core.usecases

import com.projetos.core.data.Note
import com.projetos.core.repository.NoteRepository

class RemoveNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(note: Note) =  noteRepository.removeNote(note)
}