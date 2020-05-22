package com.example.projemanag.ui.tests

import androidx.test.espresso.IdlingRegistry
import com.example.projemanag.utils.Utils
import org.junit.After
import org.junit.Before

open class BaseTest {
    @Before
    fun setup() {
        IdlingRegistry.getInstance().register(Utils.countingIdlingResource)
//        var currentUser = FirebaseAuth.getInstance().currentUser
//        if (currentUser != null) {
//            FirebaseAuth.getInstance().signOut()
//        }
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(Utils.countingIdlingResource)
    }
}
