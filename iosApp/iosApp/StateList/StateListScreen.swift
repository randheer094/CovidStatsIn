//
//  StateListScreen.swift
//
//  Created by Randheer Singh on 1/8/21.
//

import Foundation
import SwiftUI

struct StateListScreen: View {
    
    @State var query: String = ""
    @State var display: Bool = true
    @State var errorMessage = ""
    
    @ObservedObject var viewModel = StateViewModel()
    
    var body: some View {
        NavigationView {
            ZStack {
                if (!viewModel.isLoading) {
                    VStack {
                        SearchBar(
                            text: self.$query,
                            placeholder: viewModel.metaData.searchPlaceholder,
                            onSearch: { (query: String) in
                                viewModel.getStates(query: query)
                            }
                        ).padding(.top, 12)
                        StateListView(items: viewModel.items)
                    }
                    .navigationBarTitle(Text(viewModel.metaData.title))
                    .alert(isPresented: $display) { () -> Alert in
                        Alert(title: Text("Error!!"), message: Text(errorMessage), dismissButton: .default(Text("Retry"), action: {
                            display = false
                            errorMessage = ""
                            viewModel.getStates(query: query)
                        }))
                    }
                } else {
                    ProgressView()
                        .progressViewStyle(CircularProgressViewStyle(tint: .gray))
                        .scaleEffect(1.5)
                }
            }
            .onReceive(viewModel.errorPublisher, perform: { (error) in
                errorMessage = error
                display = true
            })
            .navigationBarTitleDisplayMode(.inline)
            .onAppear(perform: {
                viewModel.loadMetaData()
                viewModel.getStates(query: "")
            })
            .hideKeyboardWhenTappedAround()
        }.navigationViewStyle(StackNavigationViewStyle())
    }
}

struct StateListScreen_Previews: PreviewProvider {
    static var previews: some View {
        StateListScreen()
    }
}

struct ErrorInfo: Identifiable {
    var id: String { message }
    let message: String
}
