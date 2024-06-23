package com.example.themealapp

import com.example.themealapp.feelingLucky.RandomMealResponse
import com.example.themealapp.filteredMeals.FilterMealsResponse
import com.example.themealapp.listAllCategories.CategoriesResponse
import com.example.themealapp.searchMealByName.SearchedMealResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("categories.php")
    suspend fun getCategories(): CategoriesResponse

    @GET("random.php")
    suspend fun getRandomMeal(): RandomMealResponse

    @GET("search.php")
    suspend fun getSearchResponse(@Query("s") s:String): SearchedMealResponse

    @GET("filter.php")
    suspend fun getFilteredMealsByIngredient(@Query("i") i:String): FilterMealsResponse

    @GET("filter.php")
    suspend fun getFilteredMealsByCategory(@Query("c") c:String): FilterMealsResponse

    @GET("filter.php")
    suspend fun getFilteredMealsByArea(@Query("a") a:String): FilterMealsResponse
    //enter area like: Indian, Italian etc

    /*
    * List all Categories, Area, Ingredients
www.themealdb.com/api/json/v1/1/list.php?c=list
www.themealdb.com/api/json/v1/1/list.php?a=list
www.themealdb.com/api/json/v1/1/list.php?i=list
* */

    @GET("list.php")
    suspend fun getAllAreas()
    @GET("list.php")
    suspend fun getAllCategories()
    @GET("list.php")
    suspend fun getAllIngredients()

    //getMealById
    @GET("lookup.php")
    suspend fun getMealById(@Query("i") i:String): RandomMealResponse
}

val baseUrl = "https://www.themealdb.com/api/json/v1/1/"

//Initialise retrofit object here
val retrofit = Retrofit
    .Builder()
    .baseUrl(baseUrl)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

//Object of Type API Service
val myApiService = retrofit.create(APIService::class.java)