package me.randheer.covidstatsin.android.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import me.randheer.covidstatsin.android.ui.widget.InfoItem
import me.randheer.covidstatsin.android.ui.widget.SearchBox
import me.randheer.covidstatsin.domain.model.DistrictUiModel

@ExperimentalComposeUiApi
@Composable
fun DistrictListScreen(
    navController: NavController,
    stateCode: String,
    viewModel: DistrictListViewModel = viewModel(factory = DistrictListViewModelFactory(stateCode))
) {
    val metaData = viewModel.getMetaData()
    val loading by viewModel.loading.observeAsState(true)
    val districts: List<DistrictUiModel> by viewModel.items.observeAsState(emptyList())
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = metaData.title)
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Go Back")
                    }
                }
            )
        }, content = {
            Box(modifier = Modifier.fillMaxSize()) {
                if (loading) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator(
                            Modifier
                                .width(56.dp)
                                .height(56.dp)
                        )
                    }
                } else {
                    Column {
                        SearchBox(metaData.searchPlaceholder) { viewModel.getDistricts(it) }
                        DistrictList(districts = districts)
                    }
                }
            }
        })
}

@Composable
fun DistrictList(
    districts: List<DistrictUiModel>
) {
    LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
        items(districts.size, key = { i -> districts[i].name }) { idx ->
            DistrictItem(item = districts[idx])
        }
    }
}

@Composable
fun DistrictItem(
    item: DistrictUiModel
) {
    Box(modifier = Modifier.padding(bottom = 8.dp, top = 2.dp)) {
        Card(
            shape = RoundedCornerShape(8.dp),
            backgroundColor = MaterialTheme.colors.surface,
            elevation = 4.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Text(text = item.name, style = MaterialTheme.typography.h2)
                Spacer(modifier = Modifier.height(8.dp))
                Divider()
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.padding(bottom = 8.dp)) {
                    InfoItem(
                        title = item.confirmedTitle,
                        value = item.confirmed,
                        valueColor = Color(204, 68, 68),
                        modifier = Modifier.fillMaxWidth(0.5f)
                    )
                    InfoItem(
                        title = item.deceasedTitle,
                        value = item.deceased,
                        valueColor = Color.Red,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Row(modifier = Modifier.padding(bottom = 8.dp)) {
                    InfoItem(
                        title = item.recoveredTitle,
                        value = item.recovered,
                        valueColor = Color(153, 204, 0),
                        modifier = Modifier.fillMaxWidth(0.5f)
                    )
                    InfoItem(
                        title = item.testedTitle,
                        value = item.tested,
                        valueColor = Color.Black,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Row(modifier = Modifier.padding(bottom = 8.dp)) {
                    InfoItem(
                        title = item.vaccinated1Title,
                        value = item.vaccinated1,
                        valueColor = Color(153, 204, 0),
                        modifier = Modifier.fillMaxWidth(0.5f)
                    )
                    InfoItem(
                        title = item.vaccinated2Title,
                        value = item.vaccinated2,
                        valueColor = Color.Green,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}
