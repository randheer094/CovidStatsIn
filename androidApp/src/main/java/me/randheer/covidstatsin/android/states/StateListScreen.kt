package me.randheer.covidstatsin.android.states

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import me.randheer.covidstatsin.android.details.DistrictListScreenProps
import me.randheer.covidstatsin.android.ui.widget.InfoItem
import me.randheer.covidstatsin.android.ui.widget.SearchBox
import me.randheer.covidstatsin.android.util.ColorUtil
import me.randheer.covidstatsin.android.util.Retry
import me.randheer.covidstatsin.domain.model.StateUiModel

@ExperimentalComposeUiApi
@Composable
fun StateListScreen(
    navController: NavController,
    viewModel: StateListViewModel = viewModel()
) {
    val metaData = viewModel.getMetaData()
    val loading by viewModel.loading.observeAsState(false)
    val error by viewModel.error.observeAsState("")
    val states: List<StateUiModel> by viewModel.items.observeAsState(emptyList())
    var query by rememberSaveable { mutableStateOf("") }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = metaData.title)
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
                        SearchBox(metaData.searchPlaceholder, query) {
                            query = it
                            viewModel.getStates(it)
                        }
                        StateList(navController = navController, states = states)
                    }
                }
                if (error.isNotEmpty()) {
                    Snackbar(
                        action = {
                            Button(onClick = { viewModel.getStates(query) }) {
                                Text(Retry)
                            }
                        },
                        modifier = Modifier.padding(8.dp)
                    ) { Text(text = error) }
                }
            }
        })
}

@Composable
fun StateList(
    navController: NavController,
    states: List<StateUiModel>
) {
    LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
        items(states.size, key = { i -> states[i].code }) { idx ->
            StateItem(navController = navController, item = states[idx])
        }
    }
}

@Composable
fun StateItem(
    navController: NavController,
    item: StateUiModel
) {
    Box(modifier = Modifier.padding(bottom = 8.dp, top = 2.dp)) {
        Card(
            shape = RoundedCornerShape(8.dp),
            backgroundColor = ColorUtil.getColor(item.cardBgColor),
            modifier = Modifier.clickable(enabled = item.clickable) {
                navController.navigate(DistrictListScreenProps.getPath(item.code))
            },
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
                Text(text = item.updatedAt, style = MaterialTheme.typography.caption)
            }
        }
    }
}
