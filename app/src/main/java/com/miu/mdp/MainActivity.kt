package com.miu.mdp

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.miu.mdp.model.User
import com.miu.mdp.register.RegisterActivity
import com.miu.mdp.shopping.ShoppingCategoryActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val usersList = mutableListOf(
        User("Vivek", "Karki", "vivek@gmail.com", "123456"),
        User("Ghana Shyam", "Regmi", "ghana@gmail.com", "123456")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        forgotPasswordTextView.setOnClickListener {
            if (emailEditText.text.toString().isEmpty()) {
                emailEditText.error = "Email is required"
                Toast.makeText(this, "Email is required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val user = usersList.find { it.username == emailEditText.text.toString() }
            if (user == null) {
                Toast.makeText(this, "User not found", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val intent = Intent(Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(user.username))
            intent.putExtra(Intent.EXTRA_SUBJECT, "Password Recovery")
            intent.putExtra(Intent.EXTRA_TEXT, "Your password is ${user.password}")
            intent.type = "message/rfc822"
            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this, "No email app found", Toast.LENGTH_LONG).show()
            }
        }

        signInButton.setOnClickListener {
            if (emailEditText.text.toString().isEmpty()) {
                emailEditText.error = "Email is required"
                Toast.makeText(this, "Email is required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (passwordEditText.text.toString().isEmpty()) {
                passwordEditText.error = "Password is required"
                Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val user = usersList.find {
                it.username == emailEditText.text.toString() && it.password == passwordEditText.text.toString()
            }
            if (user != null) {
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                startActivity(ShoppingCategoryActivity.newInstance(this, user.username))
                return@setOnClickListener
            }
            Toast.makeText(
                this,
                "Login Failed. Please check your email and password",
                Toast.LENGTH_SHORT
            ).show()
        }

        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                val user = data?.getParcelableExtra<User>("user") as User
                usersList.add(user)
                Log.d("MainActivity", "userList: $usersList")
                Toast.makeText(this, "Account Created Successfully", Toast.LENGTH_SHORT).show()
            }
        }
        createAccountButton.setOnClickListener {
            resultLauncher.launch(RegisterActivity.newInstance(this))
        }
    }
}