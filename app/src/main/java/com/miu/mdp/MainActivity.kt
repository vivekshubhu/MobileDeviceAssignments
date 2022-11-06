package com.miu.mdp

import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import android.widget.CompoundButton.OnCheckedChangeListener

import java.util.*

class MainActivity : AppCompatActivity(), OnCheckedChangeListener {




    private var answer1: String = "compileSdkVersion"
    private var answer2 = mutableListOf("INVISIBLE", "GONE")

    private var userAnswer1: String = ""
    private var userAnswer2 = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        minSdkVersion
//
//        compileSdkVersion
//
//        targetSdkVersion
//
//        testSdkVersion

        // Question 1
        radioGroup1.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioButton1 -> userAnswer1 = radioButton1.text.toString()
                R.id.radioButton2 -> userAnswer1 = radioButton2.text.toString()
                R.id.radioButton3 -> userAnswer1 = radioButton3.text.toString()
                R.id.radioButton4 -> userAnswer1 = radioButton4.text.toString()
            }
        }

        // Question 2
        checkBox1.setOnCheckedChangeListener(this)
        checkBox2.setOnCheckedChangeListener(this)
        checkBox3.setOnCheckedChangeListener(this)
        checkBox4.setOnCheckedChangeListener(this)
        checkBox5.setOnCheckedChangeListener(this)

        submitBtn.setOnClickListener {

            if (userAnswer1.isEmpty() || userAnswer2.isEmpty()) {
                Toast.makeText(this, "Please answer all questions", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            var score = 0
            if (userAnswer1 == answer1) {
                score += 50
            }
            if (userAnswer2.containsAll(answer2)) {
                score += 50
            }
            // current date and time in format
            val date = Calendar.getInstance().time
            val formatter = SimpleDateFormat("MM/dd/yyyy, hh:mm:ss a", Locale.getDefault())
            val currentDateTime = formatter.format(date)


            val message =
                if (score > 0) "Congratulations!!! You submitted on $currentDateTime. You achieved $score%"
                else "Sorry, You submitted on $currentDateTime. You achieved $score%"
            AlertDialog.Builder(this)
                .setTitle("Result")
                .setMessage(message)
                .setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()


        }


        resetBtn.setOnClickListener {
            radioGroup1.clearCheck()
            resetCheckbox()
        }

    }

    private fun resetCheckbox() {
        checkBox1.isChecked = false
        checkBox2.isChecked = false
        checkBox3.isChecked = false
        checkBox4.isChecked = false
        checkBox5.isChecked = false
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        when (buttonView?.id) {
            R.id.checkBox1 -> addAnswer("INVISIBLE", isChecked)
            R.id.checkBox2 -> addAnswer("GONE", isChecked)
            R.id.checkBox3 -> addAnswer("VISIBLE", isChecked)
            R.id.checkBox4 -> addAnswer("INVISIBLE", isChecked)
            R.id.checkBox5 -> addAnswer("GONE", isChecked)
        }
    }

    private fun addAnswer(answer: String, isChecked: Boolean) {
        if (isChecked) userAnswer2.add(answer) else userAnswer2.remove(answer)
    }
}