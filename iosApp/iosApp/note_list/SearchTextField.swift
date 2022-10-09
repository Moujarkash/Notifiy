//
//  SearchTextField.swift
//  iosApp
//
//  Created by Mod on 09/10/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct SearchTextField<Destination: View>: View {
    
    var onSearchToggled: () -> Void
    var destinationProvider: () -> Destination
    var isSearchActive: Bool
    @Binding var searchText: String
    
    var body: some View {
        HStack {
            TextField("Search...", text: $searchText)
                .textFieldStyle(.roundedBorder)
                .opacity(isSearchActive ? 1 : 0)
            if (!isSearchActive) {
                Spacer()
            }
            Button(action: onSearchToggled) {
                Image(systemName: isSearchActive ? "xmark" : "magnifyingglass")
                    .foregroundColor(.black)
            }
            NavigationLink(destination: destinationProvider()) {
                Image(systemName: "plus")
                    .foregroundColor(.black)
            }
        }
    }
}

struct SearchTextField_Previews: PreviewProvider {
    static var previews: some View {
        SearchTextField(
            onSearchToggled: {},
            destinationProvider: {EmptyView()},
            isSearchActive: true, searchText:
                    .constant("Hello")
        )
    }
}
