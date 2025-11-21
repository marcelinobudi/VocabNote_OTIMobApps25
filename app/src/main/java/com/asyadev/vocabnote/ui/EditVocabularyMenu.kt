package com.asyadev.vocabnote.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.asyadev.vocabnote.database.Vocabulary
import com.asyadev.vocabnote.navigation.Destination
import com.asyadev.vocabnote.ui.theme.VocabNoteTheme
import com.asyadev.vocabnote.viewmodel.VocabularyViewModel

@Composable
fun EditVocabularyMenu(
    viewModel: VocabularyViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val vocabularies = viewModel.preUpdatedVocabulary.observeAsState()
    val vocabulary = vocabularies.value ?: Vocabulary.Companion.empty()
    // LOAD DARI DATABASE
    val wordValue = remember{ mutableStateOf(vocabulary.word) }
    val translationValue = remember{ mutableStateOf(vocabulary.translation) }
    val descriptionValue = remember{ mutableStateOf(vocabulary.description) }
    val usingExampleValue = remember{ mutableStateOf(vocabulary.example) }
    val pronounciationValue = remember{ mutableStateOf(vocabulary.pronounciation) }
    val difficultyValue = remember{ mutableStateOf(vocabulary.difficulty) }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        item {
            Text(
                text = "Edit Kosakata"
            )
            // WORD
            Spacer(modifier = Modifier.height(30.dp))
            OutlinedTextField(
                value = wordValue.value,
                onValueChange = {
                    wordValue.value = it
                },
                label = {
                    Text("Kata")
                }
            )
            // Translation
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = translationValue.value,
                onValueChange = {
                    translationValue.value = it
                },
                label = {
                    Text("Arti")
                }
            )
            // Deskripsi
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = descriptionValue.value,
                onValueChange = {
                    descriptionValue.value = it
                },
                label = {
                    Text("Deskripsi")
                }
            )
            // Example
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = usingExampleValue.value,
                onValueChange = {
                    usingExampleValue.value = it
                },
                label = {
                    Text("Contoh Penggunaan")
                }
            )
            // Pronounciation
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = pronounciationValue.value,
                onValueChange = {
                    pronounciationValue.value = it
                },
                label = {
                    Text("Pengucapan")
                }
            )
            // Difficulty
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = difficultyValue.value,
                onValueChange = {
                    difficultyValue.value = it
                },
                label = {
                    Text("Tingkat Kesulitan")
                }
            )
            // Add Vocabulary
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = {
                    val editedVocabulary = Vocabulary(
                        word = wordValue.value,
                        translation = translationValue.value,
                        description = descriptionValue.value,
                        example = usingExampleValue.value,
                        pronounciation = pronounciationValue.value,
                        difficulty = difficultyValue.value,
                        uid = vocabulary.uid
                    )
                    viewModel.updateVocabulary(editedVocabulary)
                    navController.navigate(route = Destination.VOCABULARY_LIST.route)
                }
            ) {
                Text("Edit")
            }
        }
    }
}