package com.example.recipeapp

import android.widget.GridView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.hamcrest.Matchers.not


/**
 * This testing class ensures all the recipe filters are working correctly
 */
class RecipeSearchResultsTest {
    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun recipe_results(){
        Espresso.onView(ViewMatchers.withId(R.id.textInputEditText)).perform(ViewActions.typeText("milkshake"))
        Espresso.onView(ViewMatchers.withId(R.id.sendSearch)).perform(ViewActions.click())
        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withId(R.id.gridView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }
    @Test
    fun scroll_possible(){
        Espresso.onView(ViewMatchers.withId(R.id.textInputEditText)).perform(ViewActions.typeText("milkshake"))
        Espresso.onView(ViewMatchers.withId(R.id.sendSearch)).perform(ViewActions.click())
        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withId(R.id.gridView)).perform(ViewActions.swipeUp())

    }


}