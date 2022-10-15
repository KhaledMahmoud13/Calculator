package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.mycalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var lastNumeric: Boolean = false
    var lastDot: Boolean = false
    private lateinit var tvInput: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        tvInput = binding.tvInput
        onClick()
    }

    fun onDigit(view: View) {
        tvInput.append((view as Button).text)
        lastNumeric = true
        lastDot = false
    }

    fun onClear() {
        tvInput.text = ""
    }

    fun onDecimalPoint() {
        if (lastNumeric && !lastDot) {
            onDigit(binding.dot)
            lastNumeric = false
            lastDot = true
        }
    }

    fun onOperator(view: View) {
        if (lastNumeric && !isOperatorAdded(tvInput.text.toString())) {
            tvInput.append((view as Button).text)
            lastNumeric = false
        }
    }

    fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/")
                    || value.contains("*")
                    || value.contains("-")
                    || value.contains("+")
        }
    }

    fun onEqual() {
        if (lastNumeric) {
            var tvValue = tvInput.text.toString()
            var prefix = ""
            try {
                if (tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if (tvValue.contains("-")) {
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    var result = one.toDouble() - two.toDouble()
                    tvInput.text = removeZero(result.toString())
                }
                else if (tvValue.contains("+")) {
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    var result = one.toDouble() + two.toDouble()
                    tvInput.text = removeZero(result.toString())
                }
                else if (tvValue.contains("*")) {
                    val splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    var result = one.toDouble() * two.toDouble()
                    tvInput.text = removeZero(result.toString())
                }
                else if (tvValue.contains("/")) {
                    val splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    var result = one.toDouble() / two.toDouble()
                    tvInput.text = removeZero(result.toString())
                }
            } catch (e: java.lang.ArithmeticException) {
                e.printStackTrace()
            }
        }
    }

    fun removeZero(result: String) : String{
        var value = result
        if (result.endsWith(".0"))
            value = result.substring(0, result.length - 2)

        return value
    }

    fun onClick() {
        binding.zero.setOnClickListener {
            onDigit(binding.zero)
        }
        binding.one.setOnClickListener {
            onDigit(binding.one)
        }
        binding.two.setOnClickListener {
            onDigit(binding.two)
        }
        binding.three.setOnClickListener {
            onDigit(binding.three)
        }
        binding.four.setOnClickListener {
            onDigit(binding.four)
        }
        binding.five.setOnClickListener {
            onDigit(binding.five)
        }
        binding.six.setOnClickListener {
            onDigit(binding.six)
        }
        binding.seven.setOnClickListener {
            onDigit(binding.seven)
        }
        binding.eight.setOnClickListener {
            onDigit(binding.eight)
        }
        binding.nine.setOnClickListener {
            onDigit(binding.nine)
        }
        binding.divide.setOnClickListener {
            onOperator(binding.divide)
        }
        binding.multiply.setOnClickListener {
            onOperator(binding.multiply)
        }
        binding.subtract.setOnClickListener {
            onOperator(binding.subtract)
        }
        binding.add.setOnClickListener {
            onOperator(binding.add)
        }
        binding.CLR.setOnClickListener {
            onClear()
        }
        binding.dot.setOnClickListener {
            onDecimalPoint()
        }
        binding.equal.setOnClickListener {
            onEqual()
        }
    }
}