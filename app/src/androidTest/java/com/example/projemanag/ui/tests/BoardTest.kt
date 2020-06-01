package com.example.projemanag.ui.tests

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.projemanag.activities.SplashActivity
import com.example.projemanag.ui.robots.alert
import com.example.projemanag.ui.robots.board
import com.example.projemanag.ui.robots.createBoard
import com.example.projemanag.ui.robots.main
import com.example.projemanag.ui.utils.dateInMills
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class BoardTest : BaseTest() {

    @get:Rule
    val activityRule = ActivityTestRule(SplashActivity::class.java)
    private val emptyBoardName = ""
    private val newBoardName = "${dateInMills()}_Board"
    private val boardToBeDeleted = "boardToBeDeleted"
    private val pleaseEnterABoardNameMessage = "Please enter a Board name"

    @Test
    fun createBoardWithEmptyName() {
        signInWithTestCreds()

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
        signInWithTestCreds()

        main {
            tapOnfabCreateBoard()
        }

        createBoard {
            typeInBoardNameField(newBoardName)
            tapOnCreateButton()
        }

        main {
            isBoardDisplayed(newBoardName)
        }
    }

    @Test
    fun deleteTestBoard() {
        signInWithTestCreds()

        main {
            tapOnBoard(boardToBeDeleted)
        }

        board {
            tapOnDeleteBoard()
        }

        alert {
            isAlertMessageDisplayed(boardToBeDeleted)
            tapOnYes()
        }

        main {
            // TODO assert that deleted
        }
    }
}