package com.example.geoquiz

data class Question(
    val textResId: Int,  // Resource ID for the question text
    val answer: Boolean  // Correct answer (true/false)
)
