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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import me.randheer.covidstatsin.android.ui.widget.SearchBox
import me.randheer.covidstatsin.domain.model.DistrictUiModel

@ExperimentalComposeUiApi
@Composable
fun DistrictListScreen(
    viewModel: DistrictListViewModel = viewModel(),
    navController: NavController
) {
    val metaData = viewModel.getMetaData()
    val loading by viewModel.loading.observeAsState()
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
            if (loading == true) {
                CircularProgressIndicator()
            } else {
                Column {
                    SearchBox(metaData.searchPlaceholder) { viewModel.getDistricts(it) }
                    DistrictList(items = viewModel.items)
                }
            }
        })
}

@Composable
fun DistrictList(
    items: LiveData<List<DistrictUiModel>>
) {
    val dis: List<DistrictUiModel> by items.observeAsState(emptyList())
    LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
        items(dis.size, key = { i -> dis[i].name }) { idx ->
            DistrictItem(item = dis[idx])
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
                    .padding(8.dp)
            ) {
                Text(text = item.name)
            }
        }
    }
}
