package com.example.projemanag.ui.robots

import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.hasSibling
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withChild
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.projemanag.R
import com.example.projemanag.ui.utils.getColorWithMatcher
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
        withId(R.id.tv_add_card)
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
    private val listNameMatcher = withId(R.id.tv_task_list_title)
    private val cardsRecyclerViewMatcher = withId(R.id.rv_card_list)
    private val cardNameMatcher = withId(R.id.tv_card_name)
    private val cardColorMatcher = withId(R.id.view_label_color)
    private val deleteBoardButtonMatcher = withId(R.id.action_delete_board)

    fun tapAddCard(listName: String) {
        val currentAddCardButtonMatcher = allOf(
            addCardButtonMatcher,
            hasSibling(
                withChild(
                    allOf(
                        listNameMatcher,
                        withText(listName)
                    )
                )
            )
        )
        tapRecyclerItem(listsRecyclerViewMatcher, currentAddCardButtonMatcher)
    }

    fun tapOnDeleteBoard() = tapOn(deleteBoardButtonMatcher)
    fun tapOnAddList() = tapRecyclerItem(listsRecyclerViewMatcher, addListButtonMatcher)
    fun typeInListName(listName: String) = typeInText(listNameFieldMatcher, listName)
    fun tapOnListDoneButton() = tapOn(listDoneButtonMatcher)
    fun isListDisplayed(listName: String) =
        isRecyclerItemDisplayed(listsRecyclerViewMatcher, withText(listName))

    fun typeInCardName(cardName: String) = typeInText(cardNameFieldMatcher, cardName)
    fun tapOnCardDoneButton() = tapOn(cardDoneButtonMatcher)
    fun isCardDisplayed(listName: String, cardName: String) {
        val currentCardsRecyclerView = allOf(
            cardsRecyclerViewMatcher,
            hasSibling(
                withChild(
                    allOf(
                        listNameMatcher,
                        withText(listName)
                    )
                )
            )
        )
        val currentCardMatcher = allOf(
            cardNameMatcher,
            withText(cardName)
        )
        scrollToItemInRecyclerView(listsRecyclerViewMatcher, currentCardsRecyclerView)
        isRecyclerItemDisplayed(currentCardsRecyclerView, currentCardMatcher)
    }

    fun getCardColorDisplayed(listName: String, cardName: String): String {
        val currentCardsRecyclerView = allOf(
            cardsRecyclerViewMatcher,
            hasSibling(
                withChild(
                    allOf(
                        listNameMatcher,
                        withText(listName)
                    )
                )
            )
        )
        val currentCardMatcher = allOf(
            cardNameMatcher,
            withText(cardName)
        )
        scrollToItemInRecyclerView(listsRecyclerViewMatcher, currentCardsRecyclerView)
        val color = getColorWithMatcher(
            allOf(
                cardColorMatcher,
                isDescendantOfA(currentCardsRecyclerView),
                hasSibling(currentCardMatcher)
            )
        )
        return String.format("#%06X", (0xFFFFFF and color!!))
    }

    fun tapOnCard(listName: String, cardName: String) {
        val currentCardsRecyclerView = allOf(
            cardsRecyclerViewMatcher,
            hasSibling(
                withChild(
                    allOf(
                        listNameMatcher,
                        withText(listName)
                    )
                )
            )
        )
        val currentCardMatcher = allOf(
            cardNameMatcher,
            withText(cardName)
        )
        scrollToItemInRecyclerView(listsRecyclerViewMatcher, currentCardsRecyclerView)
        tapRecyclerItem(currentCardsRecyclerView, currentCardMatcher)
    }
}
