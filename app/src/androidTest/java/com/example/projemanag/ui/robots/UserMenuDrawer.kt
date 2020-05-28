package com.example.projemanag.ui.robots

import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.projemanag.R
import com.example.projemanag.ui.utils.getTextWithMatcher

fun drawer(func: UserMenuDrawer.() -> Unit) = UserMenuDrawer().apply { func() }

class UserMenuDrawer : BaseRobot() {
    private val userNameMatcher = withId(R.id.tv_username)

    fun getUserName() = getTextWithMatcher(userNameMatcher)
}