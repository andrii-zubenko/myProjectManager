package com.example.projemanag.ui.robots

import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.projemanag.R

fun card(func: CardRobot.() -> Unit) = CardRobot().apply { func() }

class CardRobot : BaseRobot() {
    private val cardNameMatcher = withId(R.id.et_name_card_details)
    private val colorSelectorMatcher = withId(R.id.tv_select_label_color)
    private val dueDateSelectorMatcher = withId(R.id.tv_select_due_date)
    private val updateButtonMatcher = withId(R.id.btn_update_card_details)

    fun typeInCardName(cardName: String) = typeInText(cardNameMatcher, cardName)
    fun tapOnSelectColor() = tapOn(colorSelectorMatcher)
    fun tapOnUpdateButton() = tapOn(updateButtonMatcher)
}