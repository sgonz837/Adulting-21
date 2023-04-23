/*
    Author: Sayan Gonzalez
    Description: This Class file will do all the api work by taking an url and building up on it
                Any and all api work will be done in this class file
 */


package com.example.adulting21
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class CocktailApiService {
    private val client = OkHttpClient()

    //these functions will be unused until we can succesfully implement the popularCocktails
    // function
    fun getCocktails(): String {
        val url = "https://www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita"
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).execute().use { response ->

            val responseBody = response.body
            return responseBody?.string() ?: "Error"
        }
    }


    fun get10Random(): String {
        val url = "https://www.thecocktaildb.com/api/json/v2/9973533/randomselection.php"
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).execute().use { response ->
            val responseBody = response.body
            return responseBody?.string() ?: "Error"
        }
    }

    // name typo but I cant be bothered to rewrite the name,
    fun popularCocktails(): List<Cocktail> {
        //this takes the url and requests the database for a json file
        //val url = "https://www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita"
        val url = "https://www.thecocktaildb.com/api/json/v2/9973533/randomselection.php"
        val request = Request.Builder()
            .url(url)
            .build()
        //once we get the return json data, we can start to use it using "response"
        client.newCall(request).execute().use { response ->
            val responseBody = response.body
            val jsonString = responseBody?.string() ?: "Error"

            // this is the json parser, simple yet effective
            // takes the output of this
            // https://www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita
            // to a workable format to where we can do a for loop and get what we need
            val json = JSONObject(jsonString)
            val drinks = json.getJSONArray("drinks")
            val ingredientUrl = "https://www.thecocktaildb.com/images/ingredients"
            //now we add all that we get to a class
            val cocktails = mutableListOf<Cocktail>()
            for (i in 0 until drinks.length()) {
                val drinkJson = drinks.getJSONObject(i)
                val cocktail = Cocktail(
                    id = drinkJson.getString("idDrink"),
                    name = drinkJson.getString("strDrink"),
                    imageUrl = drinkJson.getString("strDrinkThumb"),
                    ingredient1 = drinkJson.getString("strIngredient1"),
                    ingredient2 = drinkJson.getString("strIngredient2"),
                    ingredient3 = drinkJson.getString("strIngredient3"),
                    ingredient4 = drinkJson.getString("strIngredient4"),
                    ingredient5 = drinkJson.getString("strIngredient5"),
                    ingredient6 = drinkJson.getString("strIngredient6"),
                    ingredient7 = drinkJson.getString("strIngredient7"),
                    ingredient8 = drinkJson.getString("strIngredient8"),
                    ingredient9 = drinkJson.getString("strIngredient9"),
                    ingredient10 = drinkJson.getString("strIngredient10"),
                    ingredient11 = drinkJson.getString("strIngredient11"),
                    ingredient12 = drinkJson.getString("strIngredient12"),
                    ingredient13 = drinkJson.getString("strIngredient13"),
                    ingredient14 = drinkJson.getString("strIngredient14"),
                    ingredient15 = drinkJson.getString("strIngredient15"),
                    ingredient1Img = ingredientUrl + drinkJson.getString("strIngredient1") + "/png",
                    ingredient2Img = ingredientUrl + drinkJson.getString("strIngredient1") + "/png",
                    ingredient3Img = ingredientUrl + drinkJson.getString("strIngredient1") + "/png",
                    ingredient4Img = ingredientUrl + drinkJson.getString("strIngredient1") + "/png",
                    ingredient5Img = ingredientUrl + drinkJson.getString("strIngredient1") + "/png",
                    ingredient6Img = ingredientUrl + drinkJson.getString("strIngredient1") + "/png",
                    ingredient7Img = ingredientUrl + drinkJson.getString("strIngredient1") + "/png",
                    ingredient8Img = ingredientUrl + drinkJson.getString("strIngredient1") + "/png",
                    ingredient9Img = ingredientUrl + drinkJson.getString("strIngredient1") + "/png",
                    ingredient10Img = ingredientUrl + drinkJson.getString("strIngredient1") + "/png",
                    ingredient11Img = ingredientUrl + drinkJson.getString("strIngredient1") + "/png",
                    ingredient12Img = ingredientUrl + drinkJson.getString("strIngredient1") + "/png",
                    ingredient13Img = ingredientUrl + drinkJson.getString("strIngredient1") + "/png",
                    ingredient14Img = ingredientUrl + drinkJson.getString("strIngredient1") + "/png",
                    ingredient15Img = ingredientUrl + drinkJson.getString("strIngredient1") + "/png"
                )
                cocktails.add(cocktail)
            }
            return cocktails
        }
    }
}
//to get image url of ingredient
// https://www.thecocktaildb.com/images/ingredients + ingredient name + .png
/*
    This data class will hold all the information that we get from the api functions and be able
    to use it on other files
 */
data class Cocktail(
    val id: String,
    val name: String,
    val imageUrl: String,
    val ingredient1: String,
    val ingredient2: String,
    val ingredient3: String,
    val ingredient4: String,
    val ingredient5: String,
    val ingredient6: String,
    val ingredient7: String,
    val ingredient8: String,
    val ingredient9: String,
    val ingredient10: String,
    val ingredient11: String,
    val ingredient12: String,
    val ingredient13: String,
    val ingredient14: String,
    val ingredient15: String,
    val ingredient1Img: String,
    val ingredient2Img: String,
    val ingredient3Img: String,
    val ingredient4Img: String,
    val ingredient5Img: String,
    val ingredient6Img: String,
    val ingredient7Img: String,
    val ingredient8Img: String,
    val ingredient9Img: String,
    val ingredient10Img: String,
    val ingredient11Img: String,
    val ingredient12Img: String,
    val ingredient13Img: String,
    val ingredient14Img: String,
    val ingredient15Img: String,
)