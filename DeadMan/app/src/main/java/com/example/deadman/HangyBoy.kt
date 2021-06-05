package com.example.deadman

import android.util.Log

class HangyBoy {

    val guessedChars: MutableList<Int> = ArrayList()
    var word : String = ""

    var currPhase = 0
    val endPhase = 12

    constructor(gword: String){
        Log.d("wojt","Wylosowano słowo $gword")
        word = gword
        for(i in 0..word.length-1){
            guessedChars.add(0)
        }
    }

    fun guess(letter: Char){
        var foundAtLeastOne = false;
        for(i in 0..word.length-1){
            if(word[i] == letter){
                guessedChars[i] = 1
                foundAtLeastOne = true;
            }
        }

        if(!foundAtLeastOne){
            currPhase += 1
            if(currPhase > endPhase){
                currPhase = endPhase
            }
        }
    }

    fun checkIfWin():Boolean{
        var win = true
        for(i in 0..guessedChars.size-1){
            if(guessedChars[i]==0){
                win = false
            }
        }
        return win
    }

    fun getPhase():Int{
        return currPhase
    }

    fun getGuessWord():String{
        var ret = word
        for(i in 0..guessedChars.size-1){
            if(guessedChars[i]==0){
                ret = ret.substring(0, i) +  '*' + ret.substring(i + 1)
            }
        }
        return ret
    }
}