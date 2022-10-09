//
//  NoteDetailsViewModel.swift
//  iosApp
//
//  Created by Mod on 09/10/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

extension NoteDetailsScreen {
    @MainActor class NoteDetailsViewModel: ObservableObject {
        private var noteDataSource: NoteDataSource?
        private var noteId: Int64? = nil
        
        @Published var noteTtitle = ""
        @Published var noteContent = ""
        @Published private(set) var noteColor = Note.Companion().generateRandomColor()
        
        init(noteDataSource: NoteDataSource? = nil) {
            self.noteDataSource = noteDataSource
        }
        
        func loadNoteIfExist(id: Int64?) {
            if id != nil {
                self.noteId = id
                noteDataSource?.getNoteById(id: id!, completionHandler: { note, error in
                    self.noteTtitle = note?.title ?? ""
                    self.noteContent = note?.content ?? ""
                    self.noteColor = note?.colorHex ?? Note.Companion().generateRandomColor()
                })
            }
        }
        
        func saveNote(onSave: @escaping () -> Void) {
            noteDataSource?.insertNote(note: Note(
                id: noteId == nil ? nil : KotlinLong(value: noteId!), title: noteTtitle, content: noteContent, colorHex: noteColor, createdAt: DateTimeUtils().now()
            ), completionHandler: { error in
                onSave()
            })
        }
        
        func setParamsAndLoadNote(noteDataSource: NoteDataSource, noteId: Int64?) {
            self.noteDataSource = noteDataSource
            loadNoteIfExist(id: noteId)
        }
    }
}
