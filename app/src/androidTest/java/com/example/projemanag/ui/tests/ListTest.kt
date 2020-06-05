package com.example.projemanag.ui.tests

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.projemanag.activities.SplashActivity
import com.example.projemanag.ui.robots.board
import com.example.projemanag.ui.robots.main
import com.example.projemanag.ui.utils.dateInMills
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class ListTest : BaseTest() {

    @get:Rule
    val activityRule = ActivityTestRule(SplashActivity::class.java)
    private val newListName = "${dateInMills()}_List"

    @Test
    fun createListInABoard() {
        signInWithTestCreds()

        main {
            tapOnBoard(testBoard)
        }

        board {
            tapOnAddList()
            typeInListName(newListName)
            tapOnListDoneButton()
            isListDisplayed(newListName)
        }
    }
}