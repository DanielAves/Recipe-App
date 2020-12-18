package com.example.recipeapp

import org.junit.Assert.*
import org.junit.Test
import java.time.Year

/**
 * This class creates a new instance of the Recipe object to check the fields behave correctly
 * Also tests the ApiRecord methods based off the Recipe class
 */
class RecipeTest {
    val label = "chicken soup"
    val image = "image.jpg"
    val source = "recipes"
    val url = "www.recipes.com"
    val Yield = 5.0
    val DietLabels = mutableListOf("Low-Carb", "Balanced")
    val HealthLabels = mutableListOf("Sugar-Conscious", "Peanut-Free")
    val IngredientLines = mutableListOf("Bacon","chicken","garlic")
    val IngredientWeight = mutableListOf("10.0","20.0","5.0")
    val Calories = 200.0
    val TotalWeight = 500.0
    val TotalTime = 20.0
    val recipe = Recipe(label, image, source, url, Yield, DietLabels, HealthLabels, IngredientLines,IngredientWeight, Calories, TotalWeight, TotalTime)

    val apiRecord = ApiRecord()


    @Test
    fun testRecipeData(){
        assertTrue(recipe.label == label)
        assertTrue(recipe.image == image)
        assertTrue(recipe.source == source)
        assertTrue(recipe.url == url)
        assertTrue(recipe.yield == Yield)
        assertTrue(recipe.dietLabels == DietLabels)
        assertTrue(recipe.healthLabels == HealthLabels)
        assertTrue(recipe.ingredientLines == IngredientLines)
        assertTrue(recipe.ingredientWeight == IngredientWeight)
        assertTrue(recipe.calories == Calories)
        assertTrue(recipe.totalWeight == TotalWeight)
        assertTrue(recipe.totalTime == TotalTime)
    }

    @Test
    fun testRecipeSize(){
        apiRecord.addRecipeData(recipe)
        assertTrue(apiRecord.getRecipeSize() == 1 )
    }

    @Test
    fun correctSpecificRecipe(){
        apiRecord.addRecipeData(recipe)
        assertTrue(apiRecord.getSpecificRecipe(0).label == label)
    }

    @Test
    fun clearRecipeData(){
        apiRecord.addRecipeData(recipe)
        assertTrue(apiRecord.getSpecificRecipe(0).yield == Yield)
        apiRecord.clearRecipeData()
        assertFalse(apiRecord.getRecipeSize() > 0)
    }
}