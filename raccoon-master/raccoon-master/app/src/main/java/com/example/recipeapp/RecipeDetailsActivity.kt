package com.example.recipeapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.recipeapp.ui.main.ShoppingListFragment
import java.lang.reflect.InvocationTargetException

/**
 * This activity handles the screen displayed when a recipe is drilled into from the
 * RecipeResults screen.
 * @author Dan Aves
 */
class RecipeDetailsActivity : AppCompatActivity() {
    //This list is used to organise the ingredient data
    private val measureKeys = listOf<String>(
        "tablespoons",
        "to",
        "cup",
        "cups",
        "teaspoon",
        "cup",
        "teaspoons",
        "ml",
        "grams",
        "pints",
        "ounces",
        "oz",
        "c",
        "tsp",
        "scoop",
        "scoops"
    )
    val regularEx = Regex("\\b(?:${measureKeys.joinToString(separator = "|")})\\b")
    val digitRegex = Regex(".*\\d.*")

    companion object{
        var userIngredients:ArrayList<String>? = null
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_details)
        val backButton: ImageButton = findViewById(R.id.backButtonRD)
        val openMethodButton: Button = findViewById(R.id.viewMethod)
        var position = 0

        userIngredients = intent.getStringArrayListExtra("ingredients")

        var parameter = intent.extras
        if (parameter != null) {
            position = parameter.getInt("position")
            displayRecipeInfo(position)

        }

        backButton.setOnClickListener {
            val intent = Intent(applicationContext, RecipeResults::class.java)
            intent.putStringArrayListExtra("ingredients", userIngredients)
            startActivity(intent)
        }

        //Open recipe url in browser
        openMethodButton.setOnClickListener{
            val apirecord = ApiRecord()
            val url = apirecord.getSpecificRecipe(position).url

            val intent = Intent(android.content.Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
    }

    private fun checkIngredients(ingredientlist: MutableList<String>): MutableList<String> {
        var shopIngList = mutableListOf<String>()

        if (userIngredients != null) {
            for (recipeIngredient in ingredientlist) {
                var counter = 0

                for (i in 0 until (userIngredients!!.size)) {
                    if (recipeIngredient.contains(userIngredients!![i], ignoreCase = true)) {
                        counter ++
                    }
                }
                if(counter == 0){
                    shopIngList.add(recipeIngredient)
                    Log.d("shoppingListing", recipeIngredient)
                }
            }
        }
        else{
            shopIngList = ingredientlist
        }

        return shopIngList
    }


    private fun displayRecipeInfo(position: Int) {
        /*
            Local variables declared in order of each screen item
        */
        val recipeTitle: TextView = findViewById(R.id.recipeTitle)
        val recipePhoto: ImageView = findViewById(R.id.recipeDetailsPhoto)
        val calories: TextView = findViewById(R.id.calories)
        val servings: TextView = findViewById((R.id.servings))
        val ingredientGrid: GridView = findViewById(R.id.ingredientGrid)
        val addIngredients: Button = findViewById(R.id.addIngButton)


         /*
            Create instance of Apirecord to access data
         */
        val apirecord = ApiRecord()

         /*
            Populate the respective widgets on screen
         */
        recipeTitle.text = apirecord.getSpecificRecipe(position).label

        val photoUrl = apirecord.getSpecificRecipe(position).image
        Glide.with(this).load(photoUrl).into(recipePhoto)

        val yeild = apirecord.getSpecificRecipe(position).yield.toInt()

        if (yeild > 1) {
            servings.text = "for " + yeild.toString() + " servings"
        } else {
            servings.text = "for " + yeild.toString() + " serving"
        }

        val ingredientlist = apirecord.getSpecificRecipe(position).ingredientLines
        var shoppingList = checkIngredients(ingredientlist)

        calories.text = apirecord.getSpecificRecipe(position).calories.toInt().toString()

        /*
        Populate ingredientGrid with content
         */
        ingredientGrid.adapter = RecipeDetailsActivity.customAdapter(this, ingredientlist)


        addIngredients.setOnClickListener {
                try {
                    ShoppingListFragment().addToShoppingList(recipeList = shoppingList, fromRecipe = true)
                }
                catch (ex: InvocationTargetException){
                    Log.e("InvocationTargetEx", ex.message)
                    Log.e("ExceptionCause", ex.cause.toString())
                }
            Toast.makeText(applicationContext,shoppingList.toString(),Toast.LENGTH_SHORT).show()
        }
    }


    /**
     * Responsible for populating the ingredientGrid
     * @param context
     * @param ingredientList A local copy of the ingredientList from ApiRecord
     */
    private class customAdapter(context: Context, ingredientList: MutableList<String>): BaseAdapter() {
        //Represents the current activity
        private val mContext: Context = context

        val ingredientList = ingredientList


        val apirecord = ApiRecord()

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val layoutInflater = LayoutInflater.from(mContext)
            val rowMain = layoutInflater.inflate(R.layout.activity_recipe_details_grid, parent, false)

            val instance = RecipeDetailsActivity()
            instance.sortIngredients(position, rowMain, ingredientList)

            return rowMain
        }

        override fun getItem(position: Int): Any {
            return apirecord.getSpecificRecipe(position).ingredientLines
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        //Used to determine how many rows are in the list
        override fun getCount(): Int {
            return ingredientList.size
        }
    }

    /**
     * Sort Ingredients is used to identify and extract ingredient measurements from the returned API ingredient lines
     * @param position Indicates the selected on screen recipe
     * @param rowMain Represents the current screen layout
     * @param ingredientList A local copy of the ingredientList from ApiRecord
     */
    fun sortIngredients(
        position: Int,
        rowMain: View,
        ingredientList: MutableList<String>
    ) {
        val ingredientDescription: TextView = rowMain.findViewById(R.id.ingredientDesc)
        val ingredientUnits: TextView = rowMain.findViewById(R.id.units)

        var unitFound = false
        var unitString = ""
        var ingredientString = ""
        var firstWord = true
        for (word in ingredientList[position].split(" ")) {
            if (word == "") {
            } else if (word.matches(digitRegex)) {
                unitString += word + " "
                unitFound = true
            } else if (regularEx.containsMatchIn(word.toLowerCase()) and unitFound) {
                unitString += word + " "
            } else {
                unitFound = false
                if (firstWord) {
                    ingredientString += word.capitalize() + " "
                    firstWord = false
                } else {
                    ingredientString += word + " "
                }
            }
            when (unitFound) {
            }
        }
        ingredientDescription.text = (ingredientString)
        ingredientUnits.text = (unitString)

        ingredientDescription.measure(0, 0)

    }
}

