//
//  NoteItem.swift
//  iosApp
//
//  Created by Mod on 09/10/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct NoteItem: View {
    var note: Note
    var onDeleteClick: () -> Void
    
    var body: some View {
        VStack(alignment: .leading) {
            HStack {
                Text(note.title)
                    .font(.title3)
                    .fontWeight(.semibold)
                Spacer()
                Button(action: onDeleteClick) {
                    Image(systemName: "xmark").foregroundColor(.black)
                }
            }.padding(.bottom, 3)
            
            Text(note.content)
                .fontWeight(.light)
                .padding(.bottom, 3)
            
            HStack {
                Spacer()
                Text(DateTimeUtils().formatNoteDate(dateTime: note.createdAt))
                    .font(.footnote)
                    .fontWeight(.light)
            }
        }
        .padding()
        .background(Color(hex: note.colorHex))
        .clipShape(RoundedRectangle(cornerRadius: 5.0))
    }
}

struct NoteItem_Previews: PreviewProvider {
    static var previews: some View {
        NoteItem(
            note: Note(
                id: nil, title: "Title", content: "Content", colorHex: 0xFF12ed, createdAt: DateTimeUtils().now()
            ),
            onDeleteClick: {}
        )
    }
}
