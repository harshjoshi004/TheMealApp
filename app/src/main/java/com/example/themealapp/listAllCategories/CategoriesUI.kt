package com.example.themealapp.listAllCategories

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.themealapp.R
import com.example.themealapp.searchMealByName.SearchMealViewModel
import com.example.themealapp.searchMealByName.SearchedMeal
import com.example.themealapp.searchMealByName.SearchedMealCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesUI(catViewModel:CategoriesViewModel, navcon:NavHostController){
    val bottomSheetState = rememberModalBottomSheetState()

    if(catViewModel.bottomSheetOpen.value)
        ModalBottomSheet(
            onDismissRequest = { catViewModel.bottomSheetOpen.value = false },
            sheetState = bottomSheetState
        ) {
            if(catViewModel.bottomSheetCategory.value!=null){
                val meal = catViewModel.bottomSheetCategory.value!!

                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(bottom = 8.dp, start = 16.dp, end = 16.dp)
                ) {
                    item{
                        Text(
                            text = meal.strCategory,
                            fontSize = 40.sp,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    item {
                        Spacer(modifier = Modifier.size(16.dp))
                    }
                    item {
                        Text(
                            text = "Description: ",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    item {
                        Spacer(modifier = Modifier.size(16.dp))
                    }
                    item {
                        Divider()
                    }
                    item {
                        Spacer(modifier = Modifier.size(16.dp))
                    }
                    item {
                        Text(
                            text = meal.strCategoryDescription,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Light
                        )
                    }
                }

            }
        }

    Scaffold(
        topBar = {
            TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            title = {
                Text(text = "Select Category")
            },
            navigationIcon = {
            },
            actions = {
            },
            scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
        )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(selected = false,

                    onClick = { navcon.navigate("search_view_model") },
                    icon = {
                        Icon(painterResource(id = R.drawable.search_24),
                            contentDescription = null)
                    }
                )
                NavigationBarItem(selected = false,

                    onClick = { navcon.navigate("feeling_lucky_ui") },
                    icon = {
                        Icon(painterResource(id = R.drawable.randomhome),
                            contentDescription = null)
                    }
                )
                NavigationBarItem(selected = true,

                    onClick = { /*TODO*/ },
                    icon = {
                        Icon(painterResource(id = R.drawable.categoryicon),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,)
                    }
                )
            }
        }
    ) {pv->
        when {
            catViewModel.searchMealState.value.loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(pv),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            catViewModel.searchMealState.value.error != null -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(pv),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Oops! Error Occured!\n" +
                                catViewModel.searchMealState.value.error!!,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            catViewModel.searchMealState.value.list == null -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(pv),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No Meals Found!",
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            else -> {
                LazyVerticalStaggeredGrid(
                    modifier = Modifier.padding(pv),
                    columns = StaggeredGridCells.Fixed(2)
                ) {
                    catViewModel.searchMealState.value.list?.forEach { cat ->
                        item {
                            SingleCategoryCard(cat = cat, viewModel = catViewModel)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SingleCategoryCard(cat: Category, viewModel: CategoriesViewModel) {
    Card(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp, top = 16.dp)
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(8.dp)),
    ) {
        Column(modifier = Modifier.background(MaterialTheme.colorScheme.secondaryContainer)) {
            Box(
                modifier = Modifier
                    .paint(
                        painter = rememberImagePainter(cat.strCategoryThumb),
                        contentScale = ContentScale.FillBounds
                    )
                    .fillMaxWidth()
                    .height(120.dp)
            ){
                ElevatedButton(onClick = {
                    //Open Drawer Sheet and Pass Meal into it
                    viewModel.bottomSheetCategory.value = cat
                    viewModel.bottomSheetOpen.value = true
                },modifier = Modifier
                    .padding(4.dp)
                    .alpha(0.8f)) {
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
                    text = cat.strCategory,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}