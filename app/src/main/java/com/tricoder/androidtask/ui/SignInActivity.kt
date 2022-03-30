package com.tricoder.androidtask.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.tricoder.androidtask.R
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    private var isRemembered = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        supportActionBar?.hide()


        sharedPreferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        isRemembered = sharedPreferences.getBoolean("CHECKBOX", false)

        txtSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }


        val email = sharedPreferences.getString("EMAIL", "")
        val phone = sharedPreferences.getString("PHONE", "")
        val password = sharedPreferences.getString("PASSWORD", "")
        val check=sharedPreferences.getBoolean("CHECKBOX",true)

        buttonLogIn.setOnClickListener {

            if ((inputEmail.text.toString().trim() == email &&
                        inputPassword.text.toString().trim() == password) ||
                (inputEmail.text.toString().trim() == phone &&
                        inputPassword.text.toString().trim() == password)
            ) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()

            } else {
                Toast.makeText(this, "Invalid email/phone or password", Toast.LENGTH_SHORT).show()
            }
        }



//        if (isRemembered==check) {
//            startActivity(Intent(this, MainActivity::class.java))
//            finish()
//        }


    }
}