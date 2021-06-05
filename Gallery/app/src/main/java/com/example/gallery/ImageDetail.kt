package com.example.gallery

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.gallery.databinding.ActivityImageDetailBinding

class ImageDetail : AppCompatActivity() {
    private lateinit var binding: ActivityImageDetailBinding
    var id: Int = 0
    val myintent = Intent()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val a = binding.imageView2
        Log.d("test",intent.getIntExtra("img",-1).toString())
        a.setImageResource(intent.getIntExtra("img",0))


        val b = binding.textView
        b.text = intent.getStringExtra("desc")

        val c = binding.ratingBar2
        c.numStars = 5
        c.rating = intent.getFloatExtra("rating",0f)

        id = intent.getIntExtra("id",0)

        setResult(Activity.RESULT_OK,myintent)
        myintent.putExtra("id",id)
    }

    override fun onBackPressed() {
        myintent.putExtra("rating", findViewById<RatingBar>(R.id.ratingBar2).rating)
        super.onBackPressed()
    }
}