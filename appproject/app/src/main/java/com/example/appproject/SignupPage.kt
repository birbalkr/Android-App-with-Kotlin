package com.example.appproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignupPage : AppCompatActivity() {
    lateinit var database:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val gotosign = findViewById<TextView>(R.id.singinpage)
        gotosign.setOnClickListener {
            val intent = Intent(applicationContext, SigninPage::class.java)
            startActivity(intent)
        }

        val name = findViewById<TextView>(R.id.name)
        val username = findViewById<TextView>(R.id.username)
        val emial = findViewById<TextView>(R.id.email)
        val passwd = findViewById<TextView>(R.id.passwd)
        val submitbtn = findViewById<Button>(R.id.submitbtn)

        submitbtn.setOnClickListener {
            val tname = name.text.toString()
            val tusername = username.text.toString()
            val temail = emial.text.toString()
            val tpasswd = passwd.text.toString()

            val user = User(tname, tusername, temail, tpasswd)

            database = FirebaseDatabase.getInstance().getReference("Users")
            database.child(tusername).setValue(user).addOnSuccessListener {
                name.text = ""
                username.text = ""
                emial.text = ""
                passwd.text = ""
                Toast.makeText(this, "User Registered", Toast.LENGTH_SHORT).show()
                intent= Intent(this,SigninPage::class.java)
                startActivity(intent)
            }.addOnFailureListener {
                Toast.makeText(this, "Network Error", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
