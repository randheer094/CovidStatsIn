//
//  HomeView.swift
//
//  Created by Randheer Singh on 1/8/21.
//

import Foundation
import SwiftUI

struct StateListScreen: View {
    
    struct Constants {
        static let navigationTitle = "State/UT List (India)"
    }
    @State var query: String = ""
    
    @ObservedObject var viewModel = StateViewModel()
    
    var body: some View {
        NavigationView {
            ZStack {
                if (!viewModel.isLoading) {
                    VStack {
                        SearchBar(text: self.$query, onSearch: {
                            viewModel.onSearch(query: query)
                        })
                        StateItemView(items: viewModel.items)
                    }
                } else {
                    ProgressView()
                        .progressViewStyle(CircularProgressViewStyle(tint: .gray))
                        .scaleEffect(1.5)
                }
            }
            .navigationBarTitle(Text(Constants.navigationTitle))
            .onAppear(perform: {
                viewModel.loadData()
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
