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

class SigninPage : AppCompatActivity() {
    companion object{
        const val UserName="package com.example.appproject.SigninPage.name"
        const val UserEmail="package com.example.appproject.SigninPage.email"
        const val UserId="package com.example.appproject.SigninPage.userid"
    }
    lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signin_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val name = findViewById<TextView>(R.id.name)
        val passwd = findViewById<TextView>(R.id.passwd)
        val login= findViewById<Button>(R.id.loginbtn)

        login.setOnClickListener {
            val username = name.text.toString()
            val loginpasswd = passwd.text.toString()

            if (username.isNotEmpty() && loginpasswd.isNotEmpty()){
                readDataUser(username,loginpasswd)
            }
            else{
                Toast.makeText(this,"Please fill all fields",Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun readDataUser(username: String, loginpasswd: String) {
            databaseReference=FirebaseDatabase.getInstance().getReference("Users")
        databaseReference.child(username).get().addOnSuccessListener {
            if(it.exists()){
                val email = it.child("email").value
                val name = it.child("name").value
                val usernameid =it.child("username").value

                val intent = Intent(this, HomePage::class.java)
                intent.putExtra(UserName,name.toString())
                intent.putExtra(UserEmail,email.toString())
                intent.putExtra(UserId,usernameid.toString())
                startActivity(intent)
                finish()
            }
        }
    }
}