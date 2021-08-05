//
//  StateListScreen.swift
//
//  Created by Randheer Singh on 1/8/21.
//

import Foundation
import SwiftUI

struct StateListScreen: View {
    
    @State var query: String = ""
    
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
                } else {
                    ProgressView()
                        .progressViewStyle(CircularProgressViewStyle(tint: .gray))
                        .scaleEffect(1.5)
                }
            }
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
