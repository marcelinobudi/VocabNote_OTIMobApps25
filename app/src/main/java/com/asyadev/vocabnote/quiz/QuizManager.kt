package com.asyadev.vocabnote.quiz

import com.asyadev.vocabnote.database.AppDatabase
import com.asyadev.vocabnote.database.Vocabulary
import kotlin.math.max
import kotlin.random.Random

object QuizManager {
    const val maxNumber = 5
    const val maxOption = 4
    const val numberNeededForQuisRequirement = maxNumber*4;

    fun loadQuiz(vocabularies: List<Vocabulary>): List<QuizQuestion>{
        val quizQuestion: MutableList<QuizQuestion> = mutableListOf()
        val copiedVocabularies: MutableList<Vocabulary> = vocabularies.toMutableList()
        if(vocabularies.size >= numberNeededForQuisRequirement){
            for(i in 0 until maxNumber){
                val randomIndex = Random.nextInt(copiedVocabularies.size)
                val question = copiedVocabularies[randomIndex]
                val quiz = QuizQuestion(
                    question = question.word,
                    answer = question.translation,
                )
                copiedVocabularies.remove(question)
                quizQuestion.add(quiz)
            }
        } else{
            throw Exception("Kuis hanya bisa dimulai jika jumlah kosakata >= $numberNeededForQuisRequirement")
        }
        return quizQuestion
    }
    fun loadAnswerOption(trueAnswer: String, vocabularies: List<Vocabulary>): List<String>{
        val trueAnswerPosition = Random.nextInt(0, this.maxOption)
        val option: MutableList<String> = mutableListOf()
        val copiedVocabularies: MutableList<Vocabulary> = vocabularies.toMutableList()

        for(i in 0 until this.maxOption){
            if(i == trueAnswerPosition){
                option.add(trueAnswer)
            } else{
                val randomIndex = Random.nextInt(vocabularies.size)
                val vocabulary = copiedVocabularies[randomIndex]
                option.add(vocabulary.translation)
                copiedVocabularies.remove(vocabulary)
            }
        }
        return  option
    }
}