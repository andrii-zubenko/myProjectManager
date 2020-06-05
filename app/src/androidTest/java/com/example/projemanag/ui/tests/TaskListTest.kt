package com.example.projemanag.ui.tests

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.projemanag.activities.SplashActivity
import com.example.projemanag.ui.robots.alert
import com.example.projemanag.ui.robots.board
import com.example.projemanag.ui.robots.main
import com.example.projemanag.ui.utils.DataPrep.createTaskListToBeDeleted
import com.example.projemanag.ui.utils.dateInMills
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class TaskListTest : BaseTest() {

    @get:Rule
    val activityRule = ActivityTestRule(SplashActivity::class.java)
    private val newListName = "${dateInMills()}_List"
    private val listToBeDeleted = "${dateInMills()}_DeleteList"
    private val deleteTaskListAlertMessage =
        "Are you sure you want to delete Task List '$listToBeDeleted'?"

    @Test
    fun createTaskList() {
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

    @Test
    fun deleteTaskList() {
        var numberOfTaskLists: Int? = null
        createTaskListToBeDeleted(listToBeDeleted)
        signInWithTestCreds()

        main {
            tapOnBoard(testBoard)
        }

        board {
            numberOfTaskLists = getNumberOfTaskLists()
            tapOnDeleteList(listToBeDeleted)
        }

        alert {
            isAlertMessageDisplayed(deleteTaskListAlertMessage)
            tapOnYes()
        }

        board {
            val newNumberOfTaskLists = getNumberOfTaskLists()
            Assert.assertEquals(numberOfTaskLists?.minus(1), newNumberOfTaskLists)
        }
    }
}