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
    
    @Published var items = [CovidDistrictUiModel]()
    @Published var isLoading = false
    
    func loadData(stateCode: String) {
        self.isLoading = true
        repository.getDistricts(stateCode: stateCode, completionHandler: {
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

    func onSearch(stateCode: String, query: String) {
        self.isLoading = true
        repository.searchDistrict(stateCode: stateCode, query: query,  completionHandler: {
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
