//
//  DataModel.swift
//
//  Created by Randheer Singh on 31/7/21.
//

import Foundation
import MultiPlatformLibrary

struct CovidStateUiModel: Codable, Identifiable {
    
    let id = UUID()
    let code: String
    
    enum CodingKeys: String, CodingKey {
        case code = "code"
    }
}

extension CovidStateUiModel {
    static func fromCovidStateStats(input: CovidStateStats) -> CovidStateUiModel {
        return CovidStateUiModel(code: input.code)
    }
}

