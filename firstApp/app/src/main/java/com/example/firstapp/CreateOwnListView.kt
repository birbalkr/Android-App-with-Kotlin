package com.example.firstapp

import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CreateOwnListView : AppCompatActivity() {

    private lateinit var UersArrayList : ArrayList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_own_list_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val name = arrayOf("Rahul", "Sumit", "Birbal", "Aditya", "Sanket" )
        val lastmgs = arrayOf("Hey wssup", "Kya ho raha hai","nhai kuch","Good","Night")
        val lasttime= arrayOf("6:25 AM", "7:20 AM", "4:03 PM", "9:00 AM", "7:34 PM")
        val imageid = intArrayOf(R.drawable.aiml, R.drawable.code, R.drawable.block, R.drawable.first, R.drawable.cyber)
        UersArrayList = ArrayList()
        for (eachIndex in name.indices){
            val user = User(name[eachIndex], lastmgs[eachIndex], lasttime[eachIndex], imageid[eachIndex])
            UersArrayList.add(user)
        }
        val userAdapter = MyAdapter(this, UersArrayList)
        findViewById<ListView>(R.id.MyListView).adapter = userAdapter



    }
}