package com.example.projemanag.ui.tests

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.projemanag.activities.SplashActivity
import com.example.projemanag.ui.robots.intro
import com.example.projemanag.ui.robots.main
import com.example.projemanag.ui.robots.signIn
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class SignInTest : BaseTest() {

    @get:Rule
    val activityRule = ActivityTestRule(SplashActivity::class.java)
    private val signInActivityTitle = "SIGN IN"
    private val emptyString = ""
    private val pleaseEnterAnEmailAddress = "Please enter an email address"
    private val validEmail = "andrew@gmail.com"
    private val validPassword = "password"

    @Test
    fun verifySignInActivityTitle() {
        intro {
            tapOnSignInButton()
        }

        signIn {
            val actualActivityTitle = getActivityTitleText()
            Assert.assertEquals(signInActivityTitle, actualActivityTitle)
        }
    }

    @Test
    fun signInWithEmptyCreds() {
        intro {
            tapOnSignInButton()
        }

        signIn {
            typeInEmail(emptyString)
            typeInPassword(emptyString)
            clickSignInButton()
            snackbarIsDisplayed()
            val actualSnackbarMessage = getSnackbarText()
            Assert.assertEquals(pleaseEnterAnEmailAddress, actualSnackbarMessage)
        }
    }

    @Test
    fun signInWithValidCreds() {
        intro {
            tapOnSignInButton()
        }

        signIn {
            typeInEmail(validEmail)
            typeInPassword(validPassword)
            clickSignInButton()
        }

        main {
            isToolbarMainDisplayed()
        }
    }
}
