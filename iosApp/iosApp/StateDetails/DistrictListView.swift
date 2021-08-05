//
//  DistrictListView.swift
//  iosApp
//
//  Created by Randheer Singh on 3/8/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import MultiPlatformLibrary

struct DistrictListView: View {
    
    var items: [DistrictUiModel] = []
        
    var body: some View {
        ScrollView {
            LazyVStack {
                ForEach(items, id: \.name) { item in
                    DistrictItemView(item: item)
                        .padding(.top, 8)
                        .padding(.leading, 12)
                        .padding(.trailing, 12)
                }
            }
        }
    }
}
