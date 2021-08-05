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
    
    @Published var metaData = DistrictListMetaData(title: "", searchPlaceholder: "")
    @Published var items = [DistrictUiModel]()
    @Published var isLoading = false
    
    func loadMetaData(stateCode: String) {
        let param = DistrictListMetaDataUseCase.Param(stateCode: stateCode)
        self.metaData = ProvidersKt.districtMetaDataUseCase.run(input:param)
    }
    
    func getDistricts(stateCode: String, query: String) {
        self.isLoading = query.isEmpty
        let param = GetDistrictListUseCase.Param(stateCode: stateCode, query: query)
        ProvidersKt.districtUseCase.run(input:param) { (items: [DistrictUiModel]?, e: Error?) in
            if e == nil {
                let data = items ?? []
                self.items = data
            }
            self.isLoading = false
        }
    }
} 
