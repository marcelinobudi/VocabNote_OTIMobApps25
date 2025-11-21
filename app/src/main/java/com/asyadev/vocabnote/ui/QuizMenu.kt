package com.asyadev.vocabnote.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.asyadev.vocabnote.database.Vocabulary
import com.asyadev.vocabnote.quiz.QuizManager
import com.asyadev.vocabnote.quiz.QuizQuestion
import com.asyadev.vocabnote.ui.theme.ColorFail
import com.asyadev.vocabnote.ui.theme.ColorSuccess
import com.asyadev.vocabnote.ui.theme.VocabNoteTheme
import com.asyadev.vocabnote.viewmodel.VocabularyViewModel
import kotlin.random.Random

@Composable
fun QuizMenu(viewModel: VocabularyViewModel, modifier: Modifier = Modifier) {
    val vocabulariesObserver = viewModel.vocabularyList.observeAsState()
    val vocabularies: List<Vocabulary> = vocabulariesObserver.value ?: listOf()

    val quizQuestion: List<QuizQuestion> = remember(vocabularies) {
        if (vocabularies.size >= QuizManager.numberNeededForQuisRequirement) {
            try {
                QuizManager.loadQuiz(vocabularies)
            } catch (e: Exception) {
                listOf()
            }
        } else {
            listOf()
        }
    }

    if(vocabulariesObserver.value == null) {
        Column(
            modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
        viewModel.getVocabularyList()
//        LaunchedEffect(vocabularies.isEmpty()) {
//            if (vocabularies.isEmpty()) {
//
//            }
//        }
    }
    else if(vocabularies.size < QuizManager.numberNeededForQuisRequirement) {
        Column(
            modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Anda hanya dapat melakukan kuis jika sudah menambah kosakata sebanyak ${QuizManager.numberNeededForQuisRequirement}")
        }

    }
    else if(quizQuestion.isNotEmpty()){
        QuizSection(
            quizQuestion = quizQuestion,
            vocabularies = vocabularies,
            viewModel
        )

    }
}

@Composable
fun QuizSection(
    quizQuestion: List<QuizQuestion>,
    vocabularies: List<Vocabulary>,
    viewModel: VocabularyViewModel,
    modifier: Modifier = Modifier
) {
    val number = remember { mutableIntStateOf(1)}
    val numberIndex = number.intValue - 1
    val answerOption = remember {
        mutableStateOf(QuizManager.loadAnswerOption(quizQuestion[numberIndex].answer, vocabularies))
    }
    val showAnswer = remember { mutableStateOf(false)}
    val userAnswer = remember { mutableStateOf(false)}

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (numberIndex < QuizManager.maxNumber) {
            QuestionBox(
                question = quizQuestion[numberIndex].question
            )
            Spacer(modifier = Modifier.height(32.dp))
            if (!showAnswer.value) {

                answerOption.value.forEach { answer ->
                    OptionBox(
                        text = answer,
                        onClick = {
                            userAnswer.value = (answer == quizQuestion[numberIndex].answer)
                            showAnswer.value = true
                        }
                    )
                    Spacer(Modifier.height(16.dp))

                }
            } else {
                val text: String = """
                        ${if (userAnswer.value) "Benar" else "Salah"} \n
                        Terjemahan dari ${quizQuestion[numberIndex].question} adalah ${quizQuestion[numberIndex].answer}
                    """.trimIndent()
                ExplanationBox(text = text, userAnswer.value)
                Button({
                    showAnswer.value = false
                    number.intValue += 1
                    answerOption.value = QuizManager.loadAnswerOption(quizQuestion[number.intValue-1].answer, vocabularies)
                }) {
                    Text("Lanjut")
                }
            }
        } else {
            ExplanationBox("Kuis Sudah Berakhir", true) // Ini hanya CHEATCODE saya HEHE. Penggunaannya tidak sesuai fungsinya, tapi sesuai tujuan yg saya inginkan. isAnswerRight = true agar warnanya hijau.

        }
    }
}

@Composable
fun QuestionBox(question: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .width(334.dp)
            .height(334.dp)
            .background(MaterialTheme.colorScheme.primaryContainer)
    ){
        Column(
            modifier= Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = question,
                modifier = Modifier.padding(20.dp)
            )
        }
    }
}

@Composable
fun ExplanationBox(text: String, isAnswerRight: Boolean, modifier: Modifier = Modifier) {
    val color: Color = if (isAnswerRight)  ColorSuccess else ColorFail
    Box(
        modifier = modifier
            .width(334.dp)
            .background(color)
    ){
        Column(
            modifier= Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = text,
                modifier = Modifier.padding(20.dp)
            )
        }
    }
}

@Composable
fun OptionBox(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(onClick = onClick
    ){
        Box(
            modifier = modifier
                .width(334.dp)
                .height(42.dp)
        ){
            Column(
                modifier= Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = text,
                    Modifier.padding(start = 20.dp)
                )
            }
        }
    }

}
