package com.example.themealapp.feelingLucky

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.BottomAppBarScrollBehavior
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.paint
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.themealapp.ui.theme.MyCenteredTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeelingLuckyUI(viewModel:FeelingLuckyViewModel, navcon: NavHostController) {
    val context = LocalContext.current
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val feelingLuckyState by viewModel.feelingLuckyState

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MyCenteredTopBar(
                header = {
                    Text(text = "Feeling Lucky", fontWeight = FontWeight.Bold)
                },
                scrollBehavior = scrollBehavior,
                navIcon = {},
                actionIcon = {}
            )
        }
    ) { paddingVal->
        when{
            feelingLuckyState.loading ->{
                Text(
                    text = "Loading",
                    modifier = Modifier.padding(paddingVal).fillMaxSize()
                )
            }
            feelingLuckyState.error != null ->{
                Text(
                    text = "Error Occured: ${feelingLuckyState.error}",
                    modifier = Modifier.padding(paddingVal).fillMaxSize()
                )
            }
            else ->{
                LazyColumn(modifier = Modifier.padding(paddingVal)){
                    viewModel.feelingLuckyState.value.list?.forEach { rm->
                        item {
                            RandomMealCard(rm = rm, context = context, navcon = navcon)
                        }
                    }
                    item {
                        Button(
                            onClick = {
                                viewModel.fetchLuckyState()
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text(text = "Refresh")
                        }
                    }
                }
            }
        }
    }
}



@Composable
fun RandomMealCard(rm: RandomMeal,context:Context, navcon:NavHostController) {
    Card(
        modifier =Modifier
            .padding(16.dp),
    ) {
        Column(modifier =Modifier.background(MaterialTheme.colorScheme.secondaryContainer)) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .paint(
                    painter = rememberImagePainter(rm.strMealThumb),
                    contentScale = ContentScale.FillBounds
                )
            )
            Column(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 16.dp, end = 16.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = rm.strMeal,
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(16.dp))
                Divider(color = MaterialTheme.colorScheme.onSecondaryContainer)
                Spacer(modifier = Modifier.height(16.dp))

                Row {
                    Text(
                        text = "Category: ",
                        fontWeight = FontWeight.Normal,
                        fontSize = 18.sp,
                    )

                    Text(
                        text = rm.strCategory,
                        fontWeight = FontWeight.Light,
                        fontSize = 18.sp,
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row {
                    Text(
                        text = "Area: ",
                        fontWeight = FontWeight.Normal,
                        fontSize = 18.sp,
                    )

                    Text(
                        text = rm.strArea,
                        fontWeight = FontWeight.Light,
                        fontSize = 18.sp,
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                Spacer(modifier = Modifier.height(16.dp))
                Divider(color = MaterialTheme.colorScheme.onSecondaryContainer)
                Spacer(modifier = Modifier.height(16.dp))

                Button(modifier = Modifier.fillMaxWidth(),
                    onClick = { context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(rm.strYoutube))) }
                ) {
                    Text(text = "Watch YouTube Tutorial")
                }

                Spacer(modifier = Modifier.height(16.dp))

                //CategoryButton
                OutlinedButton(
                    onClick = { navcon.navigate("more_such_categories/${rm.strCategory}") },
                    modifier = Modifier.fillMaxWidth(),)
                {
                    Text(text = "More ${rm.strCategory} Meals")
                }

                Spacer(modifier = Modifier.height(16.dp))

                //Area Button
                OutlinedButton(
                    onClick = { navcon.navigate("more_such_area/${rm.strArea}") },
                    modifier = Modifier.fillMaxWidth(),)
                {
                    Text(text = "More ${rm.strArea} Meals")
                }

                Spacer(modifier = Modifier.height(16.dp))
                Divider(color = MaterialTheme.colorScheme.onSecondaryContainer)
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Ingredients:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp,
                )

                Spacer(modifier = Modifier.height(16.dp))
//column starts
                Column {
                    IngredientRow(ingredient = rm.strIngredient1, measure = rm.strMeasure1)
                    IngredientRow(ingredient = rm.strIngredient2, measure = rm.strMeasure2)
                    IngredientRow(ingredient = rm.strIngredient3, measure = rm.strMeasure3)
                    IngredientRow(ingredient = rm.strIngredient4, measure = rm.strMeasure4)
                    IngredientRow(ingredient = rm.strIngredient5, measure = rm.strMeasure5)
                    IngredientRow(ingredient = rm.strIngredient6, measure = rm.strMeasure6)
                    IngredientRow(ingredient = rm.strIngredient7, measure = rm.strMeasure7)
                    IngredientRow(ingredient = rm.strIngredient8, measure = rm.strMeasure8)
                    IngredientRow(ingredient = rm.strIngredient9, measure = rm.strMeasure9)
                    IngredientRow(ingredient = rm.strIngredient10, measure = rm.strMeasure10)
                    IngredientRow(ingredient = rm.strIngredient11, measure = rm.strMeasure11)
                    IngredientRow(ingredient = rm.strIngredient12, measure = rm.strMeasure12)
                    IngredientRow(ingredient = rm.strIngredient13, measure = rm.strMeasure13)
                    IngredientRow(ingredient = rm.strIngredient14, measure = rm.strMeasure14)
                    IngredientRow(ingredient = rm.strIngredient15, measure = rm.strMeasure15)
                    IngredientRow(ingredient = rm.strIngredient16, measure = rm.strMeasure16)
                    IngredientRow(ingredient = rm.strIngredient17, measure = rm.strMeasure17)
                    IngredientRow(ingredient = rm.strIngredient18, measure = rm.strMeasure18)
                    IngredientRow(ingredient = rm.strIngredient19, measure = rm.strMeasure19)
                    IngredientRow(ingredient = rm.strIngredient20, measure = rm.strMeasure20)
                }
//coulumn ends

                Spacer(modifier = Modifier.height(16.dp))
                Divider(color = MaterialTheme.colorScheme.onSecondaryContainer)
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Instructions:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp,
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = rm.strInstructions.replace("\n", "\n\n"),
                    fontWeight = FontWeight.Light,
                    fontSize = 18.sp,
                )

                Spacer(modifier = Modifier.height(16.dp))
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun IngredientRow(ingredient: String?, measure: String?) {
    if (ingredient.isNullOrEmpty() || measure.isNullOrEmpty()) {
        return
    }

    Row {
        Text(
            text = "$ingredient:  ",
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
        )
        Text(
            text = "$measure",
            fontWeight = FontWeight.Light,
            fontSize = 18.sp,
        )
    }
}

/*
RandomMeal(
    "52804",
    "Poutine",
    "Miscellaneous",
    "Canadian",
    "Heat oil in a deep fryer or deep heavy skillet to 365°F (185°C).\r\nWarm gravy in saucepan or microwave.\r\nPlace the fries into the hot oil, and cook until light brown, about 5 minutes.\r\nRemove to a paper towel lined plate to drain.\r\nPlace the fries on a serving platter, and sprinkle the cheese over them.\r\nLadle gravy over the fries and cheese, and serve immediately.",
    "https://www.themealdb.com/images/media/meals/uuyrrx1487327597.jpg",
    "https://www.youtube.com/watch?v=UVAMAoA2_WU",
    "Chicken Breast",
    "Pickle Juice",
    "Pickle Juice",
    strMeasure1= "1",
    strMeasure2= "1/4 cup",
    strMeasure3= "1"
)
*/