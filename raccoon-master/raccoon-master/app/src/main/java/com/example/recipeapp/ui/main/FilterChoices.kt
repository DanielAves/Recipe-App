package com.example.recipeapp.ui.main

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.recipeapp.ApiData
import com.example.recipeapp.R
import com.example.recipeapp.RecipeResults
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_filter_choices.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


/**
 * FilterChoices.kt is a fragment subclass which appears for further filtering, once the 'filter' button is clicked.
 * @author Dan Aves
 */


class FilterChoices : Fragment(), View.OnClickListener  {

    private var diet: String = ""
    private var health: String = ""
    private var cuisine: String = ""
    private var calories: String = ""
    private var results: String = ""
    lateinit var myDialog: Dialog
    private var mealBase: EditText? = null
    val dietList = mutableListOf<String>("balanced","high-fiber","high-protein","low-carb","low-fat","low-sodium")
    val dietSwitches = mutableListOf<Switch>()

    //Layouts and buttons for showing filter sections
    lateinit var freeFromButton: Button
    lateinit var freeFromLayout: RelativeLayout
    lateinit var dietButton: Button
    lateinit var dietLayout: RelativeLayout
    lateinit var cuisineLayout: RelativeLayout
    lateinit var cuisineButton: Button
    lateinit var caloriesButton: Button
    lateinit var caloriesLayout: RelativeLayout
    lateinit var resultsButton: Button
    lateinit var resultsLayout: RelativeLayout

    //SeekBar filters
    lateinit var caloriesMinSeek: SeekBar
    lateinit var caloriesMinTotal: TextView
    lateinit var caloriesMaxSeek: SeekBar
    lateinit var caloriesMaxTotal: TextView
    lateinit var resultsSeek: SeekBar
    lateinit var resultsTotal: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    /**
    Sets up the fragment when called
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_filter_choices, container, false)

        /*
        Diet filters
         */
        dietSwitches.add(v.findViewById<Switch>(R.id.balanced))
        dietSwitches.add(v.findViewById<Switch>(R.id.highFiber))
        dietSwitches.add(v.findViewById<Switch>(R.id.highProtein))
        dietSwitches.add(v.findViewById<Switch>(R.id.lowCarb))
        dietSwitches.add(v.findViewById<Switch>(R.id.lowFat))
        dietSwitches.add(v.findViewById<Switch>(R.id.lowSodium))

        /*
        Free from filters
        freeFrom list contains each specific string for the API call.
        freeFromSelection stores each switch from the section
         */

        val freeFrom = mutableListOf<String>("alcohol-free","celery-free","diary-free","egg-free","gluten-free","mustard-free","peanut-free","pork-free","red-meat-free","soy-free")
        val freeFromSwitches = mutableListOf<Switch>()
        freeFromSwitches.add(v.findViewById<Switch>(R.id.alcohol_free))
        freeFromSwitches.add(v.findViewById<Switch>(R.id.celery_free))
        freeFromSwitches.add(v.findViewById<Switch>(R.id.diary_free))
        freeFromSwitches.add(v.findViewById<Switch>(R.id.egg_free))
        freeFromSwitches.add(v.findViewById<Switch>(R.id.gluten_free))
        freeFromSwitches.add(v.findViewById<Switch>(R.id.mustard_free))
        freeFromSwitches.add(v.findViewById<Switch>(R.id.peanut_free))
        freeFromSwitches.add(v.findViewById<Switch>(R.id.pork_free))
        freeFromSwitches.add(v.findViewById<Switch>(R.id.redmeat_free))
        freeFromSwitches.add(v.findViewById<Switch>(R.id.soy_free))

        /*
        Cuisine filters
         */
        val cuisineSwitches = mutableListOf<Switch>()
        cuisineSwitches.add(v.findViewById<Switch>(R.id.american))
        cuisineSwitches.add(v.findViewById<Switch>(R.id.asian))
        cuisineSwitches.add(v.findViewById<Switch>(R.id.british))
        cuisineSwitches.add(v.findViewById<Switch>(R.id.caribbean))
        cuisineSwitches.add(v.findViewById<Switch>(R.id.centralEurope))
        cuisineSwitches.add(v.findViewById<Switch>(R.id.chinese))
        cuisineSwitches.add(v.findViewById<Switch>(R.id.easternEurope))
        cuisineSwitches.add(v.findViewById<Switch>(R.id.french))
        cuisineSwitches.add(v.findViewById<Switch>(R.id.indian))
        cuisineSwitches.add(v.findViewById<Switch>(R.id.italian))
        cuisineSwitches.add(v.findViewById<Switch>(R.id.japanese))
        cuisineSwitches.add(v.findViewById<Switch>(R.id.kosher))
        cuisineSwitches.add(v.findViewById<Switch>(R.id.mediterranean))
        cuisineSwitches.add(v.findViewById<Switch>(R.id.mexican))
        cuisineSwitches.add(v.findViewById<Switch>(R.id.middleEastern))
        cuisineSwitches.add(v.findViewById<Switch>(R.id.nordic))
        cuisineSwitches.add(v.findViewById<Switch>(R.id.southAmerican))
        cuisineSwitches.add(v.findViewById<Switch>(R.id.southEastAsian))

