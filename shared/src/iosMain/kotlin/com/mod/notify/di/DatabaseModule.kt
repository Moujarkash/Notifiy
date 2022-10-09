package com.mod.notify.di

import com.mod.notify.data.local.DatabaseDriverFactory
import com.mod.notify.data.note.SqlDelightNoteDataSource
import com.mod.notify.db.NoteDB
import com.mod.notify.domain.note.NoteDataSource

class DatabaseModule {
    private val factory by lazy { DatabaseDriverFactory() }
    val noteDataSource: NoteDataSource by lazy {
        SqlDelightNoteDataSource(NoteDB(factory.createDriver()))
    }
}