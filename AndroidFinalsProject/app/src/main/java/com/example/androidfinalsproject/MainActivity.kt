package com.example.androidfinalsproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var emailText: EditText
    private lateinit var passwordText: EditText
    private lateinit var signInButton: Button
    private lateinit var signUpButton: Button
    private lateinit var resetPasswordButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(FirebaseAuth.getInstance().currentUser != null){
            startActivity(Intent(this, ProfileActivity::class.java))
            finish()
        }


        setContentView(R.layout.activity_main)

        init()
        registerListeners()
    }

    private fun init(){
        emailText = findViewById(R.id.email)
        passwordText = findViewById(R.id.password)
        signInButton = findViewById(R.id.signIn)
        signUpButton = findViewById(R.id.signUp)
        resetPasswordButton = findViewById(R.id.resetPassword)
    }

    private fun registerListeners(){

        signInButton.setOnClickListener {

            val email = emailText.text.toString()
            val password = passwordText.text.toString()


            if(email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this,"Please Fill Both Values", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email,password)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        startActivity(Intent(this, ProfileActivity::class.java))
                        Toast.makeText(this,"Sign In Successful!", Toast.LENGTH_SHORT).show()
                        finish()


                    }else Toast.makeText(this, "Sing In FAILED!", Toast.LENGTH_SHORT).show()
                }

        }

        signUpButton.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }

        resetPasswordButton.setOnClickListener {
            startActivity(Intent(this, ResetPasswordActivity::class.java))
        }

    }
}
