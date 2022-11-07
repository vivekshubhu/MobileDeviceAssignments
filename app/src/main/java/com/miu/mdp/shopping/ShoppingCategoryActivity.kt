package com.miu.mdp.shopping

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.miu.mdp.R
import com.miu.mdp.model.Product
import com.miu.mdp.products.ProductActivity
import kotlinx.android.synthetic.main.activity_shopping_category.*

class ShoppingCategoryActivity : AppCompatActivity(), View.OnClickListener {


    private val electronicProductList = arrayListOf(
        Product(
            itemId = "1",
            title = "Laptop",
            price = 1000.0,
            color = "Black",
            desc = "This is awesome laptop",
            image = R.drawable.electronics
        ),
        Product(
            itemId = "2",
            title = "Earbuds",
            price = 800.0,
            color = "White",
            desc = "Best Walmart Earbuds Deals 2022: 16 onn True Wireless Earbuds Sale – Rolling Stone",
            image = R.drawable.earbuds
        ),
        Product(
            itemId = "3",
            title = "Nokia",
            price = 100.0,
            color = "Black",
            desc = "Nokia 3310 4G Price in India, Specifications, Comparison (6th November 2022)",
            image = R.drawable.nokia
        ),
        Product(
            itemId = "4",
            title = "OnePlus 8T",
            price = 600.0,
            color = "Black",
            desc = "OnePlus 8T is the latest smartphone from OnePlus. It has a 6.55-inch display, 12GB RAM, 256GB storage, and a 4500mAh battery. It has a 48MP + 16MP + 5MP triple rear camera and a 16MP front camera. It runs on Android 11 and is powered by a 4500mAh battery.",
            image = R.drawable.iphone
        ),
        Product(
            itemId = "5",
            title = "Earphone",
            price = 500.0,
            color = "White",
            desc = "Mrice E300 Earphones | WIRED.",
            image = R.drawable.earphone
        ),
    )


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
                startActivity(
                    ProductActivity.newInstance(
                        this,
                        productList = electronicProductList
                    )
                )
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