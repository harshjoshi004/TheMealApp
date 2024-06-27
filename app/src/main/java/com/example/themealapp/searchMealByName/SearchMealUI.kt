package com.example.themealapp.searchMealByName


import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.padding

import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold

import androidx.compose.material3.Text

import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.themealapp.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchMealUI(searchMealViewModel:SearchMealViewModel, navcon:NavHostController){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val bottomSheetState = rememberModalBottomSheetState()

    var textFieldState by remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(text = "Search Meals")
                },
                navigationIcon = {
                },
                actions = {
                },
                scrollBehavior = scrollBehavior
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(selected = true,
                    onClick = { /*TODO*/ },
                    icon = {
                        Icon(painterResource(id = R.drawable.search_24),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,)
                    }
                )
                NavigationBarItem(selected = false,

                    onClick = { navcon.navigate("feeling_lucky_ui") },
                    icon = {
                        Icon(painterResource(id = R.drawable.randomhome),
                            contentDescription = null)
                    }
                )
                NavigationBarItem(selected = false,

                    onClick = { navcon.navigate("categories_ui") },
                    icon = {
                        Icon(painterResource(id = R.drawable.categoryicon),
                            contentDescription = null)
                    }
                )
            }
        }
    ) {paddingVals->
    //start
        Column(modifier = Modifier.padding(paddingVals)){
            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = textFieldState,
                onValueChange = { newString -> textFieldState = newString },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.clickable {
                            if(textFieldState.isNotEmpty())
                                searchMealViewModel.fetchSearchedMealState(textFieldState)
                        }
                    )
                },
                label = { Text(
                    text = "Search",
                    color = MaterialTheme.colorScheme.primary
                )},
                placeholder = { Text(
                    text = "What's on your Mind?",
                    color = MaterialTheme.colorScheme.primary
                )},
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                ),
                maxLines = 2
            )
            when{
                searchMealViewModel.searchMealState.value.loading->{
                    Box(modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                searchMealViewModel.searchMealState.value.error != null ->{
                    Box(modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Oops! Error Occured!\n" +
                                searchMealViewModel.searchMealState.value.error!!,
                            color = MaterialTheme.colorScheme.primary)
                    }
                }
                searchMealViewModel.searchMealState.value.list == null ->{
                    Box(modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "No Meals Found!",
                            color = MaterialTheme.colorScheme.primary)
                    }
                }
                else->{
                    LazyVerticalStaggeredGrid(
                        columns = StaggeredGridCells.Fixed(2)
                    ){
                        searchMealViewModel.searchMealState.value.list?.forEach {meal->
                            item {
                                SearchedMealCard(sm = meal, viewModel = searchMealViewModel)
                            }
                        }
                    }
                }
            }
        }

        val context = LocalContext.current
    //end
        if(searchMealViewModel.bottomSheetOpen.value)
            ModalBottomSheet(
                onDismissRequest = { searchMealViewModel.bottomSheetOpen.value = false },
                sheetState = bottomSheetState
            ) {
                if(searchMealViewModel.bottomSheetMeal.value!=null){
                    val meal = searchMealViewModel.bottomSheetMeal.value!!
                    LazyColumn(content = {
                        item {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .padding(bottom = 8.dp, start = 16.dp, end = 16.dp)
                            ) {
                                Text(
                                    text = meal.strMeal,
                                    fontSize = 40.sp,
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.size(16.dp))
                                Row {
                                    Text(
                                        text = "Category: ",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Normal
                                    )
                                    Text(
                                        text = meal.strCategory,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Light
                                    )
                                }
                                Spacer(modifier = Modifier.size(16.dp))
                                Row {
                                    Text(
                                        text = "Area: ",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Normal
                                    )
                                    Text(
                                        text = meal.strArea,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Light
                                    )
                                }
                                Button(modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                    onClick = {
                                        context.startActivity(
                                            Intent(
                                                Intent.ACTION_VIEW,
                                                Uri.parse(meal.strYoutube)
                                            )
                                        )
                                    }
                                ) {
                                    Text(text = "Watch Youtube Tutorial")
                                }
                            }
                        }

                        item{
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .padding(bottom = 8.dp, start = 8.dp, end = 16.dp)
                            ) {
                                Divider()
                                Text(
                                    text = "Instructions: ",
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight.Normal
                                )
                                Spacer(modifier = Modifier.size(8.dp))
                                Text(
                                    text = meal.strInstructions.replace("\n","\n\n"),
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Light
                                )
                            }
                        }

                    })
                }
            }
    }
}

@Composable
fun SearchedMealCard(sm: SearchedMeal, viewModel: SearchMealViewModel) {
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
                        painter = rememberImagePainter(sm.strMealThumb),
                        contentScale = ContentScale.FillBounds
                    )
            ){
                ElevatedButton(onClick = {
                     //Open Drawer Sheet and Pass Meal into it
                    viewModel.bottomSheetMeal.value = sm
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
                    text = sm.strMeal,
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