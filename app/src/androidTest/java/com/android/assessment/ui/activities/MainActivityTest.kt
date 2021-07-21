package com.android.assessment.ui.activities

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.android.assessment.R
import com.android.assessment.data.TestData
import com.android.assessment.ui.adapter.HomeAdapter
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule<MainActivity>(
        MainActivity::class.java
    )
    private val listItem = 2
    private val venues = TestData.testVenueData?.get(listItem)


    @Test
    fun test_isListFragmentVisible_onAppLaunch() {
        try {
            Thread.sleep(4000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        onView(withId(R.id.rv_home)).check(matches(isDisplayed()))
    }

    @Test
    fun test_selectListItem_isDetailActivityVisible() {
        try {
            Thread.sleep(4000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        onView(withId(R.id.rv_home)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                listItem
            )
        )
        onView(withId(R.id.rv_home))
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(listItem, click()))

    }


    @Test
    fun test_backNavigation_toVenueListFragment() {
        try {
            Thread.sleep(4000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        onView(withId(R.id.rv_home))
            .perform(actionOnItemAtPosition<HomeAdapter.HomeViewHolder>(listItem, click()))
        onView(withId(R.id.venue_title)).check(matches(withText(venues?.name)))
        pressBack()
        onView(withId(R.id.rv_home)).check(matches(isDisplayed()))
    }

}