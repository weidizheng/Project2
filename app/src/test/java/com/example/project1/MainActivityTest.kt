package com.example.project1

import android.content.pm.ActivityInfo
import android.widget.Button
import android.widget.TextView
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [33])
class MainActivityTest {

    private lateinit var controller: ActivityController<MainActivity>
    private lateinit var activity: MainActivity
    private lateinit var display: TextView

    private lateinit var buttons: Map<Int, Button>

    @Before
    fun setUp() {
        controller = Robolectric.buildActivity(MainActivity::class.java).create().start().resume()
        activity = controller.get()
        display = activity.findViewById(R.id.text)

        buttons = mapOf(
            R.id.btn0 to activity.findViewById(R.id.btn0),
            R.id.btn1 to activity.findViewById(R.id.btn1),
            R.id.btn2 to activity.findViewById(R.id.btn2),
            R.id.btn3 to activity.findViewById(R.id.btn3),
            R.id.btn4 to activity.findViewById(R.id.btn4),
            R.id.btn5 to activity.findViewById(R.id.btn5),
            R.id.btn6 to activity.findViewById(R.id.btn6),
            R.id.btn7 to activity.findViewById(R.id.btn7),
            R.id.btn8 to activity.findViewById(R.id.btn8),
            R.id.btn9 to activity.findViewById(R.id.btn9),
            R.id.plus to activity.findViewById(R.id.plus),
            R.id.minus to activity.findViewById(R.id.minus),
            R.id.multiply to activity.findViewById(R.id.multiply),
            R.id.divide to activity.findViewById(R.id.divide),
            R.id.equals to activity.findViewById(R.id.equals),
            R.id.c to activity.findViewById(R.id.c),
            R.id.log to activity.findViewById(R.id.log),
            R.id.ln to activity.findViewById(R.id.ln),
            R.id.tan to activity.findViewById(R.id.tan),
            R.id.sin to activity.findViewById(R.id.sin),
            R.id.cos to activity.findViewById(R.id.cos),
            R.id.decimal to activity.findViewById(R.id.decimal)
        )
    }

    private fun click(vararg ids: Int) {
        ids.forEach { buttons[it]?.performClick() }
    }

    // Test single digit input
    @Test
    fun testSingleDigitInput() {
        click(R.id.c)
        click(R.id.btn1, R.id.btn2, R.id.btn3)
        assertEquals("123", display.text.toString())
    }

    // Test addition
    @Test
    fun testAdditionOperation() {
        click(R.id.c)
        click(R.id.btn1, R.id.plus, R.id.btn2, R.id.equals)
        assertEquals("3.0", display.text.toString())
    }

    // Test subtraction
    @Test
    fun testSubtractionOperation() {
        click(R.id.c)
        click(R.id.btn3, R.id.minus, R.id.btn1, R.id.equals)
        assertEquals("2.0", display.text.toString())
    }

    // Test multiplication
    @Test
    fun testMultiplicationOperation() {
        click(R.id.c)
        click(R.id.btn2, R.id.multiply, R.id.btn3, R.id.equals)
        assertEquals("6.0", display.text.toString())
    }

    // Test division
    @Test
    fun testDivisionOperation() {
        click(R.id.c)
        click(R.id.btn3, R.id.divide, R.id.btn1, R.id.equals)
        assertEquals("3.0", display.text.toString())
    }

    // Test decimal point input
    @Test
    fun testDecimalPointInput() {
        click(R.id.c)
        click(R.id.btn1, R.id.decimal, R.id.btn3)
        assertEquals("1.3", display.text.toString())
    }

    // Test clear button
    @Test
    fun testClearButton() {
        click(R.id.c)
        click(R.id.btn1, R.id.plus, R.id.btn2, R.id.c)
        assertEquals("", display.text.toString())
    }

    // Test log function in landscape mode
    @Test
    fun testLogFunction() {
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        click(R.id.c)
        click(R.id.log)
        click(R.id.btn1, R.id.btn0)
        click(R.id.equals)
        assertEquals("10", display.text.toString().trim())
    }

    // Test tan function in landscape mode
    @Test
    fun testTanFunction() {
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        click(R.id.c)
        click(R.id.tan)
        click(R.id.btn4, R.id.btn5)
        click(R.id.equals)
        assertEquals("45", display.text.toString().trim())
    }

    @Test
    fun testSinFunction() {
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        click(R.id.c)
        click(R.id.sin)
        click(R.id.btn9, R.id.btn0)
        click(R.id.equals)
        assertEquals("90", display.text.toString().trim())
    }


    // Test Cos function in landscape mode
    @Test
    fun testCosFunction() {
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        click(R.id.c)
        click(R.id.cos)
        click(R.id.btn0)
        click(R.id.equals)
        assertEquals("0", display.text.toString().trim())
    }

    // Test multiple operations sequence
    @Test
    fun testMultipleOperationsSequence() {
        click(R.id.c)
        click(R.id.btn1, R.id.plus, R.id.btn2, R.id.multiply, R.id.btn3, R.id.equals)
        assertEquals("7.0", display.text.toString())
    }

    // Test division by zero
    @Test
    fun testDivisionByZero() {
        click(R.id.c)
        click(R.id.btn1, R.id.divide, R.id.btn0, R.id.equals)
        assertEquals("Infinity", display.text.toString())
    }
}
