package com.mod.notify.data.local

import android.content.Context
import com.mod.notify.db.NoteDB
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(NoteDB.Schema, context, "note.db")
    }
}