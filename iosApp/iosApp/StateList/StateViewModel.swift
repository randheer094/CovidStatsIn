//
//  StateViewModel.swift
//
//  Created by Randheer Singh on 31/7/21.
//

import Foundation
import MultiPlatformLibrary

class StateViewModel: ObservableObject {  
    
    @Published var metaData = StateListMetaData(title: "", searchPlaceholder: "")
    @Published var items = [StateUiModel]()
    @Published var isLoading = false
    
    func loadMetaData() {
        self.metaData = ProvidersKt.stateMetaDataUseCase.run(input: KotlinUnit())
    }
    
    func getStates(query: String) {
        self.isLoading = query.isEmpty
        let param = GetStateListUseCase.Param(query: query)
        ProvidersKt.stateUseCase.run(input:param) { (items: [StateUiModel]?, e: Error?) in
            if e == nil {
                let data = items ?? []
                self.items = data
            }
            self.isLoading = false
        }
    }
}
