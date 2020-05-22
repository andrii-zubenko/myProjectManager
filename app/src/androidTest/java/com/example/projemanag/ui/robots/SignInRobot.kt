package com.example.projemanag.ui.robots

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.projemanag.R
import org.hamcrest.Matcher

fun signIn(func: SignInRobot.() -> Unit) = SignInRobot().apply { func() }

class SignInRobot : BaseRobot() {
    private val emailFieldMatcher: Matcher<View> = withId(R.id.et_email_sign_in)
    private val passwordFieldMatcher: Matcher<View> = withId(R.id.et_password_sign_in)
    private val signInButtonMatcher: Matcher<View> = withId(R.id.btn_sign_in)

    fun typeInEmail(email: String) = typeInText(emailFieldMatcher, email)
    fun typeInPassword(password: String) = typeInText(passwordFieldMatcher, password)
    fun tapOnSignInButton() = tapOn(signInButtonMatcher)
}
