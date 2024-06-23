package com.example.themealapp.feelingLucky

import com.google.gson.annotations.SerializedName

data class RandomMeal(
    @SerializedName("idMeal")
    val idMeal: String,
    @SerializedName("strMeal")
    val strMeal: String,
    @SerializedName("strCategory")
    val strCategory: String,
    @SerializedName("strArea")
    val strArea: String,
    @SerializedName("strInstructions")
    val strInstructions: String,
    @SerializedName("strMealThumb")
    val strMealThumb: String,
    @SerializedName("strYoutube")
    val strYoutube: String,
    @SerializedName("strIngredient1")
    val strIngredient1: String? = null,
    @SerializedName("strIngredient2")
    val strIngredient2: String? = null,
    @SerializedName("strIngredient3")
    val strIngredient3: String? = null,
    @SerializedName("strIngredient4")
    val strIngredient4: String? = null,
    @SerializedName("strIngredient5")
    val strIngredient5: String? = null,
    @SerializedName("strIngredient6")
    val strIngredient6: String? = null,
    @SerializedName("strIngredient7")
    val strIngredient7: String? = null,
    @SerializedName("strIngredient8")
    val strIngredient8: String? = null,
    @SerializedName("strIngredient9")
    val strIngredient9: String? = null,
    @SerializedName("strIngredient10")
    val strIngredient10: String? = null,
    @SerializedName("strIngredient11")
    val strIngredient11: String? = null,
    @SerializedName("strIngredient12")
    val strIngredient12: String? = null,
    @SerializedName("strIngredient13")
    val strIngredient13: String? = null,
    @SerializedName("strIngredient14")
    val strIngredient14: String? = null,
    @SerializedName("strIngredient15")
    val strIngredient15: String? = null,
    @SerializedName("strIngredient16")
    val strIngredient16: String? = null,
    @SerializedName("strIngredient17")
    val strIngredient17: String? = null,
    @SerializedName("strIngredient18")
    val strIngredient18: String? = null,
    @SerializedName("strIngredient19")
    val strIngredient19: String? = null,
    @SerializedName("strIngredient20")
    val strIngredient20: String? = null,
    @SerializedName("strMeasure1")
    val strMeasure1: String? = null,
    @SerializedName("strMeasure2")
    val strMeasure2: String? = null,
    @SerializedName("strMeasure3")
    val strMeasure3: String? = null,
    @SerializedName("strMeasure4")
    val strMeasure4: String? = null,
    @SerializedName("strMeasure5")
    val strMeasure5: String? = null,
    @SerializedName("strMeasure6")
    val strMeasure6: String? = null,
    @SerializedName("strMeasure7")
    val strMeasure7: String? = null,
    @SerializedName("strMeasure8")
    val strMeasure8: String? = null,
    @SerializedName("strMeasure9")
    val strMeasure9: String? = null,
    @SerializedName("strMeasure10")
    val strMeasure10: String? = null,
    @SerializedName("strMeasure11")
    val strMeasure11: String? = null,
    @SerializedName("strMeasure12")
    val strMeasure12: String? = null,
    @SerializedName("strMeasure13")
    val strMeasure13: String? = null,
    @SerializedName("strMeasure14")
    val strMeasure14: String? = null,
    @SerializedName("strMeasure15")
    val strMeasure15: String? = null,
    @SerializedName("strMeasure16")
    val strMeasure16: String? = null,
    @SerializedName("strMeasure17")
    val strMeasure17: String? = null,
    @SerializedName("strMeasure18")
    val strMeasure18: String? = null,
    @SerializedName("strMeasure19")
    val strMeasure19: String? = null,
    @SerializedName("strMeasure20")
    val strMeasure20: String? = null,
)
data class RandomMealResponse(
    val meals : List<RandomMeal>
)

/*{
    "meals": [
    {
        "idMeal": "52859",
        "strMeal": "Key Lime Pie",
        "strDrinkAlternate": null,
        "strCategory": "Dessert",
        "strArea": "American",
        "strInstructions": "Heat the oven to 160C/fan 140C/gas 3. Whizz the biscuits to crumbs in a food processor (or put in a strong plastic bag and bash with a rolling pin). Mix with the melted butter and press into the base and up the sides of a 22cm loose-based tart tin. Bake in the oven for 10 minutes. Remove and cool.\r\nPut the egg yolks in a large bowl and whisk for a minute with electric beaters. Add the condensed milk and whisk for 3 minutes then add the zest and juice and whisk again for 3 minutes. Pour the filling into the cooled base then put back in the oven for 15 minutes. Cool then chill for at least 3 hours or overnight if you like.\r\nWhen you are ready to serve, carefully remove the pie from the tin and put on a serving plate. To decorate, softly whip together the cream and icing sugar. Dollop or pipe the cream onto the top of the pie and finish with extra lime zest.",
        "strMealThumb": "https://www.themealdb.com/images/media/meals/qpqtuu1511386216.jpg",
        "strTags": "Cake,Pie,Desert,Fruity,Sour",
        "strYoutube": "https://www.youtube.com/watch?v=q4Rz7tUkX9A",
        "strIngredient1": "Digestive Biscuits",
        "strIngredient2": "Butter",
        "strIngredient3": "Condensed Milk",
        "strIngredient4": "Egg Yolks",
        "strIngredient5": "Lime",
        "strIngredient6": "Double Cream",
        "strIngredient7": "Icing Sugar",
        "strIngredient8": "Lime",
        "strIngredient9": "",
        "strIngredient10": "",
        "strIngredient11": "",
        "strIngredient12": "",
        "strIngredient13": "",
        "strIngredient14": "",
        "strIngredient15": "",
        "strIngredient16": "",
        "strIngredient17": "",
        "strIngredient18": "",
        "strIngredient19": "",
        "strIngredient20": "",
        "strMeasure1": "300g",
        "strMeasure2": "150g",
        "strMeasure3": "400g",
        "strMeasure4": "3",
        "strMeasure5": "4",
        "strMeasure6": "300ml ",
        "strMeasure7": "1 tbls",
        "strMeasure8": "to serve",
        "strMeasure9": "",
        "strMeasure10": "",
        "strMeasure11": "",
        "strMeasure12": "",
        "strMeasure13": "",
        "strMeasure14": "",
        "strMeasure15": "",
        "strMeasure16": "",
        "strMeasure17": "",
        "strMeasure18": "",
        "strMeasure19": "",
        "strMeasure20": "",
        "strSource": "https://www.bbcgoodfood.com/recipes/2155644/key-lime-pie",
        "strImageSource": null,
        "strCreativeCommonsConfirmed": null,
        "dateModified": null
    }
    ]
}*/