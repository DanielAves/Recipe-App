package com.example.recipeapp

import android.view.KeyEvent
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test


/**
 * This testing class ensures all the recipe ingredient inputs take an input
 * and the text displayed matches the string input
 */
class RecipeSearchIngredientsTest {
    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    // Test entering 5 ingredients and whether the displayed text matches the input string
    @Test
    fun edit_text_viewable_and_entry_5(){
        Espresso.onView(ViewMatchers.withText("By Ingredient")).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch1)).check(ViewAssertions.matches(ViewMatchers.withHint("Ingredient")))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch1)).perform(ViewActions.typeText("chicken")).perform(ViewActions.pressKey(KeyEvent.KEYCODE_ENTER))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch1)).check(ViewAssertions.matches(ViewMatchers.withText("chicken")))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch2)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch2)).check(ViewAssertions.matches(ViewMatchers.withHint("Ingredient")))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch2)).perform(ViewActions.typeText("tomato")).perform(ViewActions.pressKey(KeyEvent.KEYCODE_ENTER))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch2)).check(ViewAssertions.matches(ViewMatchers.withText("tomato")))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch3)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch3)).check(ViewAssertions.matches(ViewMatchers.withHint("Ingredient")))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch3)).perform(ViewActions.typeText("pasta")).perform(ViewActions.pressKey(KeyEvent.KEYCODE_ENTER))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch3)).check(ViewAssertions.matches(ViewMatchers.withText("pasta")))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch4)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch4)).check(ViewAssertions.matches(ViewMatchers.withHint("Ingredient")))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch4)).perform(ViewActions.typeText("cheese")).perform(ViewActions.pressKey(KeyEvent.KEYCODE_ENTER))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch4)).check(ViewAssertions.matches(ViewMatchers.withText("cheese")))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch5)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch5)).check(ViewAssertions.matches(ViewMatchers.withHint("Ingredient")))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch5)).perform(ViewActions.typeText("basil")).perform(ViewActions.pressKey(KeyEvent.KEYCODE_ENTER))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch5)).check(ViewAssertions.matches(ViewMatchers.withText("basil")))
    }

    // Test entering 4 ingredients and whether the displayed text matches the input string
    @Test
    fun edit_text_viewable_and_entry_4(){
        Espresso.onView(ViewMatchers.withText("By Ingredient")).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch1)).check(ViewAssertions.matches(ViewMatchers.withHint("Ingredient")))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch1)).perform(ViewActions.typeText("chicken")).perform(ViewActions.pressKey(KeyEvent.KEYCODE_ENTER))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch1)).check(ViewAssertions.matches(ViewMatchers.withText("chicken")))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch2)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch2)).check(ViewAssertions.matches(ViewMatchers.withHint("Ingredient")))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch2)).perform(ViewActions.typeText("tomato")).perform(ViewActions.pressKey(KeyEvent.KEYCODE_ENTER))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch2)).check(ViewAssertions.matches(ViewMatchers.withText("tomato")))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch3)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch3)).check(ViewAssertions.matches(ViewMatchers.withHint("Ingredient")))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch3)).perform(ViewActions.typeText("pasta")).perform(ViewActions.pressKey(KeyEvent.KEYCODE_ENTER))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch3)).check(ViewAssertions.matches(ViewMatchers.withText("pasta")))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch4)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch4)).check(ViewAssertions.matches(ViewMatchers.withHint("Ingredient")))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch4)).perform(ViewActions.typeText("cheese")).perform(ViewActions.pressKey(KeyEvent.KEYCODE_ENTER))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch4)).check(ViewAssertions.matches(ViewMatchers.withText("cheese")))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch5)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }

    // Test entering 3 ingredients and whether the displayed text matches the input string
    @Test
    fun edit_text_viewable_and_entry_3(){
        Espresso.onView(ViewMatchers.withText("By Ingredient")).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch1)).check(ViewAssertions.matches(ViewMatchers.withHint("Ingredient")))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch1)).perform(ViewActions.typeText("chicken")).perform(ViewActions.pressKey(KeyEvent.KEYCODE_ENTER))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch1)).check(ViewAssertions.matches(ViewMatchers.withText("chicken")))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch2)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch2)).check(ViewAssertions.matches(ViewMatchers.withHint("Ingredient")))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch2)).perform(ViewActions.typeText("tomato")).perform(ViewActions.pressKey(KeyEvent.KEYCODE_ENTER))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch2)).check(ViewAssertions.matches(ViewMatchers.withText("tomato")))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch3)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch3)).check(ViewAssertions.matches(ViewMatchers.withHint("Ingredient")))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch3)).perform(ViewActions.typeText("pasta")).perform(ViewActions.pressKey(KeyEvent.KEYCODE_ENTER))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch3)).check(ViewAssertions.matches(ViewMatchers.withText("pasta")))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch4)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    // Test entering 2 ingredients and whether the displayed text matches the input string
    @Test
    fun edit_text_viewable_and_entry_2(){
        Espresso.onView(ViewMatchers.withText("By Ingredient")).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch1)).check(ViewAssertions.matches(ViewMatchers.withHint("Ingredient")))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch1)).perform(ViewActions.typeText("chicken")).perform(ViewActions.pressKey(KeyEvent.KEYCODE_ENTER))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch1)).check(ViewAssertions.matches(ViewMatchers.withText("chicken")))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch2)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch2)).check(ViewAssertions.matches(ViewMatchers.withHint("Ingredient")))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch2)).perform(ViewActions.typeText("tomato")).perform(ViewActions.pressKey(KeyEvent.KEYCODE_ENTER))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch2)).check(ViewAssertions.matches(ViewMatchers.withText("tomato")))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch3)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }

    // Test entering an ingredient and whether the displayed text matches the input string
    @Test
    fun edit_text_viewable_and_entry_1(){
        Espresso.onView(ViewMatchers.withText("By Ingredient")).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch1)).check(ViewAssertions.matches(ViewMatchers.withHint("Ingredient")))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch1)).perform(ViewActions.typeText("chicken")).perform(ViewActions.pressKey(KeyEvent.KEYCODE_ENTER))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch1)).check(ViewAssertions.matches(ViewMatchers.withText("chicken")))
        Espresso.onView(ViewMatchers.withId(R.id.ingredientSearch2)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }

}