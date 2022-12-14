package com.projetos.core.usecases

import com.projetos.core.repository.NoteRepository

class GetAllNotes(private val noteRepository: NoteRepository) {
    suspend fun invoke() = noteRepository.getAllNotes()
}