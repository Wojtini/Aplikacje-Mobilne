package com.example.pingpong

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var database : Database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        GlobalScope.launch {
//            try {
//                database = Room.databaseBuilder(
//                    applicationContext,
//                    Database::class.java,
//                    "game.db"
//                ).build()
//            } catch(e: Exception){
//                Log.d("xd","something wong")
//            }
//            database.clearAllTables()
//            database.gameDao().insertAll(Game(0,0,0))
//            Log.d("xd",database.gameDao().getAll()[0].botScore.toString())
//          }

        }
}