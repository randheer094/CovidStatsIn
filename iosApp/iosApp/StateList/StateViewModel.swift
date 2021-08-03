//
//  StateViewModel.swift
//
//  Created by Randheer Singh on 31/7/21.
//

import Foundation
import MultiPlatformLibrary

class StateViewModel: ObservableObject {
    
    let repository = RepositoryProviderKt.getCovidRepository()
    @Published var items = [CovidStateUiModel]()
    @Published var isLoading = false
    
    func loadData() {
        self.isLoading = true
        repository.getStates { (items: [CovidStateStats]?, e: Error?) in
            if e == nil {
                let data = items ?? []
                self.items = data.map {
                    CovidStateUiModel.fromCovidStateStats(input: $0)
                } 
                self.isLoading = false
            }
        }
    }

    func onSearch(query: String) {
        self.isLoading = true
        repository.searchStates(query: query, completionHandler: { (items: [CovidStateStats]?, e: Error?) in
            if e == nil {
                let data = items ?? []
                self.items = data.map {
                    CovidStateUiModel.fromCovidStateStats(input: $0)
                }
                self.isLoading = false
            }
        })
    }
}
