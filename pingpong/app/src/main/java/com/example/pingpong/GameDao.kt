package com.example.pingpong

import androidx.room.*

@Dao
interface GameDao {
    @Query("SELECT * FROM games")
    fun getAll() : List<Game>

    @Insert
    fun insertAll(vararg game : Game)

}