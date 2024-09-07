package com.example.training_app

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.training_app.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * ВОПРОС. Мне сейчас надо разбираться что такое Биндинги?
         * Я пока очень условно прочел в гуглах про это, понятно крайне обще.
         * Нагуглил такое:
         * "Биндинги (Data Binding) в Android-разработке —
         * это техника, которая автоматически связывает данные между источниками данных и пользовательским интерфейсом.
         * Она упрощает процесс отображения и обновления данных на пользовательском интерфейсе,
         * минимизирует необходимость ручного кодирования для синхронизации данных и элементов интерфейса.
         *
         * Не то что бы очень понятно практически. Надо погружаться. Пока в это дело не упирался.
         */

        /**
         * Еще Вопрос. Вот есть класс ActivityMain2Binding. Он сгенерирован автоматически когда
         * я через контекстное меню создавал вторую активити. Если попытаться провалиться в него,
         * то я окажусь в xml-файле, описывающем верстку экрана. Как это так и как работает?
         * Раньше такого не встречал. Обычно проваливаешься в класс - и там класс, но не верстка...
         */
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .setAnchorView(R.id.fab).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}
/**
 * ВОПРОС: есть такой класс как AppCompatActivity, от которого наследуются все активити.
 * Тут и все методы жизненного цикла, методы взаимодействия с макетом (setContentView, например).
 * Мне в этом надо сейчас погружаться или пока не стоит?
 * Так же увидел что есть библиотека AppCompat, только она уже из Jetpack и видимо сейчас мне не нужна.
 */