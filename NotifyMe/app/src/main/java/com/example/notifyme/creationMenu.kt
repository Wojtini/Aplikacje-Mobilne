package com.example.notifyme

import android.R as hehe
import android.app.Activity
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.notifyme.databinding.CreationmenuBinding
import java.util.*
import kotlin.time.hours
import kotlin.time.minutes


class creationMenu : AppCompatActivity() {

    private lateinit var binding: CreationmenuBinding
    var items = arrayOf("1", "2", "3")
    var hour = arrayOf("0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23")
    var minute = arrayOf("0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59")
    val myintent = Intent()
    var icon = R.drawable.redsquare

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CreationmenuBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val dropdown = binding.spinner1
        val dropdown2 = binding.godzina
        val dropdown3 = binding.minuta
        val adapter: ArrayAdapter<String?> = ArrayAdapter<String?>(this, hehe.layout.simple_spinner_dropdown_item, items)
        val adapter2: ArrayAdapter<String?> = ArrayAdapter<String?>(this, hehe.layout.simple_spinner_dropdown_item, hour)
        val adapter3: ArrayAdapter<String?> = ArrayAdapter<String?>(this, hehe.layout.simple_spinner_dropdown_item, minute)
        dropdown.setAdapter(adapter)
        dropdown2.setAdapter(adapter2)
        dropdown3.setAdapter(adapter3)

        binding.datePick.spinnersShown = false

        binding.square.setImageResource(R.drawable.redsquare)

    }

    fun creationButton(view: View) {
        val nazwa = binding.nazwa.text.toString()
        val prio = binding.spinner1.selectedItem.toString()
        var date = binding.datePick
        var hours = binding.godzina.selectedItem.toString().toInt()
        var minutes = binding.minuta.selectedItem.toString().toInt()
        val date2 = Date(date.year,date.month,date.dayOfMonth,hours,minutes)

        myintent.putExtra("nazwa",nazwa)
        myintent.putExtra("prio",prio)
        myintent.putExtra("test",icon)
        myintent.putExtra("date",date2)

        setResult(Activity.RESULT_OK,myintent)
        finish()
    }

    fun onImageSelect(v: View){
        binding.triangle.setImageResource(R.drawable.graytriangle)
        binding.square.setImageResource(R.drawable.graysquare)
        binding.circle.setImageResource(R.drawable.graycircle)
        when (v.id){
            binding.triangle.id -> {
                binding.triangle.setImageResource(R.drawable.greentriangle)
                icon = R.drawable.greentriangle
            }
            binding.square.id -> {
                binding.square.setImageResource(R.drawable.redsquare)
                icon = R.drawable.redsquare
            }
            binding.circle.id -> {
                binding.circle.setImageResource(R.drawable.bluecircle)
                icon = R.drawable.bluecircle
            }
        }
    }

}
