package com.example.mathgame

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class SubtractionGame : AppCompatActivity() {

    lateinit var userAnswer: EditText
    lateinit var subtractionQuestion: TextView
    lateinit var buttonOk: Button
    lateinit var buttonNext: Button

    var correctAnswer: Int = 0
    var number1: Int = 0
    var number2: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subtraction_game)

        subtractionQuestion = findViewById(R.id.subtractionQuestion)
        userAnswer = findViewById(R.id.subtractionAnswer)
        buttonNext = findViewById(R.id.buttonNextSubtraction)
        buttonOk = findViewById(R.id.buttonOkSubtraction)

        gameRun()

        buttonOk.setOnClickListener {
            gameAnswer()
        }
    }

    private fun gameAnswer() {
        val input = userAnswer.text.toString()
        correctAnswer = number1 + number2
        if (input != null || input != "") {
            if (correctAnswer == input.toInt()) {
                Toast.makeText(this, "Congratulations! You're Awesome!", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(
                    this@SubtractionGame,
                    "Sorry, Your answer is wrong. Please try again!",
                    Toast.LENGTH_LONG
                ).show()
            }
        } else {
            Toast.makeText(
                this@SubtractionGame,
                "Please write an answer or click the next button",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun gameRun() {
        subtractionQuestion.text = getQuestion()
    }

    private fun getQuestion(): String {

        number1 = Random.nextInt(0, 100)
        number2 = Random.nextInt(0, 100)

        return "$number1 + $number2"
    }
}