//
//  DistrictListScreen.swift
//  iosApp
//
//  Created by Randheer Singh on 3/8/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

import Foundation
import SwiftUI

struct DistrictListScreen: View {
    
    struct Constants {
        static let navigationTitle = "DistrictList List (India)"
    }
    @State var query: String = ""
    
    @ObservedObject var viewModel = DetailsViewModel()
    
    var body: some View {
        NavigationView {
            ZStack {
                if (!viewModel.isLoading) {
                    VStack {
                        SearchBar(text: self.$query, onSearch: {
                            viewModel.onSearch(query: query)
                        })
                        DistrictListView(items: viewModel.items)
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

struct DistrictListScreen_Previews: PreviewProvider {
    static var previews: some View {
        DistrictListScreen()
    }
}
