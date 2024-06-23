package com.example.themealapp.feelingLucky

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.example.themealapp.ui.theme.MyCenteredTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreSuchArea(area: String, viewModel:FeelingLuckyViewModel, navcon: NavHostController) {
    val context = LocalContext.current
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val feelingLuckyState by viewModel.feelingLuckyAreaState

    LaunchedEffect(area) {viewModel.fetchLuckyAreaState(area)}

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MyCenteredTopBar(
                header = {
                    Text(text = "More $area Meals", fontWeight = FontWeight.Bold)
                },
                scrollBehavior = scrollBehavior,
                navIcon = {
                    IconButton(onClick = { navcon.navigateUp() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                actionIcon = {}
            )
        }
    ) { paddingVal->
        when{
            feelingLuckyState.loading->{
                Text(
                    text = "Loading",
                    modifier = Modifier
                        .padding(paddingVal)
                        .fillMaxSize()
                )
            }
            feelingLuckyState.error != null->{
                Text(
                    text = "Error Occured: ${feelingLuckyState.error}",
                    modifier = Modifier
                        .padding(paddingVal)
                        .fillMaxSize()
                )
            }
            else->{
                LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(2), modifier = Modifier.padding(paddingVal)){
                    viewModel.feelingLuckyAreaState.value.list?.forEach { fm->
                        item {
                            RandomMealCategoryCard(fm = fm, context = context, navcon = navcon)
                        }
                    }
                }
            }
        }
    }
}