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

    fun vodkaDrinks(): List<bottleDrinks> {
        val url = "https://www.thecocktaildb.com/api/json/v1/1/filter.php?i=Vodka"
        val request = Request.Builder()
            .url(url)
            .build()

        try {
            val response = client.newCall(request).execute()
            if (!response.isSuccessful) {
                Log.d("TAG", "Not Successful")
                return emptyList() // Return an empty list on error
            }
            Log.d("TAG", "Successful!")
            val responseBody = response.body
            val jsonString = responseBody?.string() ?: "Error"

            val json = JSONObject(jsonString)
            val drinks = json.getJSONArray("drinks")

            val bottleDrinksList = mutableListOf<bottleDrinks>()

            for (i in 0 until drinks.length()) {
                val drinkJson = drinks.getJSONObject(i)
                val drinkName = drinkJson.getString("strDrink")
                val drinkImg = drinkJson.getString("strDrinkThumb")

                bottleDrinksList.add(bottleDrinks(drinkName, drinkImg))
            }
            println(bottleDrinksList)
            return bottleDrinksList

        } catch (e: Exception) {
            return emptyList() // Return an empty list on error
        }
    }


    fun ginDrinks(): List<bottleDrinks> {
        val url = "https://www.thecocktaildb.com/api/json/v1/1/filter.php?i=Gin"
        val request = Request.Builder()
            .url(url)
            .build()

        try {
            val response = client.newCall(request).execute()
            if (!response.isSuccessful) {
                Log.d("TAG", "Not Successful")
                return emptyList() // Return an empty list on error
            }
            Log.d("TAG", "Successful!")
            val responseBody = response.body
            val jsonString = responseBody?.string() ?: "Error"

            val json = JSONObject(jsonString)
            val drinks = json.getJSONArray("drinks")

            val bottleDrinksList = mutableListOf<bottleDrinks>()

            for (i in 0 until drinks.length()) {
                val drinkJson = drinks.getJSONObject(i)
                val drinkName = drinkJson.getString("strDrink")
                val drinkImg = drinkJson.getString("strDrinkThumb")

                bottleDrinksList.add(bottleDrinks(drinkName, drinkImg))
            }
            println(bottleDrinksList)
            return bottleDrinksList

        } catch (e: Exception) {
            return emptyList() // Return an empty list on error
        }
    }

    fun rumDrinks(): List<bottleDrinks> {
        val url = "https://www.thecocktaildb.com/api/json/v1/1/filter.php?i=Rum"
        val request = Request.Builder()
            .url(url)
            .build()

        try {
            val response = client.newCall(request).execute()
            if (!response.isSuccessful) {
                Log.d("TAG", "Not Successful")
                return emptyList() // Return an empty list on error
            }
            Log.d("TAG", "Successful!")
            val responseBody = response.body
            val jsonString = responseBody?.string() ?: "Error"

            val json = JSONObject(jsonString)
            val drinks = json.getJSONArray("drinks")

            val bottleDrinksList = mutableListOf<bottleDrinks>()

            for (i in 0 until drinks.length()) {
                val drinkJson = drinks.getJSONObject(i)
                val drinkName = drinkJson.getString("strDrink")
                val drinkImg = drinkJson.getString("strDrinkThumb")

                bottleDrinksList.add(bottleDrinks(drinkName, drinkImg))
            }
            println(bottleDrinksList)
            return bottleDrinksList

        } catch (e: Exception) {
            return emptyList() // Return an empty list on error
        }
    }

    fun tequilaDrinks(): List<bottleDrinks> {
        val url = "https://www.thecocktaildb.com/api/json/v1/1/filter.php?i=Tequila"
        val request = Request.Builder()
            .url(url)
            .build()

        try {
            val response = client.newCall(request).execute()
            if (!response.isSuccessful) {
                Log.d("TAG", "Not Successful")
                return emptyList() // Return an empty list on error
            }
            Log.d("TAG", "Successful!")
            val responseBody = response.body
            val jsonString = responseBody?.string() ?: "Error"

            val json = JSONObject(jsonString)
            val drinks = json.getJSONArray("drinks")

            val bottleDrinksList = mutableListOf<bottleDrinks>()

            for (i in 0 until drinks.length()) {
                val drinkJson = drinks.getJSONObject(i)
                val drinkName = drinkJson.getString("strDrink")
                val drinkImg = drinkJson.getString("strDrinkThumb")

                bottleDrinksList.add(bottleDrinks(drinkName, drinkImg))
            }
            println(bottleDrinksList)
            return bottleDrinksList

        } catch (e: Exception) {
            return emptyList() // Return an empty list on error
        }
    }


    fun popularCocktails(): List<Cocktail> {
        val url = "https://www.thecocktaildb.com/api/json/v2/9973533/randomselection.php"
        val request = Request.Builder().url(url).build()

        try {
            val response = client.newCall(request).execute()
            if (!response.isSuccessful) {
                Log.d("TAG", "Not Successful")
                return emptyList()
            }

            val jsonString = response.body?.string() ?: ""
            val json = JSONObject(jsonString)
            val ingredientUrl = "https://www.thecocktaildb.com/images/ingredients"

            val drinksArray = json.getJSONArray("drinks")
            val cocktails = mutableListOf<Cocktail>()

            for (i in 0 until drinksArray.length()) {
                val drinkJson = drinksArray.getJSONObject(i)
                val ingredients = (1..15).map { j ->
                    val ingredientName = drinkJson.getString("strIngredient$j")
                    val ingredientImage = "$ingredientUrl/$ingredientName.png"
                    Ingredient(ingredientName, ingredientImage)
                }

                val cocktail = Cocktail(
                    id = drinkJson.getString("idDrink"),
                    name = drinkJson.getString("strDrink"),
                    imageUrl = drinkJson.getString("strDrinkThumb"),
                    ingredients = ingredients
                )

                cocktails.add(cocktail)
            }

            Log.d("TAG", "Successful!")
            return cocktails
        } catch (e: Exception) {
            Log.d("TAG", "Not Successful")
            return emptyList()
        }
    }


    fun popularDrinks(): List<popularDrinks> {
        val url = "https://www.thecocktaildb.com/api/json/v2/9973533/popular.php"

        val request = Request.Builder()
            .url(url)
            .build()

        try {
            val response = client.newCall(request).execute()
            if (!response.isSuccessful) {
                Log.d("TAG", "Not Successful")
                return emptyList() // Return an empty list on error
            }
            Log.d("TAG", "Successful!")
            val responseBody = response.body
            val jsonString = responseBody?.string() ?: "Error"

            val json = JSONObject(jsonString)
            val drinks = json.getJSONArray("drinks")

            val popularDrinksList = mutableListOf<popularDrinks>()

            for (i in 0 until drinks.length()) {
                val drinkJson = drinks.getJSONObject(i)
                val drinkName = drinkJson.getString("strDrink")
                val drinkImg = drinkJson.getString("strDrinkThumb")

                popularDrinksList.add(popularDrinks(drinkName, drinkImg))
            }
            println(popularDrinksList)
            return popularDrinksList

        } catch (e: Exception) {
            return emptyList() // Return an empty list on error
        }
    }

    fun mocktails(): List<MocktailClass> {
        val url = "https://www.thecocktaildb.com/api/json/v2/9973533/filter.php?a=Non_Alcoholic"

        val request = Request.Builder()
            .url(url)
            .build()

        try {
            val response = client.newCall(request).execute()
            if (!response.isSuccessful) {
                Log.d("TAG", "Not Successful")
                return emptyList() // Return an empty list on error
            }
            Log.d("TAG", "Successful!")
            val responseBody = response.body
            val jsonString = responseBody?.string() ?: "Error"

            val json = JSONObject(jsonString)
            val drinks = json.getJSONArray("drinks")

            val popularDrinksList = mutableListOf<MocktailClass>()

            for (i in 0 until drinks.length()) {
                val drinkJson = drinks.getJSONObject(i)
                val drinkName = drinkJson.getString("strDrink")
                val drinkImg = drinkJson.getString("strDrinkThumb")

                popularDrinksList.add(MocktailClass(drinkName, drinkImg))
            }
            println(popularDrinksList)
            return popularDrinksList

        } catch (e: Exception) {
            return emptyList() // Return an empty list on error
        }
    }

    fun searchCocktails(query: String): List<SearchResults> {
        val url = "www.thecocktaildb.com/api/json/v2/9973533/filter.php?a=Non_Alcoholic"
        val request = Request.Builder()
            .url(url)
            .build()

        try {
            val response = client.newCall(request).execute()
            if (!response.isSuccessful) {
                Log.e("TAG", "Search Not Successful. Error message: ${response.message}")
                return emptyList()
            }

            val jsonString = response.body?.string() ?: ""
            Log.d("TAG", "Response JSON: $jsonString")
            val json = JSONObject(jsonString)

            val drinksArray = json.getJSONArray("drinks")
            val searchResults = mutableListOf<SearchResults>()
            Log.d("TAG", url)

            for (i in 0 until drinksArray.length()) {
                val drinkJson = drinksArray.getJSONObject(i)
                val resultName = drinkJson.getString("strDrink")
                val resultImage = drinkJson.getString("strDrinkThumb")

                searchResults.add(SearchResults(resultName, resultImage))
            }

            Log.d("TAG", "Search Successful!")
            return searchResults
        } catch (e: Exception) {
            Log.e("TAG", "Search Exception: ${e.message}")
            return emptyList()
        }
    }

    /*
        fun searchCocktails(query: String): List<SearchResults> {
            val url1 = "https://www.thecocktaildb.com/api/json/v2/9973533/search.php?s=$query"
            val request = Request.Builder()
                .url(url1)
                .build()

            try {
                val response = client.newCall(request).execute()
                if (!response.isSuccessful) {
                    Log.d("TAG", "Search Not Successful: " + response.message)
                  //  Log.d("TAG", "Searchhh Not Successful")
                    return emptyList()
                }

                val jsonString = response.body?.string() ?: ""
                Log.d("TAG", "Response JSON: $jsonString")
                val json = JSONObject(jsonString)

                val drinksArray = json.getJSONArray("drinks")
                val searchResults = mutableListOf<SearchResults>()
                Log.d("TAG",url1)
                for (i in 0 until drinksArray.length()) {
                    val drinkJson = drinksArray.getJSONObject(i)
                    val resultName = drinkJson.getString("strDrink")
                    //val resultDescription = drinkJson.getString("strInstructions")
                    val resultImage = drinkJson.getString("strDrinkThumb")

                    searchResults.add(SearchResults(resultName, resultImage))
                }

                Log.d("TAG", "Search Successful!")
                return searchResults
            } catch (e: Exception) {
                Log.d("TAG", "Search Not Successful")
                return emptyList()
            }
        }

     */
}




data class Ingredient(
    val name: String,
    val imageUrl: String

    )

data class Cocktail(
    val id: String,
    val name: String,
    val imageUrl: String,
    val ingredients: List<Ingredient>
    )
data class bottleDrinks(
    val drinkName: String,
    val drinkImg: String
)

data class popularDrinks(
    val drinkName: String,
    val drinkImg: String
)

data class MocktailClass(
    val drinkName: String,
    val drinkImg: String
)

data class SearchResults(
    val Name: String,
//    val resultDescription: String,  // You can adjust the fields as needed
    val Image: String
)
