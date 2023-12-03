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

        val btnClear = findViewById<Button>(R.id.btnClear)
        val firstInputField = findViewById<EditText>(R.id.editTextNumber1)
        val secondInputField = findViewById<EditText>(R.id.editTextNumber2)
        val inputFields = listOf<EditText>(firstInputField, secondInputField)
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

        fun addNumToInputField(field: EditText) { // надо переделать так что бы ввод осуществлялся в активное поле
            for (numButton in listOfNumButtons) {
                numButton.setOnClickListener {
                    field.append(
                        alreadyInField +
                            listOfNumButtons.indexOf(numButton),
                    )
                }
            }
        }

        fun inputTextToField(fields: List<EditText>) { // дай норм имя методу.
            for (field in fields)
                if (field.isFocused) {
                    addNumToInputField(field)
                }
        }
        inputTextToField(fields = inputFields)

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

fun clickOnEachButton() { // што оно делает и что я тут задумывал?...
    createButtons().forEach { numButton ->
        numButton.onClick()
    }
}

/**
 * Что я хочу сейчас и позже?
 * Сначала - прога где я могу ввести числа в поля и по нажатию на кнопку символа операции произвести
 * эту операцию с введенными числами.
 * Потом хочется написать логику так что я ввожу выражение, а потом жму кнопку "вычислить" и
 * выражение парсится, а в третье поле выводится результат вычисления или служеюное сообщение.
 * Тут интересно как сделать переход из 1 в другое приложение. Из старого в новое. Возможно, через 2
 * активности или, как более модно, через 1 активность и фрагменты(кажется, так это называется).
 *
 *
 * Что эта штука уже умеет?
 * - Клик на кнопку циферки производит ввод соответствующей цифры в поле ввода 1
 * - Кнопка clear стирает данные в обоих полях
 * -
 *
 * что еще не сделано и что надо вкорячить?
 * - Второе поле не умеет принимать данные
 * - Кнопки с Символами операции не работают
 * -Запилить кнопочки перехода между старым и новым дизайном приложения (разные фрагменты, как
 * описано выше, это вычисление по клику на операцию и вычисление через парсинг введенного выражения.
 * -
 * -
 *
 * Везде оставляй больше комментариев что хотел реализовать а то не помню...
 */
