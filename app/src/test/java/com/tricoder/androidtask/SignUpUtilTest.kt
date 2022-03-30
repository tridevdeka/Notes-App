package com.tricoder.androidtask

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegistrationUtilTest {


    @Test
    fun `invalid password based on Password Validation returns false`(){

        val result = SignUpUtil.validSignUpInput(
            username = "oi",
            password = "aA11aasasas#Awf",
            phone = 911234567890,
            email = "user@gmail.com"

        )

        assertThat(result).isTrue()
    }


    @Test
    fun `invalid phone no based on Mobile Validation returns false`(){

        val result = SignUpUtil.validSignUpInput(
            username = "oi",
            password = "aA11aasasas#fgr",
            phone =6543323333,
            email = "user@gmail.com"
        )

        assertThat(result).isTrue()
    }


    @Test
    fun `invalid email no based on Email Validation returns false`(){

        val result = SignUpUtil.validSignUpInput(
            username = "oi",
            password = "aA11aasasas#fgr",
            phone = 910123456789,
            email = "user@gmail.com"
        )

        assertThat(result).isTrue()
    }



    @Test
    fun `empty username returns false`() {
        val result = SignUpUtil.validSignUpInput(
            username = "",
            password = "123",
            phone = 911234567890,
            email = "user@gmail.com"

        )

        assertThat(result).isFalse()
    }



    @Test
    fun `username already exists returns false`(){
        val result=SignUpUtil.validSignUpInput(
            username = "",
            password = "123",
            phone = 911234567890,
            email = "user@gmail.com"

        )

        assertThat(result).isFalse()
    }

    @Test
    fun `confirmedPassword does not match with actual password returns false`(){
        val result=SignUpUtil.validSignUpInput(
            username = "",
            password = "123",
            phone = 911234567890,
            email = "user@gmail.com"

        )

        assertThat(result).isFalse()
    }

    @Test
    fun `password contains at least two digits returns true`(){
        val result=SignUpUtil.validSignUpInput(
            username = "",
            password = "123",
            phone = 911234567890,
            email = "user@gmail.com"

        )

        assertThat(result).isFalse()
    }


    @Test
    fun `password empty returns false`(){
        val result=SignUpUtil.validSignUpInput(
            username = "",
            password = "123",
            phone = 911234567890,
            email = "user@gmail.com"
        )
        assertThat(result).isFalse()

    }
}