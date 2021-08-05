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
    
    @State var query: String = ""
    var stateCode: String
    
    @ObservedObject var viewModel = DetailsViewModel()
    
    var body: some View {
        NavigationView {
            ZStack { 
                if (!viewModel.isLoading) {
                    VStack {
                        SearchBar(
                            text: self.$query,
                            placeholder: viewModel.metaData.searchPlaceholder,
                            onSearch: { (query: String) in
                                viewModel.getDistricts(stateCode: self.stateCode,query: query)
                            }
                        ).padding(.top, 12)
                        DistrictListView(items: viewModel.items)
                    }
                    .navigationBarTitle(Text(String(format: viewModel.metaData.title, self.stateCode)))
                } else {
                    ProgressView()
                        .progressViewStyle(CircularProgressViewStyle(tint: .gray))
                        .scaleEffect(1.5)
                }
            }
            .navigationBarTitleDisplayMode(.inline)
            .onAppear(perform: {
                viewModel.loadMetaData(stateCode: self.stateCode)
                viewModel.getDistricts(stateCode: self.stateCode, query: "")
            })
            .hideKeyboardWhenTappedAround()
        }.navigationViewStyle(StackNavigationViewStyle())
    }
}
