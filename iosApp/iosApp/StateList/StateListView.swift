//
//  StateListView.swift
//  iosApp
//
//  Created by Randheer Singh on 3/8/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct StateListView: View {
    
    var items: [CovidStateUiModel] = []
        
    var body: some View {
        ScrollView {
            LazyVStack {
                ForEach(items) { item in
                    StateItemView(item: item)
                        .padding(.top, 8)
                        .padding(.leading, 12)
                        .padding(.trailing, 12)
                }
            }
        }
    }
}

struct StateListView_Previews: PreviewProvider {
    static var previews: some View {
        StateListScreen()
    }
}
