package com.example.projemanag.utils

import android.content.Context
import android.view.View
import android.widget.Toast


class ToastManager {
    private val listener: View.OnAttachStateChangeListener =
        object : View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(v: View?) {
                println("incrementing")
                Utils.countingIdlingResource.increment()
            }

            override fun onViewDetachedFromWindow(v: View?) {
                println("decrementing")
                Utils.countingIdlingResource.decrement()
            }
        }

    fun makeText(context: Context?, text: CharSequence?, duration: Int): Toast {
        val t = Toast.makeText(context, text, duration)
        t.view.addOnAttachStateChangeListener(listener)
        return t
    }

}