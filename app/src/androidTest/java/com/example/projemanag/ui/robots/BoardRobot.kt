package com.example.projemanag.ui.robots

import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.hasSibling
import androidx.test.espresso.matcher.ViewMatchers.withChild
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
    private val listCancelButtonMatcher = allOf(
        withId(R.id.ib_close_list_name),
        withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)
    )
    private val listDoneButtonMatcher = allOf(
        withId(R.id.ib_done_list_name),
        withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)
    )
    private val addCardButtonMatcher = allOf(
        withId(R.id.tv_add_card),
        withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)
    )
    private val cardCancelButtonMatcher = allOf(
        withId(R.id.ib_close_card_name),
        withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)
    )
    private val cardDoneButtonMatcher = allOf(
        withId(R.id.ib_done_card_name),
        withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)
    )
    private val cardNameFieldMatcher = allOf(
        withId(R.id.et_card_name),
        withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)
    )
    private val listsRecyclerViewMatcher = withId(R.id.rv_task_list)
    private val listMatcher = withId(R.id.ll_task_item)
    private val cardsRecyclerViewMatcher = withId(R.id.rv_card_list)
    private val cardMatcher = withId(R.id.tv_card_name)

    fun tapAddCard(cardName: String) {
        val currentAddCardButtonMatcher = allOf(
            addCardButtonMatcher,
            hasSibling(
                withChild(
                    allOf(
                        withId(R.id.tv_task_list_title),
                        withText(cardName)
                    )
                )
            )
        )
        tapRecyclerItem(listsRecyclerViewMatcher, currentAddCardButtonMatcher)
    }

    fun tapOnAddList() = tapRecyclerItem(listsRecyclerViewMatcher, addListButtonMatcher)
    fun typeInListName(listName: String) = typeInText(listNameFieldMatcher, listName)
    fun tapOnListDoneButton() = tapOn(listDoneButtonMatcher)
    fun isListDisplayed(listName: String) =
        isRecyclerItemDisplayed(listsRecyclerViewMatcher, withText(listName))

    fun typeInCardName(cardName: String) = typeInText(cardNameFieldMatcher, cardName)
    fun tapOnCardDoneButton() = tapOn(cardDoneButtonMatcher)
    fun isCardDisplayed(cardName: String) {
        val currentCardRecyclerViewMatcher = allOf(
            cardsRecyclerViewMatcher,
            hasDescendant(allOf(
                cardMatcher,
                withText(cardName)
            ))
        )
        isRecyclerItemDisplayed(currentCardRecyclerViewMatcher, withText(cardName))
    }
}