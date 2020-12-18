package com.example.recipeapp

import com.example.recipeapp.ui.main.RecipeByIngredient

/**
The purpose of this class is to hold a collection of Recipe
 @author Dan Aves
 */
class ApiRecord(){
    companion object {
        val recipe : MutableList<Recipe> = arrayListOf()
    }

    fun clearRecipeData(){
        recipe.clear()
    }

    fun addRecipeData(data: Recipe){
        recipe.add(data)
    }

    fun getRecipeList(): MutableList<Recipe> ?{
        return recipe
    }

    fun getSpecificRecipe(count: Int): Recipe{
        return recipe[count]
    }

    fun getRecipeSize(): Int{
        return recipe.size
    }

}




