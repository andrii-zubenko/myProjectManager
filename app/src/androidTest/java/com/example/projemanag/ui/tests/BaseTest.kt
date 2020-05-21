package com.example.projemanag.ui.tests

import androidx.test.espresso.IdlingRegistry
import com.example.projemanag.utils.Utils
import com.schibsted.spain.barista.rule.cleardata.ClearDatabaseRule
import com.schibsted.spain.barista.rule.cleardata.ClearFilesRule
import com.schibsted.spain.barista.rule.cleardata.ClearPreferencesRule
import org.junit.After
import org.junit.Before
import org.junit.Rule

open class BaseTest {
    @Before
    fun setup() {
        IdlingRegistry.getInstance().register(Utils.countingIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(Utils.countingIdlingResource)
    }

//    @get:Rule
//    var clearPreferencesRule = ClearPreferencesRule()
//    @get:Rule
//    var clearDatabaseRule = ClearDatabaseRule()
//    @get:Rule
//    var clearFilesRule = ClearFilesRule()
}
