package com.example.firstapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ListviewDefults : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_listview_defults)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val listview = findViewById<ListView>(R.id.listview)

        val tasklist = arrayListOf<String>()
        tasklist.add("Attend Exam")
        tasklist.add("Complete one app dev project")
        tasklist.add("make one post on all handles")

        val adapterForMylist =  ArrayAdapter(this, android.R.layout.simple_list_item_1,tasklist)

        listview.adapter = adapterForMylist
    }
}