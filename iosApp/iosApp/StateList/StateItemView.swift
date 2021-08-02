//
//  StateItemView.swift
//  iosApp
//
//  Created by Randheer Singh on 3/8/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct StateItemView: View {
    
    var items: [CovidStateUiModel] = []
        
    var body: some View {
        List {
            ForEach(items) { item in
                VStack(alignment: .leading, spacing: 12, content: {
                    VStack(alignment: .leading) {
                        Text(item.code).font(.footnote).fontWeight(.semibold)
                    }
                })
            }
        }
    }
}

struct StateItemView_Previews: PreviewProvider {
    static var previews: some View {
        StateListScreen()
    }
}
