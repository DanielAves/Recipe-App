package com.example.recipeapp

import android.view.KeyEvent
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Matchers.anything
import org.junit.Rule
import org.junit.Test


/**
 * This testing class ensures all the recipe ingredient inputs are accepted and
 * produce results based on the inputs
 */
class RecipeResultsIngredientsTest {
    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    // Test checking if the results are displayed from a 3 ingredient query to the api
    @Test
    fun recipe_ingredients_results_3(){
        Espresso.onView(ViewMatchers.withText("By Ingredient")).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch1)).check(ViewAssertions.matches(ViewMatchers.withHint("Ingredient")))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch1)).perform(ViewActions.typeText("chicken")).perform(ViewActions.pressKey(KeyEvent.KEYCODE_ENTER))

        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch2)).check(ViewAssertions.matches(ViewMatchers.withHint("Ingredient")))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch2)).perform(ViewActions.typeText("tomato")).perform(ViewActions.pressKey(KeyEvent.KEYCODE_ENTER))

        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch3)).check(ViewAssertions.matches(ViewMatchers.withHint("Ingredient")))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch3)).perform(ViewActions.typeText("pasta")).perform(ViewActions.pressKey(KeyEvent.KEYCODE_ENTER)).perform(ViewActions.pressKey(KeyEvent.KEYCODE_TAB))

        Espresso.onView(ViewMatchers.withId(R.id.submitIngredients)).perform(ViewActions.click())
        Thread.sleep(1500);
        Espresso.onView(ViewMatchers.withId(R.id.gridView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    // Test checking if the results are displayed from a 4 ingredient query to the api
    @Test
    fun recipe_ingredients_results_4(){
        Espresso.onView(ViewMatchers.withText("By Ingredient")).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch1)).check(ViewAssertions.matches(ViewMatchers.withHint("Ingredient")))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch1)).perform(ViewActions.typeText("chicken")).perform(ViewActions.pressKey(KeyEvent.KEYCODE_ENTER))

        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch2)).check(ViewAssertions.matches(ViewMatchers.withHint("Ingredient")))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch2)).perform(ViewActions.typeText("tomato")).perform(ViewActions.pressKey(KeyEvent.KEYCODE_ENTER))

        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch3)).check(ViewAssertions.matches(ViewMatchers.withHint("Ingredient")))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch3)).perform(ViewActions.typeText("pasta")).perform(ViewActions.pressKey(KeyEvent.KEYCODE_ENTER))

        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch4)).check(ViewAssertions.matches(ViewMatchers.withHint("Ingredient")))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch4)).perform(ViewActions.typeText("cheese")).perform(ViewActions.pressKey(KeyEvent.KEYCODE_ENTER)).perform(ViewActions.pressKey(KeyEvent.KEYCODE_TAB))

        Espresso.onView(ViewMatchers.withId(R.id.submitIngredients)).perform(ViewActions.click())
        Thread.sleep(2000);
        Espresso.onView(ViewMatchers.withId(R.id.gridView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    // Test checking if the results are displayed from a 5 ingredient query to the api
    @Test
    fun recipe_ingredients_results_5(){
        Espresso.onView(ViewMatchers.withText("By Ingredient")).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch1)).check(ViewAssertions.matches(ViewMatchers.withHint("Ingredient")))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch1)).perform(ViewActions.typeText("chicken")).perform(ViewActions.pressKey(KeyEvent.KEYCODE_ENTER))

        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch2)).check(ViewAssertions.matches(ViewMatchers.withHint("Ingredient")))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch2)).perform(ViewActions.typeText("tomato")).perform(ViewActions.pressKey(KeyEvent.KEYCODE_ENTER))

        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch3)).check(ViewAssertions.matches(ViewMatchers.withHint("Ingredient")))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch3)).perform(ViewActions.typeText("pasta")).perform(ViewActions.pressKey(KeyEvent.KEYCODE_ENTER))

        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch4)).check(ViewAssertions.matches(ViewMatchers.withHint("Ingredient")))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch4)).perform(ViewActions.typeText("cheese")).perform(ViewActions.pressKey(KeyEvent.KEYCODE_ENTER))

        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch5)).check(ViewAssertions.matches(ViewMatchers.withHint("Ingredient")))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch5)).perform(ViewActions.typeText("basil")).perform(ViewActions.pressKey(KeyEvent.KEYCODE_ENTER))

        Espresso.onView(ViewMatchers.withId(R.id.submitIngredients)).perform(ViewActions.click())
        Thread.sleep(3000);
        Espresso.onView(ViewMatchers.withId(R.id.gridView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    // Checks if after a 3 ingredient query the screen allows the user to scroll on the scrollview
    @Test
    fun recipe_results_scroll(){
        Espresso.onView(ViewMatchers.withText("By Ingredient")).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch1)).check(ViewAssertions.matches(ViewMatchers.withHint("Ingredient")))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch1)).perform(ViewActions.typeText("chicken")).perform(ViewActions.pressKey(KeyEvent.KEYCODE_ENTER))

        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch2)).check(ViewAssertions.matches(ViewMatchers.withHint("Ingredient")))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch2)).perform(ViewActions.typeText("tomato")).perform(ViewActions.pressKey(KeyEvent.KEYCODE_ENTER))

        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch3)).check(ViewAssertions.matches(ViewMatchers.withHint("Ingredient")))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch3)).perform(ViewActions.typeText("pasta")).perform(ViewActions.pressKey(KeyEvent.KEYCODE_ENTER)).perform(ViewActions.pressKey(KeyEvent.KEYCODE_TAB))

        Espresso.onView(ViewMatchers.withId(R.id.submitIngredients)).perform(ViewActions.click())
        Thread.sleep(1500);
        Espresso.onView(ViewMatchers.withId(R.id.gridView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.gridView)).perform(ViewActions.swipeUp())
        Espresso.onView(ViewMatchers.withId(R.id.gridView)).perform(ViewActions.swipeDown())
    }

    // Test checking if the results are displayed from a 3 ingredient query to the api
    @Test
    fun recipe_ingredients_results_details(){
        Espresso.onView(ViewMatchers.withText("By Ingredient")).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch1)).check(ViewAssertions.matches(ViewMatchers.withHint("Ingredient")))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch1)).perform(ViewActions.typeText("chicken")).perform(ViewActions.pressKey(KeyEvent.KEYCODE_ENTER))

        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch2)).check(ViewAssertions.matches(ViewMatchers.withHint("Ingredient")))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch2)).perform(ViewActions.typeText("tomato")).perform(ViewActions.pressKey(KeyEvent.KEYCODE_ENTER))

        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch3)).check(ViewAssertions.matches(ViewMatchers.withHint("Ingredient")))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch3)).perform(ViewActions.typeText("pasta")).perform(ViewActions.pressKey(KeyEvent.KEYCODE_ENTER)).perform(ViewActions.pressKey(KeyEvent.KEYCODE_TAB))

        Espresso.onView(ViewMatchers.withId(R.id.submitIngredients)).perform(ViewActions.click())
        Thread.sleep(1500);
        Espresso.onView(ViewMatchers.withId(R.id.gridView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onData(anything()).inAdapterView(withId(R.id.gridView)).atPosition(0).perform(click());
    }

    // Test checking if the selected item in the grid view can have the remaining ingredients needed
    // to make the recipe are sent to the shopping list
    @Test
    fun add_ingredients_to_shopping_list(){
        Espresso.onView(ViewMatchers.withText("By Ingredient")).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch1)).check(ViewAssertions.matches(ViewMatchers.withHint("Ingredient")))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch1)).perform(ViewActions.typeText("chicken")).perform(ViewActions.pressKey(KeyEvent.KEYCODE_ENTER))

        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch2)).check(ViewAssertions.matches(ViewMatchers.withHint("Ingredient")))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch2)).perform(ViewActions.typeText("tomato")).perform(ViewActions.pressKey(KeyEvent.KEYCODE_ENTER))

        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch3)).check(ViewAssertions.matches(ViewMatchers.withHint("Ingredient")))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch3)).perform(ViewActions.typeText("pasta")).perform(ViewActions.pressKey(KeyEvent.KEYCODE_ENTER)).perform(ViewActions.pressKey(KeyEvent.KEYCODE_TAB))
        Espresso.onView(ViewMatchers.withId(R.id.submitIngredients)).perform(ViewActions.click())

        Thread.sleep(1500);

        Espresso.onView(ViewMatchers.withId(R.id.gridView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onData(anything()).inAdapterView(withId(R.id.gridView)).atPosition(0).perform(click())

        Espresso.onView(ViewMatchers.withText("Add Ingredients to Shopping List")).perform(ViewActions.click())
    }

    // Checks the shopping list to see if the ingredient from the recipe have been added to the list and are displayed
    @Test
    fun check_shopping_list_for_ingredients(){
        Espresso.onView(ViewMatchers.withText("Shopping List")).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.shoppingListView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }


}