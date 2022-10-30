package com.miu.mdp.shopping

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.miu.mdp.R
import kotlinx.android.synthetic.main.activity_shopping_category.*

class ShoppingCategoryActivity : AppCompatActivity(), View.OnClickListener {


    companion object {
        const val USERNAME = "userName"

        fun newInstance(context: Context, userName: String): Intent {
            val intent = Intent(context, ShoppingCategoryActivity::class.java)
            intent.putExtra(USERNAME, userName)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_category)
        val userName = intent.getStringExtra(USERNAME)
        welcome_text.text = "Welcome $userName"

        setListener()
    }

    private fun setListener() {
        category_electronics.setOnClickListener(this)
        category_electronics_text.setOnClickListener(this)
        category_beauty.setOnClickListener(this)
        category_beauty_text.setOnClickListener(this)
        category_clothing.setOnClickListener(this)
        category_clothing_text.setOnClickListener(this)
        category_food.setOnClickListener(this)
        category_food_text.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            category_electronics, category_electronics_text -> {
                Toast.makeText(
                    this,
                    "You have chosen the Electronics category of shopping”",
                    Toast.LENGTH_SHORT
                ).show()
            }
            category_clothing, category_clothing_text -> {
                Toast.makeText(
                    this,
                    "You have chosen the Clothing category of shopping”",
                    Toast.LENGTH_SHORT
                ).show()
            }
            category_beauty, category_beauty_text -> {
                Toast.makeText(
                    this,
                    "You have chosen the Beauty category of shopping”",
                    Toast.LENGTH_SHORT
                ).show()
            }
            category_food, category_food_text -> {
                Toast.makeText(
                    this,
                    "You have chosen the Food category of shopping”",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}