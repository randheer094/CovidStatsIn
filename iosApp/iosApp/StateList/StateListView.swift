//
//  StateListView.swift
//  iosApp
//
//  Created by Randheer Singh on 3/8/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import MultiPlatformLibrary

struct StateListView: View {
    
    var items: [StateUiModel] = []
        
    var body: some View {
        ScrollView {
            LazyVStack {
                ForEach(items, id: \.code) { item in
                    StateItemView(item: item)
                        .padding(.top, 8)
                        .padding(.leading, 12)
                        .padding(.trailing, 12)
                }
            }
        }
    }
}
