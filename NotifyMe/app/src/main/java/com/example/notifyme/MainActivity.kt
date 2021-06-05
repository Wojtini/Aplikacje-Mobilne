package com.example.notifyme

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notifyme.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    //var notArray: Array<Notification>
    private lateinit var binding: ActivityMainBinding
    val data : MutableList<Notification> = ArrayList()
    val display_data : MutableList<Notification> = ArrayList()
    lateinit var test : RecyclerView

    //Swipey
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    //Notification things
    private val CHANNEL_ID = "channel_id_example_01"
    private val notificationId = 101
    lateinit var builder: AlertDialog.Builder

    var currentImageId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        builder = AlertDialog.Builder(this)

        //Recycler
        val recyclerView = findViewById<RecyclerView>(R.id.recycler)
        test = recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        viewAdapter = CustomAdapter(display_data)
        recyclerView.adapter = viewAdapter
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        val ha3 = ItemTouchHelper(itemTouchHelperCallback)
        ha3.attachToRecyclerView(recyclerView)


    }

    val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT){
        override fun onMove(p0: RecyclerView, p1:RecyclerView.ViewHolder, p2: RecyclerView.ViewHolder): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, position: Int) {
            val index = (viewAdapter as CustomAdapter).removeItem(viewHolder)


            data.remove(index)
        }
    }

    fun addToData(n : Notification){
        data.add(0,n)
        test.adapter?.notifyItemInserted(0)
        Log.d("Insert","To tutaj")
        refreshData()
    }

    fun onCreateNew(view: View) {
        val myintent = Intent(this, creationMenu::class.java)
        startActivityForResult(myintent,123)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val n = data?.getStringExtra("nazwa")
        val p = data?.getStringExtra("prio")
        val i = data?.getIntExtra("test",R.drawable.redsquare)
        val dateObj = data?.extras?.get("date")
        Log.d("Return","yupi ka yey" + n + p + i + "Data " + dateObj)
        if(n!=null && p!= null && i!=null && dateObj!=null){
            Log.d("Return","yupi ka yey hura")
            val n = Notification(n,p.toInt(),i, dateObj as Date)
            addToData(n)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
//         Save the user's current game state
        outState?.run {
            putInt("size",data.size)
        }
        for(i in 0..data.size-1){
            outState?.run {
                putSerializable(i.toString(),data[i])
            }
        }

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(outState)
    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle){
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState)
        var size = 0
        // Restore state members from saved instance
        savedInstanceState?.run {
            size = getInt("size")
        }
        for(i in 0..size-1){
//            var test : Notification?
            savedInstanceState?.run {
                var test = getSerializable(i.toString()) as Notification
                addToData(test)
            }
        }
    }

    fun onSortClick(v: View) {
        when (v.id){
            binding.imageView.id -> {
                if(currentImageId == 0){
                    binding.imageView.setImageResource(R.drawable.redsquare)
                    changeFilters(R.drawable.redsquare)
                }else if (currentImageId == 1){
                    binding.imageView.setImageResource(R.drawable.bluecircle)
                    changeFilters(R.drawable.bluecircle)
                }else if (currentImageId == 2){
                    binding.imageView.setImageResource(R.drawable.greentriangle)
                    changeFilters(R.drawable.greentriangle)
                }else{
                    binding.imageView.setImageResource(R.drawable.graysquare)
                    changeFilters(-1)
                }
                currentImageId = (currentImageId + 1) % 4
            }

            binding.sortTime.id -> {
                if(timeSort == 0){
                    timeSort = 1
                    binding.sortTime.text = "Sortuj po czasie: najpierw szybsze"
                }else if(timeSort == 1){
                    timeSort = -1
                    binding.sortTime.text = "Sortuj po czasie: najpierw pózniejsze"
                }else{
                    timeSort = 0
                    binding.sortTime.text = "Sortuj po czasie: w kolejnosci dodania"
                }
            }
        }
        refreshData()
    }

    var imageFilter = -1
    var timeSort = 0

    fun refreshData(){
        display_data.removeAll(display_data)
        for(i in 0..data.size-1){
            if(data[i].image==imageFilter || imageFilter==-1){
                display_data.add(0,data[i])
            }
        }
        if(timeSort==1){
            sortDataSzybciej()
        }else if(timeSort==-1){
            sortDataWolniej()
        }

        test.adapter?.notifyDataSetChanged();
        Log.d("Insert","To tutaj 2")
    }

    fun sortDataSzybciej(){
        for(i in 0..display_data.size-1){
            var k = display_data[i]
            var j = i - 1

            while (j>=0 && display_data[j].date.after(k.date)){
                display_data[j+1] = display_data[j]
                j=j-1
            }
            display_data[j+1] = k
        }
    }

    fun sortDataWolniej(){
        for(i in 0..display_data.size-1){
            var k = display_data[i]
            var j = i - 1

            while (j>=0 && !(display_data[j].date.after(k.date))){
                display_data[j+1] = display_data[j]
                j=j-1
            }
            display_data[j+1] = k
        }
    }


    fun changeFilters(image: Int){
        imageFilter = image
    }
}