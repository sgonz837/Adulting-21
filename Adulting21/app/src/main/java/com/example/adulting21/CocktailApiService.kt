/*
    Author: Sayan Gonzalez
    Description: This Class file will do all the api work by taking an url and building up on it
                Any and all api work will be done in this class file
 */


package com.example.adulting21
import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class CocktailApiService {
    private val client = OkHttpClient()

    fun getCocktails(): String {
        val url = "https://www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita"
        val request = Request.Builder()
            .url(url)
            .build()

        try {
            val response = client.newCall(request).execute()
            if (!response.isSuccessful) {
                return "Error: ${response.code}"
            }
            val responseBody = response.body
            return responseBody?.string() ?: "Error"
        } catch (e: Exception) {
            return "Error: ${e.message}"
        }
    }

    fun get10Random(): String {
        val url = "https://www.thecocktaildb.com/api/json/v2/9973533/randomselection.php"
        val request = Request.Builder()
            .url(url)
            .build()

        try {
            val response = client.newCall(request).execute()
            if (!response.isSuccessful) {
                return "Error: ${response.code}"
            }
            val responseBody = response.body
            return responseBody?.string() ?: "Error"
        } catch (e: Exception) {
            return "Error: ${e.message}"
        }
    }
/*
    fun vodkaDrinks(): List<bottleDrinks> {
        val url = "www.thecocktaildb.com/api/json/v1/1/filter.php?i=Vodka"
        val request = Request.Builder()
            .url(url)
            .build()

        try {
            val response = client.newCall(request).execute()
            if (!response.isSuccessful) {
                return emptyList() // Return an empty list on error
            }
            val responseBody = response.body
            val jsonString = responseBody?.string() ?: "Error"

            val json = JSONObject(jsonString)
            val drinks = json.getJSONArray("drinks")

            val bottleDrinks = mutableListOf<bottleDrinks>()

            for (i in 0 until drinks.length()) {
                val drinkJson = drinks.getJSONObject(i)
                val cocktail = bottleDrinks(
                    drinkName1 = drinkJson.getString("strDrink"),
                    drinkImg1 = drinkJson.getString("strDrinkThumb")
                )
                bottleDrinks.add(cocktail)
            }
            return bottleDrinks
        } catch (e: Exception) {
            return emptyList() // Return an empty list on error
        }
    }



 */


    fun popularCocktails(): List<Cocktail> {
        val url = "https://www.thecocktaildb.com/api/json/v2/9973533/randomselection.php"
        val request = Request.Builder()
            .url(url)
            .build()
        try {
            val response = client.newCall(request).execute()
            if (!response.isSuccessful) {
                Log.d("TAG", "Not Succesful")
                return emptyList() // Return an empty list on error
            }

            Log.d("TAG", "Succesful!")
            val responseBody = response.body
            val jsonString = responseBody?.string() ?: "Error"

            val json = JSONObject(jsonString)
            val drinks = json.getJSONArray("drinks")
            val ingredientUrl = "https://www.thecocktaildb.com/images/ingredients"
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
                    ingredient1Img = ingredientUrl +"/"+ drinkJson.getString("strIngredient1") + ".png",
                    ingredient2Img = ingredientUrl +"/"+ drinkJson.getString("strIngredient1") + ".png",
                    ingredient3Img = ingredientUrl +"/"+ drinkJson.getString("strIngredient1") + ".png",
                    ingredient4Img = ingredientUrl +"/"+ drinkJson.getString("strIngredient1") + ".png",
                    ingredient5Img = ingredientUrl +"/"+ drinkJson.getString("strIngredient1") + ".png",
                    ingredient6Img = ingredientUrl +"/"+ drinkJson.getString("strIngredient1") + ".png",
                    ingredient7Img = ingredientUrl +"/"+ drinkJson.getString("strIngredient1") + ".png",
                    ingredient8Img = ingredientUrl +"/"+ drinkJson.getString("strIngredient1") + ".png",
                    ingredient9Img = ingredientUrl +"/"+ drinkJson.getString("strIngredient1") + ".png",
                    ingredient10Img = ingredientUrl +"/"+ drinkJson.getString("strIngredient1") + ".png",
                    ingredient11Img = ingredientUrl +"/"+ drinkJson.getString("strIngredient1") + ".png",
                    ingredient12Img = ingredientUrl +"/"+ drinkJson.getString("strIngredient1") + ".png",
                    ingredient13Img = ingredientUrl +"/"+ drinkJson.getString("strIngredient1") + ".png",
                    ingredient14Img = ingredientUrl +"/"+ drinkJson.getString("strIngredient1") + ".png",
                    ingredient15Img = ingredientUrl +"/"+ drinkJson.getString("strIngredient1") + ".png"
                )
                cocktails.add(cocktail)
            }
            return cocktails
        } catch (e: Exception) {
            Log.d("TAG", "Not Succesful")
            return emptyList() // Return an empty list on error
        }
    }
}

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

/*
data class bottleDrinks(
    val drinkName1: String,
    val drinkImg1: String,
    val drinkName2: String,
    val drinkImg2: String,
    val drinkName3: String,
    val drinkImg3: String,
    val drinkName4: String,
    val drinkImg4: String,
    val drinkName5: String,
    val drinkImg5: String,
    val drinkName6: String,
    val drinkImg6: String,
    val drinkName7: String,
    val drinkImg7: String,
    val drinkName8: String,
    val drinkImg8: String,
    val drinkName9: String,
    val drinkImg9: String,
    val drinkName10: String,
    val drinkImg10: String,
    val drinkName11: String,
    val drinkImg11: String,
    val drinkName12: String,
    val drinkImg12: String,
    )


 */