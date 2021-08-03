//
//  DetailsViewModel.swift
//  iosApp
//
//  Created by Randheer Singh on 2/8/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import MultiPlatformLibrary

class DetailsViewModel: ObservableObject {
    
    let repository = RepositoryProviderKt.getCovidRepository()
    var stateCode = "BR"
    
    @Published var items = [CovidDistrictUiModel]()
    @Published var isLoading = false
    
    func loadData() {
        self.isLoading = true
        repository.getDistricts(stateCode: self.stateCode, completionHandler: {
            (items: [CovidDistrictStats]?, e: Error?) in
            if e == nil {
                let data = items ?? []
                self.items = data.map {
                    CovidDistrictUiModel.fromCovidDistrictUiModel(input: $0)
                }
                self.isLoading = false
            }
        })
    }

    func onSearch(query: String) {
        self.isLoading = true
        repository.searchDistrict(stateCode: self.stateCode, query: query,  completionHandler: {
            (items: [CovidDistrictStats]?, e: Error?) in
            if e == nil {
                let data = items ?? []
                self.items = data.map {
                    CovidDistrictUiModel.fromCovidDistrictUiModel(input: $0)
                }
                self.isLoading = false
            }
        })
    }
} 