        /*
        Calories filter
         */
        caloriesMinSeek =  v.findViewById<SeekBar>(R.id.caloriesMinSeek)
        caloriesMinTotal = v.findViewById<TextView>(R.id.caloriesMinTotal)
        caloriesMaxSeek = v.findViewById<SeekBar>(R.id.caloriesMaxSeek)
        caloriesMaxTotal = v.findViewById<TextView>(R.id.caloriesMaxTotal)
        resultsSeek = v.findViewById<SeekBar>(R.id.resultsFrequencySeek)
        resultsTotal = v.findViewById<TextView>(R.id.resultsFrequencyTotal)

        /*
        On screen buttons
         */
        val submit =  v.findViewById<Button>(R.id.submitIngredients)
        val clearAllButton = v.findViewById<Button>(R.id.clearAll)

        freeFromButton = v.findViewById<Button>(R.id.freeFromButton)
        freeFromLayout = v.findViewById<RelativeLayout>(R.id.freeFromLayout)
        dietButton = v.findViewById<Button>(R.id.dietButton)
        dietLayout = v.findViewById<RelativeLayout>(R.id.dietLayout)
        cuisineLayout = v.findViewById<RelativeLayout>(R.id.cuisineTypeLayout)
        cuisineButton = v.findViewById<Button>(R.id.cuisineTypeButton)
        caloriesButton = v.findViewById<Button>(R.id.caloriesButton)
        caloriesLayout = v.findViewById<RelativeLayout>(R.id.caloriesLayout)
        resultsButton = v.findViewById<Button>(R.id.numOfResultsButton)
        resultsLayout = v.findViewById<RelativeLayout>(R.id.resultsLayout)

        //Handle layout expansion
        layoutExpanding()

        //Handle SeekBar alterations
        seekBarChanges()


        /*
        Call override method to only permit one selection from diet choices
         */
        dietSwitches[0].setOnClickListener(this)
        dietSwitches[1].setOnClickListener(this)
        dietSwitches[2].setOnClickListener(this)
        dietSwitches[3].setOnClickListener(this)
        dietSwitches[4].setOnClickListener(this)
        dietSwitches[5].setOnClickListener(this)


        /**
         * Handle user submitting filer choices for recipe search
         */
        submit.setOnClickListener{
            //Reset variables for each run
            diet = ""
            health = ""
            cuisine = ""
            calories = ""
            results = ""

            //check if a diet option is selected
            for (i in 0 until dietSwitches.size){
                if (dietSwitches[i].isChecked){
                diet = "&diet="
                diet += dietList[i]
                }
            }

            //Loop through each free from option
            for (i in 0 until freeFromSwitches.size) {
                if (freeFromSwitches[i].isChecked) {
                    health += "&health="
                    health += freeFrom[i]
                }
            }

            //Loop through each cuisine option
            for (i in 0 until 18) {
                if (cuisineSwitches[i].isChecked) {
                    cuisine += "&cuisinetype="
                    cuisine += cuisineSwitches[i].text.toString()
                }
            }

            //Handle seek bar progress for calories
            calories = "&calories=${caloriesMinSeek.progress * 10}"
            if (caloriesMaxSeek.progress !=0){
                calories += "-${caloriesMaxSeek.progress * 10}"
            }
            else{
                calories += "-1000"
            }

            //Number of results
            results = "&from=0&to=${resultsFrequencySeek.progress}"

            var text = mealBase?.text.toString()
            if(text.isEmpty()){
                mealBase?.setError("Please enter a mealbase");
                mealBase?.requestFocus();
            }
            else{
                //Pass final filter choices to ApiData to query API
                ApiData(mealBase!!.text.toString(),diet,health,cuisine,calories,results, 2)
            }
        }

