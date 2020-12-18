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
 * This testing class ensures that the shopping list is populated properly and the functionality to clear is working.
 */
class ShoppingListTest {
    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun shopping_list_entry(){
        Espresso.onView(ViewMatchers.withText("Shopping List")).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.editText)).check(
            ViewAssertions.matches(ViewMatchers.withHint("Add an Item"))).perform(ViewActions.typeText("Chicken"))
        Espresso.onView(ViewMatchers.withId(R.id.addItem)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.editText)).check(
            ViewAssertions.matches(ViewMatchers.withHint("Add an Item"))).perform(ViewActions.typeText("Beef"))
        Espresso.onView(ViewMatchers.withId(R.id.addItem)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.editText)).check(
            ViewAssertions.matches(ViewMatchers.withHint("Add an Item"))).perform(ViewActions.typeText("Carrots"))
        Espresso.onView(ViewMatchers.withId(R.id.addItem)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.editText)).check(
            ViewAssertions.matches(ViewMatchers.withHint("Add an Item"))).perform(ViewActions.typeText("Pasta"))
        Espresso.onView(ViewMatchers.withId(R.id.addItem)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.editText)).check(
            ViewAssertions.matches(ViewMatchers.withHint("Add an Item"))).perform(ViewActions.typeText("Potato"))
        Espresso.onView(ViewMatchers.withId(R.id.addItem)).perform(ViewActions.click())
    }

    @Test
    fun clear_button(){
        Espresso.onView(ViewMatchers.withText("Shopping List")).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.clearList)).perform(ViewActions.click())
    }

}