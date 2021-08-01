//
//  DataModel.swift
//  DeliveryHeroFTW
//
//  Created by Randheer Singh on 31/7/21.
//

import Foundation

struct CovidStateUiModel: Codable, Identifiable {
    
    let id = UUID()
    let code: String
    
    enum CodingKeys: String, CodingKey {
        case code = "code"
    }

    func matches(text: String) -> Bool {
        return self.code.lowercased().contains(text.lowercased())
    }
}

