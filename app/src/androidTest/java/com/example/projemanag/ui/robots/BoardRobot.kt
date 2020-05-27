package com.example.projemanag.ui.robots

import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.projemanag.R
import org.hamcrest.Matchers.allOf

fun board(func: BoardRobot.() -> Unit) = BoardRobot().apply { func() }

class BoardRobot : BaseRobot() {
    private val addListButtonMatcher = allOf(
        withId(R.id.tv_add_task_list),
        withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)
    )
    private val listNameFieldMatcher = allOf(
        withId(R.id.et_task_list_name),
        withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)
    )
    private val cancelButtonMatcher = allOf(
        withId(R.id.ib_close_list_name),
        withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)
    )
    private val doneButtonMatcher = allOf(
        withId(R.id.ib_done_list_name),
        withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)
    )
    private val listsRecyclerViewMatcher = withId(R.id.rv_task_list)

    fun tapOnAddList() = tapRecyclerItem(listsRecyclerViewMatcher, addListButtonMatcher)
    fun typeInListName(listName: String) = typeInText(listNameFieldMatcher, listName)
    fun tapOnDoneButton() = tapOn(doneButtonMatcher)
    fun isListDisplayed(listName: String) =
        isRecyclerItemDisplayed(listsRecyclerViewMatcher, withText(listName))
}