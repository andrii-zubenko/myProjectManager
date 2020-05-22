package com.example.projemanag.ui.tests

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.projemanag.activities.SplashActivity
import com.example.projemanag.ui.robots.createBoard
import com.example.projemanag.ui.robots.intro
import com.example.projemanag.ui.robots.main
import com.example.projemanag.ui.robots.signIn
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class CreateBoardTest : BaseTest() {

    @get:Rule
    val activityRule = ActivityTestRule(SplashActivity::class.java)
    private val validEmail = "andrew@gmail.com"
    private val validPassword = "password"
    private val emptyBoardName = ""
    private val newBoardName = "new board"
    private val pleaseEnterABoardNameMessage = "Please enter a Board name"
    private val boardCreatedSuccessfullyMessage = "Board created successfully."

    @Test
    fun createBoardWithEmptyName() {
        intro {
            tapOnSignInButton()
        }

        signIn {
            typeInEmail(validEmail)
            typeInPassword(validPassword)
            tapOnSignInButton()
        }

        main {
            tapOnfabCreateBoard()
        }

        createBoard {
            typeInBoardNameField(emptyBoardName)
            tapOnCreateButton()
            snackbarIsDisplayed()
            Assert.assertEquals(pleaseEnterABoardNameMessage, getSnackbarText())
        }
    }

    @Test
    fun createBoard() {
        intro {
            tapOnSignInButton()
        }

        signIn {
            typeInEmail(validEmail)
            typeInPassword(validPassword)
            tapOnSignInButton()
        }

        main {
            tapOnfabCreateBoard()
        }

        createBoard {
            typeInBoardNameField(newBoardName)
            tapOnCreateButton()
            toastWithTextIsDiplayed(boardCreatedSuccessfullyMessage)
            // TODO finish assertion
        }
    }
}