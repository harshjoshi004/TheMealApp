package com.example.themealapp.searchMealByName

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themealapp.feelingLucky.RandomMeal
import com.example.themealapp.myApiService
import kotlinx.coroutines.launch

class SearchMealViewModel:ViewModel() {
    private val _searchMealState = mutableStateOf(SearchedMealState())
    val searchMealState: State<SearchedMealState> = _searchMealState

    var bottomSheetOpen = mutableStateOf(false)

    var bottomSheetMeal = mutableStateOf<SearchedMeal?>(null)
    data class SearchedMealState(
        var loading: Boolean = true,
        var list: List<SearchedMeal>? = null,
        var error: String?=null
    )

    init {
        fetchSearchedMealState("")
    }

    fun fetchSearchedMealState(s:String) {
        viewModelScope.launch {
            try {
                //fetch code
                val response = myApiService.getSearchResponse(s = s)
                _searchMealState.value = _searchMealState.value.copy(
                    loading = false,
                    list = response.meals,
                    error = null
                )

            } catch (e: Exception) {
                _searchMealState.value = _searchMealState.value.copy(
                    loading = false,
                    error = "error in fetch fun: ${e.message}"
                )
            }
        }
    }
}