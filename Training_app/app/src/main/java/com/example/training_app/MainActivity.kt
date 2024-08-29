package com.example.training_app

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val a: Button = findViewById(R.id.button)
        a.setOnClickListener {
            with(DialogFragment()) {
                dialog?.setTitle(R.string.dialog_title)
                show(supportFragmentManager, "lalala")
                // тут надо бы задать что должно появиться в диалоге, а то диалог есть, а содержимого ну вообще нет.
            }

            Log.i("Activity LifeCycle training", "Fragment showed")
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