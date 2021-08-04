//
//  SearchBar.swift
//
//  Created by Randheer Singh on 31/7/21.
//

import SwiftUI

struct SearchBar: View {
    
    @Binding var text: String
    var placeholder: String
    var onSearch: (String) -> Void
 
    @State private var isEditing = false
  
    var body: some View {
        HStack { 
            TextField(self.placeholder, text: $text.onChange({ (value) in
                onSearch(value)
            }))
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
                    self.onSearch("")
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

extension Binding {
    func onChange(_ handler: @escaping (Value) -> Void) -> Binding<Value> {
        Binding(
            get: { self.wrappedValue },
            set: { newValue in
                self.wrappedValue = newValue
                handler(newValue)
            }
        )
    }
}
