package com.example.projemanag.ui.tests

import androidx.test.espresso.IdlingRegistry
import com.example.projemanag.ui.robots.intro
import com.example.projemanag.ui.robots.signIn
import com.example.projemanag.ui.utils.getJsonValue
import com.example.projemanag.utils.Utils
import org.junit.After
import org.junit.Before

open class BaseTest {

    val testEmail = getJsonValue("email")
    val testPassword = getJsonValue("password")
    val testBoard = "testBoard"
    val testList = "testList"
    val testCard = "testCard"

    @Before
    fun setup() {
        IdlingRegistry.getInstance().register(Utils.countingIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(Utils.countingIdlingResource)
    }

    fun signInWithTestCreds() {
        intro {
            tapOnSignInButton()
        }

        signIn {
            typeInEmail(testEmail)
            typeInPassword(testPassword)
            tapOnSignInButton()
        }
    }
}