        /**
        Handles deselecting each filter
         */
        clearAllButton.setOnClickListener{
            for (i in 0 until dietSwitches.size){
                dietSwitches[i].isChecked = false
            }
            for (i in 0 until freeFromSwitches.size){
                freeFromSwitches[i].isChecked = false
            }
            for (i in 0 until cuisineSwitches.size){
                cuisineSwitches[i].isChecked = false
            }
            caloriesMaxSeek.progress = 0
            caloriesMinSeek.progress = 0
            resultsSeek.progress = 10
        }
        return v
    }

    /**
     * Checks so see whether the API returned data  performs the respective next steps
     * @param result to specify whether the API call was successful
     */

    fun returnResults(result: Boolean){

        if (result){
            //Start new activity for Recipe Results
            val intent = Intent(v?.context, RecipeResults::class.java)
            v?.context.startActivity(intent)
            }
        else{
            val snack = Snackbar.make(v,"No recipes were returned for the selected filters.", Snackbar.LENGTH_LONG)
            snack.show()
        }
    }

    companion object {
        //View defined so it cannot be reset when new instances are created
        lateinit var v: View
        /**
         * Companion object called when a new instance of this fragment is created
         * @param textField Search text field from fragment_recipe_by_filter to be used in this fragment
         */
        @JvmStatic
        fun newInstance(textField: EditText) =
            FilterChoices().apply {
                mealBase = textField
                }
    }

    /**
     * Overrides on click listener to only permit one selection for the diet drop down
     */
    override fun onClick(view: View){
        for(i in 0 until dietSwitches.size){
            if(!dietSwitches[i].id.equals(view.id)){
                dietSwitches[i].isChecked = false
            }
        }
    }

    /**
     * This method handles a user adjusting any seek bars on the interface
     */
    private fun seekBarChanges(){
        /*
        Calories filter
        */
        caloriesMinSeek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val caloriesNum = progress * 10
                caloriesMinTotal.text = "Calories : $caloriesNum"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
        caloriesMaxSeek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val caloriesNum = progress * 10
                caloriesMaxTotal.text = "Calories : $caloriesNum"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        /*
        Results filter
         */
        resultsSeek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                resultsTotal.text = "Total results to show: $progress"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }

    /**
     * On click listeners for each filter section. The onclicklisteners contained within this method will
     * expand the respective layouts and handle the UI drawable icons.
     */
    private fun layoutExpanding(){
            /*
     On click listeners for each filter section. These methods expand the respective layouts and
     handle the UI drawable icons.
      */
        freeFromButton.setOnClickListener{
            if(freeFromLayout.visibility == View.GONE){
                val img = freeFromButton.context.resources.getDrawable(R.drawable.remove)
                freeFromButton.setCompoundDrawablesWithIntrinsicBounds( img, null, null, null);
                freeFromLayout.visibility = View.VISIBLE
            }
            else{
                freeFromLayout.visibility = View.GONE
                val img = freeFromButton.context.resources.getDrawable(R.drawable.add)
                freeFromButton.setCompoundDrawablesWithIntrinsicBounds( img, null, null, null);

            }
        }
        dietButton.setOnClickListener{
            if(dietLayout.visibility == View.GONE){
                val img = dietButton.context.resources.getDrawable(R.drawable.remove)
                dietButton.setCompoundDrawablesWithIntrinsicBounds( img, null, null, null);
                dietLayout.visibility = View.VISIBLE
            }
            else{
                dietLayout.visibility = View.GONE
                val img = dietButton.context.resources.getDrawable(R.drawable.add)
                dietButton.setCompoundDrawablesWithIntrinsicBounds( img, null, null, null);
            }
        }
        cuisineButton.setOnClickListener{
            if(cuisineLayout.visibility == View.GONE){
                val img = cuisineButton.context.resources.getDrawable(R.drawable.remove)
                cuisineButton.setCompoundDrawablesWithIntrinsicBounds( img, null, null, null);
                cuisineLayout.visibility = View.VISIBLE
            }
            else{
                cuisineLayout.visibility = View.GONE
                val img = cuisineButton.context.resources.getDrawable(R.drawable.add)
                cuisineButton.setCompoundDrawablesWithIntrinsicBounds( img, null, null, null);
            }
        }
        caloriesButton.setOnClickListener{
            if(caloriesLayout.visibility == View.GONE){
                val img = caloriesButton.context.resources.getDrawable(R.drawable.remove)
                caloriesButton.setCompoundDrawablesWithIntrinsicBounds( img, null, null, null);
                caloriesLayout.visibility = View.VISIBLE
            }
            else{
                caloriesLayout.visibility = View.GONE
                val img = caloriesButton.context.resources.getDrawable(R.drawable.add)
                caloriesButton.setCompoundDrawablesWithIntrinsicBounds( img, null, null, null);
            }
        }
        resultsButton.setOnClickListener{
            if(resultsLayout.visibility == View.GONE){
                val img = resultsButton.context.resources.getDrawable(R.drawable.remove)
                resultsButton.setCompoundDrawablesWithIntrinsicBounds( img, null, null, null);
                resultsLayout.visibility = View.VISIBLE
            }
            else{
                resultsLayout.visibility = View.GONE
                val img = resultsButton.context.resources.getDrawable(R.drawable.add)
                resultsButton.setCompoundDrawablesWithIntrinsicBounds( img, null, null, null);
            }
        }
    }
}
