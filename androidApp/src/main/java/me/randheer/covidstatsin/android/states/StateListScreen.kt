package me.randheer.covidstatsin.android.states

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import me.randheer.covidstatsin.android.navigation.Screens
import me.randheer.covidstatsin.android.ui.widget.SearchBox
import me.randheer.covidstatsin.android.util.ColorUtil
import me.randheer.covidstatsin.domain.model.StateUiModel

@ExperimentalComposeUiApi
@Composable
fun StateListScreen(
    viewModel: StateListViewModel = viewModel(),
    navController: NavController
) {
    val metaData = viewModel.getMetaData()
    val loading by viewModel.loading.observeAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = metaData.title)
                }
            )
        }, content = {
            if (loading == true) {
                CircularProgressIndicator()
            } else {
                Column {
                    SearchBox(metaData.searchPlaceholder) { viewModel.getStates(it) }
                    StateList(navController = navController, items = viewModel.items)
                }
            }

        })
}

@Composable
fun StateList(
    navController: NavController,
    items: LiveData<List<StateUiModel>>
) {
    val states: List<StateUiModel> by items.observeAsState(emptyList())
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
                navController.navigate(Screens.DistrictList.route)
            },
            elevation = 4.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(text = item.name)
            }
        }
    }
}
