//
//  CovidStateUiModel.swift
//
//  Created by Randheer Singh on 31/7/21.
//

import Foundation
import MultiPlatformLibrary

struct CovidStateUiModel: Codable, Identifiable {
    
    let id = UUID()
    let code: String
    let name: String
    let confirmed: String
    let deceased: String
    let recovered: String
    let tested: String
    let vaccinated1: String
    let vaccinated2: String
    let population: String
    let updatedAt: String
    
    enum CodingKeys: String, CodingKey {
        case code = "code"
        case name = "name"
        case confirmed = "confirmed"
        case deceased = "deceased"
        case recovered = "recovered"
        case tested = "tested"
        case vaccinated1 = "vaccinated1"
        case vaccinated2 = "vaccinated2"
        case population = "population"
        case updatedAt = "updatedAt"
    }
}

extension CovidStateUiModel {
    static func fromCovidStateStats(input: CovidStateStats) -> CovidStateUiModel {
        let notAvailable = "Not Available"
        var updated = input.updatedAt
        if updated.isEmpty {
            updated = notAvailable
        } else {
            updated = "Last updated at: " + input.updatedAt
        }
        return CovidStateUiModel(
            code: input.code,
            name: input.name,
            confirmed: input.confirmed?.stringValue ?? notAvailable,
            deceased: input.deceased?.stringValue ?? notAvailable,
            recovered: input.recovered?.stringValue ?? notAvailable,
            tested: input.tested?.stringValue ?? notAvailable,
            vaccinated1: input.vaccinated1?.stringValue ?? notAvailable,
            vaccinated2: input.vaccinated2?.stringValue ?? notAvailable,
            population: input.population?.stringValue ?? notAvailable,
            updatedAt: updated
        )
    }
}

