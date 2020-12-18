package com.example.recipeapp.ui.main

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import android.view.animation.LinearInterpolator
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.recipeapp.ApiDataByIngredient
import com.example.recipeapp.R
import com.example.recipeapp.RecipeResults
import com.google.android.material.snackbar.Snackbar
import java.util.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RecipeByIngredient.newInstance] factory method to
 * create an instance of this fragment.
 *
 * This fragment is used to search for recipes by ingredient
 *  e.g. chicken, pasta, tomato, cheese
 *
 *  When the app is opened, and you navigate to the "By Ingredient"
 *  tab in the "Search" tab, the fragments view is controlled by whats
 *  below
 *
 *  The activity consists of 5 total editText objects that each
 *  appear on the screen at different intervals. A new editText
 *  can be displayed by entering your ingredient in the current
 *  edit text and pressing the "enter" button on the phone or pc
 *  keyboard.
 *
 *
 */
class RecipeByIngredient : Fragment() {

    lateinit var ingredientText1: EditText
    lateinit var ingredientText2: EditText
    lateinit var ingredientText3: EditText
    lateinit var ingredientText4: EditText
    lateinit var ingredientText5: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    // Creates the view and all of the components for the user to interact with
    // gets the al the editTexts by the ids and will add them to the api call
    // if there is text entered by the user.
    // The ingredients list is cleared each time to prevent repetition and
    // incorrect api querys

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_recipe_by_ingredient, container, false)

        ingredients.clear()

        // Each button that creates a new
        val searchButton = v.findViewById<View>(R.id.submitIngredients)

        ingredientText1 = v.findViewById<View>(R.id.ingredientSearch1) as EditText
        ingredientText2 = v.findViewById<View>(R.id.ingredientSearch2) as EditText
        ingredientText3 = v.findViewById<View>(R.id.ingredientSearch3) as EditText
        ingredientText4 = v.findViewById<View>(R.id.ingredientSearch4) as EditText
        ingredientText5 = v.findViewById<View>(R.id.ingredientSearch5) as EditText

        ingredientText2.visibility = View.GONE
        ingredientText3.visibility = View.GONE
        ingredientText4.visibility = View.GONE
        ingredientText5.visibility = View.GONE

        layout()

        searchButton.setOnClickListener {
            Log.d("ingredients", ingredients.toString())

            if(ingredientText1.text.toString() != ""){
                ingredients.add(ingredientText1.text.toString())
            }
            if(ingredientText2.text.toString() != ""){
                ingredients.add(ingredientText2.text.toString())
            }
            if(ingredientText3.text.toString() != ""){
                ingredients.add(ingredientText3.text.toString())
            }
            if(ingredientText4.text.toString() != ""){
                ingredients.add(ingredientText4.text.toString())
            }
            if(ingredientText5.text.toString() != ""){
                ingredients.add(ingredientText5.text.toString())
            }

            // Send the data to the ApiData file to create the query to send to the api
            Log.d("ingredients", ingredients.toString())
            ApiDataByIngredient(ingredients, 3)
        }
        return v
    }

    // returnResults() gets the parsed recipe results from the api call.
    // It checks if they are valid and if so, will display them in the
    // Recipe Results Activity.
    fun returnResults(result: Boolean){
        if (result){
            //Start new activity for Recipe Results
            val intent = Intent(v?.context, RecipeResults::class.java)
            intent.putStringArrayListExtra("ingredients", ingredients as ArrayList<String>?)
            v?.context.startActivity(intent)
        }
        else{
            val snack = Snackbar.make(FilterChoices.v,"No recipes were returned for the selected filters.", Snackbar.LENGTH_LONG)
            snack.show()
        }
    }

    // Companion object defines variables to be used throughout the class
    // It gives the variables the attributes of a "static" variable
    companion object {
        var ingredients = mutableListOf<String>()
        lateinit var v: View

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                RecipeByIngredient().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }

    // layout() defines the overall layout of the view and the edit texts.
    // once the enter button has been pressed the edit text animates and
    // the next becomes visible
    private fun layout() {
        val duration = 125
        ingredientText1.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                if (event.action === KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                        animate(ingredientText1, duration)
                        ingredientText2.visibility = View.VISIBLE
                    return true
                }
                return false
            }
        })

        ingredientText2.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                if (event.action === KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    animate(ingredientText2, duration)
                    ingredientText3.visibility = View.VISIBLE
                    return true
                }
                return false
            }
        })

        ingredientText3.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                if (event.action === KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    animate(ingredientText3, duration)
                    ingredientText4.visibility = View.VISIBLE
                    return true
                }
                return false
            }
        })

        ingredientText4.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                if (event.action === KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    animate(ingredientText4, duration)
                    ingredientText5.visibility = View.VISIBLE
                    return true
                }
                return false
            }
        })

        ingredientText5.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                if (event.action === KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    animate(ingredientText5, duration)
                    return true
                }
                return false
            }
        })
    }

        // The animate method is called on each editText on the screen.
        // It gets the width and height of the device's screen, and will then
        // ove the ediiText diagonally to the upper lef part of the screen
        private fun animate(ingredientText: EditText, aniDuration: Int){
            val w = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val d = w.defaultDisplay
            val metrics: DisplayMetrics = DisplayMetrics()
            d.getMetrics(metrics)

            val screenWidth: Int = -(metrics.widthPixels)
            val screenHeight: Int = -(metrics.heightPixels)

            val widthWantValue = 0.15
            val heightWantValue = 0.15

            val animator = AnimatorSet()
            val y = ObjectAnimator.ofFloat(ingredientText, "translationY", v.y, (screenHeight*heightWantValue).toFloat())
            val x = ObjectAnimator.ofFloat(ingredientText, "translationX", v.x, (screenWidth*widthWantValue).toFloat())

            animator.playTogether(x, y)
            animator.interpolator = LinearInterpolator()
            animator.duration = aniDuration.toLong()

            animator.start()
    }





}
