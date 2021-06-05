package com.example.tictactoe

import android.util.Log
import java.lang.Exception

class tictactoegame {
    public var board = arrayOf(arrayOf('-'));
    var s = 0

    constructor(gridSize: Int){
        if (gridSize == 3){
            Create3x3Board()
            s = 3
        }else if (gridSize == 5) {
            Create5x5Board()
            s = 5
        }
    }

    private fun Create3x3Board(){
        board = arrayOf(
            arrayOf('-','-','-'),
            arrayOf('-','-','-'),
            arrayOf('-','-','-')
        )
    }

    private fun Create5x5Board(){
        board = arrayOf(
            arrayOf('-','-','-','-','-'),
            arrayOf('-','-','-','-','-'),
            arrayOf('-','-','-','-','-'),
            arrayOf('-','-','-','-','-'),
            arrayOf('-','-','-','-','-')
        )
    }

    public fun getPawn(x: Int,y: Int):Char{
        return board[x][y]
    }

    public fun CheckIfDraw(): Boolean{
        val i = board.iterator()
        while(i.hasNext()){
            val j = i.next().iterator()
            while(j.hasNext()){
                if (j.next()=='-'){
                    return false
                }
            }
        }
        return true
    }

    public fun CheckIfWin(requiredAmount: Int, who: Char): Boolean{
        var counter = 0
        // | | | | |
        for(i in 0..s-1){
            for(j in 0..s-1){
                counter = 0
                if (who == board[i][j]){
                    for(k in i..s-1){
                        if(board[k][j]==who){
                            counter += 1
                            if(counter==requiredAmount){
                                return true
                            }
                        }else{
                            counter = -100
                        }
                    }
                }
            }
        }

        // - - - - -
        for(i in 0..s-1){
            for(j in 0..s-1){
                counter = 0
                if (who == board[i][j]){
                    for(k in j..s-1){
                        if(board[i][k]==who){
                            counter += 1
                            if(counter==requiredAmount){
                                return true
                            }
                        }else{
                            counter = -100
                        }
                    }
                }
            }
        }

        // \ \ \ \ \
        for(i in 0..s-1){
            for(j in 0..s-1){
                counter = 0
                if (who == board[i][j]){
                    for(k in 0..s-j-1){
                        if(i+k < s && j+k < s) {
                            if (board[i + k][j + k] == who) {
                                counter += 1
                                if (counter == requiredAmount) {
                                    return true
                                }
                            } else {
                                counter = -100
                            }
                        }
                    }
                }
            }
        }
        // / / / / /

        for(i in 0..s-1){
            for(j in 0..s-1){
                counter = 0
                if (who == board[i][j]){
                    Log.d("Search","-------Tried win for pawn on $i and $j")
                    for(k in 0..s){
                        try{
                            Log.d("Search","Searched on $i and $j curr k: $k")
                            if (board[i + k][j - k] == who) {
                                counter += 1
                                Log.d("Search","Success")
                                if (counter == requiredAmount) {
                                    return true
                                }
                            } else {
                                counter = -100
                                Log.d("Search","Rip")
                            }
                        }catch (ex: Exception){
                            Log.d("Search","xD")
                        }
                    }
                }
            }
        }
        return false
    }

    public fun PlacePawn(x: Int, y: Int, who: Char): Boolean{
        Log.d("Refresh","Tried pawn on $x and $y")
        if(board[x][y].equals('-')){
            Log.d("Refresh","Pawn inserted on $x and $y")
            board[x][y] = who;
            return true
        }else{
            return false
        }
    }

}