package com.example.geoquiz

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.geoquiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val quizViewModel: QuizViewModel by viewModels()
    private var isCheater = false

    // Register for activity result to handle the CheatActivity result
    private val cheatActivityLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                isCheater = result.data?.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false) ?: false
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Display the initial question
        updateQuestion()

        // Set up button click listeners
        binding.trueButton.setOnClickListener { checkAnswer(true) }
        binding.falseButton.setOnClickListener { checkAnswer(false) }
        binding.nextButton.setOnClickListener {
            quizViewModel.moveToNext()
            isCheater = false
            updateQuestion()
        }
        binding.previousButton.setOnClickListener {
            quizViewModel.moveToPrevious()
            isCheater = false
            updateQuestion()
        }
        binding.cheatButton.setOnClickListener {
            val answerIsTrue = quizViewModel.getCurrentAnswer()
            val intent = CheatActivity.newIntent(this, answerIsTrue)
            cheatActivityLauncher.launch(intent)
        }
    }

    private fun updateQuestion() {
        // Update the question text from the ViewModel
        val questionTextResId = quizViewModel.getCurrentQuestionTextResId()
        binding.questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        // Get the correct answer from the ViewModel
        val correctAnswer = quizViewModel.getCurrentAnswer()
        val messageResId = when {
            isCheater -> R.string.judgment_toast
            userAnswer == correctAnswer -> R.string.correct_toast
            else -> R.string.incorrect_toast
        }

        // Display a toast message
        Toast.makeText(this, getString(messageResId), Toast.LENGTH_SHORT).show()
    }
}
