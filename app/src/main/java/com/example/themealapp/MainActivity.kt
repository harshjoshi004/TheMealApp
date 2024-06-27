package com.example.themealapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.themealapp.feelingLucky.FeelingLuckyUI
import com.example.themealapp.feelingLucky.FeelingLuckyUINavigator
import com.example.themealapp.feelingLucky.FeelingLuckyViewModel
import com.example.themealapp.feelingLucky.RandomMealResponse
import com.example.themealapp.listAllCategories.CategoriesResponse
import com.example.themealapp.listAllCategories.CategoriesUI
import com.example.themealapp.listAllCategories.CategoriesViewModel
import com.example.themealapp.listAllCategories.Category
import com.example.themealapp.searchMealByName.SearchMealUI
import com.example.themealapp.searchMealByName.SearchedMealResponse
import com.example.themealapp.ui.theme.TheMealAppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel:FeelingLuckyViewModel = viewModel()
            TheMealAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FeelingLuckyUINavigator()
                }
            }
        }
    }
}

