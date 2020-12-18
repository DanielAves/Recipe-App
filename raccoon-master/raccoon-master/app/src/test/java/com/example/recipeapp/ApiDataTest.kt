package com.example.recipeapp

import org.junit.Test
import org.junit.Assert.*
import java.io.*

/**
 * This class tests the ApiRecord.kt file with an offline example Json response from the recipe API.
 * The tests validate that the JSON is being parsed correctly and populated into the Recipe collections respectively
 */
class ApiDataTest {
    val apiData = ApiData("chicken","","","","&calories=","&from=0&to=50",3)
    val url = "https://api.edamam.com/search?q=chicken&app_id=9e4bbb6d&app_key=8c2b6a23d887b94e021c74ae85cb5637&from=0&to=5"


    /*
    Inital setup for tests
     */
    val bufferedReader: BufferedReader = File("src/test/java/com/example/recipeapp/ExampleResponse.json").bufferedReader()
    val jsonString = bufferedReader.use { it.readText() }
    val jsonHandle = handleJson(jsonString)
    var apiRecordInstance =  ApiRecord()
    val testInstance1 = apiRecordInstance.getSpecificRecipe(0)
    val testInstance2 = apiRecordInstance.getSpecificRecipe(3)


    @Test
    fun checkJsonParsing(){
        assertTrue(testInstance1.label == "Chicken Vesuvio")
        assertTrue(testInstance2.label == "Catalan Chicken")
    }
    @Test
    fun correctYield(){
        assertTrue(testInstance1.yield == 4.0)
        assertTrue(testInstance2.yield == 12.0)
    }
    @Test
    fun dietLabelsQuantity(){
        assertFalse(testInstance1.dietLabels.size > 1)
        assertFalse(testInstance2.dietLabels.size > 1)
    }
    @Test
    fun ingredientJsonParsing(){
        assertTrue(testInstance1.ingredientLines[1] == "5 cloves garlic, peeled")
        assertTrue(testInstance1.ingredientLines[2] == "2 large russet potatoes, peeled and cut into chunks")
        assertTrue(testInstance1.ingredientLines.size == 10)
        assertTrue(testInstance2.ingredientLines[1] == "8 slices bacon")
        assertTrue(testInstance2.ingredientLines[2] == "30 cloves garlic")
        assertTrue(testInstance2.ingredientLines.size == 6)
    }
    @Test
    fun healthLabelsJsonParsing(){
        assertTrue(testInstance1.healthLabels[1] == "Peanut-Free")
        assertTrue(testInstance2.healthLabels[1] == "Peanut-Free")
    }
    @Test
    fun correctCalories(){
        assertTrue(testInstance1.calories == 4055.7632762010808)
        assertTrue(testInstance2.calories == 3298.8)
    }
    @Test
    fun correctTotalWeight(){
        assertTrue(testInstance1.totalWeight == 2765.5901364771207 )
        assertTrue(testInstance2.totalWeight == 1707.5 )
    }
    @Test
    fun correctTotalTime(){
        assertTrue(testInstance1.totalTime == 60.0 )
        assertTrue(testInstance2.totalTime == 0.0 )
    }
    @Test
    fun totalParsedRecipes(){
        assertTrue(apiRecordInstance.getRecipeSize() == 10)
    }





}