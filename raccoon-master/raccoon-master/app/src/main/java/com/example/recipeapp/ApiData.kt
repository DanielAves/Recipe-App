package com.example.recipeapp

import android.util.Log
import com.example.recipeapp.ui.main.FilterChoices
import com.example.recipeapp.ui.main.RecipeByFilter
import com.example.recipeapp.ui.main.RecipeByIngredient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import java.io.FileNotFoundException
import java.net.HttpURLConnection
import java.net.URL

/*
 * States whether an exception was caused
 */
private var success: Boolean = true
/*
 * Used to determine which fragment this class has been called from
 */
private var fragmentNum = 0


/**
 * ApiData communicates with the Edamam api based on a supplied query and processes the returned data
 * @author Dan Aves
 */
fun ApiData(mealBase: String, diet: String, health: String, cuisine: String, calories: String,results: String, called: Int){
    fragmentNum = called
    val url = "https://api.edamam.com/search?q=$mealBase&app_id=9e4bbb6d&app_key=8c2b6a23d887b94e021c74ae85cb5637$results$calories$diet$health$cuisine"
    apiRequest(url)
}

fun ApiDataByIngredient(ingredients: List<String>, called: Int){
    fragmentNum = called
    val baseUrl = "https://api.edamam.com/search?&app_id=9e4bbb6d&app_key=8c2b6a23d887b94e021c74ae85cb5637&q="
    var buildUrl = ""
    for(ingredient in ingredients){
        buildUrl = "$buildUrl $ingredient"
    }

    var tempUrl = baseUrl + buildUrl + "&ingr=" + ingredients.size
    val url = tempUrl.trimEnd().replace(" ", "%20")

    Log.d("url", url)
    apiRequest(url)
}

/**
Open connection to API
 */

fun apiRequest(vararg  url: String?){
    var text: String = ""
    //Handle as background process
    CoroutineScope(IO).launch {
        async {

            var connection = URL(url[0]).openConnection() as HttpURLConnection
            try {
                connection.connect()
                text = connection.inputStream.use { it.reader().use { reader -> reader.readText() } }
                success = true
            }
            catch (e: FileNotFoundException){
                success = false
            }finally {
                connection.disconnect()
            }
            //return text
        }.await()

        if(success){
            handleJson(text)
        }


        //Call the method in the class which ApiData was called from
        when (fragmentNum){
            1 -> {
                var recipebyfilter = RecipeByFilter()
                recipebyfilter.returnResults(success)
            }
            2 -> {
                var filterchoices = FilterChoices()
                filterchoices.returnResults(success)
            }
            3->{
                var RecipeByIngredient = RecipeByIngredient()
                RecipeByIngredient.returnResults(success)
            }
        }
    }

}


/**
 Filters the JSON response extracting the desired information
 */
  fun handleJson(jsonString: String?){
    val jsonObject = JSONObject(jsonString)
    val jsonArray = jsonObject.getJSONArray("hits")
    var hitsObj = JSONObject()

    val apirecord = ApiRecord()

    apirecord.clearRecipeData()

    //Consider each recipe returned
    for (x in 0 until jsonArray.length()){
        hitsObj = jsonArray.get(x) as JSONObject

        //Save the current recipe object
        var recipeObj = JSONObject()
        recipeObj = hitsObj.get("recipe") as JSONObject


        apirecord.addRecipeData(Recipe(recipeObj.get("label").toString(),
            recipeObj.get("image").toString(),
            recipeObj.get("source").toString(),
            recipeObj.get("url").toString(),
            recipeObj.get("yield") as Double,
            fetchArrayContents("dietLabels", recipeObj),
            fetchArrayContents("healthLabels", recipeObj),
            fetchArrayContents("ingredientLines", recipeObj),
            fetchArrayContents("ingredients", recipeObj),
            recipeObj.get("calories") as Double,
            recipeObj.get("totalWeight") as Double,
            recipeObj.get("totalTime") as Double))
    }
}

/**
 Function is used to extracting JSON arrays contained within the recipe object
 */
fun fetchArrayContents(label: String?, recipeObj: JSONObject ): MutableList<String>{
    var currentArray = JSONArray()
    currentArray = recipeObj.get(label) as JSONArray

    val currentList: MutableList<String> = arrayListOf()

    for(i in 0 until currentArray.length()){
        if(currentArray.get(i) is JSONObject){
            var currentObj = JSONObject()
            currentObj = currentArray.get(i) as JSONObject
            currentList.add(currentObj.get("weight").toString())
        }
        else{
            currentList.add(currentArray.get(i).toString())
        }
    }
    return currentList
}

