package com.komeyama.simple.weather.forecast.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavigationBehavior constructor(context: Context, attrs: AttributeSet? = null) :
    CoordinatorLayout.Behavior<BottomNavigationView>(context, attrs) {

    /**
     * Because the initial movement of the animation is slowed down by the slight change in the scrolling
     */
    var scrolledUp = true
    var scrolledDown = true

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: BottomNavigationView,
        dependency: View
    ): Boolean {
        return dependency is FrameLayout
    }

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: BottomNavigationView,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL
    }

    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout, child: BottomNavigationView,
        target: View, dx: Int, dy: Int, consumed: IntArray, type: Int
    ) {
        if (dy < 0) {
            if (scrolledUp) {
                scrolledUp = false
                scrolledDown = true
                showBottomNavigationView(child)
            }
        } else if (dy > 0) {
            if (scrolledDown) {
                scrolledUp = true
                scrolledDown = false
                hideBottomNavigationView(child)
            }
        }
    }

    private fun hideBottomNavigationView(view: BottomNavigationView) {
        view.animate().translationY(view.height.toFloat())
    }

    private fun showBottomNavigationView(view: BottomNavigationView) {
        view.animate().translationY(0f)
    }
}