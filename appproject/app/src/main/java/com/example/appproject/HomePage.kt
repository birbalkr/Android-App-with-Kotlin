package com.example.appproject

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Website
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appproject.SigninPage.Companion.UserId

class HomePage : AppCompatActivity() {
    companion object {
        const val contactId = "package com.example.appproject.ContactPage.userid"
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val name = intent.getStringExtra(SigninPage.UserName)
        val userid = intent.getStringExtra(SigninPage.UserId)

        val tname=findViewById<TextView>(R.id.tname)
        val tnameid=findViewById<TextView>(R.id.username)
        val uppername1 = name?.toUpperCase()
        tname.text="$uppername1"
        tnameid.text=" $userid"

        val contact=findViewById<Button>(R.id.contact)
        val aboutme=findViewById<Button>(R.id.about)

        contact.setOnClickListener {
            intent = Intent(this, ContactPage::class.java)
            intent.putExtra(contactId,userid.toString())
            startActivity(intent)
        }
        aboutme.setOnClickListener {
            intent = Intent(this, MoreAboutMe::class.java)
            startActivity(intent)
        }



    }


}