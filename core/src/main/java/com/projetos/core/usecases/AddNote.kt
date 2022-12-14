package com.projetos.core.usecases

import com.projetos.core.data.Note
import com.projetos.core.repository.NoteRepository

class AddNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(note: Note) = noteRepository.addNote(note)
}