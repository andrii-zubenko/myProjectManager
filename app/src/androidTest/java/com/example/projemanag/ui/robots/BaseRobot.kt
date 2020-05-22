package com.example.projemanag.ui.robots

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.projemanag.R
import com.example.projemanag.ui.utils.ToastMatcher
import com.example.projemanag.ui.utils.getTextWithMatcher
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

open class BaseRobot {
    private val activityTitleMatcher: Matcher<View> = withId(R.id.tv_title)
    private val snackbarTextMatcher: Matcher<View> = withId(R.id.snackbar_text)

    fun tapOn(matcher: Matcher<View>) = onView(matcher).perform(click())
    fun typeInText(matcher: Matcher<View>, text: String) = onView(matcher)
        .perform(replaceText(text), closeSoftKeyboard())
    fun getActivityTitleText(): String? = getTextWithMatcher(activityTitleMatcher)
    fun snackbarIsDisplayed() =
        onView(snackbarTextMatcher).check(matches(isDisplayed()))
    fun getSnackbarText(): String? = getTextWithMatcher(snackbarTextMatcher)
    fun snackbarWithTextIsDisplayed(text: String) =
        onView(allOf(snackbarTextMatcher, withText(text))).check(matches(isDisplayed()))
    fun toastWithTextIsDiplayed(text: String) =
        onView(withText(text)).inRoot(ToastMatcher()).check(matches(isDisplayed()))
}
