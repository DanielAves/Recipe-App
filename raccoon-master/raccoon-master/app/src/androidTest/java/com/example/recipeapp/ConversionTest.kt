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
class ConversionTest {
    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

//    @Test
//    fun milligrams_to_grams(){
//        Espresso.onView(ViewMatchers.withText("Conversion")).perform(ViewActions.click())
//        Espresso.onView(ViewMatchers.withId(R.id.weightInput)).perform(ViewActions.typeText("456"))
//        Espresso.onView(ViewMatchers.withId(R.id.sp_from)).perform(ViewActions.click())
//
//        Espresso.onData(View.allOf(`is`(instanceOf(String::class.java)),
//            `is`("Milligrams (mg)"))).perform(click())
//        Espresso.onView(ViewMatchers.withId(R.id.sp_to)).perform(ViewActions.click())
//        Espresso.onData(View.allOf(`is`(instanceOf(String::class.java)),
//            `is`("Grams (g)"))).perform(click())
//
//        Espresso.onView(ViewMatchers.withId(R.id.convert1)).perform(ViewActions.click())
//        Espresso.onView(ViewMatchers.withId(R.id.result)).check(ViewAssertions.matches(ViewMatchers.withText("0.456")))
//
//    }
//
//    @Test
//    fun milligrams_to_ounces(){
//        Espresso.onView(ViewMatchers.withId(R.id.weightInput)).perform(ViewActions.typeText("456"))
//        Espresso.onView(ViewMatchers.withId(R.id.sp_from)).perform(ViewActions.click())
//
//        Espresso.onData(View.allOf(`is`(instanceOf(String::class.java)),
//            `is`("Milligrams (mg)"))).perform(click())
//
//        Espresso.onView(ViewMatchers.withId(R.id.sp_to)).perform(ViewActions.click())
//        Espresso.onData(View.allOf(`is`(instanceOf(String::class.java)),
//            `is`("Ounces (oz)"))).perform(click())
//
//        Espresso.onView(ViewMatchers.withId(R.id.convert1)).perform(ViewActions.click())
//        Espresso.onView(ViewMatchers.withId(R.id.result)).check(ViewAssertions.matches(ViewMatchers.withText("0.456")))
//
//    }
//
//    @Test
//    fun grams_to_ounces(){
//        Espresso.onView(ViewMatchers.withId(R.id.weightInput)).perform(ViewActions.typeText("456"))
//        Espresso.onView(ViewMatchers.withId(R.id.sp_from)).perform(ViewActions.click())
//
//        Espresso.onData(View.allOf(`is`(instanceOf(String::class.java)),
//            `is`("Grams (g)"))).perform(click())
//
//        Espresso.onView(ViewMatchers.withId(R.id.sp_to)).perform(ViewActions.click())
//        Espresso.onData(View.allOf(`is`(instanceOf(String::class.java)),
//            `is`("Ounces (oz)"))).perform(click())
//
//        Espresso.onView(ViewMatchers.withId(R.id.convert1)).perform(ViewActions.click())
//        Espresso.onView(ViewMatchers.withId(R.id.result)).check(ViewAssertions.matches(ViewMatchers.withText("0.456")))
//
//    }
//
//    @Test
//    fun grams_to_miligrams(){
//        Espresso.onView(ViewMatchers.withId(R.id.weightInput)).perform(ViewActions.typeText("456"))
//        Espresso.onView(ViewMatchers.withId(R.id.sp_from)).perform(ViewActions.click())
//
//        Espresso.onData(View.allOf(`is`(instanceOf(String::class.java)),
//            `is`("Grams (g)"))).perform(click())
//
//        Espresso.onView(ViewMatchers.withId(R.id.sp_to)).perform(ViewActions.click())
//        Espresso.onData(View.allOf(`is`(instanceOf(String::class.java)),
//            `is`("Miligrams (mg)"))).perform(click())
//
//        Espresso.onView(ViewMatchers.withId(R.id.convert1)).perform(ViewActions.click())
//        Espresso.onView(ViewMatchers.withId(R.id.result)).check(ViewAssertions.matches(ViewMatchers.withText("0.456")))
//
//    }
//
//
//    @Test
//    fun ounces_to_miligrams(){
//        Espresso.onView(ViewMatchers.withId(R.id.weightInput)).perform(ViewActions.typeText("456"))
//        Espresso.onView(ViewMatchers.withId(R.id.sp_from)).perform(ViewActions.click())
//
//        Espresso.onData(View.allOf(`is`(instanceOf(String::class.java)),
//            `is`("Ounces (oz)"))).perform(click())
//        Espresso.onView(ViewMatchers.withId(R.id.sp_to)).perform(ViewActions.click())
//        Espresso.onData(View.allOf(`is`(instanceOf(String::class.java)),
//            `is`("Milligrams (mg)"))).perform(click())
//
//        Espresso.onView(ViewMatchers.withId(R.id.convert1)).perform(ViewActions.click())
//        Espresso.onView(ViewMatchers.withId(R.id.result)).check(ViewAssertions.matches(ViewMatchers.withText("0.456")))
//
//    }
//
//    @Test
//    fun ounces_to_grams(){
//        Espresso.onView(ViewMatchers.withId(R.id.weightInput)).perform(ViewActions.typeText("456"))
//        Espresso.onView(ViewMatchers.withId(R.id.sp_from)).perform(ViewActions.click())
//
//        Espresso.onData(View.allOf(`is`(instanceOf(String::class.java)),
//            `is`("Ounces (oz)"))).perform(click())
//        Espresso.onView(ViewMatchers.withId(R.id.sp_to)).perform(ViewActions.click())
//        Espresso.onData(View.allOf(`is`(instanceOf(String::class.java)),
//            `is`("Grams (g)"))).perform(click())
//
//        Espresso.onView(ViewMatchers.withId(R.id.convert1)).perform(ViewActions.click())
//        Espresso.onView(ViewMatchers.withId(R.id.result)).check(ViewAssertions.matches(ViewMatchers.withText("0.456")))
//
//    }

}