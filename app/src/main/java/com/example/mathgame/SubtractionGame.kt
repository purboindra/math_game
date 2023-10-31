package com.example.mathgame

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.Locale
import java.util.Objects
import kotlin.random.Random

class SubtractionGame : AppCompatActivity() {

    lateinit var userAnswer: EditText
    lateinit var subtractionQuestion: TextView
    lateinit var subtractionDuration: TextView
    lateinit var buttonOk: Button
    lateinit var buttonNext: Button

    lateinit var timer: CountDownTimer
    private var startTimerInMillis: Long = 30000
    private var timeLeftInMillis: Long = startTimerInMillis

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
        subtractionDuration = findViewById(R.id.subtractionDuration)

        gameRun()

        buttonOk.setOnClickListener {
            gameAnswer()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }

    private fun updateTextDuration() {
        val remainingDuration = (timeLeftInMillis / 1000).toInt()
        subtractionDuration.text = String.format(Locale.getDefault(), "%02d", remainingDuration)
    }

    private fun stopTimer() {
        timer.cancel()
    }

    private fun durationRun() {
        timer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(p0: Long) {
                timeLeftInMillis = p0
                updateTextDuration()
            }

            override fun onFinish() {
                showToast()
                stopTimer()
                reRunGame()
            }
        }.start()
    }

    private fun showToast() {
        Toast.makeText(this, "Sorry, Times Up!", Toast.LENGTH_LONG).show()
    }

    private fun gameAnswer() {
        val input = userAnswer.text.toString()
        correctAnswer = number1 + number2
        val isCorrect: Boolean = correctAnswer == input.toInt()
        if (input != null || input != "") {
            if (isCorrect) {
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

    private fun reRunGame() {
        startTimerInMillis = 30000
        timeLeftInMillis = startTimerInMillis
        durationRun()
        subtractionQuestion.text = getQuestion()
        updateTextDuration()

    }

    private fun gameRun() {
        durationRun()
        subtractionQuestion.text = getQuestion()
        updateTextDuration()
    }

    private fun getQuestion(): String {
        number1 = Random.nextInt(0, 100)
        number2 = Random.nextInt(0, 100)

        return "$number1 + $number2"
    }


}