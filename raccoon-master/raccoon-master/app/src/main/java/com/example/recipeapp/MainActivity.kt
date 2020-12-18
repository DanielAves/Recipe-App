package com.example.recipeapp

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.recipeapp.ui.main.RecipeByFilter
import com.example.recipeapp.ui.main.RecipeByIngredient
import com.example.recipeapp.ui.main.ShoppingListFragment
import com.example.recipeapp.ui.main.ConversionFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.VISIBLE
import kotlinx.android.synthetic.main.activity_main.*

/**
 * The MainActivity houses the nav bar for navigation between the various fragments within this app
 * @author Dan Aves
 */
class MainActivity : AppCompatActivity() {

    lateinit var recipeByFilter: RecipeByFilter
    lateinit var recipeByIngredient: RecipeByIngredient
    lateinit var shoppingListFragment: ShoppingListFragment
    lateinit var conversionFragment: ConversionFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val tabs: TabLayout = findViewById(R.id.navBar)
        val searchTabs: TabLayout = findViewById(R.id.navBarSearch)


        /*
        Create instances of fragments
         */
        recipeByFilter = RecipeByFilter()
        recipeByIngredient = RecipeByIngredient()
        shoppingListFragment = ShoppingListFragment()
        conversionFragment = ConversionFragment()



        /*
        Setup initial fragment for main page
         */
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_frame, recipeByFilter)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()



        /*
        Change to relevant fragment based on selected tab
         */
        tabs.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                lateinit var current:Fragment
                when(tab.position){
                    0 -> {
                        current = recipeByFilter
                        navBarSearch.visibility = View.VISIBLE
                    }
                    1 -> {
                        current = shoppingListFragment
                        navBarSearch.visibility = View.GONE
                    }
                    2 -> {
                        current = conversionFragment
                        navBarSearch.visibility = View.GONE
                    }

                }
                //
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_frame, current)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        /*
        Change to search fragment based on selection
         */
        searchTabs.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                lateinit var current:Fragment
                when(tab.position){
                    0 -> current = recipeByFilter
                    1 -> current = recipeByIngredient
                }
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_frame, current)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }
}


