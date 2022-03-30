package com.tricoder.androidtask.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.core.widget.doOnTextChanged
import com.tricoder.androidtask.R
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private var isRemembered = false

    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportActionBar?.hide()

        termsSpan()

        sharedPreferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        isRemembered = sharedPreferences.getBoolean("CHECKBOX", false)


        buttonSignUp.setOnClickListener {

            val name: String = edtInputUserName.text.toString().trim()
            val email: String = edtInputEmail.text.toString().trim()
            val phone: String = edtInputPhone.text.toString().trim()
            val password: String = edtInputPassword.text.toString().trim()


            if (name.isEmpty() && email.isEmpty() && phone.isEmpty() && password.isEmpty()) {
                nameInputLayout.error = "Field is empty"
                emailInputLayout.error = "Field is empty"
                phoneInputLayout.error = "Field is empty"
                passwordInputLayout.error = "Field is empty"

                edtInputUserName.doOnTextChanged { text, start, before, count ->
                    nameInputLayout.error=""
                }

            } else if (!(email.matches(emailPattern.toRegex())) ||
                email.length < 4 ||
                email.length > 25
            ) {
                edtInputEmail.doOnTextChanged { text, start, before, count ->
                    emailInputLayout.error=""
                }
                emailInputLayout.error = "Email between 4-25 characters\nNot a valid format"

            } else if (!(phone.startsWith("91") ||
                        phone.length == 12)
            ) {
                edtInputPhone.doOnTextChanged { text, start, before, count ->
                    phoneInputLayout.error=""
                }
                phoneInputLayout.error = "Number must begin with 91 "

            } else if ((password.length < 8 || password.length > 15) ||
                password.count { it.isDigit() } < 2 ||
                password.contains(name) ||
                !password[0].isLowerCase() ||
                password.contains(password.lowercase()) ||
                !isValidPassword(password)
            ) {
                edtInputPassword.doOnTextChanged { text, start, before, count ->
                    passwordInputLayout.error=""
                }
                passwordInputLayout.error =
                    "min 8 and max 15 characters\nshould not contain your name\nfirst character should be lowercase\nmust contain at least 2 uppercase characters\n2 digits and 1 special character"

            } else {
                val editor : SharedPreferences.Editor = sharedPreferences.edit()

                editor.putString("USERNAME", name)
                editor.putString("EMAIL", email)
                editor.putString("PHONE", phone)
                editor.putString("PASSWORD", password)
                editor.putBoolean("CHECKBOX",false)
                editor.apply()
                editor.commit()

                startActivity(Intent(this, SignInActivity::class.java))
                finish()
            }


        }

        if (isRemembered) {
            startActivity(Intent(this, SignInActivity::class.java))
        }



        txtLogIn.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }

    }

    private fun isValidPassword(password: String): Boolean {
        val pattern: Pattern
        val specialCharacters = "-@%\\[\\}+'!/#$^?:;,\\(\"\\)~`.*=&\\{>\\]<_"
        val PASSWORD_REGEX =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[$specialCharacters])(?=\\S+$).{8,20}$"
        pattern = Pattern.compile(PASSWORD_REGEX)
        val matcher: Matcher = pattern.matcher(password)
        return matcher.matches()
    }


    private fun termsSpan() {
        val text = "By signing up, you're agree to our Terms & Conditions and Privacy Policy "
        val spannableString = SpannableString(text)
        val fColor = ForegroundColorSpan(Color.BLUE)
        val fcsColor = ForegroundColorSpan(Color.BLUE)
        spannableString.setSpan(fColor, 35, 54, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(fcsColor, 58, 72, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        txtTerms.text = spannableString
    }


}