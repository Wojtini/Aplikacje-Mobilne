package com.example.deadman

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.deadman.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var hb : HangyBoy = HangyBoy("")
    var gameOn = false;

    private var images : Array<Int> = arrayOf(R.drawable.hangy0,R.drawable.hangy1,R.drawable.hangy2,R.drawable.hangy3,R.drawable.hangy4,R.drawable.hangy5,R.drawable.hangy6,R.drawable.hangy7,
        R.drawable.hangy8,R.drawable.hangy9,R.drawable.hangy10,R.drawable.hangy11,R.drawable.hangy12
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun refresh(){
        binding.displayedWord.text = hb.getGuessWord()
        binding.hangman.setImageResource(images[hb.getPhase()])
    }

    fun generateNewWord(){
        val array: Array<String> = resources.getStringArray(R.array.slownik)
        val r = Random.nextInt(0,array.size)
        val randWord = array[r]
        hb = HangyBoy(randWord)
        refresh()
        gameOn = true
    }

    fun onClick(v : View){
        generateNewWord()
    }

    fun keyPressed(v : View){
        if(!gameOn){
            return
        }
        when (v.id){
            binding.q.id-> hb.guess('q')
            binding.w.id-> hb.guess('w')
            binding.e.id-> hb.guess('e')
            binding.r.id-> hb.guess('r')
            binding.t.id-> hb.guess('t')
            binding.y.id-> hb.guess('y')
            binding.u.id-> hb.guess('u')
            binding.i.id-> hb.guess('i')
            binding.o.id-> hb.guess('o')
            binding.p.id-> hb.guess('p')
            binding.a.id-> hb.guess('a')
            binding.s.id-> hb.guess('s')
            binding.d.id-> hb.guess('d')
            binding.f.id-> hb.guess('f')
            binding.g.id-> hb.guess('g')
            binding.h.id-> hb.guess('h')
            binding.j.id-> hb.guess('j')
            binding.k.id-> hb.guess('k')
            binding.l.id-> hb.guess('l')
            binding.z.id-> hb.guess('z')
            binding.x.id-> hb.guess('x')
            binding.c.id-> hb.guess('c')
            binding.v.id-> hb.guess('v')
            binding.b.id-> hb.guess('b')
            binding.n.id-> hb.guess('n')
            binding.m.id-> hb.guess('m')
        }
        refresh()
        //Lose condition
        if(hb.currPhase==hb.endPhase && gameOn){
            Toast.makeText(this,"PRZEGRANA",Toast.LENGTH_LONG).show()
            gameOn = false;
        }
        //Win condition
        if(hb.checkIfWin()){
            Toast.makeText(this,"WYGRALES",Toast.LENGTH_LONG).show()
            gameOn = false;
        }
    }
}