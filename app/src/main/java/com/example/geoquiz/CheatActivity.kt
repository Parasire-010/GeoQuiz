package com.example.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CheatActivity : AppCompatActivity() {

    companion object {
        // Keys for Intent Extras
        private const val EXTRA_ANSWER_IS_TRUE = "com.example.geoquiz.answer_is_true"
        const val EXTRA_ANSWER_SHOWN = "com.example.geoquiz.answer_shown"

        // Helper function for starting CheatActivity
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }

    private var answerIsTrue = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        // Retrieve passed data (correct answer)
        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)

        // Initialize views
        val answerTextView: TextView = findViewById(R.id.answerTextView)
        val showAnswerButton: Button = findViewById(R.id.showAnswerButton)
        val cancelButton: Button = findViewById(R.id.cancelButton)

        // Handle "Show Answer" button click
        showAnswerButton.setOnClickListener {
            val answerText = if (answerIsTrue) "True" else "False"
            answerTextView.text = answerText // Show answer
            answerTextView.visibility = TextView.VISIBLE

            // Send result back to MainActivity
            setResult(Activity.RESULT_OK, Intent().apply {
                putExtra(EXTRA_ANSWER_SHOWN, true)
            })
            finish()
        }

        // Handle "Cancel" button click
        cancelButton.setOnClickListener {
            // Set result as NOT revealing the answer
            setResult(Activity.RESULT_CANCELED, Intent().apply {
                putExtra(EXTRA_ANSWER_SHOWN, false)
            })
            finish()
        }
    }

    // Handle back button press
    override fun onBackPressed() {
        // Treat back press as canceling the cheat
        setResult(Activity.RESULT_CANCELED, Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN, false)
        })
        super.onBackPressed()
    }
}
