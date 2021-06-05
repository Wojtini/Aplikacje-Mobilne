package com.example.pingpong

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [(Game::class)], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun gameDao() : GameDao

}