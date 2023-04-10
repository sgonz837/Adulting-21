package com.example.adulting21
import okhttp3.OkHttpClient
import okhttp3.Request


/*
 Description: This Class will do all the api work by taking an url and building up on it
                Any and all api work will be done in this class file
*/
class CocktailApiService {
    private val client = OkHttpClient()

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
}