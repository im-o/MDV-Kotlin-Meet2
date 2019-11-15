package com.dhytodev.workshopandroid

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val buttonSubmit = findViewById<Button>(R.id.buttonSubmit)

        buttonSubmit.setOnClickListener {
            val username = editTextUserName.text.toString()
            val password = editTextPassword.text.toString()

            if (isValid()) {
                val intent = Intent(this,
                    HomeActivity::class.java )
                intent.putExtra("username", username)
                intent.putExtra("password", password)
                startActivity(intent)
            }
        }
    }

    private fun isValid(): Boolean {
        var valid = true

        if (TextUtils.isEmpty(editTextUserName.text.toString())) {
            editTextUserName.error = "Username tdk boleh kosong"
            valid = false
        }
        if (TextUtils.isEmpty(editTextPassword.text.toString())) {
            editTextPassword.error = "Password tdk boleh kosong"
            valid = false
        }

        return valid
    }
}
