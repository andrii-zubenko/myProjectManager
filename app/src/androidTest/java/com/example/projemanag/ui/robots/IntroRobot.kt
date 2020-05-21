package com.example.projemanag.ui.robots

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.projemanag.R
import org.hamcrest.Matcher

fun intro(func: IntroRobot.() -> Unit) = IntroRobot().apply { func() }

class IntroRobot : BaseRobot() {
    private val signInButtonMatcher: Matcher<View> = withId(R.id.btn_sign_in_intro)
    private val signUpButtonMatcher: Matcher<View> = withId(R.id.btn_sign_up_intro)

    fun tapOnSignInButton() = tapOn(signInButtonMatcher)
    fun tapOnSignUpButton() = tapOn(signUpButtonMatcher)
}