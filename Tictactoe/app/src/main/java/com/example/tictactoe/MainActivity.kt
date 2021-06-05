package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.tictactoe.databinding.ActivityActivity3x3Binding
import com.example.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.result.text = "Ostatnią gra:\n brak"
    }

    fun create3x3Game(view: View){
        val myintent = Intent(this, Activity3x3::class.java)
        startActivityForResult(myintent,123)
    }

    fun create5x5Game(view: View){
        val myintent = Intent(this, Activity5x5::class.java)
        startActivityForResult(myintent,123)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val res = data?.getStringExtra("winner")
        binding.result.text = "Ostatnią gra:\n $res"
        Log.d("Result","return string $res")
    }
}