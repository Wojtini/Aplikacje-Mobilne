package com.example.pingpong

import android.graphics.Canvas
import android.view.SurfaceHolder

class PongThread(private val surfaceHolder: SurfaceHolder, private val pongView: PongView)
    : Thread() {

    public var running = false
    private var canvas : Canvas? = null
    private var targetFPS = 30

    override fun run() {
        super.run()
        var startTime : Long
        var timeMillis : Long
        var waitTime : Long
        var targetTime = (1000/targetFPS).toLong()

        while (running){
            startTime = System.nanoTime()
            var canvas = surfaceHolder.lockCanvas()
            pongView.update()
            pongView.draw(canvas)
            surfaceHolder.unlockCanvasAndPost(canvas)

            timeMillis = (System.nanoTime() - startTime)/ 1000000
            waitTime = targetTime - timeMillis

            if(waitTime >= 0)
                sleep(waitTime)
        }
    }
}