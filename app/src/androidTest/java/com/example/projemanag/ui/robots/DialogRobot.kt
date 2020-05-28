package com.example.projemanag.ui.robots

import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.projemanag.R

fun dialog(func: DialogRobot.() -> Unit) = DialogRobot().apply { func() }

class DialogRobot : BaseRobot() {
    private val dialogTitleMatcher = withId(R.id.tvTitle)
    private val dialogRecyclerViewerMatcher = withId(R.id.rvList)
    private val colorMatcher = withId(R.id.view_main)
}