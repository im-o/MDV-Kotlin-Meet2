package com.dhytodev.workshopandroid

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val username = intent.getStringExtra("username")
        val password = intent.getStringExtra("password")

        Toast.makeText(
            this,
            "Halo $username. Password anda $password",
            Toast.LENGTH_SHORT
        ).show()

        Log.d("username", username.toString())
        Log.d("password", password.toString())
    }
}
