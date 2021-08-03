//
//  CovidDistrictUiModel.swift
//  iosApp
//
//  Created by Randheer Singh on 2/8/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import MultiPlatformLibrary

struct CovidDistrictUiModel: Codable, Identifiable {
    
    let id = UUID()
    let stateCode: String
    let name: String
    let confirmed: String
    let deceased: String
    let recovered: String
    let tested: String
    let vaccinated1: String
    let vaccinated2: String
    let population: String
    
    enum CodingKeys: String, CodingKey {
        case stateCode = "stateCode"
        case name = "name"
        case confirmed = "confirmed"
        case deceased = "deceased"
        case recovered = "recovered"
        case tested = "tested"
        case vaccinated1 = "vaccinated1"
        case vaccinated2 = "vaccinated2"
        case population = "population"
    }
}

extension CovidDistrictUiModel {
    static func fromCovidDistrictUiModel(input: CovidDistrictStats) -> CovidDistrictUiModel {
        let notAvailable = "Not Available"
        return CovidDistrictUiModel(
            stateCode: input.stateCode,
            name: input.name,
            confirmed: input.confirmed?.stringValue ?? notAvailable,
            deceased: input.deceased?.stringValue ?? notAvailable,
            recovered: input.recovered?.stringValue ?? notAvailable,
            tested: input.tested?.stringValue ?? notAvailable,
            vaccinated1: input.vaccinated1?.stringValue ?? notAvailable,
            vaccinated2: input.vaccinated2?.stringValue ?? notAvailable,
            population: input.population?.stringValue ?? notAvailable
        )
    }
}
