package com.example.projemanag.ui.robots

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.projemanag.R
import org.hamcrest.Matcher

fun signUp(func: SignUpRobot.() -> Unit) = SignUpRobot().apply { func() }

class SignUpRobot : BaseRobot() {
    private val nameFieldMatcher: Matcher<View> = withId(R.id.et_name)
    private val emailFieldMatcher: Matcher<View> = withId(R.id.et_email)
    private val passwordFieldMatcher: Matcher<View> = withId(R.id.et_password)
    private val signUpButtonMatcher: Matcher<View> = withId(R.id.btn_sign_up)

    fun typeInName(name: String) = typeInText(nameFieldMatcher, name)
    fun typeInEmail(email: String) = typeInText(emailFieldMatcher, email)
    fun typeInPassword(password: String) = typeInText(passwordFieldMatcher, password)
    fun tapOnSignUpButton() = tapOn(signUpButtonMatcher)
}
