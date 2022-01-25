package com.example.projemanag.ui.utils

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.PerformException
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matcher

object Waiter {

    fun waitForViewIsDisplayed(
        viewMatcher: Matcher<View>,
        waitMillis: Int = 30000
    ): ViewInteraction {
        val waitMillisPerTry = 500
        val maxTries = waitMillis / waitMillisPerTry

        for (tries in 0..maxTries) {
            if (onView(viewMatcher).isDisplayed()) {
                return onView(viewMatcher)
            }
            Thread.sleep(waitMillisPerTry.toLong())
        }
        throw PerformException.Builder()
            .withActionDescription("Waiting for view to be displayed")
            .withViewDescription(viewMatcher.toString())
            .withCause(
                RuntimeException("Unable to find a view $viewMatcher")
            )
            .build()
    }

    fun ViewInteraction.isDisplayed(): Boolean {
        return try {
            check(matches(ViewMatchers.isDisplayed()))
            true
        } catch (e: NoMatchingViewException) {
            false
        } catch (t: Throwable) {
            false
        }
    }
}
