package com.miu.mdp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        forgotPasswordTextView.setOnClickListener {
            Toast.makeText(this, "Forgot Password Clicked", Toast.LENGTH_SHORT).show()
        }

        signInButton.setOnClickListener {
            if (emailEditText.text.toString().isEmpty()) {
                emailEditText.error = "Email is required"
                return@setOnClickListener
            }

            if (passwordEditText.text.toString().isEmpty()) {
                passwordEditText.error = "Password is required"
                return@setOnClickListener
            }

            Toast.makeText(
                this,
                "Sign In: email: ${emailEditText.text} password: ${passwordEditText.text}",
                Toast.LENGTH_SHORT
            ).show()
        }

        createAccountButton.setOnClickListener {
            Toast.makeText(this, "Create Account Clicked", Toast.LENGTH_SHORT).show()
        }
    }
}