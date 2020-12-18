package com.example.recipeapp

/**
Recipe holds the data required from the API call for a specific recipe
@author Dan Aves
 */
data class Recipe(val label: String,
                  val image: String,
                  val source: String,
                  val url: String,
                  val yield: Double,
                  val dietLabels: MutableList<String> = arrayListOf(),
                  val healthLabels: MutableList<String> = arrayListOf(),
                  val ingredientLines: MutableList<String> = arrayListOf(),
                  val ingredientWeight: MutableList<String> = arrayListOf(),
                  val calories: Double,
                  val totalWeight: Double,
                  val totalTime: Double
                  ){
}