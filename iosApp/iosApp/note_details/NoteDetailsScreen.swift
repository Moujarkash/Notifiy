//
//  NoteDetailsScreen.swift
//  iosApp
//
//  Created by Mod on 09/10/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct NoteDetailsScreen: View {
    private var noteDataSource: NoteDataSource?
    private var noteId: Int64? = nil
    
    init(noteDataSource: NoteDataSource? = nil, noteId: Int64? = nil) {
        self.noteDataSource = noteDataSource
        self.noteId = noteId
    }
    
    var body: some View {
        Text(/*@START_MENU_TOKEN@*/"Hello, World!"/*@END_MENU_TOKEN@*/)
    }
}

struct NoteDetailsScreen_Previews: PreviewProvider {
    static var previews: some View {
        NoteDetailsScreen()
    }
}
