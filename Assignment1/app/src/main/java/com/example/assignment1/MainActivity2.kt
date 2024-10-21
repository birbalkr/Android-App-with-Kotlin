package com.example.assignment1

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val Android = findViewById<Button>(R.id.android)
        val webapp = findViewById<Button>(R.id.webApp)
        val Ios = findViewById<Button>(R.id.ISO)
        val Alml = findViewById<Button>(R.id.AIML)
        val blackchain = findViewById<Button>(R.id.blockchain)
        val hack = findViewById<Button>(R.id.hacking)
        val callBtn = findViewById<Button>(R.id.call)

        Android.setOnClickListener {
            intent = Intent(application, MainActivity::class.java)
            startActivity(intent)
        }
        webapp.setOnClickListener {
            intent = Intent(application, MainActivity::class.java)
            startActivity(intent)
        }
        Ios.setOnClickListener {
            intent = Intent(application, MainActivity::class.java)
            startActivity(intent)
        }
        Alml.setOnClickListener {
            intent = Intent(application, MainActivity::class.java)
            startActivity(intent)
        }
        blackchain.setOnClickListener {
            intent = Intent(application, MainActivity::class.java)
            startActivity(intent)
        }
        hack.setOnClickListener {
            intent = Intent(application, MainActivity::class.java)
            startActivity(intent)
        }

        callBtn.setOnClickListener {
            // Check if the CALL_PHONE permission is granted
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // Request the permission if not granted
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 1)
            } else {
                // If permission is granted, make the call
                val phoneNumber = "tel:ff"  // Replace with your desired phone number
                val intent = Intent(Intent.ACTION_CALL).apply {
                    data = Uri.parse(phoneNumber)
                }
                startActivity(intent)
            }
        }
    }

    // Handle the result of the permission request
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Permission was granted, make the call
            val phoneNumber = "tel:8102832930"  // Replace with your desired phone number
            val intent = Intent(Intent.ACTION_CALL).apply {
                data = Uri.parse(phoneNumber)
            }
            startActivity(intent)
        } else {
            // Permission was denied, show a message
            Toast.makeText(this, "Permission denied to make phone calls", Toast.LENGTH_SHORT)
                .show()
        }
    }
}
