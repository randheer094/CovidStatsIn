//
//  StateItemView.swift
//  iosApp
//
//  Created by Randheer Singh on 3/8/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import MultiPlatformLibrary

struct StateItemView: View {
    
    var item: StateUiModel
    
    var body: some View {
        NavigationLink(destination: DistrictListScreen(stateCode: item.code)) {
            ZStack {
                RoundedRectangle(cornerRadius: 8, style: .continuous)
                    .fill(Color.init(hex: item.cardBgColor))
                    .shadow(radius: 2)
                VStack(alignment: .leading) {
                    HStack {
                        Text(item.name).font(.title3).bold().foregroundColor(Color.black)
                        Spacer()
                    }
                    Divider()
                    HStack {
                        InfoView(
                            title: item.confirmedTitle,
                            info: item.confirmed,
                            infoColor: Color(red: 204/255, green: 68/255, blue: 68/255, opacity: 1))
                        InfoView(
                            title: item.deceasedTitle,
                            info: item.deceased,
                            infoColor: Color.red)
                    }
                    HStack {
                        InfoView(
                            title: item.recoveredTitle,
                            info: item.recovered,
                            infoColor: Color(red: 153/255, green: 204/255, blue: 0/255, opacity: 1))
                        InfoView(
                            title: item.testedTitle,
                            info: item.tested,
                            infoColor: Color.black)
                    }
                    HStack {
                        InfoView(
                            title: item.vaccinated1Title,
                            info: item.vaccinated1,
                            infoColor: Color(red: 153/255, green: 204/255, blue: 0/255, opacity: 1))
                        InfoView(
                            title: item.vaccinated2Title,
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
        }.disabled(!item.clickable)
    }
}

