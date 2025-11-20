package com.asyadev.vocabnote.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.asyadev.vocabnote.MainActivity
import com.asyadev.vocabnote.database.Vocabulary
import com.asyadev.vocabnote.ui.theme.VocabNoteTheme
import com.asyadev.vocabnote.viewmodel.VocabularyViewModel

@Composable
fun AddVocabularyMenu(viewModel: VocabularyViewModel, modifier: Modifier = Modifier) {
    val wordValue = remember{ mutableStateOf("")}
    val translationValue = remember{ mutableStateOf("")}
    val descriptionValue = remember{ mutableStateOf("")}
    val usingExampleValue = remember{ mutableStateOf("")}
    val pronounciationValue = remember{ mutableStateOf("")}
    val difficultyValue = remember{ mutableStateOf("")}
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Tambah Kosakata"
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
                // TAMBAHKAN
                viewModel.addVocabulary(
                    word = wordValue.value,
                    translation = translationValue.value,
                    description = descriptionValue.value,
                    example = usingExampleValue.value,
                    pronounciation = pronounciationValue.value,
                    difficulty = difficultyValue.value

                )
                wordValue.value = ""
                translationValue.value = ""
                descriptionValue.value = ""
                usingExampleValue.value = ""
                pronounciationValue.value = ""
                difficultyValue.value = ""
            }
        ) {
            Text("Tambah")
        }
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun AddVocabularyMenuPreview() {
//    VocabNoteTheme {
//        VocabularyMenu()
//    }
//}