package com.example.projemanag.ui.robots

import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.projemanag.R

fun createBoard(func: CreateBoardRobot.() -> Unit) =
    CreateBoardRobot().apply { func() }

class CreateBoardRobot : BaseRobot() {
    private val boardImageMatcher = withId(R.id.iv_board_image)
    private val boardNameFieldMatcher = withId(R.id.et_board_name)
    private val createButtonMatcher = withId(R.id.btn_create)

    fun typeInBoardNameField(text: String) = typeInText(boardNameFieldMatcher, text)
    fun tapOnCreateButton() = tapOn(createButtonMatcher)
}
