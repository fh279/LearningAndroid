package com.example.diceroller

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "SetTextI18n", "CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) { // повтори цикл жизни приложения
        super.onCreate(savedInstanceState) // это что такое?
        setContentView(R.layout.activity_main) // а это что такое?

        val firstInputField = findViewById<EditText>(R.id.editTextField1)
        val secondInputField = findViewById<EditText>(R.id.editTextField2)
        val resultField = findViewById<TextView>(R.id.textViewResults)
        val inputFields = listOf<EditText>(firstInputField, secondInputField) //
        val alreadyInField = firstInputField.text.toString()
        val btnClear = findViewById<Button>(R.id.btnClear) // очищаем оба поля ввода чисел
        val btnBackSpace =
            findViewById<Button>(R.id.btnBackSpace) // удаляем последнее имеещееся число в поле ввода

        val btnPlus = findViewById<Button>(R.id.btnPlus) // суммируем содержимое полей ввода
        val btnMinus = findViewById<Button>(R.id.btnMinus)
        val btnMultiplications = findViewById<Button>(R.id.btnMultiply)
        val btnDivision = findViewById<Button>(R.id.btnDivide)
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
                        alreadyInField + listOfNumButtons.indexOf(numButton),
                    )
                }
            }
        }

        // колбек это что? - колбэк это функция, которая будет вызвана когда определенный процесс
        // завершит свою работу. Ничерта в этом пока что не понимаю.  в лямбдах нижнее подчеркивание обозначает неиспользуемые аргументы.

        fun deleteLastSymInActiveField() {
            for (field in inputFields) {
                if (field.hasFocus()) {
                    if (field.toString().isNotEmpty()) {
                        val fieldText = field.text
                        val textWithoutLastSymblol =
                            fieldText.substring(0, fieldText.length - 1)
                        field.setText(textWithoutLastSymblol)
                    }
                    field.setSelection(field.text.length)
                }
            }
        }

        btnBackSpace.setOnClickListener {
            deleteLastSymInActiveField()
        }





        clickOnEachButton() // так что же оно все таки делает?...

        // Оно, конечно, неплохо, но может все таки чистить все поля?...
        fun clearTextInActiveField(fields: List<EditText>) {
            for (field in fields) {
                if (field.hasFocus()) {
                    field.text.clear()
                }
            }
        }

        // Оно работает, но чую написано невероятно коряво...
        // Может тут надо параметры там и все такое...
        // А то при вызове же не будет видно что куда кого...
        fun summation(firstField: EditText, secondField: EditText): Int {
            val firstFieldValueAsInt = Integer.parseInt(firstField.text.toString())
            val secondFieldValueAsInt = Integer.parseInt(secondField.text.toString())
            if (firstField.text.isNotEmpty() && secondField.text.isNotEmpty()) {
                return firstFieldValueAsInt + secondFieldValueAsInt
            }
            return 100500
        }

        btnPlus.setOnClickListener {
            resultField.text = summation(firstInputField, secondInputField).toString()
        }

        fun subtraction(firstField: EditText, secondField: EditText): Int {
            val firstFieldValueAsInt = Integer.parseInt(firstField.text.toString())
            val secondFieldValueAsInt = Integer.parseInt(secondField.text.toString())
            if (firstField.text.isNotEmpty() && secondField.text.isNotEmpty()) {
                return firstFieldValueAsInt - secondFieldValueAsInt
            }
            return 100600
        }

        btnMinus.setOnClickListener {
            resultField.text = subtraction(firstInputField, secondInputField).toString()
        }

        fun multiplication(firstField: EditText, secondField: EditText): Int {
            val firstFieldValueAsInt = Integer.parseInt(firstField.text.toString())
            val secondFieldValueAsInt = Integer.parseInt(secondField.text.toString())
            if (firstField.text.isNotEmpty() && secondField.text.isNotEmpty()) {
                return firstFieldValueAsInt * secondFieldValueAsInt
            }
            return 100700
        }

        btnMultiplications.setOnClickListener {
            resultField.text = multiplication(firstInputField, secondInputField).toString()
        }

        fun division(firstField: EditText, secondField: EditText): Int {
            val firstFieldValueAsInt = Integer.parseInt(firstField.text.toString())
            val secondFieldValueAsInt = Integer.parseInt(secondField.text.toString())

            if (firstField.text.isNotEmpty() && secondField.text.isNotEmpty()) {
                return firstFieldValueAsInt / secondFieldValueAsInt
            }
            return 100700
        }

        btnDivision.setOnClickListener {

            if (secondInputField.text.toString().equals("0")) {
                Toast.makeText(
                    applicationContext,
                    "Делить на ноль нельзя, дубина!",
                    Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            resultField.text = division(firstInputField, secondInputField).toString()
            /*try {
                resultField.text = division(firstInputField, secondInputField).toString()
            }
            catch (arExc: ArithmeticException) {
                Toast.makeText(
                    applicationContext,
                    "Делить на ноль нельзя, дубина!",
                    Toast.LENGTH_SHORT)
                    .show()
            }*/
        }

        btnClear.setOnClickListener { clearTextInActiveField(inputFields) }

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


        // btnBackSpace.setOnClickListener { deleteLastDigitInActiveField(inputFields) } // щас это рботает не совсем верно. Оно убирает последний символ у ВСЕХ ДВУХ полей. А надо чтоб убирало из активного и только.
    }

    data class NumButton(
        val value: Int, // что передаетя
        val text: String = "$value", // что отображать на кнопке
        val onClick: () -> Unit, // Что тут происходит?..
    )

    fun createButtons() = // тут создаем кнопки, присваем из занчение и текст в них.
        List(10) { index ->
            NumButton(value = index, text = "$index") {
                print(index)
            }
        }

    // што оно делает и что я тут задумывал?...
    // Кажется это проверятор на какую кнопку кликнуло.
    // Типа эта штука постоянно вызывается и когда будет выполнено условие (onClick() ),
    // то произойдет действие, запрограммированное на эту кнопку. Так ли это?
    // А почему оно не вызывается?.....
    fun clickOnEachButton() {
        createButtons().forEach { numButton ->
            numButton.onClick()
        }
    }
}

/**
 * Йоу. А мы не хотим параметризировать арифметические операции? А то в каждом методе у нас 2
 * поля ввода и однотипное (достаточно) действие. И что? Просто копипаста кода...
 *
 *
 *
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
 * - Кнопка clear стирает данные в обоих полях - не, теперь стирает только в активном поле.
 * -
 *
 * что еще не сделано и что надо вкорячить?
 * - Второе поле не умеет принимать данные - уже научилось.
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

/**
 * что надо уточнить.
 * под каждой работающий фичей подпиши что она делает.
 * выдели список всех рабочих методов, чтоб были списком чтоб было как то видно.
 * Еще одним списком, рядом лежащим, опиши что еще надо сделать. Хотя бы в виде списка хотело.
 *      Ну и проанализируй уже имеющиеся хотелки. Выше описаны.
 *
 * В XML'ках поправь чтоб кнопки цифер были расположены так как надо.
 *
 */
