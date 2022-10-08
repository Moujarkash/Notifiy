package com.mod.notify.android.note_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mod.notify.domain.note.Note
import com.mod.notify.domain.note.NoteDataSource
import com.mod.notify.domain.time.DateTimeUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteDetailsViewModel @Inject constructor(
    private val noteDataSource: NoteDataSource,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val noteTitle = savedStateHandle.getStateFlow("noteTitle", "")
    private val isNoteTitleFocused = savedStateHandle.getStateFlow("isNoteTitleFocused", false)
    private val noteContent = savedStateHandle.getStateFlow("noteContent", "")
    private val isNoteContentFocused = savedStateHandle.getStateFlow("isNoteContentFocused", false)
    private val noteColor = savedStateHandle.getStateFlow("noteColor", Note.generateRandomColor())

    val state = combine(
        noteTitle,
        isNoteTitleFocused,
        noteContent,
        isNoteContentFocused,
        noteColor
    ) { noteTitle, isNoteTitleFocused, noteContent, isNoteContentFocused, noteColor ->
        NoteDetailsState(
            noteTitle = noteTitle,
            noteContent = noteContent,
            isNoteTitleHintIsVisible = noteTitle.isEmpty() && !isNoteTitleFocused,
            isNoteContentHintIsVisible = noteContent.isEmpty() && !isNoteContentFocused,
            noteColor = noteColor
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), NoteDetailsState())

    private val _hasNoteBeenSaved = MutableStateFlow(false)
    val hasNoteBeenSave = _hasNoteBeenSaved.asStateFlow()

    private var existingNoteId: Long? = null

    init {
        savedStateHandle.get<Long>("noteId")?.let { existingNoteId ->
            if (existingNoteId == -1L) {
                return@let
            }

            this.existingNoteId = existingNoteId
            viewModelScope.launch {
                noteDataSource.getNoteById(existingNoteId)?.let { note ->
                    savedStateHandle["noteTitle"] = note.title
                    savedStateHandle["noteContent"] = note.content
                    savedStateHandle["noteColor"] = note.colorHex
                }
            }
        }
    }

    fun onNoteTitleChanged(text: String) {
        savedStateHandle["noteTitle"] = text
    }

    fun onNoteContentChanged(text: String) {
        savedStateHandle["noteContent"] = text
    }

    fun onNoteTitleFocusChanged(isFocus: Boolean) {
        savedStateHandle["isNoteTitleFocused"] = isFocus
    }

    fun onNoteContentFocusChanged(isFocus: Boolean) {
        savedStateHandle["isNoteContentFocused"] = isFocus
    }

    fun saveNote() {
        viewModelScope.launch {
            noteDataSource.insertNote(
                Note(
                    id = existingNoteId,
                    title = noteTitle.value,
                    content = noteContent.value,
                    colorHex = noteColor.value,
                    createdAt = DateTimeUtils.now()
                )
            )
            _hasNoteBeenSaved.value = true
        }
    }
}