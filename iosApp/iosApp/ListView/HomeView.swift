//
//  HomeView.swift
//  DeliveryHeroFTW
//
//  Created by Randheer Singh on 1/8/21.
//

import Foundation
import SwiftUI

struct HomeView: View {
    
    struct Constants {
        static let refreshTitle = "Refresh"
        static let navigationTitle = "Home"
    }
    
    @ObservedObject var viewModel = ViewModel()
    @State private var searchText : String = ""
    
    var body: some View {
        NavigationView {
            ZStack {
                if (!viewModel.isLoading) {
                    VStack {
                        SearchBar(text: $searchText)
                        List {
                            ForEach(viewModel.filterResults(searchText: self.searchText)) { item in
                                VStack(alignment: .leading, spacing: 12, content: {
                                    VStack(alignment: .leading) {
                                        Text(item.code).font(.footnote).fontWeight(.semibold)
                                    }
                                })
                            }
                        }
                    }
                } else {
                    ProgressView()
                        .progressViewStyle(CircularProgressViewStyle(tint: .gray))
                        .scaleEffect(1.5)
                }
            }
            .navigationBarTitle(Text(Constants.navigationTitle))
            .navigationBarItems(trailing: Button(action: {
                viewModel.fetchAPI()
                self.searchText = ""
            }, label: {
                Text(Constants.refreshTitle)
            }))
            .onAppear(perform: {
                viewModel.fetchAPI()
            })
            .hideKeyboardWhenTappedAround()
        }.navigationViewStyle(StackNavigationViewStyle())
    }
}

struct HomeView_Previews: PreviewProvider {
    static var previews: some View {
        HomeView()
    }
}
