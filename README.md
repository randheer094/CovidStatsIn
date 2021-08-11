# CovidStatsIn
Android and iOS application having shared code for API, data, and, business logic using Kotlin Multiplatform Mobile. API is provided by [COVID19-India API](https://api.covid19india.org/).

---

#### How much code can be shared?
CovidStatsIn has platform specific ViewModel and UI, everything remains is shared. An ideal target can be sharing everything except UI.

##### Shared Code ([MultiplatformLibrary](/MultiPlatformLibrary))
- API client ([Ktor](https://ktor.io/docs/http-client-multiplatform.html))
- Database ([SQLDelight](https://github.com/cashapp/sqldelight))
- Repository and Usecases

##### Platform specific code ([Android](/androidApp), [iOS](/iosApp))
- ViewModel
- UI (Jetpack Compose and SwiftUI)

---

#### Screenshots

| Platform | Screen 1 | Screen 2 | Screen 3 | Screen 4 |
| :---     |  :----:  |  :----:  |  :----:  |  :----:  |
| Android  |![AND1](/assets/screens/And1.png)|![AND2](/assets/screens/And2.png)|![AND3](/assets/screens/And3.png)|![AND4](/assets/screens/And4.png)
| iOS      |![iOS1](/assets/screens/iOS1.png)|![iOS2](/assets/screens/iOS2.png)|![iOS3](/assets/screens/iOS3.png)|![iOS4](/assets/screens/iOS4.png)


---

#### Architecture
![Architech](/assets/images/arch.jpg)

---

#### Whats Next
- Sharing ViewModel. One way to share ViewModel is using [D-KMP](https://github.com/dbaroncelli/D-KMP-sample)
- Shared Error Handling Mechanism
- Adding Unit Test Cases

---

#### Contributions
All the contributions are welcomed. 
