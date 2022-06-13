package com.reeta.loginandsignupproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

     lateinit var edtEmail:TextInputEditText
    lateinit var edtPassword:TextInputEditText
    private var mAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edtEmail = findViewById(R.id.email)
        edtPassword = findViewById(R.id.password)
        mAuth = FirebaseAuth.getInstance()
        btnSignUp.setOnClickListener {
            profileProgressBar.visibility = View.VISIBLE
            var email = edtEmail.editableText.toString()
            var password = edtPassword.editableText.toString()


            mAuth!!.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this@MainActivity,
                    OnCompleteListener<AuthResult?> { task ->
                        if (task.isSuccessful) {

                            profileProgressBar.visibility = View.INVISIBLE
                            val intent = Intent(this, LogoutActivity::class.java)
                            startActivity(intent)
                            Toast.makeText(
                                this@MainActivity, "Authentication SuccessFul", Toast.LENGTH_LONG
                            ).show()

                        } else {

                            Toast.makeText(
                                this@MainActivity,
                                "Authentication Failed",
                                Toast.LENGTH_LONG
                            ).show()

                        }
                    })
        }

        tvAlreadyUser.setOnClickListener {
            val intent = Intent(this, SigninActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onStart() {
        super.onStart()
        var user: FirebaseUser? =mAuth?.currentUser
        if (user!=null){
            startActivity(Intent(this,LogoutActivity::class.java))
            Toast.makeText(this,"User already Login",Toast.LENGTH_SHORT).show()
            finish()
        }else{
            Toast.makeText(this,"First Login",Toast.LENGTH_LONG).show()
        }
    }
}