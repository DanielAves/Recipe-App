package com.example.recipeapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlin.collections.ArrayList

/**
 * RecipeResults displays the returned recipes in a grid format showing images for each recipe
 * @author Dan Aves
 */
class RecipeResults : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_results)
        
        val backButton: ImageButton = findViewById(R.id.backButton)
        val resultField: GridView = findViewById<GridView>(R.id.gridView)

        /*
        Call customer Adapter for ListView
         */
        val ingredients: ArrayList<String>? = intent.getStringArrayListExtra("ingredients")
        Log.d("ingredientsOnCreate", ingredients.toString())
        resultField.adapter = customAdapter(this, ingredients)


        /*
        Back button handling
         */
        backButton.setOnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }

        /*
        Handles a user click on the recipe photo grid
         */
        resultField.setOnItemClickListener(){ adapterView: AdapterView<*>, view: View, position: Int, l: Long ->

            val intent = Intent(applicationContext, RecipeDetailsActivity::class.java)
            intent.putExtra("position", position)
            intent.putStringArrayListExtra("ingredients", ingredients)
            startActivity(intent)
        }
    }


    private class customAdapter(context: Context, ingredients: ArrayList<String>?): BaseAdapter() {
        //Represents the current activity
        private val mContext: Context
        private val userIngredientList: ArrayList<String>? = ingredients

        val apirecord = ApiRecord()

        init {
            mContext = context
        }


        fun checkIngredients(ingredientlist: MutableList<String>, position: Int): Int {
            var counter = 0
            var numOfIngNeeded = 0

            if (userIngredientList != null) {
                for (recipeIngredient in ingredientlist) {
                    for (i in 0 until (userIngredientList.size)) {
                        if (recipeIngredient.contains(userIngredientList[i], ignoreCase = true)) {
                            counter++
                            break
                        }
                    }
                }
                numOfIngNeeded = (ingredientlist.size - counter)
            }
            else{
                numOfIngNeeded = (ingredientlist.size)
            }

            return numOfIngNeeded
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val layoutInflater = LayoutInflater.from(mContext)
            val rowMain = layoutInflater.inflate(R.layout.activity_recipe_layout, parent, false)

            /*
            Create local variables for respective UI widgets
             */
            val labelTextView = rowMain.findViewById<TextView>(R.id.label_textview)
            val recipePhoto = rowMain.findViewById<ImageView>(R.id.recipePhoto)
            val ing_needed = rowMain.findViewById<TextView>(R.id.ing_needed)

            val ingredientlist = apirecord.getSpecificRecipe(position).ingredientLines

            /*
            Populate Image View with photo derived from URL
             */
            val photoUrl = apirecord.getSpecificRecipe(position).image
            Glide.with(rowMain).load(photoUrl).into(recipePhoto)

            /*
            Set text for textView for recipe title
             */
            labelTextView.text = apirecord.getSpecificRecipe(position).label
            var numOfIngNeeded = checkIngredients(ingredientlist, position)
            if(numOfIngNeeded <= 0){
                ing_needed.text = "You can make this recipe already"
            }
            else{
                ing_needed.text = numOfIngNeeded.toString() +  " ingredients needed"
            }

            return rowMain
        }

        override fun getItem(position: Int): Any {
            return position
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        //Used to determine how many rows are in the list
        override fun getCount(): Int {
            return apirecord.getRecipeSize()
        }
    }

}
