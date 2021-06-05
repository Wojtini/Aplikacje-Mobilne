package com.example.gallery

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gallery.databinding.ActivityMainBinding
import com.example.myapplication.onClickInterface


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    val data : MutableList<Image> = ArrayList()
    val display_data : MutableList<Image> = ArrayList()
    lateinit var recyclerView : RecyclerView

    private var onclickInterface: onClickInterface? = null

    private lateinit var viewAdapter: RecyclerView.Adapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        recyclerView = findViewById<RecyclerView>(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        viewAdapter = CustomAdapter(display_data,this)
        recyclerView.adapter = viewAdapter


    }

    private fun createBasicData(){
        var pom = Image(R.drawable.photo1,"bardzo fajne zdjecie",1f)
        data.add(pom)
        pom = Image(R.drawable.photo2,"mniej fajne zdjecie",5f)
        data.add(pom)
        pom = Image(R.drawable.photo3,"podziwiam artyste",3f)
        data.add(pom)
    }

    fun showImageDetail(pos: Int){
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            var frag2 = supportFragmentManager.findFragmentById(R.id.frag) as BlankFragment
            val e = display_data[pos]
            if(frag2.getIdHehehe() == e.image){
                val myintent = Intent(this, ImageDetail::class.java)
                val e = display_data[pos]
                myintent.putExtra("id",pos)
                myintent.putExtra("img",e.image)
                myintent.putExtra("desc",e.desc)
                myintent.putExtra("rating",e.rating)
                startActivityForResult(myintent,123)
            }else{
                frag2.display(e.desc,e.image)
            }
        }else{
            val myintent = Intent(this, ImageDetail::class.java)
            val e = display_data[pos]
            myintent.putExtra("id",pos)
            myintent.putExtra("img",e.image)
            myintent.putExtra("desc",e.desc)
            myintent.putExtra("rating",e.rating)
            startActivityForResult(myintent,123)
        }
    }

    private fun refreshData(){
        display_data.removeAll(display_data)
        display_data.addAll(data)
        for(i in 0..display_data.size-1){
            var k = display_data[i]
            var j = i - 1

            while (j>=0 && display_data[j].rating < (k.rating)){
                display_data[j+1] = display_data[j]
                j=j-1
            }
            display_data[j+1] = k
        }

        recyclerView.adapter?.notifyDataSetChanged();
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val id = data?.getIntExtra("id",-1)
        val rating = data?.getFloatExtra("rating",-1f)
        if (id != null  && rating != null) {
            if(id >= 0 && rating >= 0){
                display_data[id].rating = rating
                refreshData()
            }
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
                var test = getSerializable(i.toString()) as Image
                addToData(test)
            }
        }
    }

    fun addToData(n : Image){
        data.add(0,n)
        recyclerView.adapter?.notifyItemInserted(0)
        Log.d("Insert","To tutaj")
        refreshData()
    }

    fun onGenerateClick(view: View) {
        createBasicData()
        refreshData()
    }


}