package com.tricoder.androidtask

import java.util.regex.Matcher
import java.util.regex.Pattern


object SignUpUtil {


    /**
     * the input is not valid if...
     * ...the username/password is empty
     * ...confirmedPassword is not same as password
     * ...password does not contain more than two characters
     * ...username is already taken
     * : regex-based email validation; name/domain min 4 character and max 25 characters long.
     * min 8 and max 15 characters, should not contain your name, the first character should be lowercase, must contain at least 2 uppercase characters, 2 digits and 1 special character.
     * validate Indian mobile numbers only;
     */

    val Users = listOf("User", "Bob")



    private fun isValidPassword(password: String): Boolean {
        val pattern: Pattern
        val specialCharacters = "-@%\\[\\}+'!/#$^?:;,\\(\"\\)~`.*=&\\{>\\]<_"
        val PASSWORD_REGEX =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[$specialCharacters])(?=\\S+$).{8,20}$"
        pattern = Pattern.compile(PASSWORD_REGEX)
        val matcher: Matcher = pattern.matcher(password)
        return matcher.matches()
    }

    private const val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"


    fun validSignUpInput(
        username: String,
        password: String,
        phone: Long,
        email: String
    ): Boolean {


        if (username in Users) {
            return false
        }

        if (!((phone.toString().startsWith("91") && phone.toString().length==12))){
            return false
        }

        if (!(email.matches(emailPattern.toRegex())) || email.length<4 || email.length>25){
            return false
        }


        if ((password.length < 8 || password.length >15) ||
            password.count { it.isDigit() } < 2 ||
            password.contains(username) ||
            !password[0].isLowerCase() ||
            password.contains(password.lowercase()) ||
            !isValidPassword(password)
        ) {
            return false
        }

        return true
    }
}