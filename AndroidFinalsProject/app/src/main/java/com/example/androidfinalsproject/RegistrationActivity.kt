package com.example.androidfinalsproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class RegistrationActivity : AppCompatActivity() {

    private lateinit var editTextMail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var editTextRepeatPassword: EditText
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        init()
        registerListeners()
    }

    private fun init(){
        editTextMail = findViewById(R.id.editTextEmailAddress)
        editTextPassword = findViewById(R.id.editTextPassword)
        editTextRepeatPassword = findViewById(R.id.editTextConfirmPassword)
        registerButton = findViewById(R.id.registerButton)
    }


    private fun registerListeners(){
        registerButton.setOnClickListener {
            val email = editTextMail.text.toString()
            val password = editTextPassword.text.toString()
            val confirmPassword = editTextRepeatPassword.text.toString()


            if(email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
                Toast.makeText(this,"One of the inputs is Empty!", LENGTH_LONG).show()
                return@setOnClickListener
            }else if(password.length <= 8){
                Toast.makeText(this,"Password should be more than 8 symbols!", LENGTH_SHORT).show()
                return@setOnClickListener
            }else if(password != confirmPassword){
                Toast.makeText(this,"Passwords DO NOT MATCH!!", LENGTH_SHORT).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        Toast.makeText(this,"User Registered Successfully!", LENGTH_LONG).show()
                        startActivity(Intent(this, MainActivity::class.java))
                    }else{
                        Toast.makeText(this,"Firebase Error! Try again!", LENGTH_LONG).show()
                    }
                }
        }

    }

}