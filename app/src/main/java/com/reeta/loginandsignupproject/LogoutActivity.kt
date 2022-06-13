package com.reeta.loginandsignupproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_logout.*

class LogoutActivity : AppCompatActivity() {
    lateinit var name:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logout)
        name=findViewById(R.id.tvName)

        saveInDatabase.setOnClickListener {
            val database= FirebaseDatabase.getInstance()
            val root:DatabaseReference=database.reference
            root.setValue("name",name.text.toString())
            Toast.makeText(this,"Data insert",Toast.LENGTH_SHORT).show()
        }


    }
}