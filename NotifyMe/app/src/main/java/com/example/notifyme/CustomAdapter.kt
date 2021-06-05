package com.example.notifyme

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat

class CustomAdapter(private val data: MutableList<Notification>): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView
        val priority: TextView
        val finishDate: TextView
        val logo: ImageView
        init {
            title = view.findViewById(R.id.Title)
            priority = view.findViewById(R.id.Priority)
            finishDate = view.findViewById(R.id.finishDate)
            logo = view.findViewById(R.id.logo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notify_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = data[position].name
        holder.priority.text = data[position].priority.toString()

        holder.logo.setImageResource(data[position].image)

        val date = data[position].date
        date.year = date.year
        Log.d("Insert",date.year.toString())
        val pattern = "-MM-dd HH:mm"
        val simpleDateFormat = SimpleDateFormat(pattern)
        val datestr: String = date.year.toString() + simpleDateFormat.format(date)

        holder.finishDate.text = datestr
    }

    fun removeItem(viewHolder: RecyclerView.ViewHolder): Notification{
        val n = viewHolder.adapterPosition
        val not = data[n]
        data.removeAt(viewHolder.adapterPosition)
        notifyItemRemoved(viewHolder.adapterPosition)
        return not
    }

    override fun getItemCount(): Int {
        return data.size
    }
}