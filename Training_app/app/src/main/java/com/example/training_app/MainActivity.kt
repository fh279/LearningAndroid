package com.example.training_app

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.DialogFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("Activity LifeCycle training", "Activity Created!")
        /**
         * ВОПРОС.
         * Что делает метод edgetoedge()? Погуглил - включает отрисовку на весь экран включая статус бар.
         * Пробовал запускать без него, разницы не увидел и не понял. Наверное, он тут автоматически
         * появился и в контексте имеющихся элементов ни на что не влияет.
         */
        enableEdgeToEdge()
        /**
         * ВОПРОС.
         * Задает корневой макет для активити где он был вызван.
         * Важно ли понимать откуда берется корневой макет, как мне его создавать?
         * Или я чисто обзорно прохожусь по вьюхам и больше к этому моменту не вернусь?
         */
        setContentView(R.layout.activity_main)
        /**
         * ВОПРОС. setOnApplyWindowInsetsListener - лиснер вставки пользовательских вьюх(так пишет гугл).
         * Гугл говорит что есть окно и лиснер проверяет что пользователь что-то в это окно вставил
         * и применяет его к представлению (что за представление?...) и устанавливает отступы.
         * А зачем это? Как это работает? Сходу не понятно.
         * Выглядит так что этот лиснер нужен когда я программно создаю элементы и тогда
         * он обрабатывает это программное добавление.
         */

        /**
         * ВОПРОС. Что такое insets? Зачем надо?
         * Это типа концепция которая помогает показать где пользовательский интерфейс (добавленный разрабом?)
         * пересекается с системным (это вообще как и когда такое бывает?)
         * и помогает перемещать элементы что бы пользовательские и системные UI-элементы не конфликтовали.
         * Семантически звучит понятно, а вот по сути на практике вообще не ясно что оно такое и что делает.
         */
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        /**
         * ВОПРОС. Сейчас я создаю кнопки в методе onCreate и оборачиваю в переменную типа Button. А как это делают профессионалы?
          */
        val a: Button = findViewById(R.id.button)
        a.setOnClickListener {
            CustomNewDialogFragment().show(supportFragmentManager, "alala")
            Log.i("Activity LifeCycle training", "Fragment showed")
        }

        val btnGoToSecondScreen: Button = findViewById(R.id.button2)
        btnGoToSecondScreen.setOnClickListener {
            startActivity(Intent(baseContext, MainActivity2::class.java))
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Activity LifeCycle training", "Activity is Destroyed")
    }

    override fun onPause() {
        super.onPause()
        Log.i("Activity LifeCycle training", "Activity is Paused")
    }
    
    override fun onResume() {
        super.onResume()
        Log.i("Activity LifeCycle training", "Activity is Resumed")
    }

    override fun onStart() {
        super.onStart()
        Log.i("Activity LifeCycle training", "Activity is Started")
    }

    override fun onStop() {
        super.onStop()
        Log.i("Activity LifeCycle training", "Activity is Stopped")
    }
}

class CustomNewDialogFragment(): DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        with(builder) {
            setTitle("Title")
            setPositiveButton("Close", ) {dialog, id ->
                dialog.dismiss()
            }
        }
        return builder.create()
    }

}