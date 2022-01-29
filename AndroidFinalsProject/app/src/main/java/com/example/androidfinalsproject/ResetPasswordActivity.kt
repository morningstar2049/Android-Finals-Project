package com.example.androidfinalsproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class ResetPasswordActivity : AppCompatActivity() {

    private lateinit var emailText: EditText
    private lateinit var resetButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resetpassword)

        init()
        registerListeners()

    }

    private fun init(){
        emailText = findViewById(R.id.editTextEmail)
        resetButton = findViewById(R.id.resetButton)

    }

    private fun registerListeners(){

        val email = emailText.text.toString()

        resetButton.setOnClickListener {

            if(emailText.text.toString().isEmpty()){
                Toast.makeText(this, "Please enter email!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance().sendPasswordResetEmail(emailText.text.toString())
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        Toast.makeText(this, "Check your email!" , Toast.LENGTH_LONG).show()
                        startActivity(Intent(this, MainActivity::class.java))
                    }else Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
                }
        }
    }
}