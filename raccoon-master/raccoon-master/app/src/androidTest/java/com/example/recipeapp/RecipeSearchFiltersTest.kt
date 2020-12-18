package com.example.recipeapp

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isChecked
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.hamcrest.Matchers.not


/**
 * This testing class ensures all the recipe filters are working correctly
 */
class RecipeSearchFiltersTest {
    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun meal_base_entry(){
        Espresso.onView(ViewMatchers.withId(R.id.textInputEditText)).check(
            ViewAssertions.matches(ViewMatchers.withHint("Meal Base")))
        Espresso.onView(ViewMatchers.withId(R.id.textInputEditText)).perform(ViewActions.typeText("milkshake"))
        Espresso.onView(ViewMatchers.withId(R.id.textInputEditText)).check(ViewAssertions.matches(ViewMatchers.withText("milkshake")))

    }

    @Test
    fun filter_dropdown(){
        Espresso.onView(ViewMatchers.withId(R.id.filter_button)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.dietButton)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.freeFromButton)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.cuisineTypeButton)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.caloriesButton)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.numOfResultsButton)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.clearAll)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.submitIngredients)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }

    @Test
    fun filter_dropdown_retracted(){
        Espresso.onView(ViewMatchers.withId(R.id.filter_button)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.dietLayout)).check(matches(not(isDisplayed())))
        Espresso.onView(ViewMatchers.withId(R.id.cuisineTypeLayout)).check(matches(not(isDisplayed())))
        Espresso.onView(ViewMatchers.withId(R.id.caloriesLayout)).check(matches(not(isDisplayed())))
        Espresso.onView(ViewMatchers.withId(R.id.freeFromLayout)).check(matches(not(isDisplayed())))
        Espresso.onView(ViewMatchers.withId(R.id.resultsLayout)).check(matches(not(isDisplayed())))
    }

    @Test
    fun filter_dropdown_expanded(){
        Espresso.onView(ViewMatchers.withId(R.id.filter_button)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.dietButton)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.dietLayout)).check(matches(isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.balanced)).check(matches(isDisplayed()))
    }

    @Test
    fun only_one_diet_filter(){
        Espresso.onView(ViewMatchers.withId(R.id.filter_button)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.dietButton)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.balanced)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.highFiber)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.balanced)).check(matches(not(isChecked())))
    }

    @Test
    fun filter_seekbar(){
        Espresso.onView(ViewMatchers.withId(R.id.filter_button)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.caloriesButton)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.caloriesMinSeek)).perform(ViewActions.swipeRight())
        Espresso.onView(ViewMatchers.withId(R.id.caloriesMinTotal)).check(ViewAssertions.matches(ViewMatchers.withText("Calories : 1000")))
        Espresso.onView(ViewMatchers.withId(R.id.caloriesMinSeek)).perform(ViewActions.swipeLeft())
        Espresso.onView(ViewMatchers.withId(R.id.caloriesMinTotal)).check(ViewAssertions.matches(ViewMatchers.withText("Calories : 0")))
    }

    @Test
    fun clear_button(){
        Espresso.onView(ViewMatchers.withId(R.id.filter_button)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.dietButton)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.balanced)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.freeFromButton)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.alcohol_free)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.diary_free)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.gluten_free)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.freeFromButton)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.caloriesButton)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.caloriesMinSeek)).perform(ViewActions.swipeRight())

        Espresso.onView(ViewMatchers.withId(R.id.clearAll)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.balanced)).check(matches(not(isChecked())))
        Espresso.onView(ViewMatchers.withId(R.id.alcohol_free)).check(matches(not(isChecked())))
        Espresso.onView(ViewMatchers.withId(R.id.diary_free)).check(matches(not(isChecked())))
        Espresso.onView(ViewMatchers.withId(R.id.gluten_free)).check(matches(not(isChecked())))
        Espresso.onView(ViewMatchers.withId(R.id.caloriesMinTotal)).check(ViewAssertions.matches(ViewMatchers.withText("Calories : 0")))
    }

}