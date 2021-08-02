//
//  SearchBar.swift
//
//  Created by Randheer Singh on 31/7/21.
//

import SwiftUI

struct SearchBar: View {
    @Binding var text: String
 
    @State private var isEditing = false
    var onSearch: () -> Void
 
    var body: some View {
        HStack { 
            TextField("Search by state name or code", text: $text, onEditingChanged: { active in
            }, onCommit: {
                self.onSearch()
            })
                .padding(8)
                .background(Color(.systemGray6))
                .cornerRadius(8)
                .padding(.horizontal, 16)
                .onTapGesture {
                    self.isEditing = true
                }
 
            if isEditing {
                Button(action: {
                    self.isEditing = false
                    self.text = ""
                    self.onSearch()
                }) {
                    Text("Cancel")
                }
                .hideKeyboardWhenTappedAround()
                .padding(.trailing, 10)
                .transition(.move(edge: .trailing))
                .animation(.default)
            }
        }
    }
}
