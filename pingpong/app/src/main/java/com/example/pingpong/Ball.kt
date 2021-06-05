package com.example.pingpong

import android.graphics.Paint
import android.graphics.RectF


class Ball(var radius: Int, var paint: Paint) {
    var x = 0f
    var y = 0f
    var dx = 0f
    var dy = 0f
    var rect: RectF = RectF()
}