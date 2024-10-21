package com.example.firstapp

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView


class MyAdapter(private val contxt:Activity, private val arrayList:ArrayList<User> ):
    ArrayAdapter<User>(contxt, R.layout.eachitem, arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater = LayoutInflater.from(contxt)
        val view = inflater.inflate(R.layout.eachitem, null)

        val image = view.findViewById<ImageView>(R.id.imageView)
        val name = view.findViewById<TextView>(R.id.tvName)
        val lastmgs = view.findViewById<TextView>(R.id.tvLastMassage)
        val lasttime = view.findViewById<TextView>(R.id.tvTime)

        name.text = arrayList[position].name
        lastmgs.text = arrayList[position].lastmgs
        lasttime.text = arrayList[position].lasttime
        image.setImageResource(arrayList[position].imageid)


        return view
    }
}