package com.example.recipeapp.ui.main

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.recipeapp.*
import com.google.android.material.snackbar.Snackbar


internal lateinit var myDialog: Dialog
/**
 * This fragment is displayed on the initial recipe search screen
 * @author Dan Aves
 */
class RecipeByFilter : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        v = inflater.inflate(R.layout.fragment_recipe_by_filter, container, false)
        val sendSearch: ImageButton = v.findViewById(R.id.sendSearch)

        var searchText = v.findViewById<View>(R.id.textInputEditText) as EditText

        val filterButton: Button = v.findViewById(R.id.filter_button)

        //Handle when Filter button is clicked by user
        filterButton.setOnClickListener{
            val filterChoices = FilterChoices.newInstance(searchText)
            //Set widgets to invisible
            filterButton.visibility = View.INVISIBLE
            sendSearch.visibility = View.INVISIBLE

            val fragmentManager:FragmentManager = activity!!.supportFragmentManager
            fragmentManager
                .beginTransaction()
                .replace(R.id.filterFrame, filterChoices)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
        }

        //Handle user clicking the search button
        sendSearch.setOnClickListener {
            var text = searchText.text.toString()
            if(text.isEmpty()){
                searchText.setError("Please enter a mealbase");
                searchText.requestFocus();
            }
            else{
                ApiData(searchText.text.toString(),"","","","","",1)
            }
        }
        // Inflate the layout for this fragment
        return v
    }

    /**
     * Checks so see whether the API returned data and performs the respective next steps
     * @param result to specify whether the API call was successful
     */
    fun returnResults(result: Boolean){

        if (result){
            val intent = Intent(RecipeByFilter.v?.context, RecipeResults::class.java)
            RecipeByFilter.v?.context.startActivity(intent)
        }
        else{
            val snack = Snackbar.make(FilterChoices.v,"No recipes were returned for the selected filters.", Snackbar.LENGTH_LONG)
            snack.show()
        }
    }

    companion object {
        //View defined so it cannot be reset when new instances are created
        lateinit var v: View
        /**
         * Companion object called when a new instance of this fragment is created
         */
        @JvmStatic fun newInstance() =
                RecipeByFilter().apply {

                }
    }


}
