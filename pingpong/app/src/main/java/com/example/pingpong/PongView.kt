package com.example.pingpong

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PongView(context: Context, attributeSet: AttributeSet)
    : SurfaceView(context,attributeSet), SurfaceHolder.Callback
{
    val red = Paint().apply {
        color = Color.RED
    }
    val blue = Paint().apply {
        color = Color.BLUE
    }
    val green = Paint().apply {
        color = Color.GREEN
    }
    val white = Paint().apply {
        color = Color.WHITE
    }
    lateinit var database : Database
    private var goLeft: Boolean=false
    private var goRight: Boolean=false
    private val gkOffset: Float
    private val ball : Ball
    private val player : Player
    private val bot : Player
    private val Thread : PongThread
    init {
        gkOffset = 100f

        holder.addCallback(this)
        Thread = PongThread(holder,this)

        ball = Ball(100,green)
        ball.dx = 10f
        ball.dy = 10f


        player = Player(0f,0f,250f,30f,red)
        bot = Player(0f,0f,250f,30f,blue)

    }
    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        Thread.running = true
        resetBall()
        Thread.start()
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        Thread.running = false
        Thread.join()
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        loadScore()
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        if(canvas == null) return

        //rysowanie wyniku
        white.textSize = 200f
        canvas.drawText(player.score.toString(),width.toFloat()/2-50f,height.toFloat()/2-100f,white)
        canvas.drawText(bot.score.toString(),width.toFloat()/2-50f,height.toFloat()/2+100f,white)

        //gra
        player.y = gkOffset
        bot.y = height - gkOffset

        // ''''AI''''
        bot.x = ball.x - ball.radius
        //popraw ai
        if(bot.x+bot.width > width){
            bot.x = width - bot.width
        }
        if(bot.x < 0)
            bot.x = 0f

        //popraw gracza
        if(player.x+player.width > width){
            player.x = width - bot.width
        }
        if(player.x < 0)
            player.x = 0f

        canvas.drawRect(player.rect,player.paint)
        canvas.drawRect(bot.rect,bot.paint)
        canvas.drawOval(ball.rect, ball.paint)
    }

    fun saveScore(){
        GlobalScope.launch {
            try {
                database = Room.databaseBuilder(
                    context,
                    Database::class.java,
                    "game.db"
                ).build()
            } catch(e: Exception){
                Log.d("xd","something wrong")
            }
            database.clearAllTables()
            database.gameDao().insertAll(Game(0,player.score,bot.score))
            Log.d("xd",database.gameDao().getAll().toString())
        }
    }

    fun loadScore(){
        GlobalScope.launch {
            try {
                database = Room.databaseBuilder(
                        context,
                        Database::class.java,
                        "game.db"
                ).build()
            } catch(e: Exception){
                Log.d("xd","something wrong")
            }
//            database.clearAllTables()
//            database.gameDao().insertAll(Game(0,player.score,bot.score))
            Log.d("xd",database.gameDao().getAll().toString())
            if(database.gameDao().getAll().isEmpty()){
                return@launch
            }
            bot.score = database.gameDao().getAll()[0].botScore
            player.score = database.gameDao().getAll()[0].playerScore
        }
    }

    fun update(){
        ball.x += ball.dx
        ball.y += ball.dy

        if(goLeft){
            player.x -= player.speed
            goLeft = false
        }
        if(goRight){
            player.x += player.speed
            goRight = false
        }

        if(ball.x <=0 || ball.x + ball.radius>=width) {
            ball.dx = -ball.dx
        }

        if(ball.y <=0) { //gora czyli gracz
            ball.dy = -ball.dy
            bot.score += 1
            saveScore()
            resetBall()
        }
        if(ball.y + ball.radius>=height){ // bot
            ball.dy = -ball.dy
            player.score += 1
            saveScore()
            resetBall()
        }


        player.rect.set(player.x,player.y,player.x+player.width,player.y+player.height)
        bot.rect.set(bot.x,bot.y,bot.x+bot.width,bot.y+bot.height)
        ball.rect.set(ball.x, ball.y, ball.x+ball.radius, ball.y+ball.radius)

        if(checkCol(bot) || checkCol(player)){
            ball.dy = -ball.dy
        }
    }

    fun checkCol(p : Player): Boolean {
        return p.rect.intersect(ball.rect)
    }

    fun resetGame(){
        resetBall()
        player.score = 0
        bot.score = 0
    }

    fun resetBall(){
        ball.x = width.toFloat()/2 - ball.radius
        ball.y = height.toFloat()/2 - ball.radius
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
//        player.x = event!!.x.toFloat() - player.width/2
        if(event!!.x > width/2){
            //idz w prawo
            goRight = true
            goLeft = false
        }else{
            //idz w lewo
            goLeft = true
            goRight = false
        }
        return true
    }
}