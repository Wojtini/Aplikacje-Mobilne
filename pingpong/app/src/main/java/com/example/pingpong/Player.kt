package com.example.pingpong

import android.graphics.Paint
import android.graphics.RectF


class Player(
    var x: Float,
    var y: Float,
    var width: Float,
    var height: Float,
    var paint: Paint,
    var score: Int=0, //not used
    var speed: Float=10f,
    var rect: RectF = RectF()
) {
}