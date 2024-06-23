package com.example.themealapp.feelingLucky

import android.content.Context
import com.example.themealapp.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.paint
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.themealapp.filteredMeals.FilteredMeal
import com.example.themealapp.ui.theme.MyCenteredTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreSuchCategory(category: String, viewModel:FeelingLuckyViewModel, navcon:NavHostController) {
    val context = LocalContext.current
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val feelingLuckyState by viewModel.feelingLuckyCategoryState

    LaunchedEffect(category) {viewModel.fetchLuckyCategoriesState(category)}

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MyCenteredTopBar(
                header = {
                    Text(text = "More from $category", fontWeight = FontWeight.Bold)
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
            feelingLuckyState.loading ->{
                Text(
                    text = "Loading",
                    modifier = Modifier
                        .padding(paddingVal)
                        .fillMaxSize()
                )
            }
            feelingLuckyState.error != null ->{
                Text(
                    text = "Error Occured: ${feelingLuckyState.error}",
                    modifier = Modifier
                        .padding(paddingVal)
                        .fillMaxSize()
                )
            }
            else ->{
                LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(2), modifier = Modifier.padding(paddingVal)){
                    viewModel.feelingLuckyCategoryState.value.list?.forEach { fm->
                        item {
                            RandomMealCategoryCard(fm = fm, context = context, navcon = navcon)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RandomMealCategoryCard(fm: FilteredMeal,context: Context,navcon: NavHostController) {
    Card(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp, top = 16.dp),
    ) {
        Column(modifier = Modifier.background(MaterialTheme.colorScheme.secondaryContainer)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .paint(
                        painter = rememberImagePainter(fm.strMealThumb),
                        contentScale = ContentScale.FillBounds
                    )
            ){
                ElevatedButton(onClick = { navcon.navigate("meal_info_view/${fm.idMeal}") },modifier = Modifier.padding(4.dp).alpha(0.8f)) {
                    Icon(painterResource(id = R.drawable.baseline_arrow_outward_24), contentDescription = null)
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(start = 4.dp, end = 4.dp)
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = fm.strMeal,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(8.dp))

//                OutlinedButton(
//                    modifier = Modifier//.fillMaxWidth()
//                    ,onClick = { /*TODO*/ }
//                ) {
//                    Text(text = "Explore",textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
//                }
//
//                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}