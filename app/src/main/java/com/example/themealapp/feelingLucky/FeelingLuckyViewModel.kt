package com.example.themealapp.feelingLucky

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themealapp.filteredMeals.FilteredMeal
import com.example.themealapp.myApiService
import kotlinx.coroutines.launch

class FeelingLuckyViewModel:ViewModel() {

    private val _feelingLuckyState = mutableStateOf(RandomMealState())
    val feelingLuckyState: State<RandomMealState> = _feelingLuckyState

    data class RandomMealState(
        var loading: Boolean = true,
        var list: List<RandomMeal>? = null,
        var error: String?=null
    )

    init {
        fetchLuckyState()
    }

    fun fetchLuckyState() {
        viewModelScope.launch {
            try {
                //fetch code
                val response = myApiService.getRandomMeal()
                _feelingLuckyState.value = _feelingLuckyState.value.copy(
                    loading = false,
                    list = response.meals,
                    error = null
                )

            } catch (e: Exception) {
                _feelingLuckyState.value = _feelingLuckyState.value.copy(
                    loading = false,
                    error = "error in fetch fun: ${e.message}"
                )
            }
        }
    }

    //get similar category meal
    private val _feelingLuckyCategoryState = mutableStateOf(RandomMealCategoryState())
    val feelingLuckyCategoryState: State<RandomMealCategoryState> = _feelingLuckyCategoryState

    data class RandomMealCategoryState(
        var loading: Boolean = true,
        var list: List<FilteredMeal>? = null,
        var error: String?=null
    )

    fun fetchLuckyCategoriesState(c:String) {
        viewModelScope.launch {
            try {
                //fetch code
                val response = myApiService.getFilteredMealsByCategory(c)
                _feelingLuckyCategoryState.value = _feelingLuckyCategoryState.value.copy(
                    loading = false,
                    list = response.meals,
                    error = null
                )

            } catch (e: Exception) {
                _feelingLuckyCategoryState.value = _feelingLuckyCategoryState.value.copy(
                    loading = false,
                    error = "error in fetch fun: ${e.message}"
                )
            }
        }
    }

    //get similar Area meal
    private val _feelingLuckyAreaState = mutableStateOf(RandomMealAreaState())
    val feelingLuckyAreaState: State<RandomMealAreaState> = _feelingLuckyAreaState

    data class RandomMealAreaState(
        var loading: Boolean = true,
        var list: List<FilteredMeal>? = null,
        var error: String?=null
    )

    fun fetchLuckyAreaState(a:String) {
        viewModelScope.launch {
            try {
                //fetch code
                val response = myApiService.getFilteredMealsByArea(a)
                _feelingLuckyAreaState.value = _feelingLuckyAreaState.value.copy(
                    loading = false,
                    list = response.meals,
                    error = null
                )
            } catch (e: Exception) {
                _feelingLuckyAreaState.value = _feelingLuckyAreaState.value.copy(
                    loading = false,
                    error = "error in fetch fun: ${e.message}"
                )
            }
        }
    }

    //get meal by id

    //get similar Area meal
    private val _feelingLuckyidState = mutableStateOf(RandomMealidState())
    val feelingLuckyidState: State<RandomMealidState> = _feelingLuckyidState

    data class RandomMealidState(
        var loading: Boolean = true,
        var list: List<RandomMeal>? = null,
        var error: String?=null
    )

    fun fetchLuckyidState(i:String) {
        viewModelScope.launch {
            try {
                //fetch code
                val response = myApiService.getMealById(i)
                _feelingLuckyidState.value = _feelingLuckyidState.value.copy(
                    loading = false,
                    list = response.meals,
                    error = null
                )
            } catch (e: Exception) {
                _feelingLuckyidState.value = _feelingLuckyidState.value.copy(
                    loading = false,
                    error = "error in fetch fun: ${e.message}"
                )
            }
        }
    }
}
