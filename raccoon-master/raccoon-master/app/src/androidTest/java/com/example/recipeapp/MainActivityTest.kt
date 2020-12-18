package com.example.recipeapp

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * MainActivityTest ensures the three main pages of the app can be clicked and loaded successfully.
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun user_can_click(){
        Espresso.onView(ViewMatchers.withText("Search")).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withText("Shopping List")).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withText("Conversion")).perform(ViewActions.click())
    }
    @Test
    fun recipe_search_loaded(){
        Espresso.onView(ViewMatchers.withId(R.id.textInputEditText)).check(ViewAssertions.matches(ViewMatchers.withHint("Meal Base")))
    }

    @Test
    fun recipe_ingredients_loaded(){
        Espresso.onView(ViewMatchers.withText("By Ingredient")).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.searchIngredient)).check(ViewAssertions.matches(ViewMatchers.withText("Search for recipes by their ingredients")))
    }

    @Test
    fun recipe_shoppingList_loaded(){
        Espresso.onView(ViewMatchers.withText("Shopping List")).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.editText)).check(ViewAssertions.matches(ViewMatchers.withHint("Add an Item")))
    }

    @Test
    fun conversion_loaded(){
        Espresso.onView(ViewMatchers.withText("Conversion")).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.MetricConverter)).check(ViewAssertions.matches(ViewMatchers.withText("Metric Converter")))

    }
}

