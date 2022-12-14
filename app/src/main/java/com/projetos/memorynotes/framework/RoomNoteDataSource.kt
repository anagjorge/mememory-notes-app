package com.projetos.memorynotes.framework

import android.content.Context
import com.projetos.core.data.Note
import com.projetos.core.repository.NoteDataSource
import com.projetos.memorynotes.framework.db.DatabaseService
import com.projetos.memorynotes.framework.db.NoteEntity

class RoomNoteDataSource(context: Context): NoteDataSource {

    private val noteDao = DatabaseService.getInstance(context).noteDao()

    override suspend fun add(note: Note) = noteDao.addNoteEntity(NoteEntity.fromNote(note))

    override suspend fun get(id: Long) = noteDao.getNoteEntity(id)?.toNote()

    override suspend fun getAll() = noteDao.getAllNoteEntities().map {it.toNote() }

    override suspend fun remove(note: Note) = noteDao.deleteNoteEntity(NoteEntity.fromNote(note))
}