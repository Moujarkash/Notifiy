package com.mod.notify.data.note

import com.mod.notify.db.NoteDB
import com.mod.notify.domain.note.Note
import com.mod.notify.domain.note.NoteDataSource
import com.mod.notify.domain.time.DateTimeUtils

class SqlDelightNoteDataSource(
    db: NoteDB
) : NoteDataSource {
    private val queries = db.noteQueries

    override suspend fun insertNote(note: Note) {
        queries.insertNote(
            id = note.id,
            title = note.title,
            content = note.content,
            colorHex = note.colorHex,
            createdAt = DateTimeUtils.toEpochMillis(note.createdAt)
        )
    }

    override suspend fun getNoteById(id: Long): Note? {
        return queries.getNoteById(id)
            .executeAsOneOrNull()
            ?.toNote()
    }

    override suspend fun getAllNotes(): List<Note> {
        return queries.getAllNotes()
            .executeAsList()
            .map { it.toNote() }
    }

    override suspend fun deleteNoteById(id: Long) {
        queries.deleteNoteById(id)
    }
}