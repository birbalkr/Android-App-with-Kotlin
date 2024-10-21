package com.example.appproject

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appproject.databinding.ActivityContactPageBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ContactPage : AppCompatActivity() {
    lateinit var binding: ActivityContactPageBinding
    private lateinit var dialog: Dialog
    lateinit var database: DatabaseReference
    lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityContactPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val userid = intent.getStringExtra(HomePage.contactId)

        // Initialize the dialog
        dialog = Dialog(this)
        dialog.setContentView(R.layout.contact_dialog)

        // Show the dialog on button click
        binding.Addcontact.setOnClickListener {
            dialog.show()
        }

        // Initialize EditText fields in the dialog
        val name = dialog.findViewById<EditText>(R.id.contactNameEnter)
        val phone = dialog.findViewById<EditText>(R.id.contactPhoneEnter)
        val email = dialog.findViewById<EditText>(R.id.contactEmailEnter)

        // Initialize OK and Exit buttons in the dialog
        val ok = dialog.findViewById<Button>(R.id.ok)
        val exit = dialog.findViewById<Button>(R.id.exit)

        // Set listener for OK button to save contact
        ok.setOnClickListener {
            val tcname = name.text.toString()
            val tcphone = phone.text.toString()
            val tcemail = email.text.toString()

            if (tcname.isNotEmpty() && tcphone.isNotEmpty() && tcemail.isNotEmpty()) {
                val cont = ContactSave(tcname, tcphone, tcemail)
                database = FirebaseDatabase.getInstance().getReference("Users/$userid/contact")

                // Save contact to Firebase
                database.child(tcphone).setValue(cont).addOnSuccessListener {
                    Toast.makeText(this, "Contact Saved", Toast.LENGTH_SHORT).show()

                    // Dismiss the dialog and show the saved data
                    dialog.dismiss()

                    // Call method to display saved data
                    displaySavedData(userid, tcphone)
                }.addOnFailureListener {
                    Toast.makeText(this, "Network Error", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        // Set listener for Exit button to dismiss the dialog
        exit.setOnClickListener {
            dialog.dismiss()
        }
    }

    // Function to retrieve and display the saved contact data
    private fun displaySavedData(userid: String?, tcphone: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Users/$userid/contact")

        // Retrieve the saved contact from Firebase
        databaseReference.child(tcphone).get().addOnSuccessListener {
            if (it.exists()) {
                val name = it.child("tname").value.toString()
                val email = it.child("temail").value.toString()
                val phone = it.child("tphone").value.toString()

                // Display the contact data on the screen
                binding.contactName.text = name
                binding.contactEmail.text = email
                binding.contactNumber.text = phone
            } else {
                Toast.makeText(this, "Contact not found", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Failed to retrieve contact", Toast.LENGTH_SHORT).show()
        }
    }
}
