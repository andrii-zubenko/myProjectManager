package com.example.projemanag.ui.robots

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.projemanag.R

fun main(func: MainRobot.() -> Unit) = MainRobot().apply { func() }

class MainRobot : BaseRobot() {
    private val toolbarMatcher = withId(R.id.toolbar_main_activity)
    private val fabCreateBoardMatcher = withId(R.id.fab_create_board)
    private val boardRecyclerViewMatcher = withId(R.id.rv_boards_list)

    fun isMainToolbarDisplayed() =
        onView(toolbarMatcher).check(matches(isDisplayed()))
    fun tapOnfabCreateBoard() = tapOn(fabCreateBoardMatcher)
}