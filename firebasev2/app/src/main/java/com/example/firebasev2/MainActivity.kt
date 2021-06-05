package com.example.firebasev2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import com.firebase.ui.database.FirebaseListAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private var adapter: FirebaseListAdapter<Message>? = null
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = Firebase.database.reference
        displayMsg()
    }


    fun onSendButton(view: View) {
        val input = findViewById<View>(R.id.input) as EditText
        val input_username = findViewById<View>(R.id.input_username) as EditText
        writeNewPost(input_username.text.toString(),input.text.toString())
        input.setText("")
    }

    private fun writeNewPost(username: String, msg: String) {
        val post = Message(username, msg)
        database.push().setValue(post)
    }

    private fun displayMsg() {
        val listOfMessages: ListView = findViewById<View>(R.id.msglist) as ListView


        adapter = object : FirebaseListAdapter<Message>(
            this, Message::class.java,
            R.layout.message, Firebase.database.reference
        ) {
            override fun populateView(v: View, model: Message, position: Int) {
                val messageText =
                    v.findViewById<View>(R.id.message_text) as TextView
                val messageUser =
                    v.findViewById<View>(R.id.message_user) as TextView

                messageText.setText(model.text)
                messageUser.setText(model.author + ":")
            }
        }

        listOfMessages.setAdapter(adapter)
    }
}