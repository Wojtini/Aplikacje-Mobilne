package com.example.pingpong

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "games")
data class Game(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id : Long = 0,
    @ColumnInfo(name = "player_score") var playerScore : Int,
    @ColumnInfo(name = "bot_score") var botScore : Int
)