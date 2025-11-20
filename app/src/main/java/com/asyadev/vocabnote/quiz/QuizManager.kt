package com.asyadev.vocabnote.quiz

import com.asyadev.vocabnote.database.AppDatabase
import kotlin.random.Random

object QuizManager {
    const val maxNumber = 5
    const val maxOption = 4

    fun loadQuiz(): List<QuizQuestion>{
        val quizQuestion: MutableList<QuizQuestion> = mutableListOf()
        return quizQuestion
    }
    fun loadAnswerOption(trueAnswer: String): List<String>{
        val trueAnswerPosition = Random.nextInt(0, this.maxOption)
        val option: MutableList<String> = mutableListOf()

        for(i in 0 until this.maxOption){
            if(i == trueAnswerPosition){
                option.add(trueAnswer)
            } else{
                // cari dari Database secara random
//                option.add()
            }
        }
        return  option
    }
}