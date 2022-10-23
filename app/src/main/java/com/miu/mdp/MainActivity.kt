package com.miu.mdp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val foodList = arrayListOf("Momo", "Pizza", "Nepali", "Sandwich", "Shrimp", "Indian")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFoodBtn.setOnClickListener {
            if (addNewFoodEditText.text.isEmpty()) {
                Toast.makeText(this, "Please enter a food", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val newFood = addNewFoodEditText.text.toString()
            foodList.add(newFood)
            addNewFoodEditText.text.clear()
            println(foodList)
            Toast.makeText(this, "Added $newFood", Toast.LENGTH_SHORT).show()
        }
        decideBtn.setOnClickListener {
            val random = java.util.Random()
            val randomFood = random.nextInt(foodList.count())
            dinnerText.text = foodList[randomFood]
        }
    }
}