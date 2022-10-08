package com.mod.notify.android.note_details

data class NoteDetailsState(
    val noteTitle: String = "",
    val isNoteTitleHintIsVisible: Boolean = false,
    val noteContent: String = "",
    val isNoteContentHintIsVisible: Boolean = false,
    val noteColor: Long = 0xffffff
)
