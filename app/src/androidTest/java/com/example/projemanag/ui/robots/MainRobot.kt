package com.example.projemanag.ui.robots

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.projemanag.R

fun main(func: MainRobot.() -> Unit) = MainRobot().apply { func() }

class MainRobot : BaseRobot() {
    private val toolbarMatcher = withId(R.id.toolbar_main_activity)
    private val membersMenuDrawerMatcher = withId(R.id.drawer_layout)
    private val fabCreateBoardMatcher = withId(R.id.fab_create_board)
    private val boardRecyclerViewMatcher = withId(R.id.rv_boards_list)

    fun openMemberMenuDrawer() =
        onView(membersMenuDrawerMatcher).perform(DrawerActions.open())
    fun tapOnfabCreateBoard() = tapOn(fabCreateBoardMatcher)
    fun isBoardDisplayed(boardName: String) =
        isRecyclerItemDisplayed(boardRecyclerViewMatcher, withText(boardName))

    fun tapOnBoard(boardName: String) =
        tapRecyclerItem(boardRecyclerViewMatcher, withText(boardName))
}