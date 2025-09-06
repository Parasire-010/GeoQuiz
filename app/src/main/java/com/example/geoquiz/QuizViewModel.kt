package com.example.geoquiz

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class QuizViewModel(private val state: SavedStateHandle) : ViewModel() {

    companion object {
        private const val CURRENT_INDEX_KEY = "currentIndex"
    }

    // Questions stored as a list of `Question` objects with `textResId`
    private val questionBank = listOf(
        Question(textResId = R.string.question_earth_flat, answer = false),
        Question(textResId = R.string.question_sky_blue, answer = true),
        Question(textResId = R.string.question_mount_everest, answer = true),
        Question(textResId = R.string.question_canada_south, answer = false),
        Question(textResId = R.string.question_amazon_longest, answer = false),
        Question(textResId = R.string.question_antarctica_country, answer = false),
        Question(textResId = R.string.question_africa_countries, answer = true),
        Question(textResId = R.string.question_greenland_independent, answer = false),
        Question(textResId = R.string.question_sahara_largest, answer = true),
        Question(textResId = R.string.question_seasons_equator, answer = false)
    )

    val questionList: List<Question>
        get() = questionBank

    // Persist the current question index
    var currentIndex: Int
        get() = state[CURRENT_INDEX_KEY] ?: 0
        set(value) {
            state[CURRENT_INDEX_KEY] = value
        }

    fun getCurrentQuestionTextResId(): Int {
        return questionBank[currentIndex].textResId
    }

    fun getCurrentAnswer(): Boolean {
        return questionBank[currentIndex].answer
    }

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun moveToPrevious() {
        currentIndex = if (currentIndex == 0) questionBank.size - 1 else currentIndex - 1
    }
}
