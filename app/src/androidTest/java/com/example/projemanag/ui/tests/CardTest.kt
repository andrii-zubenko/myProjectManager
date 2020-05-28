package com.example.projemanag.ui.tests

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.projemanag.activities.SplashActivity
import com.example.projemanag.ui.robots.board
import com.example.projemanag.ui.robots.card
import com.example.projemanag.ui.robots.dialog
import com.example.projemanag.ui.robots.main
import com.example.projemanag.ui.utils.dateInMills
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class CardTest : BaseTest() {

    @get:Rule
    val activityRule = ActivityTestRule(SplashActivity::class.java)
    private val newCardName = "${dateInMills()}_Card"
    private val green = "#43C86F"
    private val blue = "#0C90F1"

    @Test
    fun createCardInTestBoard() {
        signInWithTestCreds()

        main {
            tapOnBoard(testBoard)
        }

        board {
            tapAddCard(testList)
            typeInCardName(newCardName)
            tapOnCardDoneButton()
            isCardDisplayed(testList, newCardName)
        }
    }

    @Test
    fun assignColorToTestCard() {
        signInWithTestCreds()

        main {
            tapOnBoard(testBoard)
        }

        board {
            tapOnCard(testList, testCard)
        }

        card {
            tapOnSelectColor()

            dialog {
                tapOnColor(green)
            }

            tapOnUpdateButton()
        }

        board {
            val actualCardColor = getCardColorDisplayed(testList, testCard)
            Assert.assertEquals(green, actualCardColor)
        }
    }
}
