package com.example.notifyme

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.CalendarView
import android.widget.DatePicker


class scrollCalendar : DatePicker {
    constructor(context: Context?) : super(context!!) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!,
        attrs
    ) {
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyle: Int
    ) : super(context!!, attrs, defStyle) {
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        if (ev.actionMasked == MotionEvent.ACTION_DOWN) {
            val p = this.parent
            p?.requestDisallowInterceptTouchEvent(true)
        }
        return false
    }
}