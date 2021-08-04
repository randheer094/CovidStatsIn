//
//  StateListScreen.swift
//
//  Created by Randheer Singh on 1/8/21.
//

import Foundation
import SwiftUI

struct StateListScreen: View {
    
    struct Constants {
        static let navigationTitle = "State/UT List (India)"
        static let searchPlaceHolder = "Search by state name or code"
    }
    @State var query: String = ""
    
    @ObservedObject var viewModel = StateViewModel()
    
    var body: some View {
        NavigationView {
            ZStack {
                if (!viewModel.isLoading) {
                    VStack {
                        SearchBar(
                            text: self.$query,
                            placeholder: Constants.searchPlaceHolder,
                            onSearch: { (query: String) in
                                viewModel.onSearch(query: query)
                            }
                        ).padding(.top, 12)
                        StateListView(items: viewModel.items)
                    }
                    .navigationBarTitle(Text(Constants.navigationTitle))
                } else {
                    ProgressView()
                        .progressViewStyle(CircularProgressViewStyle(tint: .gray))
                        .scaleEffect(1.5)
                }
            }
            .navigationBarTitleDisplayMode(.inline)
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
