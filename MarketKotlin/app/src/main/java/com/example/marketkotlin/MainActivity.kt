package com.example.marketkotlin

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.marketkotlin.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var isGameOn = false
    private var day = 1
    private var money = 0
    var iron: Product? = null
    var gold: Product? = null

    inner class Product(var name: String, var minCost: Int, var maxCost: Int, private var buysellOffset: Int) {
        var actCost: Int
        var playerStockpile = 0

        fun GetBuyingCost(): Int {
            return actCost + buysellOffset
        }

        fun GetSellingCost(): Int {
            return actCost - buysellOffset
        }

        fun GenerateNewPrice() {
            val rand = Random()
            val change = rand.nextInt(10)
            val costRise = rand.nextBoolean()
            if (costRise) {
                actCost += change
                if (actCost < maxCost) {
                    actCost = maxCost
                }
            } else {
                actCost -= change
                if (actCost < minCost) {
                    actCost = minCost
                }
            }
        }

        init {
            actCost = (minCost + maxCost) / 2
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.ndbutton.text = "Create new game"
    }

    private fun CreateNewGame() {
        isGameOn = true
        day = 0
        money = 200
        iron = Product("Iron", 50, 100, 5)
        gold = Product("Gold", 100, 500, 25)
    }

    @SuppressLint("SetTextI18n")
    fun NextDay(view: View) {
        if (isGameOn) {
            day++
            money -= 25
            if (money < 0) {
                isGameOn = false
                setResultText("You lost! Try again?")
                binding.ndbutton.text = "Create new game"
            } else {
                iron!!.GenerateNewPrice()
                gold!!.GenerateNewPrice()
            }
        } else {
            CreateNewGame()
            binding.ndbutton.text = "Next day (-25 money)"
        }
        Update()
    }

    @SuppressLint("SetTextI18n")
    private fun Update() {
        binding.moneyId.text = "Player money: $money"
        binding.dayCount.text = "Current day: $day"
        binding.res1.text = """${iron!!.name}
 Buy: ${iron!!.GetBuyingCost()} Sell: ${iron!!.GetSellingCost()}
 You own: ${iron!!.playerStockpile}"""
        binding.res2.text = """${gold!!.name}
 Buy: ${gold!!.GetBuyingCost()} Sell: ${gold!!.GetSellingCost()}
 You own: ${gold!!.playerStockpile}"""
    }

    private fun setResultText(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
        //TextView res1 = (TextView)findViewById(R.id.result);
        //res1.setText(text);
    }

    fun buy1(view: View?) {
        if (money >= iron!!.GetBuyingCost()) {
            money -= iron!!.GetBuyingCost()
            iron!!.playerStockpile++
            //setResultText("Purchase successful!");
            Toast.makeText(this, "Purchase successful!", Toast.LENGTH_SHORT).show()
        } else {
            //setResultText("Not enough money!");
            Toast.makeText(this, "Not enough money!", Toast.LENGTH_SHORT).show()
        }
        Update()
    }

    fun buy2(view: View?) {
        if (money >= gold!!.GetBuyingCost()) {
            money -= gold!!.GetBuyingCost()
            gold!!.playerStockpile++
            Toast.makeText(this, "Purchase successful!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Not enough money!", Toast.LENGTH_SHORT).show()
        }
        Update()
    }

    fun sell1(view: View?) {
        if (iron!!.playerStockpile > 0) {
            money += iron!!.GetSellingCost()
            iron!!.playerStockpile--
            Toast.makeText(this, "You sold a resource!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "You don't have that resource!", Toast.LENGTH_SHORT).show()
        }
        Update()
    }

    fun sell2(view: View?) {
        if (gold!!.playerStockpile > 0) {
            money += gold!!.GetSellingCost()
            gold!!.playerStockpile--
            Toast.makeText(this, "You sold a resource!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "You don't have that resource!", Toast.LENGTH_SHORT).show()
        }
        Update()
    }
}