//
//  ViewModel.swift
//  DeliveryHeroFTW
//
//  Created by Randheer Singh on 31/7/21.
//

import Foundation
import MultiPlatformLibrary

class ViewModel: ObservableObject {
    
    let repository = RepositoryProviderKt.getCovidRepository()
    
    //Variables
    var initialResponse = [CovidStateUiModel]()
    @Published var isLoading = false
    
    //Functions
    func filterResults(searchText: String) -> [CovidStateUiModel] {
        guard !searchText.isEmpty else  { return  initialResponse }
        return initialResponse.filter {
            $0.code.lowercased().contains(searchText.lowercased())
        }
    }
    
    func fetchAPI() {
        self.isLoading = true
        repository.getStates { (items: [CovidStateStats]?, e: Error?) in
            if e == nil {
                let data = items ?? []
                self.initialResponse = data.map {
                    self.fromCovidStateStats(input: $0)
                }
                self.isLoading = false
            }
        }
    }
    
    func fromCovidStateStats(input: CovidStateStats) -> CovidStateUiModel {
        return CovidStateUiModel(code: input.code)
    }
}
