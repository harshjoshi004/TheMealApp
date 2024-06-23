package com.example.themealapp.filteredMeals

data class CategoryNamesListResponse(val meals: List<CategoryNameString>)
data class CategoryNameString(val strCategory:String)

data class AreaNamesListResponse(val meals: List<AreaNameString>)
data class AreaNameString(val strArea:String)

data class IngredientsNamesListResponse(val meals: List<Ingredients>)
data class Ingredients(
    val idIngredient:String,
    val strIngredient:String,
    val strDescription:String,
    val strType:String,
)

/*
  "meals": [
    {
      "idIngredient": "1",
      "strIngredient": "Chicken",
      "strDescription": "The chicken is a type of domesticated fowl, a subspecies of the red junglefowl (Gallus gallus). It is one of the most common and widespread domestic animals, with a total population of more than 19 billion as of 2011. There are more chickens in the world than any other bird or domesticated fowl. Humans keep chickens primarily as a source of food (consuming both their meat and eggs) and, less commonly, as pets. Originally raised for cockfighting or for special ceremonies, chickens were not kept for food until the Hellenistic period (4th–2nd centuries BC).\r\n\r\nGenetic studies have pointed to multiple maternal origins in South Asia, Southeast Asia, and East Asia, but with the clade found in the Americas, Europe, the Middle East and Africa originating in the Indian subcontinent. From ancient India, the domesticated chicken spread to Lydia in western Asia Minor, and to Greece by the 5th century BC. Fowl had been known in Egypt since the mid-15th century BC, with the \"bird that gives birth every day\" having come to Egypt from the land between Syria and Shinar, Babylonia, according to the annals of Thutmose III.",
      "strType": null

{
  "meals": [
    {
      "strCategory": "Beef"
    },
    {
      "strCategory": "Breakfast"
    },
    {},{},{} ]
}
* */