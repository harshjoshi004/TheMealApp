package com.example.themealapp.filteredMeals

data class FilterMealsResponse(val meals:List<FilteredMeal>)

data class FilteredMeal(
    val strMeal: String,
    val strMealThumb: String,
    val idMeal: String
)

/*
url: https://www.themealdb.com/api/json/v1/1/filter.php?i=chicken_breast
{
  "meals": [
    {
      "strMeal": "Chick-Fil-A Sandwich",
      "strMealThumb": "https://www.themealdb.com/images/media/meals/sbx7n71587673021.jpg",
      "idMeal": "53016"
    },
    {
      "strMeal": "Chicken Couscous",
      "strMealThumb": "https://www.themealdb.com/images/media/meals/qxytrx1511304021.jpg",
      "idMeal": "52850"
    }
    ,{},{} ]
}
* */