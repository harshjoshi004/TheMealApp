package com.example.themealapp.feelingLucky

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.themealapp.listAllCategories.CategoriesUI
import com.example.themealapp.listAllCategories.CategoriesViewModel
import com.example.themealapp.searchMealByName.SearchMealUI
import com.example.themealapp.searchMealByName.SearchMealViewModel

@Composable
fun FeelingLuckyUINavigator(){
    val viewModel:FeelingLuckyViewModel = viewModel()
    val catviewModel:CategoriesViewModel = viewModel()
    val searchviewModel:SearchMealViewModel = viewModel()
    val navcon = rememberNavController()

    NavHost(navController = navcon, startDestination = "feeling_lucky_ui"){
        composable("feeling_lucky_ui"){
            FeelingLuckyUI(viewModel = viewModel, navcon = navcon)
        }
        composable(
            route = "more_such_categories/{c}",
            arguments = listOf(
                navArgument("c") { type = NavType.StringType }
            )
        ){
            val c = it.arguments?.getString("c")?:"Beef"
            MoreSuchCategory(category = c, viewModel = viewModel, navcon = navcon)
        }
        composable(
            route = "more_such_area/{a}",
            arguments = listOf(
                navArgument("a") { type = NavType.StringType }
            )
        ){
            val a = it.arguments?.getString("a")?:"Italian"
            MoreSuchArea(area = a, viewModel = viewModel, navcon = navcon)
        }
        composable(route = "meal_info_view/{id}",
            arguments = listOf(
                navArgument("id"){ type = NavType.StringType }
            )
        ){
            val id = it.arguments?.getString("id")?:"52772"
            MealInfoView(i = id, viewModel = viewModel, navcon = navcon)
        }
        composable("categories_ui"){
            CategoriesUI(catViewModel = catviewModel, navcon)
        }
        composable("search_view_model"){
            SearchMealUI(searchviewModel,navcon)
        }
    }
}