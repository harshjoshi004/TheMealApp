package com.example.themealapp.listAllCategories

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themealapp.feelingLucky.RandomMeal
import com.example.themealapp.filteredMeals.FilteredMeal
import com.example.themealapp.myApiService
import com.example.themealapp.searchMealByName.SearchedMeal
import kotlinx.coroutines.launch

class CategoriesViewModel:ViewModel() {
    private val _categoryState = mutableStateOf(CategoriesState())
    val searchMealState: State<CategoriesState> = _categoryState

    var bottomSheetOpen = mutableStateOf(false)

    var bottomSheetCategory = mutableStateOf<Category?>(null)
    data class CategoriesState(
        var loading: Boolean = true,
        var list: List<Category>? = null,
        var error: String?=null
    )

    init {
        fetchCategories("")
    }

    fun fetchCategories(s:String) {
        viewModelScope.launch {
            try {
                //fetch code
                val response = myApiService.getCategories()
                _categoryState.value = _categoryState.value.copy(
                    loading = false,
                    list = response.categories,
                    error = null
                )

            } catch (e: Exception) {
                _categoryState.value = _categoryState.value.copy(
                    loading = false,
                    error = "error in fetch fun: ${e.message}"
                )
            }
        }
    }
}