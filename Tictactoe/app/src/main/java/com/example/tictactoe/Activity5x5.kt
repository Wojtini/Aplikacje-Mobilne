package com.example.tictactoe

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.tictactoe.databinding.ActivityActivity3x3Binding
import com.example.tictactoe.databinding.ActivityActivity5x5Binding

class Activity5x5 : AppCompatActivity() {
    private lateinit var binding: ActivityActivity5x5Binding

    var game = tictactoegame(5);
    var turn = 'O'
    val myintent = Intent()
    var gameOn = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActivity5x5Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        refresh()
        setResult(Activity.RESULT_OK,myintent)
        myintent.putExtra("winner","PRZERWANO")
    }

    fun clickHandler(v: View){
        if(!gameOn){
            return
        }
        var success = false
        Log.d("Test",v.id.toString())
        when (v.id){
            binding.b00.id -> success = game.PlacePawn(0,0, turn)
            binding.b01.id -> success = game.PlacePawn(0,1, turn)
            binding.b02.id -> success = game.PlacePawn(0,2, turn)
            binding.b03.id -> success = game.PlacePawn(0,3, turn)
            binding.b04.id -> success = game.PlacePawn(0,4, turn)

            binding.b10.id -> success = game.PlacePawn(1,0, turn)
            binding.b11.id -> success = game.PlacePawn(1,1, turn)
            binding.b12.id -> success = game.PlacePawn(1,2, turn)
            binding.b13.id -> success = game.PlacePawn(1,3, turn)
            binding.b14.id -> success = game.PlacePawn(1,4, turn)

            binding.b20.id -> success = game.PlacePawn(2,0, turn)
            binding.b21.id -> success = game.PlacePawn(2,1, turn)
            binding.b22.id -> success = game.PlacePawn(2,2, turn)
            binding.b23.id -> success = game.PlacePawn(2,3, turn)
            binding.b24.id -> success = game.PlacePawn(2,4, turn)

            binding.b30.id -> success = game.PlacePawn(3,0, turn)
            binding.b31.id -> success = game.PlacePawn(3,1, turn)
            binding.b32.id -> success = game.PlacePawn(3,2, turn)
            binding.b33.id -> success = game.PlacePawn(3,3, turn)
            binding.b34.id -> success = game.PlacePawn(3,4, turn)

            binding.b40.id -> success = game.PlacePawn(4,0, turn)
            binding.b41.id -> success = game.PlacePawn(4,1, turn)
            binding.b42.id -> success = game.PlacePawn(4,2, turn)
            binding.b43.id -> success = game.PlacePawn(4,3, turn)
            binding.b44.id -> success = game.PlacePawn(4,4, turn)
        }
        if(success){
            Log.d("Refresh","sukces")
            refresh()

            if(game.CheckIfWin(4,turn)){
                myintent.putExtra("winner","$turn Wygrał")
                setResult(Activity.RESULT_OK,myintent)
                binding.gameinfo.text = "$turn Wygrał"
                gameOn = false
            } else if(game.CheckIfDraw()){
                myintent.putExtra("winner","REMIS")
                setResult(Activity.RESULT_OK,myintent)
                binding.gameinfo.text = "REMIS"
            }

            nextTurn()
        }
    }

    fun nextTurn(){
        if(turn=='X'){
            turn = 'O'
        }else{
            turn = 'X'
        }
    }

    fun refresh(){
        Log.d("Refresh","git")
        binding.b00.text = game.getPawn(0,0).toString()
        binding.b01.text = game.getPawn(0,1).toString()
        binding.b02.text = game.getPawn(0,2).toString()
        binding.b03.text = game.getPawn(0,3).toString()
        binding.b04.text = game.getPawn(0,4).toString()
        binding.b10.text = game.getPawn(1,0).toString()
        binding.b11.text = game.getPawn(1,1).toString()
        binding.b12.text = game.getPawn(1,2).toString()
        binding.b13.text = game.getPawn(1,3).toString()
        binding.b14.text = game.getPawn(1,4).toString()
        binding.b20.text = game.getPawn(2,0).toString()
        binding.b21.text = game.getPawn(2,1).toString()
        binding.b22.text = game.getPawn(2,2).toString()
        binding.b23.text = game.getPawn(2,3).toString()
        binding.b24.text = game.getPawn(2,4).toString()
        binding.b30.text = game.getPawn(3,0).toString()
        binding.b31.text = game.getPawn(3,1).toString()
        binding.b32.text = game.getPawn(3,2).toString()
        binding.b33.text = game.getPawn(3,3).toString()
        binding.b34.text = game.getPawn(3,4).toString()
        binding.b40.text = game.getPawn(4,0).toString()
        binding.b41.text = game.getPawn(4,1).toString()
        binding.b42.text = game.getPawn(4,2).toString()
        binding.b43.text = game.getPawn(4,3).toString()
        binding.b44.text = game.getPawn(4,4).toString()
    }
}