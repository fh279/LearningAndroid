package com.example.training_app

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
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
        Log.i("Activity LifeCycle training", "Activity Created!")
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val a: Button = findViewById(R.id.button)
        a.setOnClickListener {
            CustomNewDialogFragment().show(supportFragmentManager, "alala")
            /*val builder = AlertDialog.Builder(this)
            with(builder) {
                setTitle("Title")
                create()
            }*/

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