//
//  InfoView.swift
//  iosApp
//
//  Created by Randheer Singh on 3/8/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct InfoView: View {
    var title: String
    var info: String
    var infoColor: Color
    
    var body: some View {
        VStack {
            HStack {
                Text(title)
                    .font(.body)
                    .foregroundColor(Color.black)
                    .fixedSize()
                Spacer( )
            }
            HStack {
                Text(info)
                    .font(.headline)
                    .bold()
                    .foregroundColor(infoColor)
                    .fixedSize()
                Spacer()
            }
        }
        .padding(.top, 8)
        .frame(minWidth: 0, maxWidth: .infinity)
    }
}
