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
        val url = "https://www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita"
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

            //now we add all that we get to a class
            val cocktails = mutableListOf<Cocktail>()
            for (i in 0 until drinks.length()) {
                val drinkJson = drinks.getJSONObject(i)
                val cocktail = Cocktail(
                    id = drinkJson.getString("idDrink"),
                    name = drinkJson.getString("strDrink"),
                    imageUrl = drinkJson.getString("strDrinkThumb")
                )
                cocktails.add(cocktail)
            }
            return cocktails
        }
    }
}

/*
    This data class will hold all the information that we get from the api functions and be able
    to use it on other files
 */
data class Cocktail(
    val id: String,
    val name: String,
    val imageUrl: String
)