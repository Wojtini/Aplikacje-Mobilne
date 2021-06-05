package com.example.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment


class BlankFragment : Fragment() {

    var idImage: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity?.intent != null){
            val extra = activity!!.intent.getStringExtra("data")
            display(extra,-1)
        }
    }

    public fun display(s:String?,id: Int){
        view!!.findViewById<TextView>(R.id.textView).text = s
        this.idImage = id
    }
    public fun getIdHehehe(): Int{
        return this.idImage
    }
}