package com.example.projemanag.ui.tests.signin

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.projemanag.activities.IntroActivity
import com.example.projemanag.ui.robots.drawer
import com.example.projemanag.ui.robots.intro
import com.example.projemanag.ui.robots.main
import com.example.projemanag.ui.robots.signIn
import com.example.projemanag.ui.tests.BaseTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class SignInTest : BaseTest() {

    @get:Rule
    val activityRule = ActivityTestRule(IntroActivity::class.java)

    private val signInActivityTitle = "SIGN IN"
    private val emptyString = ""
    private val pleaseEnterAnEmailAddress = "Please enter an email address"
    private val userName = "tester"
    private val authFailedMessage = "Authentication failed."
    private val invalidEmail = "invalidEmail"
    private val invalidPassword = "invalidPassword"

    @Test
    fun verifySignInActivityTitle() {
        intro {
            tapOnSignInButton()
        }

        signIn {
            Assert.assertEquals(signInActivityTitle, getActivityTitleText())
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
            tapOnSignInButton()
            snackbarIsDisplayed()
            Assert.assertEquals(pleaseEnterAnEmailAddress, getSnackbarText())
        }
    }

    @Test
    fun signInWithValidCreds() {
        signInWithTestCreds()

        main {
            openMemberMenuDrawer()
        }

        drawer {
            Assert.assertEquals(userName, getUserName())
        }
    }

    @Test
    fun signInWithInvalidCreds() {
        intro {
            tapOnSignInButton()
        }

        signIn {
            typeInEmail(invalidEmail)
            typeInPassword(invalidPassword)
            tapOnSignInButton()
            toastWithTextIsDiplayed(authFailedMessage)
        }
    }

    @Test
    fun signInTestThatShouldFail() {
        intro {
            tapOnSignInButton()
        }

        signIn {
            typeInEmail(invalidEmail)
            typeInPassword(invalidPassword)
            tapOnSignInButton()
            toastWithTextIsDiplayed("Some text that does not exist")
        }
    }
}
