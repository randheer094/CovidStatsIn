//
//  StateItemView.swift
//  iosApp
//
//  Created by Randheer Singh on 3/8/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct StateItemView: View {
    
    var item: CovidStateUiModel
    
    var body: some View {
        ZStack {
            RoundedRectangle(cornerRadius: 8, style: .continuous)
                .fill(getCardBgColor(code: item.code))
                .shadow(radius: 2)
            VStack(alignment: .leading) {
                HStack {
                    Text(item.name).font(.title3).bold().foregroundColor(Color.black)
                    Spacer()
                }
                Divider()
                HStack {
                    InfoView(
                        title: "Confirmed",
                        info: item.confirmed,
                        infoColor: Color(red: 204/255, green: 68/255, blue: 68/255, opacity: 1))
                    InfoView(
                        title: "Deceased",
                        info: item.confirmed,
                        infoColor: Color.red)
                }
                HStack {
                    InfoView(
                        title: "Recovered",
                        info: item.recovered,
                        infoColor: Color(red: 153/255, green: 204/255, blue: 0/255, opacity: 1))
                    InfoView(
                        title: "Tested",
                        info: item.tested,
                        infoColor: Color.black)
                }
                HStack {
                    InfoView(
                        title: "Partially Vaccinated",
                        info: item.confirmed,
                        infoColor: Color(red: 153/255, green: 204/255, blue: 0/255, opacity: 1))
                    InfoView(
                        title: "Fully Vaccinated",
                        info: item.vaccinated2,
                        infoColor: Color.green)
                }
                HStack {
                    Text(item.updatedAt).font(.footnote).foregroundColor(Color.black)
                    Spacer()
                }.padding(.top, 8)
            }
            .padding(12)
        }
    }
    
    func getCardBgColor(code: String) -> Color {
        if code == "TT" {
            return Color(red: 204/255, green: 204/255, blue: 204/255, opacity: 1)
        } else {
            return Color.white
        }
    }
}

