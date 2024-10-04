package com.example.project1

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import android.content.pm.ActivityInfo
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    // Test single digit input
    @Test
    fun testSingleDigitInput() {
        onView(withId(R.id.c)).perform(click())
        onView(withId(R.id.btn1)).perform(click())
        onView(withId(R.id.btn2)).perform(click())
        onView(withId(R.id.btn3)).perform(click())
        onView(withId(R.id.text)).check(matches(withText("123")))
    }

    // Test addition operation
    @Test
    fun testAdditionOperation() {
        onView(withId(R.id.c)).perform(click())
        onView(withId(R.id.btn1)).perform(click())
        onView(withId(R.id.plus)).perform(click())
        onView(withId(R.id.btn2)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.text)).check(matches(withText("3.0")))
    }

    // Test subtraction operation
    @Test
    fun testSubtractionOperation() {
        onView(withId(R.id.c)).perform(click())
        onView(withId(R.id.btn3)).perform(click())
        onView(withId(R.id.minus)).perform(click())
        onView(withId(R.id.btn1)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.text)).check(matches(withText("2.0")))
    }

    // Test multiplication operation
    @Test
    fun testMultiplicationOperation() {
        onView(withId(R.id.c)).perform(click())
        onView(withId(R.id.btn2)).perform(click())
        onView(withId(R.id.multiply)).perform(click())
        onView(withId(R.id.btn3)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.text)).check(matches(withText("6.0")))
    }

    // Test (1 . 3)
    @Test
    fun testDecimalPointInput() {
        onView(withId(R.id.c)).perform(click())
        onView(withId(R.id.btn1)).perform(click())
        onView(withId(R.id.decimal)).perform(click())
        onView(withId(R.id.btn3)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.text)).check(matches(withText("1.3")))
    }

    // Test clear functionality
    @Test
    fun testClearFunctionality() {
        onView(withId(R.id.c)).perform(click())
        onView(withId(R.id.btn5)).perform(click())
        onView(withId(R.id.plus)).perform(click())
        onView(withId(R.id.btn5)).perform(click())
        onView(withId(R.id.c)).perform(click())
        onView(withId(R.id.text)).check(matches(withText("")))
    }

    // Test Sin
    @Test
    fun testSinFunction() {
        onView(withId(R.id.c)).perform(click())
        activityRule.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        onView(withId(R.id.sin)).perform(click())
        onView(withId(R.id.btn9)).perform(click())
        onView(withId(R.id.btn0)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.text)).check(matches(withText("1.0")))
    }

    // Test Cos
    @Test
    fun testCosFunction() {
        onView(withId(R.id.c)).perform(click())
        activityRule.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        onView(withId(R.id.cos)).perform(click())
        onView(withId(R.id.btn0)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.text)).check(matches(withText("1.0")))
    }

    // Test (1 + 2 * 3)
    @Test
    fun testComplexExpression() {
        onView(withId(R.id.c)).perform(click())
        onView(withId(R.id.btn1)).perform(click())
        onView(withId(R.id.plus)).perform(click())
        onView(withId(R.id.btn2)).perform(click())
        onView(withId(R.id.multiply)).perform(click())
        onView(withId(R.id.btn3)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.text)).check(matches(withText("7.0")))
    }

    // Test division by zero
    @Test
    fun testDivisionByZero() {
        onView(withId(R.id.c)).perform(click())
        onView(withId(R.id.btn1)).perform(click())
        onView(withId(R.id.divide)).perform(click())
        onView(withId(R.id.btn0)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.text)).check(matches(withText("Infinity")))
    }
}
