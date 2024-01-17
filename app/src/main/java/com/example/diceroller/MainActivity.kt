package com.example.diceroller

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "SetTextI18n", "CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnClear = findViewById<Button>(R.id.btnClear)
        val firstInputField = findViewById<EditText>(R.id.editTextNumber1)
        val secondInputField = findViewById<EditText>(R.id.editTextNumber2)
        val inputFields = listOf<EditText>(firstInputField, secondInputField) //
        val alreadyInField = firstInputField.text.toString()

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

        /**
         * addNumToInputField - это метод, который добавляет число в то поле, в которое указали.
         * То есть это как бы приложение к какому-то методу, который будет указывать на конкретное поле.
         * В данном случае в роли такого метода выступает inputTextToActiveTextField(это его старое название,
         * он будет в дальнейшем переименован. Он определяет какое поле активно и в него сует число
         * используя метод addNumToInputField.
         */

        fun addNumToInputField(field: EditText) {
            for (numButton in listOfNumButtons) {
                numButton.setOnClickListener {
                    field.append(
                        alreadyInField +
                            listOfNumButtons.indexOf(numButton),
                    )
                }
            }
        }

        // дай норм имя методу. И не понятно как это работает.
        /**
         * Короче мы берем оба поля, перебираем. При переборе смотрим, что с фокусом.
         * Один из параметров это лямбда
         * ААА! Все равно не понимаю как это работает. Долбай Вову.
         * Еще. Перед и после лямбды можно поставить скобки. Значит это аргумент. Как мы описали
         * выше. Вот в эту сторону смотри.
         * */
        fun inputTextToActiveTextField(fields: List<EditText>) {
            for (field in fields) {
                field.setOnFocusChangeListener { _, hasFocus -> // колбек это что?  в лямбдах нижнее подчеркивание обозначает неиспользуемые элементы.
                    if (hasFocus) {
                        addNumToInputField(field)
                    }
                }
            }
        }
        inputTextToActiveTextField(fields = inputFields)

        fun clearTextFields() {
            firstInputField.text.clear()
            secondInputField.text.clear()
        }

        btnClear.setOnClickListener { clearTextFields() }
    }
}

data class NumButton(
    val value: Int, // что передаетя
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
    // some edit for Serge
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
 *
 * понятие callback! разобрать. pattern obseverer!!!!!!!!
 *
 * это точно норм практика - объявить функцию и чуть наже сразу же ее вызывать?
 *
 * Сделай так что бы кнопка Clear работала только на то поле на котором сейчас стоит фокус.
 * А то сейчас она стирает оба поля. Не очень хорошо учитывая что ты мог безошибочно ввести данные
 * в одно поле, начать вводить во второе, там ошибиться и стирая значение второго поля ты вынужден
 * затирать в том числе и безошибочно введенное значение. Возможно длинное. Обида.
 */
