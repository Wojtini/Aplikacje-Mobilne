package com.example.tictactoe

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.tictactoe.databinding.ActivityActivity3x3Binding

class Activity3x3 : AppCompatActivity() {
    private lateinit var binding: ActivityActivity3x3Binding

    var game = tictactoegame(3);
    var turn = 'O'
    val myintent = Intent()
    var gameOn = true;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActivity3x3Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        refresh()
        myintent.putExtra("winner","PRZERWANO")
        setResult(Activity.RESULT_OK,myintent)
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
            binding.b10.id -> success = game.PlacePawn(1,0, turn)
            binding.b11.id -> success = game.PlacePawn(1,1, turn)
            binding.b12.id -> success = game.PlacePawn(1,2, turn)
            binding.b20.id -> success = game.PlacePawn(2,0, turn)
            binding.b21.id -> success = game.PlacePawn(2,1, turn)
            binding.b22.id -> success = game.PlacePawn(2,2, turn)
        }
        if(success){
            Log.d("Refresh","sukces")
            refresh()

            if(game.CheckIfWin(3,turn)){
                myintent.putExtra("winner","$turn Wygrał")
                setResult(Activity.RESULT_OK,myintent)
                binding.gameinfo.text = "$turn Wygrał"
                gameOn = false
            } else if(game.CheckIfDraw()){
                myintent.putExtra("winner","REMIS")
                setResult(Activity.RESULT_OK,myintent)
                binding.gameinfo.text = "Remis"
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
        binding.b10.text = game.getPawn(1,0).toString()
        binding.b11.text = game.getPawn(1,1).toString()
        binding.b12.text = game.getPawn(1,2).toString()
        binding.b20.text = game.getPawn(2,0).toString()
        binding.b21.text = game.getPawn(2,1).toString()
        binding.b22.text = game.getPawn(2,2).toString()
    }
}