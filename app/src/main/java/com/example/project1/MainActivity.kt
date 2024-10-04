package com.example.project1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlin.math.cos
import kotlin.math.ln
import kotlin.math.log10
import kotlin.math.sin
import kotlin.math.tan

class MainActivity : AppCompatActivity() {
    private var canAddOperation = false
    private var canAddDecimal = true
    private var canAddPercent = false
    private var textLineText = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textLine = findViewById<TextView>(R.id.text)

        // Restore state if available
        if (savedInstanceState != null) {
            textLineText = savedInstanceState.getString("TEXT_KEY").toString()
            textLine.text = textLineText
        }

        // Number buttons
        val buttonIds = listOf(R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9)
        buttonIds.forEach { id ->
            findViewById<Button>(id).setOnClickListener {
                val newText = textLine.text.toString() + (it as Button).text
                textLineText = newText
                textLine.text = newText
                canAddOperation = true
                canAddPercent = true
            }
        }

        // Operation buttons
        findViewById<Button>(R.id.plus).setOnClickListener { appendOperation(textLine, "+") }
        findViewById<Button>(R.id.minus).setOnClickListener { appendOperation(textLine, "-") }
        findViewById<Button>(R.id.multiply).setOnClickListener { appendOperation(textLine, "x") }
        findViewById<Button>(R.id.divide).setOnClickListener { appendOperation(textLine, "/") }

        // Special buttons
        findViewById<Button>(R.id.c).setOnClickListener {
            textLine.text = ""
            textLineText = ""
            canAddOperation = false
            canAddDecimal = true
            canAddPercent = false
        }

        findViewById<Button>(R.id.plusMinus).setOnClickListener {
            val newText = textLine.text.toString() + "–"
            textLineText = newText
            textLine.text = newText
        }

        findViewById<Button>(R.id.percent).setOnClickListener {
            if (canAddPercent) {
                canAddPercent = false
                val newText = textLine.text.toString() + "%"
                textLineText = newText
                textLine.text = newText
            }
        }

        findViewById<Button>(R.id.decimal).setOnClickListener {
            if (canAddDecimal) {
                canAddDecimal = false
                val newText = textLine.text.toString() + "."
                textLineText = newText
                textLine.text = newText
            }
        }

        findViewById<Button>(R.id.equals).setOnClickListener {
            val solution = evaluate(textLine.text.toString())
            textLineText = solution
            textLine.text = solution
        }

        // Check if landscape-only buttons are present and set listeners
        val sinButton = findViewById<Button?>(R.id.sin)
        val cosButton = findViewById<Button?>(R.id.cos)
        val tanButton = findViewById<Button?>(R.id.tan)
        val logButton = findViewById<Button?>(R.id.log)
        val lnButton = findViewById<Button?>(R.id.ln)

        sinButton?.setOnClickListener { appendFunction(textLine, "sin(") }
        cosButton?.setOnClickListener { appendFunction(textLine, "cos(") }
        tanButton?.setOnClickListener { appendFunction(textLine, "tan(") }
        logButton?.setOnClickListener { appendFunction(textLine, "log(") }
        lnButton?.setOnClickListener { appendFunction(textLine, "ln(") }
    }

    private fun appendOperation(textLine: TextView, operation: String) {
        if (canAddOperation) {
            canAddOperation = false
            canAddDecimal = true
            canAddPercent = false
            val newText = textLine.text.toString() + operation
            textLineText = newText
            textLine.text = newText
        }
    }

    private fun appendFunction(textLine: TextView, function: String) {
        val newText = textLine.text.toString() + function
        textLineText = newText
        textLine.text = newText
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("TEXT_KEY", textLineText)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        if (savedInstanceState != null) {
            textLineText = savedInstanceState.getString("TEXT_KEY").toString()
        }
        super.onRestoreInstanceState(savedInstanceState)
    }

    private fun evaluate(s: String): String {
        var solution = ""
        if (s.isNotEmpty()) {
            val nums = s.split("+", "-", "x", "/").toMutableList()
            var opps = s.filter { it == '+' || it == '-' || it == 'x' || it == '/' }

            if (s.contains("–")) {
                nums.replaceAll { it.replace("–", "-") }
            }
            if (s.contains('%')) {
                nums.replaceAll { if (it.contains('%')) (it.replace("%", "").toDouble() / 100).toString() else it }
            }
            if (s.contains("sin(") || s.contains("cos(") || s.contains("tan(") || s.contains("log(") || s.contains("ln(")) {
                for ((index, value) in nums.withIndex()) {
                    val valSplit = value.split("(").toMutableList()
                    valSplit.replaceAll {
                        when (it) {
                            "sin" -> sin(Math.toRadians(valSplit.last().toDouble())).toString()
                            "cos" -> cos(Math.toRadians(valSplit.last().toDouble())).toString()
                            "tan" -> tan(Math.toRadians(valSplit.last().toDouble())).toString()
                            "log" -> log10(valSplit.last().toDouble()).toString()
                            "ln" -> ln(valSplit.last().toDouble()).toString()
                            else -> it
                        }
                    }
                    nums[index] = valSplit[0]
                }
            }

            if (s.contains('x') || s.contains('/')) {
                var count = 0
                while (opps.contains('x') || opps.contains('/')) {
                    when (opps[count]) {
                        'x' -> {
                            val newNum = nums[count].toDouble() * nums[count + 1].toDouble()
                            nums[count] = newNum.toString()
                            nums.removeAt(count + 1)
                            opps = opps.replaceFirst("x", "")
                        }
                        '/' -> {
                            val newNum = nums[count].toDouble() / nums[count + 1].toDouble()
                            nums[count] = newNum.toString()
                            nums.removeAt(count + 1)
                            opps = opps.replaceFirst("/", "")
                        }
                        else -> count++
                    }
                }
            }
            if (s.contains('+') || s.contains('-')) {
                var count = 0
                while (opps.contains('+') || opps.contains('-')) {
                    when (opps[count]) {
                        '+' -> {
                            val newNum = nums[count].toDouble() + nums[count + 1].toDouble()
                            nums[count] = newNum.toString()
                            nums.removeAt(count + 1)
                            opps = opps.replaceFirst("+", "")
                        }
                        '-' -> {
                            val newNum = nums[count].toDouble() - nums[count + 1].toDouble()
                            nums[count] = newNum.toString()
                            nums.removeAt(count + 1)
                            opps = opps.replaceFirst("-", "")
                        }
                        else -> count++
                    }
                }
            }
            solution = nums[0]
        }
        return solution
    }
}
