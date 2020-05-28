package com.example.projemanag.ui.robots

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withTagValue
import com.example.projemanag.R
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf

fun dialog(func: DialogRobot.() -> Unit) = DialogRobot().apply { func() }

class DialogRobot : BaseRobot() {
    private val dialogTitleMatcher = withId(R.id.tvTitle)
    private val dialogRecyclerViewerMatcher = withId(R.id.rvList)
    private val colorMatcher = withId(R.id.view_main)

    fun tapOnColor(color: String) {
        onView(
            allOf(
                withTagValue(`is`(color)),
                colorMatcher
            )
        ).perform(click())
    }
}