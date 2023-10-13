package com.example.diceroller

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    // @SuppressLint("MissingInflatedId", "SetTextI18n")
    @SuppressLint("MissingInflatedId", "SetTextI18n", "CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnNumOne = findViewById<Button>(R.id.btnNum1)
        val btnClear = findViewById<Button>(R.id.btnClear)
        val firstInputField = findViewById<EditText>(R.id.editTextNumber1)
        val secondInputField = findViewById<EditText>(R.id.editTextNumber2)
        val alreadyInField = firstInputField.text.toString()
        val textToAdd = getString(R.string.num1)

        val listOfNumButtons = listOf<Button>(
            findViewById(R.id.btnNum0), // binding????
            findViewById(R.id.btnNum1),
            findViewById(R.id.btnNum2),
            findViewById(R.id.btnNum3),
            findViewById(R.id.btnNum4),
            findViewById(R.id.btnNum5),
            findViewById(R.id.btnNum6),
            findViewById(R.id.btnNum7),
            findViewById(R.id.btnNum8),
            findViewById(R.id.btnNum9),
        )
        for (numButton in listOfNumButtons) {
            numButton.setOnClickListener { firstInputField.append(alreadyInField + listOfNumButtons.indexOf(numButton)) }
        }

        fun clearTextFields() {
            firstInputField.text.clear()
            secondInputField.text.clear()
        }

        btnClear.setOnClickListener { clearTextFields() }
    }
}

data class NumButton(
    val value: Int, // что передает
    val text: String = "$value", // что отображать на кнопке
    val onClick: () -> Unit,
)

fun createButtons() =
    List(10) { index ->
        NumButton(value = index, text = "$index") {
            print(index)
        }
    }

fun clickOnEachButton() {
    createButtons().forEach { numButton ->
        numButton.onClick()
    }
}
