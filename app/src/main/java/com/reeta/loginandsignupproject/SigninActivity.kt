package com.reeta.loginandsignupproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.tabs.TabLayout
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_signin.*

class SigninActivity : AppCompatActivity() {

    lateinit var edtEmail: TextInputEditText
    lateinit var edtPassword: TextInputEditText
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        edtEmail=findViewById(R.id.signinemail)
        edtPassword=findViewById(R.id.singinpassword)
        mAuth = FirebaseAuth.getInstance()
        btnSignIn.setOnClickListener {
             singinProgressBar.visibility= View.VISIBLE
            var email=edtEmail.editableText.toString()
            var password=edtPassword.editableText.toString()

            mAuth!!.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this@SigninActivity,
                    OnCompleteListener<AuthResult?> { task ->
                        if (task.isSuccessful) {
                            singinProgressBar.visibility=View.INVISIBLE
                            val intent=Intent(this,LogoutActivity::class.java)
                            startActivity(intent)

                        } else {
                            singinProgressBar.visibility=View.INVISIBLE
                            Toast.makeText(this,"Invalid Email and password",Toast.LENGTH_LONG).show()
                        }
                    })
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